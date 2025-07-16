package net.minheur.mhm_bitsnbobs.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minheur.mhm_bitsnbobs.recipe.MysteriousMagicRecipe;
import net.minheur.mhm_bitsnbobs.screen.MysteriousAltarMenu;
import net.minheur.mhm_bitsnbobs.util.ModTags;
import net.minheur.mhm_bitsnbobs.util.MysteriousMagicContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MysteriousAltarBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (INPUT_SLOTS.contains(slot)) return true;
            if (slot == FUEL_SLOT) {
                return stack.is(ModTags.Items.MAGIC_FUELS);
            }
            return false;
        }
    };

    public static final int OUTPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int PRIMARY_INPUT = 2;
    public static final int UP_INPUT_SLOT = 3;
    public static final int RIGHT_INPUT_SLOT = 4;
    public static final int DOWN_INPUT_SLOT = 5;
    public static final int LEFT_INPUT_SLOT = 6;
    public static final List<Integer> INPUT_SLOTS =
            List.of(PRIMARY_INPUT, UP_INPUT_SLOT, RIGHT_INPUT_SLOT, DOWN_INPUT_SLOT, LEFT_INPUT_SLOT);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private final int maxProgress = 100;
    private int maxPower = 0;
    private int power = 0;

    public boolean hasPower() {
        return !itemHandler.getStackInSlot(FUEL_SLOT).isEmpty();
    }

    public MysteriousAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MYSTERIOUS_MAGIC_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> MysteriousAltarBlockEntity.this.progress;
                    case 1 -> MysteriousAltarBlockEntity.this.maxProgress;
                    case 2 -> MysteriousAltarBlockEntity.this.power;
                    case 3 -> MysteriousAltarBlockEntity.this.maxPower;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MysteriousAltarBlockEntity.this.progress = pValue;
                    case 1, 3 -> throw new RuntimeException("Can't modify this max value !");
                    case 2 -> MysteriousAltarBlockEntity.this.power = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public ItemStack getRenderStack() {
        if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            return itemHandler.getStackInSlot(PRIMARY_INPUT);
        } else {
            return itemHandler.getStackInSlot(OUTPUT_SLOT);
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.mhm_bitsnbobs.mysterious_magic.name");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new MysteriousAltarMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("mysterious_magic.progress", progress);
        pTag.putInt("mysterious_magic.power", power);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("mysterious_magic.progress");
        power = pTag.getInt("mysterious_magic.power");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        maxPower = itemHandler.getStackInSlot(FUEL_SLOT).getMaxDamage();
        power = maxPower - itemHandler.getStackInSlot(FUEL_SLOT).getDamageValue();
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void increaseCraftingProgress() {
        progress++;
    }
    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private boolean hasEnoughPower(Optional<MysteriousMagicRecipe> recipe) {
        return recipe.get().getFuelAmount() <= power;
    }
    private boolean hasRecipe() {
        Optional<MysteriousMagicRecipe> recipe = getCurentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        if (!hasEnoughPower(recipe)) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlop(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }
    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }
    private boolean canInsertAmountIntoOutputSlop(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private Optional<MysteriousMagicRecipe> getCurentRecipe() {
        MysteriousMagicContainer inventory = new MysteriousMagicContainer(this.power, this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(MysteriousMagicRecipe.Type.INSTANCE, inventory, level);
    }

    private void craftItem() {
        Optional<MysteriousMagicRecipe> recipe = getCurentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        this.itemHandler.extractItem(PRIMARY_INPUT, recipe.get().getPrimaryInput().getCount(), false);
        this.itemHandler.extractItem(UP_INPUT_SLOT, recipe.get().getUpInput().getCount(), false);
        this.itemHandler.extractItem(DOWN_INPUT_SLOT, recipe.get().getDownInput().getCount(), false);
        this.itemHandler.extractItem(LEFT_INPUT_SLOT, recipe.get().getLeftInput().getCount(), false);
        this.itemHandler.extractItem(RIGHT_INPUT_SLOT, recipe.get().getRightInput().getCount(), false);
        consumePower(recipe.get().getFuelAmount());

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private void consumePower(int fuelAmount) {
        int newDamage = this.itemHandler.getStackInSlot(FUEL_SLOT).getDamageValue() + fuelAmount;
        int maxDamage = this.itemHandler.getStackInSlot(FUEL_SLOT).getMaxDamage();
        if (newDamage > maxDamage) {
            throw new RuntimeException("You are tying to consume more power than existing !");
        } else {
            this.itemHandler.getStackInSlot(FUEL_SLOT).setDamageValue(newDamage);
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
