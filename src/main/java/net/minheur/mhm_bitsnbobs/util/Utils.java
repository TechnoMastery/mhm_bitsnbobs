package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.world.item.ItemStack;

public class Utils {
    public static void damageAndBreakItem(ItemStack pStack) {
        int newDamage = pStack.getDamageValue() + 1;
        int maxDamage = pStack.getMaxDamage();
        if (newDamage >= maxDamage) {
            pStack.setCount(0);
        } else {
            pStack.setDamageValue(newDamage);
        }
    }
}
