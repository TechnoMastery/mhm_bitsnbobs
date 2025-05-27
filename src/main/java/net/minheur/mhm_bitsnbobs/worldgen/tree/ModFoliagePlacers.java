package net.minheur.mhm_bitsnbobs.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.worldgen.tree.custom.DarkFoliagePlacer;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<DarkFoliagePlacer>> DARK_FOLIAGE_PLACER =
            FOLIAGE_PLACER.register("dark_foliage_placer", () -> new FoliagePlacerType<>(DarkFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACER.register(eventBus);
    }
}
