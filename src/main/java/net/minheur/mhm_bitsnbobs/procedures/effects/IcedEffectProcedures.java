package net.minheur.mhm_bitsnbobs.procedures.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minheur.mhm_bitsnbobs.util.cold_head.ColdHeadCapability;

public class IcedEffectProcedures {
    public static void executeActiveTickEffect(LevelAccessor world, Entity entity) {
        entity.getCapability(ColdHeadCapability.INSTANCE).ifPresent(cold -> {
            cold.addCold(entity.level().getGameTime(), 0.01f);
        });

    }

}
