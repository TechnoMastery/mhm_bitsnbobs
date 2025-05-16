package net.minheur.mhm_bitsnbobs.procedures.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class QuantumChockedProcedures {
    public static boolean executeActiveTickCondition(double amplifier, double duration) {
        double baseRate = 0;
        double rateWithAmplifier = 0;
        boolean valid = false;
        baseRate = 2;
        rateWithAmplifier = baseRate / Math.pow(2, amplifier);
        if (Math.floor(rateWithAmplifier) > 0) {
            return duration % Math.floor(rateWithAmplifier) == 0;
        }
        return true;
    }
    public static void executeActiveTickEffect(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,
                    new ResourceLocation("mhm_bitsnbobs:electrocuted")))), 1);
    }

}
