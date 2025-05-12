package net.minheur.mhm_bitsnbobs.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minheur.mhm_bitsnbobs.worldgen.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DarkTrunkPlacer extends TrunkPlacer {
    public static final Codec<DarkTrunkPlacer> CODEC = RecordCodecBuilder.create(darkTrunkPlacerInstance ->
            trunkPlacerParts(darkTrunkPlacerInstance).apply(darkTrunkPlacerInstance, DarkTrunkPlacer::new));

    public DarkTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.DARK_TRUNK_PLACER.get();
    }



    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        // block place logic
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        int height = pFreeTreeHeight + pRandom.nextInt(heightRandA, heightRandA + 3) + pRandom.nextInt(heightRandB - 1, heightRandB + 1);

        for(int i = 0; i < height; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }


        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
    }
}
