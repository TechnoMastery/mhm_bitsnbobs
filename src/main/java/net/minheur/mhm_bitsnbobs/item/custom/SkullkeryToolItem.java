package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkullkeryToolItem extends Item {
    public SkullkeryToolItem(Properties pProperties) {
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.mhm_bitsnbobs.skullkery_tool.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
