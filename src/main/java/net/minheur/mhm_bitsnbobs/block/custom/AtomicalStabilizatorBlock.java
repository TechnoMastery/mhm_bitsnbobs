package net.minheur.mhm_bitsnbobs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.minheur.mhm_bitsnbobs.block.entity.AtomicalStabilizatorBlockEntity;
import net.minheur.mhm_bitsnbobs.block.entity.GemPolishingStationBlockEntity;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AtomicalStabilizatorBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE;

    static {
        VoxelShape shapeBox = Block.box(0, 0, 0, 2, 2, 2);
        shapeBox = Shapes.or(shapeBox, Block.box(14, 0, 0, 16, 2, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(0, 14, 0, 2, 14, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 14, 0, 16, 16, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(0, 0, 2, 16, 16, 14));
        shapeBox = Shapes.or(shapeBox, Block.box(3, 11, 3, 13, 13, 13));
        shapeBox = Shapes.or(shapeBox, Block.box(5, 11, 2, 11, 13, 3));
        shapeBox = Shapes.or(shapeBox, Block.box(7, 11, 1, 8, 13, 2));
        shapeBox = Shapes.or(shapeBox, Block.box(2, 11, 5, 3, 13, 11));
        shapeBox = Shapes.or(shapeBox, Block.box(1, 11, 7, 2, 13, 9));
        shapeBox = Shapes.or(shapeBox, Block.box(5, 11, 13, 11, 13, 14));
        shapeBox = Shapes.or(shapeBox, Block.box(7, 11, 14, 9, 13, 15));
        shapeBox = Shapes.or(shapeBox, Block.box(13, 11, 5, 14, 13, 11));
        shapeBox = Shapes.or(shapeBox, Block.box(14, 11, 7, 15, 13, 9));

        SHAPE = shapeBox;
    }

    public AtomicalStabilizatorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof GemPolishingStationBlockEntity) {
                ((GemPolishingStationBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof GemPolishingStationBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (GemPolishingStationBlockEntity) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing");
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AtomicalStabilizatorBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.ATOMICAL_STABILIZATOR_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
