package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CraftingDamageItem extends Item {
    public CraftingDamageItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack retval = new ItemStack(this);
        retval.setDamageValue(itemStack.getDamageValue() + 1);
        if(retval.getDamageValue() >= retval.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return retval;
    }
}
