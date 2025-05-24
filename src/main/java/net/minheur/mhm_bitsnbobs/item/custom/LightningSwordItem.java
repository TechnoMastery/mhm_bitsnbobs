package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.procedures.LightningSwordHitEvent;

public class LightningSwordItem extends SwordItem {
    public LightningSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        boolean returnValue = super.hurtEnemy(pStack, pTarget, pAttacker);
        LightningSwordHitEvent.execute(pTarget.level(), pTarget.getX(), pTarget.getY(), pTarget.getZ(), pAttacker);
        return returnValue;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
