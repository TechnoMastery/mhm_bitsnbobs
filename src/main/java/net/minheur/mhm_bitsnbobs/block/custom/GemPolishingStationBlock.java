package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minheur.mhm_bitsnbobs.block.entity.GemPolishingStationBlockEntity;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.techno_lib.custom.block.AbstractMenuBlock;
import org.jetbrains.annotations.Nullable;

public class GemPolishingStationBlock extends AbstractMenuBlock {
    public static final VoxelShape SHAPE;

    static {
        VoxelShape shapeBox = Block.box(0, 4, 0, 16, 12, 16);
        shapeBox = Shapes.or(shapeBox, Block.box(0, 0, 0, 2, 4, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 0, 0, 16, 4, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(0, 0, 14, 2, 4, 16));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 0, 14, 16, 4, 16));

        SHAPE = shapeBox;
    }

    public GemPolishingStationBlock(Properties pProperties) {
        super(pProperties, ModBlockEntities.GEM_POLISHING_BE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GemPolishingStationBlockEntity(blockPos, blockState);
    }
}
