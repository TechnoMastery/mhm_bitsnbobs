package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;

public class DelegatingItemStackHandler extends ItemStackHandler {
    private BlockEntity owner;

    public DelegatingItemStackHandler(int size) {
        super(size);
    }

    public void setOwner(BlockEntity owner) {
        this.owner = owner;
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (owner != null) {
            owner.setChanged();
            owner.getLevel().sendBlockUpdated(owner.getBlockPos(), owner.getBlockState(), owner.getBlockState(), 3);
        }
    }
}
