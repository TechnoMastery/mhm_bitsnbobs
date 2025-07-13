package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;
import net.minheur.mhm_bitsnbobs.util.Utils;

public class QuantumStaffItem extends Item {
    public QuantumStaffItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!((Player) pAttacker).getCooldowns().isOnCooldown(this)) {
            pTarget.addEffect(new MobEffectInstance(ModEffects.QUANTUM_CHOCKED.get(), 300));
            Utils.damageAndBreakItem(pStack);
            ((Player) pAttacker).getCooldowns().addCooldown(this, 600);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
