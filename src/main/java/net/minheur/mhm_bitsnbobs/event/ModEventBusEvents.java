package net.minheur.mhm_bitsnbobs.event;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.entity.ModEntities;
import net.minheur.mhm_bitsnbobs.entity.custom.RhinoEntity;
import net.minheur.mhm_bitsnbobs.util.cold_head.IColdHead;

/**
 * The events under the mod's bus
 */
@Mod.EventBusSubscriber(modid = MhmBitsnbobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IColdHead.class);
    }
}
