package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.util.cold_head.SyncColdHeadPacket;

public class ModNetworking {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "main"),
            () -> "1.0", s -> true, s -> true
    );

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, SyncColdHeadPacket.class,
                SyncColdHeadPacket::encode,
                SyncColdHeadPacket::decode,
                SyncColdHeadPacket::handle);
    }


}
