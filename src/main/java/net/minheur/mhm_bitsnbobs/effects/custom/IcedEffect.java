package net.minheur.mhm_bitsnbobs.effects.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static net.minheur.mhm_bitsnbobs.util.Utils.getRGB;

public class IcedEffect extends MobEffect {
    protected IcedEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, getRGB(192, 2, 87));
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
        return cures;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
