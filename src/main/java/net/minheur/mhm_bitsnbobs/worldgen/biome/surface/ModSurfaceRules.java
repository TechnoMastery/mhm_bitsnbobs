package net.minheur.mhm_bitsnbobs.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RESSOURCE_DIRT = makeStateRule(ModBlocks.RESSOURCE_DIRT_BLOCK.get());



    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RESSOURCE_DIRT)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, RESSOURCE_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, RESSOURCE_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, RESSOURCE_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RESSOURCE_DIRT)),


                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
