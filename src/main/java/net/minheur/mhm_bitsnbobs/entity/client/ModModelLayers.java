package net.minheur.mhm_bitsnbobs.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModModelLayers {
    public static final ModelLayerLocation RHINO_LAYER = new ModelLayerLocation(
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "rhino_layer"), "main");

    public static final ModelLayerLocation DARK_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "boat/dark"), "main");
    public static final ModelLayerLocation DARK_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "chest_boat/dark"), "main");

}
