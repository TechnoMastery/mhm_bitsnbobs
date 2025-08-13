package net.minheur.mhm_bitsnbobs.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_BE =
            BLOCK_ENTITIES.register("gem_polishing_be", () ->
                    BlockEntityType.Builder.of(GemPolishingStationBlockEntity::new,
                            ModBlocks.GEM_POLISHING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<IncubatorBlockEntity>> INCUBATOR_BE =
            BLOCK_ENTITIES.register("incubator_be", () ->
                    BlockEntityType.Builder.of(IncubatorBlockEntity::new,
                            ModBlocks.INCUBATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<MysteriousAltarBlockEntity>> MYSTERIOUS_MAGIC_BE =
            BLOCK_ENTITIES.register("mysterious_magic_be", () ->
                    BlockEntityType.Builder.of(MysteriousAltarBlockEntity::new,
                            ModBlocks.MYSTERIOUS_ALTAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<FreezerBlockEntity>> FREEZING_BE =
            BLOCK_ENTITIES.register("freezer_be", () ->
                    BlockEntityType.Builder.of(FreezerBlockEntity::new,
                            ModBlocks.FREEZER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CryptoMinerBlockEntity>> CRYPTO_MINER_BE =
            BLOCK_ENTITIES.register("crypto_miner_be", () ->
                    BlockEntityType.Builder.of(CryptoMinerBlockEntity::new,
                            ModBlocks.CRYPTO_MINER.get()).build(null));

    /// add here all signs
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.DARK_SIGN.get(), ModBlocks.DARK_WALL_SIGN.get()).build(null));

    /// add here all hanging signs
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
