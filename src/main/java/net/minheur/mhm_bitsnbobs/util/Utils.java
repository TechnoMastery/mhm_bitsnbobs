package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

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

    public static int getRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static boolean areStacksEqualEnough(ItemStack expected, ItemStack actual) {
        return expected.getItem() == actual.getItem()
                && actual.getCount() >= expected.getCount();
    }

    public static String getBuiltInItemRegistry(ItemLike itemLike) {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).toString();
    }
}
