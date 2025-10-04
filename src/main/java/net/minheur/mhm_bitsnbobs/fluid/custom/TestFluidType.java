package net.minheur.mhm_bitsnbobs.fluid.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class TestFluidType extends FluidType {
    private static final ResourceLocation STILL = new ResourceLocation("block/water_still");
    private static final ResourceLocation FLOW = new ResourceLocation("block/water_flow");
    private static final ResourceLocation OVERLAY = new ResourceLocation("block/water_overlay");

    public TestFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture() { return STILL; }
            @Override public ResourceLocation getFlowingTexture() { return FLOW; }
            @Override public ResourceLocation getOverlayTexture() { return OVERLAY; }
            @Override public int getTintColor() { return 0xFF00FFFF; }
        });
    }
}
