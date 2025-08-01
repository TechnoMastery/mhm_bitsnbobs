package net.minheur.mhm_bitsnbobs.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;

public class QuantumCoreProcedures {
    public static void executeTickInInventory(Entity entity) {
        ((Player) entity).addEffect(new MobEffectInstance(ModEffects.QUANTUM_CHOCKED.get(), 20));
    }
    public static void executeFinishedUsing(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        {
            Entity _ent = entity;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "clear @s mhm_bitsnbobs:quantum_core 1");
            }
        }
        if (world instanceof Level _level && !_level.isClientSide())
            _level.explode(null, x, y, z, 15, Level.ExplosionInteraction.MOB);
    }

    // stabilized
    public static void stabilizedQuantumCoreInventoryTick(Entity entity) {
        if(entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
                && (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? _livEnt.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getDuration() : 0) < 40) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
        } else if (!(entity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(MobEffects.MOVEMENT_SLOWDOWN))) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
        }
    }
}
