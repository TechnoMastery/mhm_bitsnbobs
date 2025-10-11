package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minheur.mhm_bitsnbobs.block.entity.AtomicalStabilizatorBlockEntity;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.techno_lib.custom.block.AbstractMenuBlock;
import org.jetbrains.annotations.Nullable;

public class AtomicalStabilizatorBlock extends AbstractMenuBlock {
    public static final VoxelShape SHAPE;

    static {
        // feet
        VoxelShape shapeBox = Block.box(0, 0, 0, 2, 2, 2);
        shapeBox = Shapes.or(shapeBox, Block.box(0, 0, 14, 2, 2, 16));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 0, 0, 16, 2, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 0, 14, 16, 2, 16));
        // main box
        shapeBox = Shapes.or(shapeBox, Block.box(0, 2, 0, 16, 12, 16));
        // control panel
        shapeBox = Shapes.or(shapeBox, Block.box(3, 12, 3, 13, 13, 13));

        shapeBox = Shapes.or(shapeBox, Block.box(2, 12, 5, 3, 13, 11));
        shapeBox = Shapes.or(shapeBox, Block.box(1, 12, 7, 2, 13, 9));

        shapeBox = Shapes.or(shapeBox, Block.box(5, 12, 2,11, 13, 3));
        shapeBox = Shapes.or(shapeBox, Block.box(7, 12, 1, 9, 13, 2));

        shapeBox = Shapes.or(shapeBox, Block.box(13, 12, 5, 14, 13, 11));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 12, 7, 15, 13, 9));

        shapeBox = Shapes.or(shapeBox, Block.box(5, 12, 13, 11, 13, 14));
        shapeBox = Shapes.or(shapeBox, Block.box(7, 12, 14, 9, 13, 15));

        SHAPE = shapeBox;
    }

    public AtomicalStabilizatorBlock(Properties pProperties) {
        super(pProperties, ModBlockEntities.ATOMICAL_STABILIZATOR_BE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AtomicalStabilizatorBlockEntity(pPos, pState);
    }
}
