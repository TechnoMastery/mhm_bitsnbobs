package net.minheur.mhm_bitsnbobs.item.custom.runes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.procedures.RunesExecuteEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class XpRuneItem extends Item {
    public XpRuneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.mhm_bitsnbobs.runes.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        InteractionResultHolder<ItemStack> ar=  super.use(pLevel, pPlayer, pUsedHand);
        RunesExecuteEvents.executeXp(pPlayer);
        return ar;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
