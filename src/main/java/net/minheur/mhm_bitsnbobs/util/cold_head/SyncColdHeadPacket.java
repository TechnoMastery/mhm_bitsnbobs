package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncColdHeadPacket {
    private final float cold;

    public SyncColdHeadPacket(float cold) {
        this.cold = cold;
    }

    public static void encode(SyncColdHeadPacket pPacket, FriendlyByteBuf pBuffer) {
        pBuffer.writeFloat(pPacket.cold);
    }

    public static SyncColdHeadPacket decode(FriendlyByteBuf pBuffer) {
        return new SyncColdHeadPacket(pBuffer.readFloat());
    }

    public static void handle(SyncColdHeadPacket pPacket, Supplier<NetworkEvent.Context> pContext) {
        pContext.get().enqueueWork(() -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(ColdHeadCapability.INSTANCE).ifPresent(cap -> {
                    cap.setColdLevel(pPacket.cold);
                });
            }
        });
        pContext.get().setPacketHandled(true);
    }
}
