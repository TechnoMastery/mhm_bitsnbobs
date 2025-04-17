package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CraftingKeepOtherItem extends Item {
    private final ItemStack craftingRemainer;

    public CraftingKeepOtherItem(Properties pProperties, ItemStack craftingRemainer) {
        super(pProperties);
        this.craftingRemainer = craftingRemainer;
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(craftingRemainer.getItem());
    }
}
