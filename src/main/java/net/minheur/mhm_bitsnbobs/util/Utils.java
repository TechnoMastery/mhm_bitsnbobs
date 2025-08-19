package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

/**
 * The utility class of the mod
 */
public class Utils {
    /**
     * You enter an RBG code, and it gets you usable code in effects
     * @return a code usable in effects colors
     */
    public static int getRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    /**
     * Will check if the stack you have is the same and is minimum equals to expected stack.
     * @param expected the stack you should have
     * @param actual the stack you have
     * @return if the actual is the same item and has minimum the size of expected
     */
    public static boolean areStacksEqualEnough(ItemStack expected, ItemStack actual) {
        return expected.getItem() == actual.getItem()
                && actual.getCount() >= expected.getCount();
    }

    /**
     * This is a shortcut for Recipes Bluider (datagen).
     * <p> See use : {@link net.minheur.mhm_bitsnbobs.recipe.datagen.FreezingRecipeBuilder}
     * @param itemLike the item you want to get the key
     * @return the built-in registry of the item you gave, as a {@link String}
     */
    public static String getBuiltInItemRegistry(ItemLike itemLike) {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).toString();
    }
}
