package net.minheur.mhm_bitsnbobs.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minheur.mhm_bitsnbobs.block.SimpleEnergyStorage;
import net.minheur.mhm_bitsnbobs.compat.OtherModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CryptoMinerBlockEntity extends BlockEntity {

    private static final int CAPACITY = 10000;
    private static final int MAX_INPUT_PER_TICK = 1000;
    private static final int MAX_OUTPUT_PER_TICK = 0;
    private static final int FE_PER_TICK = 10;
    private final int MAX_PROGRESS = 1000;
    public static final int SLOT = 0;

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INPUT_PER_TICK, MAX_OUTPUT_PER_TICK,
            this::onEnergyChanged);
    private LazyOptional<IEnergyStorage> energyCap = LazyOptional.of(() -> energyStorage);

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return false;
        }
    };
    private LazyOptional<IItemHandler> itemCap = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;

    public CryptoMinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRYPTO_MINER_BE.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CryptoMinerBlockEntity.this.progress;
                    case 1 -> MAX_PROGRESS;
                    case 2 -> CryptoMinerBlockEntity.this.energyStorage.getEnergyStored();
                    case 3 -> CryptoMinerBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CryptoMinerBlockEntity.this.progress = pValue;
                    case 1, 3 -> throw new RuntimeException("Can't modify this max value !");
                    case 2 -> CryptoMinerBlockEntity.this.energyStorage.forceSetEnergy(pValue);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CryptoMinerBlockEntity be) {
        if (level.isClientSide()) return;

        System.out.println("Energy=" + be.energyStorage.getEnergyStored());
        System.out.println("Progress=" + be.progress);

        if (be.canWork()) {
            be.progress++;
            be.energyStorage.extractEnergy(FE_PER_TICK, false);

            if (be.progress >= be.MAX_PROGRESS) {
                be.tryOutputProduce();
                be.progress = 0;
            }
            setChanged(level, pos, state);
        }
    }

    private boolean canWork() {
        return hasEnergyForTick() && hasRoomInOutputSlot();
    }

    private boolean hasEnergyForTick() {
        return energyStorage.getEnergyStored() >= FE_PER_TICK;
    }

    private boolean hasRoomInOutputSlot() {
        Item target = OtherModItems.NUMISMATICS_SPUR;
        if (target == null) return false;

        ItemStack stack = itemHandler.getStackInSlot(SLOT);
        if (stack.isEmpty()) return true;
        if (stack.getItem() != target) return false;
        return stack.getCount() < stack.getMaxStackSize();
    }

    private void tryOutputProduce() {
        Item target = OtherModItems.NUMISMATICS_SPUR;
        if (target == null) return;

        ItemStack stack = itemHandler.getStackInSlot(SLOT);
        if (stack.isEmpty()) {
            itemHandler.setStackInSlot(SLOT, new ItemStack(target, 1));
        } else if (stack.getItem() == target && stack.getCount() < stack.getMaxStackSize()) {
            stack.grow(1);
            itemHandler.setStackInSlot(SLOT, stack);
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) return itemCap.cast();
        if (cap == ForgeCapabilities.ENERGY) return energyCap.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.itemCap = LazyOptional.of(() -> itemHandler);
        this.energyCap = LazyOptional.of(() -> energyStorage);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCap.invalidate();
        energyCap.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        energyCap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("progress", progress);
        pTag.putInt("energy", energyStorage.getEnergyStored());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("progress");
        energyStorage.forceSetEnergy(pTag.getInt("energy"));
    }

    private void onEnergyChanged() {
        if (this.level != null && !this.level.isClientSide()) {
            setChanged();
        }
    }

    public ContainerData getData() {
        return data;
    }
}
