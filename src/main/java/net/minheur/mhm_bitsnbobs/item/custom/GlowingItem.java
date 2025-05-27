package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GlowingItem extends Item {
    public GlowingItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}