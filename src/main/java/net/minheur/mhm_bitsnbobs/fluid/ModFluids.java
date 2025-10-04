package net.minheur.mhm_bitsnbobs.fluid;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<ForgeFlowingFluid.Source> TEST_SOURCE =
            FLUIDS.register("test_source", () -> new ForgeFlowingFluid.Source(ModFluids.TEST_PROPERTIES));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> TEST_FLOWING =
            FLUIDS.register("test_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.TEST_PROPERTIES));

    public static final ForgeFlowingFluid.Properties TEST_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidsTypes.FLUID_TEST,
            TEST_SOURCE,
            TEST_FLOWING)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> ModBlocks.TEST_FLUID_BLOCK.get())
            .bucket(() -> ModItems.TEST_BUCKET.get());


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
