package net.minheur.mhm_bitsnbobs.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(MhmBitsnbobs.MOD_ID, "overworld"), 5));
    }

}
