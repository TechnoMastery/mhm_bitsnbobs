package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CatalyzerItem extends Item {
    private String rank;

    public CatalyzerItem(Properties pProperties, String rank) {
        super(pProperties);
        this.rank = rank;
    }
    public String getRank() { return rank; }
    // TODO: add things here. Don't know what.
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.mhm_bitsnbobs." + rank + ".tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
