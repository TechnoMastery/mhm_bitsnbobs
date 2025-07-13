package net.minheur.mhm_bitsnbobs.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.effects.custom.QuantumChockedEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<MobEffect> QUANTUM_CHOCKED = EFFECTS.register("quantum_chocked",
            () -> new QuantumChockedEffect());

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
