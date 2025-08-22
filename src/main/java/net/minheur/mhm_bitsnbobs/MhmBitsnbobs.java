package net.minheur.mhm_bitsnbobs;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.mhm_bitsnbobs.commands.ModCommandsRegister;
import net.minheur.mhm_bitsnbobs.config.ModServerConfig;
import net.minheur.mhm_bitsnbobs.entity.ModEntities;
import net.minheur.mhm_bitsnbobs.entity.client.ModBoatRenderer;
import net.minheur.mhm_bitsnbobs.entity.client.RhinoRenderer;
import net.minheur.mhm_bitsnbobs.item.ModCreativeModTabs;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.loot.ModLootModifiers;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.mhm_bitsnbobs.screen.*;
import net.minheur.mhm_bitsnbobs.sound.ModSounds;
import net.minheur.mhm_bitsnbobs.util.ModNetworking;
import net.minheur.mhm_bitsnbobs.util.ModWoodTypes;
import net.minheur.mhm_bitsnbobs.villager.ModVillagers;
import net.minheur.mhm_bitsnbobs.worldgen.tree.ModFoliagePlacers;
import net.minheur.mhm_bitsnbobs.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

/***
 * The file is the core of the mod. It's him who link all classes together, making all items and blocks registered.
 */
@Mod(MhmBitsnbobs.MOD_ID)
public class MhmBitsnbobs
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mhm_bitsnbobs";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Here we call the register method created with every {@link net.minecraftforge.registries.DeferredRegister}.
     */
    public MhmBitsnbobs() // FMLJavaModLoadingContext context
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ModServerConfig.CONFIG, "mhm_bitsnbobs-server.toml");

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        ModCommandsRegister.registerCommands(dispatcher);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModNetworking.register();

            // ligne suivante : duplication pour les plantes
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CATMINT.getId(), ModBlocks.POTTED_CATMINT);

            // ligne suivante : ajout au compostage
            ComposterBlock.COMPOSTABLES.put(ModItems.PINE_CONE.get(), 0.2f);
            ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY.get(), 0.5f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CORN.get(), 0.3f);
        });
    }

    /**
     * Here we add items to vanilla creative mod tabs
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
             event.accept(ModItems.SAPPHIRE);
             event.accept(ModItems.RAW_SAPPHIRE);
             event.accept(ModItems.HARDENED_INGOT);
             event.accept(ModItems.ROTTEN_LEATHER);
             event.accept(ModItems.CREATIVE_RESIDUE);
             event.accept(ModItems.CREATIVE_ESSENCE);
             event.accept(ModItems.SMALL_CREATIVE_NUGGET);
             event.accept(ModItems.QUANTUM_CORE);
             event.accept(ModItems.CHOCOLATE_SCOOP);
             event.accept(ModItems.STRAWBERRY_SCOOP);
             event.accept(ModItems.VANILLA_SCOOP);
             event.accept(ModItems.SWEET_BERRIES_SCOOP);
             event.accept(ModItems.CHOCOLATE_SNOWBALL);
             event.accept(ModItems.VANILLA_SNOWBALL);
             event.accept(ModItems.STRAWBERRIES_SNOWBALL);
             event.accept(ModItems.SWEET_BERRIES_SNOWBALL);
             event.accept(ModItems.BUCKET_OF_LIQUID_CHOCOLATE_ICE_CREAM);
             event.accept(ModItems.BUCKET_OF_LIQUID_VANILLA_ICE_CREAM);
             event.accept(ModItems.BUCKET_OF_LIQUID_STRAWBERRIES_ICE_CREAM);
             event.accept(ModItems.BUCKET_OF_LIQUID_SWEET_BERRIES_ICE_CREAM);
             event.accept(ModItems.SCOOP_OF_STRAWBERRIES_SORBET);
             event.accept(ModItems.SCOOP_OF_CHOCOLATE_SORBET);
             event.accept(ModItems.SCOOP_OF_SWEET_BERRIES_SORBET);
             event.accept(ModItems.SCOOP_OF_VANILLA_SORBET);
             event.accept(ModItems.CREATIVE_NUGGET);
             event.accept(ModItems.BUCKET_OF_LIQUID_ICE_CREAM);
             event.accept(ModItems.VANILLA_EXTRACT);
             event.accept(ModItems.VANILLA_POD);
             event.accept(ModItems.DRIED_VANILLA_POD);
             event.accept(ModItems.CREATIVE_INGOT);
             event.accept(ModBlocks.CREATIVE_BLOCK);
             event.accept(ModBlocks.CREATIVE_RESIDUE_BLOCK);
             event.accept(ModItems.LIGHTNING_UPGRADE);
             event.accept(ModItems.FIRE_DIAMOND);
             event.accept(ModItems.FIRE_STICK);
             event.accept(ModItems.RUBIS);
             event.accept(ModItems.RUBINIUM);
             event.accept(ModItems.QUARTZ_SHARD);
             event.accept(ModItems.SUPER_CHARGED_INGOT);
             event.accept(ModItems.IRON_BALL);
             event.accept(ModItems.COPPER_BALL);
             event.accept(ModItems.GOLD_BALL);
             event.accept(ModItems.DIAMOND_BALL);
             event.accept(ModItems.SAPPHIRE_BALL);
             event.accept(ModItems.CONTROL_PANEL);
             event.accept(ModItems.CONTROLLED_STICK);
             event.accept(ModItems.HALF_STICK);
             event.accept(ModItems.QUARTER_STICK);
             event.accept(ModItems.ZOMBIE_ARM);
             event.accept(ModItems.BIOMASS);
             event.accept(ModItems.SAPPHIRE_NUGGET);
             event.accept(ModItems.RED_CLAY_BALL);
            event.accept(ModItems.OXIDIZED_ZINC);
        }
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.BASE_EGG);
            event.accept(ModItems.RHINO_SPAWN_EGG);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BURGER);
            event.accept(ModItems.STRAWBERRY);
            event.accept(ModItems.EXPLODED_POTATO);
            event.accept(ModItems.YEAST);
            event.accept(ModItems.HOLY_BREAD);
            event.accept(ModItems.DEVIL_BREAD);
            event.accept(ModItems.STRAWBERRY_ICE_CREAM);
            event.accept(ModItems.VANILLA_ICE_CREAM);
            event.accept(ModItems.CHOCOLATE_ICE_CREAM);
            event.accept(ModItems.SWEET_BERRIES_ICE_CREAM);
            event.accept(ModItems.SWEET_BERRIES_SORBET);
            event.accept(ModItems.CHOCOLATE_SORBET);
            event.accept(ModItems.STRAWBERRIES_SORBET);
            event.accept(ModItems.VANILLA_SORBET);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.SPAWNER_PART);
            event.accept(ModItems.FIRE_STICK);
            event.accept(ModItems.EMPTY_BIG_FLASK);
            event.accept(ModItems.EMPTY_LITTLE_FLASK);
            event.accept(ModItems.HUMID_POTION);
            event.accept(ModItems.DIRTY_HUMID_POTION);
            event.accept(ModItems.LITTLE_HUMID_POTION);
            event.accept(ModItems.SKULLKERY_TOOL);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.FIRE_SWORD);
            event.accept(ModItems.LIGHTNING_SWORD);
            event.accept(ModItems.RUBINIUM_SWORD);
            event.accept(ModItems.ALLOYED_SWORD);
            event.accept(ModItems.WIND_STICK);
            event.accept(ModItems.SLIME_SWORD);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModItems.FIRE_SEEDS);
            event.accept(ModBlocks.RUBINIUM_BLOCK);
            event.accept(ModItems.TREE_GROWER);
            event.accept(ModItems.BIOMASS);
            event.accept(ModBlocks.RED_CLAY);
        }
    }

    /**
     * You can use SubscribeEvent and let the Event Bus discover methods to call
      */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("MhM Bitsnbobs working !");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // wood types
            Sheets.addWoodType(ModWoodTypes.DARK);

            // entities
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
            EntityRenderers.register(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));
            EntityRenderers.register(ModEntities.DICE_PROJECTILE.get(), ThrownItemRenderer::new);

            // block entities
            MenuScreens.register(ModMenuTypes.FREEZER_MENU.get(), FreezerScreen::new);
            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingStationScreen::new);
            MenuScreens.register(ModMenuTypes.INCUBATOR_MENU.get(), IncubatorScreen::new);
            MenuScreens.register(ModMenuTypes.MYSTERIOUS_ALTAR_MENU.get(), MysteriousAltarScreen::new);
            MenuScreens.register(ModMenuTypes.ATOMICAL_STABILIZATOR_MENU.get(), AtomicalStabilizatorScreen::new);
        }
    }
}
