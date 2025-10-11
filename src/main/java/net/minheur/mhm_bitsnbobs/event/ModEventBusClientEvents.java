package net.minheur.mhm_bitsnbobs.event;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.mhm_bitsnbobs.block.entity.renderer.GemPolishingBlockEntityRenderer;
import net.minheur.mhm_bitsnbobs.block.entity.renderer.MysteriousAltarBlockEntityRenderer;
import net.minheur.mhm_bitsnbobs.entity.client.ModModelLayers;
import net.minheur.mhm_bitsnbobs.entity.client.RhinoModel;
import net.minheur.techno_lib.custom.block.entity.AbstractMenuBlockEntity;

/**
 * The clients events under the mod's bus.
 */
@Mod.EventBusSubscriber(modid = MhmBitsnbobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);

        // event.registerLayerDefinition(ModModelLayers.DARK_BOAT_LAYER, BoatModel::createBodyModel);
        // event.registerLayerDefinition(ModModelLayers.DARK_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        registerMenuRenderer(event, ModBlockEntities.GEM_POLISHING_BE, GemPolishingBlockEntityRenderer::new);
        registerMenuRenderer(event, ModBlockEntities.MYSTERIOUS_MAGIC_BE, MysteriousAltarBlockEntityRenderer::new);

        // event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        // event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SuppressWarnings("unchecked")
    private static <T extends AbstractMenuBlockEntity> void registerMenuRenderer(
            EntityRenderersEvent.RegisterRenderers event,
            RegistryObject<? extends BlockEntityType<? extends AbstractMenuBlockEntity>> type,
            BlockEntityRendererProvider<? super T> renderer
    ) {
        event.registerBlockEntityRenderer(
                (BlockEntityType<T>) type.get(),
                renderer
        );
    }
}
