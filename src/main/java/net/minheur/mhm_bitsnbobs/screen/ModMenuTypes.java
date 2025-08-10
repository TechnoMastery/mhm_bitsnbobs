package net.minheur.mhm_bitsnbobs.screen;


import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<MenuType<GemPolishingStationMenu>> GEM_POLISHING_MENU =
            registerMenuType("gem_polishing_menu", GemPolishingStationMenu::new);
    public static final RegistryObject<MenuType<IncubatorMenu>> INCUBATOR_MENU =
            registerMenuType("incubator_menu", IncubatorMenu::new);
    public static final RegistryObject<MenuType<MysteriousAltarMenu>> MYSTERIOUS_ALTAR_MENU =
            registerMenuType("mysterious_altar_menu", MysteriousAltarMenu::new);
    public static final RegistryObject<MenuType<FreezerMenu>> FREEZER_MENU =
            registerMenuType("freezer_menu", FreezerMenu::new);
    public static final RegistryObject<MenuType<AtomicalStabilizatorMenu>> ATOMICAL_STABILIZATOR_MENU =
            registerMenuType("atomical_stabilizator_menu", AtomicalStabilizatorMenu::new);


private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
    return MENUS.register(name, () -> IForgeMenuType.create(factory));
}

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
