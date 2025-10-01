package net.minheur.mhm_bitsnbobs.effects.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minheur.mhm_bitsnbobs.procedures.effects.IcedEffectProcedures;

import java.util.ArrayList;
import java.util.List;

import static net.minheur.techno_lib.Utils.getRGB;

public class IcedEffect extends MobEffect {
    public IcedEffect() {
        super(MobEffectCategory.HARMFUL, getRGB(192, 2, 87));
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
        return cures;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        IcedEffectProcedures.executeActiveTickEffect(pLivingEntity.level(), pLivingEntity);
    }
}
