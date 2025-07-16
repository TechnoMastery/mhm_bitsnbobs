package net.minheur.mhm_bitsnbobs.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.util.cold_head.ColdHeadCapability;

@Mod.EventBusSubscriber(modid = MhmBitsnbobs.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || event.getOverlay() != VanillaGuiOverlay.VIGNETTE.type()) return;

        player.getCapability(ColdHeadCapability.INSTANCE).ifPresent(cold -> {
            float level = cold.getColdLevel();
            if (level <= 0f) return;

            // Render overlay
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1f, 1f, 1f, level);

            RenderSystem.setShaderTexture(0, new ResourceLocation(MhmBitsnbobs.MOD_ID, "textures/gui/overlay/brain_freeze_outline.png"));

            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();

            guiGraphics.blit(new ResourceLocation(MhmBitsnbobs.MOD_ID, "textures/gui/overlay/brain_freeze_outline.png"),
                    0, 0, 0, 0, width, height, width, height);

            RenderSystem.disableBlend();
        });
    }
}
