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
import net.minheur.mhm_bitsnbobs.recipe.FreezingRecipe;
import net.minheur.mhm_bitsnbobs.screen.FreezerMenu;
import net.minheur.techno_lib.builders.DelegatingItemStackHandler;
import net.minheur.techno_lib.custom.block.entity.AbstractMenuBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FreezerBlockEntity extends AbstractMenuBlockEntity {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 500;

    public FreezerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FREEZING_BE.get(), pPos, pBlockState, new DelegatingItemStackHandler(2) {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return slot == INPUT_SLOT;
            }
        });

        ((DelegatingItemStackHandler) this.itemHandler).setOwner(this);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FreezerBlockEntity.this.progress;
                    case 1 -> FreezerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FreezerBlockEntity.this.progress = pValue;
                    case 1 -> FreezerBlockEntity.this.maxProgress = pValue;
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
        return Component.translatable("block.mhm_bitsnbobs.freezer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player pPlayer) {
        return new FreezerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("freezer.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("freezer.progress");
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
        Optional<FreezingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        ItemStack input = recipe.get().getIngredients().get(0).getItems()[0];
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        if (input.getItem().hasCraftingRemainingItem()) {
            if (input.getCount() > 1) {
                throw new RuntimeException("This stack is too big for this slot !");
            }
            this.itemHandler.setStackInSlot(INPUT_SLOT, input.getCraftingRemainingItem());
        }
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Optional<FreezingRecipe> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<FreezingRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(FreezingRecipe.Type.INSTANCE, inventory, level);
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
