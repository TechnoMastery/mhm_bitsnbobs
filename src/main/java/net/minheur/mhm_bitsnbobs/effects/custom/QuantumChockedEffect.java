package net.minheur.mhm_bitsnbobs.effects.custom;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minheur.mhm_bitsnbobs.procedures.effects.QuantumChockedProcedures;

import java.util.ArrayList;
import java.util.List;

public class QuantumChockedEffect extends MobEffect {
    public QuantumChockedEffect() {
        super(MobEffectCategory.HARMFUL, -3791065);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
        return cures;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
            return QuantumChockedProcedures.executeActiveTickCondition(pAmplifier, pDuration);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        QuantumChockedProcedures.executeActiveTickEffect(pLivingEntity.level(), pLivingEntity);
    }
}
