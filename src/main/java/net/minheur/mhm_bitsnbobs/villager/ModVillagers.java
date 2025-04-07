package net.minheur.mhm_bitsnbobs.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MhmBitsnbobs.MOD_ID);
    public static final DeferredRegister<VillagerProfession> Villager_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MhmBitsnbobs.MOD_ID);

    // start create professions
    ///  Pour créer une profession, il faut d'abord ajouter le POI. Donc le premier public static final va définir, dans notre cas,
    /// un type de POI 'SOUND'. Il va lui donner des indications comme le block (ModBlocks.SOUND_BLOCK.get()), ainsi que des
    /// valeurs : maxTickets donc le nombre de villageois sur ce block. validRange est la distance à laquelle le villageois doit être
    /// pour utiliser le block (recup le job et restock).
    /// Pour en ajouter, il faut ainsi dupliquer aussi le second public static final qui défini le villageois en lui-même.
    public static final RegistryObject<PoiType> SOUND_POI = POI_TYPES.register("sound_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SOUND_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> SOUND_MASTER =
            Villager_PROFESSIONS.register("soundmaster", () -> new VillagerProfession("soundmaster",
                    holder -> holder.get() == SOUND_POI.get(), holder -> holder.get() == SOUND_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));



    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        Villager_PROFESSIONS.register(eventBus);
    }
}
