package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;

public class FirePlantCropBlock extends SugarCaneBlock implements BonemealableBlock {
    public FirePlantCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockPos = pPos.below();
        BlockState groundState = pLevel.getBlockState(blockPos);
        return groundState.is(this) || groundState.is(ModBlocks.RUBINIUM_BLOCK.get());
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.NETHER;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(pLevel.isEmptyBlock(pPos.above())) {
            int i = 1;
            for(; pLevel.getBlockState(pPos.below(i)).is(this); i++);
            if(1 < 5) {
                int j = pState.getValue(AGE);
                if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, true)) {
                    if (j == 15) {
                        pLevel.setBlockAndUpdate(pPos.above(), defaultBlockState());
                        pLevel.setBlock(pPos, pState.setValue(AGE, 0), 4);
                    } else {
                        pLevel.setBlock(pPos, pState.setValue(AGE, j + 1), 4);
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
    }
}
