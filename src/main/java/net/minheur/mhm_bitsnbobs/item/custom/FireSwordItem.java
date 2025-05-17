package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minheur.mhm_bitsnbobs.procedures.FireSwordHitEvent;

public class FireSwordItem extends SwordItem {
    public FireSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        boolean retval = super.hurtEnemy(pStack, pTarget, pAttacker);
        FireSwordHitEvent.execute(pTarget);
        return retval;
    }
}
