package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetherStickItem extends Item {
    public NetherStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.mhm_bitsnbobs.nether_stick.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 20;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        InteractionResultHolder<ItemStack> ar = super.use(pLevel, pPlayer, pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return ar;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!((Player) pAttacker).getCooldowns().isOnCooldown(this)) {
            if (pTarget.getRemainingFireTicks() <60) {
                pTarget.setRemainingFireTicks(60);
            }
            pStack.hurtAndBreak(1, pAttacker, player -> player.broadcastBreakEvent(player.getUsedItemHand()));
            ((Player) pAttacker).getCooldowns().addCooldown(this, 60);
        }
        return hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack returnValue = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        double x = pLivingEntity.getX();
        double y = pLivingEntity.getY();
        double z = pLivingEntity.getZ();
        if (!((Player) pLivingEntity).getCooldowns().isOnCooldown(this)) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 0));
            pStack.hurtAndBreak(1, pLivingEntity, player -> player.broadcastBreakEvent(player.getUsedItemHand()));
            ((Player) pLivingEntity).getCooldowns().addCooldown(this, 60);
        }
        if (pLivingEntity instanceof Player player) player.awardStat(Stats.ITEM_USED.get(this));
        return returnValue;
    }
}
