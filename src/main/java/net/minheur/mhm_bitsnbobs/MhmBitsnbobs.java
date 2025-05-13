package net.minheur.mhm_bitsnbobs;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.block.entity.ModBlockEntities;
import net.minheur.mhm_bitsnbobs.entity.ModEntities;
import net.minheur.mhm_bitsnbobs.entity.client.ModBoatRenderer;
import net.minheur.mhm_bitsnbobs.entity.client.RhinoRenderer;
import net.minheur.mhm_bitsnbobs.item.ModCreativeModTabs;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.loot.ModLootModifiers;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.mhm_bitsnbobs.screen.GemPolishingStationScreen;
import net.minheur.mhm_bitsnbobs.screen.ModMenuTypes;
import net.minheur.mhm_bitsnbobs.sound.ModSounds;
import net.minheur.mhm_bitsnbobs.util.ModWoodTypes;
import net.minheur.mhm_bitsnbobs.villager.ModVillagers;
import net.minheur.mhm_bitsnbobs.worldgen.biome.ModTerrablender;
import net.minheur.mhm_bitsnbobs.worldgen.biome.surface.ModSurfaceRules;
import net.minheur.mhm_bitsnbobs.worldgen.tree.ModFoliagePlacers;
import net.minheur.mhm_bitsnbobs.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

/// ce fichier est le coeur, le cerveau, le tous ce que tu veut de ton mod.
/// C'est entre autre lui qui va définire les bases, qui va appeler les fichier de données comme les creative mod tabs etc.
/// Le supprimmer est critique (comme la plupare des fichiers xD) : cela empècherais le mod de comprendre ce qui ce passe.
/// Il s'agit de la javaClass principale.

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MhmBitsnbobs.MOD_ID)
public class MhmBitsnbobs
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mhm_bitsnbobs";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    /// Ici donc on vient utiliser la métode register a chaque fois pour les fichier ayant une DeferredRegister
    public MhmBitsnbobs(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        ModTerrablender.registerBiomes();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            // ligne suivante : dupli pour les plantes
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CATMINT.getId(), ModBlocks.POTTED_CATMINT);

            // biome rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }

    /// c'est ici que l'on ajoute des items a des creative mod tabs vanilla
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
             event.accept(ModItems.CREATIVE_NUGGET);
             event.accept(ModItems.CREATIVE_INGOT);
             event.accept(ModBlocks.CREATIVE_BLOCK);
             event.accept(ModBlocks.CREATIVE_RESIDUE_BLOCK);
             event.accept(ModItems.LITTLE_COPPER_NUGGET);
             event.accept(ModItems.LIGHTNING_UPGRADE);
             event.accept(ModItems.FIRE_DIAMOND);
             event.accept(ModItems.FIRE_STICK);
             event.accept(ModItems.RUBIS);
             event.accept(ModItems.RUBINIUM);
             event.accept(ModItems.SUPER_CHARGED_INGOT);
             event.accept(ModItems.IRON_BALL);
             event.accept(ModItems.COPPER_BALL);
             event.accept(ModItems.GOLD_BALL);
             event.accept(ModItems.DIAMOND_BALL);
             event.accept(ModItems.CONTROL_PANEL);
             event.accept(ModItems.CONTROLLED_STICK);
             event.accept(ModItems.HALF_STICK);
             event.accept(ModItems.QUATER_STICK);
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
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.SPAWNER_PART);
            event.accept(ModItems.FIRE_STICK);
            event.accept(ModItems.TRANSFER_FLASK);
            event.accept(ModItems.EMPTY_BIG_FLASK);
            event.accept(ModItems.EMPTY_LITTLE_FLASK);
            event.accept(ModItems.HUMID_POTION);
            event.accept(ModItems.DIRTY_HUMID_POTION);
            event.accept(ModItems.LITTLE_HUMID_POTION);
            event.accept(ModItems.CONTROLLED_PICKAXE);
            event.accept(ModItems.SKULLKERY_TOOL);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.FIRE_SWORD);
            event.accept(ModItems.LIGHTNING_SWORD);
            event.accept(ModItems.RUBINIUM_SWORD);
            event.accept(ModItems.ALLOYED_SWORD);
            event.accept(ModItems.WIND_STICK);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModItems.FIRE_SEEDS);
            event.accept(ModBlocks.RUBINIUM_BLOCK);
            event.accept(ModItems.TREE_GROWER);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
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
            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingStationScreen::new);
        }
    }
}
