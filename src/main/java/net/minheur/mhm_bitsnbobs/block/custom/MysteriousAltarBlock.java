package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.mhm_bitsnbobs.block.entity.MysteriousAltarBlockEntity;
import net.minheur.techno_lib.custom.block.AbstractMenuBlock;
import org.jetbrains.annotations.Nullable;

public class MysteriousAltarBlock extends AbstractMenuBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 10, 16);

    public MysteriousAltarBlock(Properties pProperties) {
        super(pProperties, ModBlockEntities.MYSTERIOUS_MAGIC_BE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MysteriousAltarBlockEntity(blockPos, blockState);
    }
}
