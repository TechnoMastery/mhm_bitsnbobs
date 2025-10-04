package net.minheur.mhm_bitsnbobs.fluid;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.fluid.custom.TestFluidType;

public class ModFluidsTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<FluidType> FLUID_TEST = FLUID_TYPES.register("test_fluid",
            () -> new TestFluidType(FluidType.Properties.create()
                    .lightLevel(5)
                    .viscosity(1000)
                    .density(1000)
                    .temperature(300)));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
