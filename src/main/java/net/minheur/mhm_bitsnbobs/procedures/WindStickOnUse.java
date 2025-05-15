package net.minheur.mhm_bitsnbobs.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class WindStickOnUse {
    public static void execute(LevelAccessor pLevel, double x, double y, double z, Entity pLivingEntity) {
        if (pLivingEntity == null) return;
        {
            Entity pEntity = pLivingEntity;
            if (!pEntity.level().isClientSide() && pEntity.getServer() != null){
                pEntity.getName();
                pEntity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, pEntity.position(),pEntity.getRotationVector(),pEntity.level() instanceof ServerLevel ? (ServerLevel) pEntity.level() : null,4,
                        pEntity.getName().getString(),pEntity.getDisplayName(),pEntity.level().getServer(),pEntity),"tp @a[distance=..5,name=!" + "]");
            }
        }

    }
}
