package net.minheur.mhm_bitsnbobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.entity.custom.RhinoEntity;

public class ModEntities {
    /// si vous êtes observateurs, vous remarquerez que c'est à peu près pareille à chaque nouvelle DeferredRegister xD
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino", () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("rhino"));


    /// pareille pour la register methode, toujours la avec les deferredRegister
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
