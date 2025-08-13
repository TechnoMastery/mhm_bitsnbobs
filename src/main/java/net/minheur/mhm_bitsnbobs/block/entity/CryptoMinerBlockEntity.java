package net.minheur.mhm_bitsnbobs.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
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
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minheur.mhm_bitsnbobs.block.SimpleEnergyStorage;
import net.minheur.mhm_bitsnbobs.compat.OtherModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CryptoMinerBlockEntity extends BlockEntity {
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000);
    private LazyOptional<SimpleEnergyStorage> lazyEnergy = LazyOptional.of(() -> energyStorage);

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return false;
        }
    };

    public static final int SLOT = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private final int maxProgress = 1000;

    public CryptoMinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRYPTO_MINER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CryptoMinerBlockEntity.this.progress;
                    case 1 -> CryptoMinerBlockEntity.this.maxProgress;
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
                    case 2 -> CryptoMinerBlockEntity.this.energyStorage.addEnergy(pValue);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergy.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("crypto_miner.progress", progress);
        pTag.putInt("crypto_miner.energy", energyStorage.getEnergyStored());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("crypto_miner.progress");
        energyStorage.forceSetEnergy(pTag.getInt("crypto_miner.energy"));
        super.load(pTag);
    }

    public void tick(Level level, BlockPos pos, BlockState blockState) {
        if (isPowered() && hasRoomInOutputSlot()) {
            increaseProgressAndConsume();
            setChanged(level, pos, blockState);
            if (hasProgressFinished()) {
                createCoin();
                resetProgress();
            }
        }
    }

    private boolean hasRoomInOutputSlot() {
        return itemHandler.getStackInSlot(SLOT).getItem() == OtherModItems.NUMISMATICS_SPUR &&
                itemHandler.getStackInSlot(SLOT).getCount() < itemHandler.getStackInSlot(SLOT).getMaxStackSize();
    }

    private void createCoin() {
        this.itemHandler.setStackInSlot(SLOT, new ItemStack(OtherModItems.NUMISMATICS_SPUR, itemHandler.getStackInSlot(SLOT).getCount() +1));
    }

    private boolean isPowered() {
        return energyStorage.getEnergyStored() < 0;
    }

    private void increaseProgressAndConsume() {
        progress++;
        energyStorage.removeEnergy(1);
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void resetProgress() {
        progress = 0;
    }
}
