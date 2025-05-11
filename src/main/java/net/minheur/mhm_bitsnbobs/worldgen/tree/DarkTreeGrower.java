package net.minheur.mhm_bitsnbobs.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minheur.mhm_bitsnbobs.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class DarkTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return ModConfiguredFeatures.DARK_KEY;
    }
}
