package net.minheur.mhm_bitsnbobs.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import net.minheur.mhm_bitsnbobs.recipe.AtomicalStabilizatorRecipe;
import net.minheur.mhm_bitsnbobs.screen.AtomicalStabilizatorMenu;
import net.minheur.mhm_bitsnbobs.util.DelegatingItemStackHandler;
import net.minheur.mhm_bitsnbobs.util.ModTags;
import net.minheur.techno_lib.custom.block.entity.AbstractMenuBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AtomicalStabilizatorBlockEntity extends AbstractMenuBlockEntity {
    public final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot == GLUE_SLOT) {
                return stack.is(ModTags.Items.ATOMICAL_STABILIZATOR_GLUES);
            } else {
                return slot != OUTPUT_SLOT;
            }
        }
    };

    public static final int INPUT_SLOT_LEFT = 0;
    public static final int INPUT_SLOT_RIGHT = 1;
    public static final int GLUE_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 500;

    public AtomicalStabilizatorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ATOMICAL_STABILIZATOR_BE.get(), pPos, pBlockState, new DelegatingItemStackHandler(4) {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return slot == GLUE_SLOT ? stack.is(ModTags.Items.ATOMICAL_STABILIZATOR_GLUES) : slot != OUTPUT_SLOT;
            }
        });

        ((DelegatingItemStackHandler) this.itemHandler).setOwner(this);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AtomicalStabilizatorBlockEntity.this.progress;
                    case 1 -> AtomicalStabilizatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AtomicalStabilizatorBlockEntity.this.progress = pValue;
                    case 1 -> AtomicalStabilizatorBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.mhm_bitsnbobs.atomical_stabilizator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AtomicalStabilizatorMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("atomical_stabilizator.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("atomical_stabilizator.progress");
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    @Override
    public ContainerData getData() {
        return data;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<AtomicalStabilizatorRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        this.itemHandler.extractItem(INPUT_SLOT_LEFT, recipe.get().getInputLeft().getCount(), false);
        this.itemHandler.extractItem(INPUT_SLOT_RIGHT, recipe.get().getInputRight().getCount(), false);
        this.itemHandler.extractItem(GLUE_SLOT, recipe.get().getGlue().getCount(), false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }
    private boolean hasRecipe() {
        Optional<AtomicalStabilizatorRecipe> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<AtomicalStabilizatorRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(AtomicalStabilizatorRecipe.Type.INSTANCE, inventory, level);
    }
    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }
    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }
    private void increaseCraftingProgress() {
        progress++;
    }

}
