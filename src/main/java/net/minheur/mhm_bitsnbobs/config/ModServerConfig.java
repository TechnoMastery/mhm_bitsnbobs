package net.minheur.mhm_bitsnbobs.config;

import net.minecraft.core.BlockPos;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    // command /spawn
public static final ForgeConfigSpec.BooleanValue SPAWN_ENABLED;
    public static final ForgeConfigSpec.IntValue SPAWN_X;
    public static final ForgeConfigSpec.IntValue SPAWN_Y;
    public static final ForgeConfigSpec.IntValue SPAWN_Z;

    static {
        BUILDER.push("spawn");

        SPAWN_ENABLED = BUILDER.comment("Is the /spawn command useable").define("enableSpawnCommand", false);
        SPAWN_X = BUILDER.comment("X coordinate of custom spawn").defineInRange("spawnX", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        SPAWN_Y = BUILDER.comment("Y coordinate of custom spawn").defineInRange("spawnY", 64, 0, 320);
        SPAWN_Z = BUILDER.comment("Z coordinate of custom spawn").defineInRange("spawnZ", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        BUILDER.pop();

        CONFIG = BUILDER.build();
    }

    public static boolean getSpawnEnabled() {
        return SPAWN_ENABLED.get();
    }

    public static void setSpawnEnabled(boolean enabled) {
        SPAWN_ENABLED.set(enabled);
    }

    public static BlockPos getSpawnPos() {
        return new BlockPos(SPAWN_X.get(), SPAWN_Y.get(), SPAWN_Z.get());
    }

    public static void setSpawnPos(BlockPos pPos) {
        SPAWN_X.set(pPos.getX());
        SPAWN_Y.set(pPos.getY());
        SPAWN_Z.set(pPos.getZ());
    }

}
