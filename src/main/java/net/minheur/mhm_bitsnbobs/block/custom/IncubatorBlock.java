package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minheur.mhm_bitsnbobs.block.entity.IncubatorBlockEntity;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.techno_lib.custom.block.AbstractMenuBlock;
import org.jetbrains.annotations.Nullable;

public class IncubatorBlock extends AbstractMenuBlock {

    public IncubatorBlock(Properties pProperties) {
        super(pProperties, ModBlockEntities.INCUBATOR_BE);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new IncubatorBlockEntity(blockPos, blockState);
    }
}
