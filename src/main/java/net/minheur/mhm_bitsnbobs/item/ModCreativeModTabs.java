package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MhmBitsnbobs.MOD_ID);

    // première tab : indiqué par TUTORIAL_TAB (le nom)
    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_SAPPHIRE.get());
                        output.accept(ModItems.SAPPHIRE.get());
                        output.accept(ModItems.SAPPHIRE_NUGGET.get());
                        output.accept(ModItems.SAPPHIRE_STAFF.get());
                        output.accept(ModItems.CORN.get());
                        output.accept(ModItems.RHINO_SPAWN_EGG.get());
                        output.accept(ModBlocks.FREEZER.get());
                        output.accept(ModItems.DICE.get());
                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModItems.PINE_CONE.get());
                        output.accept(ModItems.BURGER.get());
                        output.accept(ModItems.STRAWBERRY.get());
                        output.accept(ModItems.STRAWBERRY_SEEDS.get());
                        output.accept(ModItems.CORN_SEEDS.get());
                        output.accept(ModItems.SAPPHIRE_SWORD.get());
                        output.accept(ModItems.SAPPHIRE_PICKAXE.get());
                        output.accept(ModItems.SAPPHIRE_AXE.get());
                        output.accept(ModItems.SAPPHIRE_SHOVEL.get());
                        output.accept(ModItems.SAPPHIRE_HOE.get());
                        output.accept(ModItems.SAPPHIRE_HELMET.get());
                        output.accept(ModItems.SAPPHIRE_CHESTPLATE.get());
                        output.accept(ModItems.SAPPHIRE_LEGGINGS.get());
                        output.accept(ModItems.SAPPHIRE_BOOTS.get());
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.END_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                        output.accept(ModBlocks.SAPPHIRE_STAIRS.get());
                        output.accept(ModBlocks.SAPPHIRE_SLAB.get());
                        output.accept(ModBlocks.SAPPHIRE_BUTTON.get());
                        output.accept(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.SAPPHIRE_FENCE.get());
                        output.accept(ModBlocks.SAPPHIRE_FENCE_GATE.get());
                        output.accept(ModBlocks.SAPPHIRE_WALL.get());
                        output.accept(ModBlocks.SAPPHIRE_DOOR.get());
                        output.accept(ModBlocks.SAPPHIRE_TRAPDOOR.get());
                        output.accept(ModBlocks.CATMINT.get());
                        output.accept(Items.DIAMOND);
                        output.accept(Blocks.DIAMOND_BLOCK);
                        output.accept(ModBlocks.GEM_POLISHING_STATION.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MUSIC_DISCS = CREATIVE_MODE_TABS.register("discs",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BASE_OF_DISC.get()))
                    .title(Component.translatable("creativetab.discs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BASE_OF_DISC.get());
                        output.accept(ModItems.END_OF_THE_START_MUSIC_DISC.get());
                        output.accept(ModItems.DARK_SOUL_MUSIC_DISC.get());
                        output.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());
                        output.accept(ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC.get());
                        output.accept(ModItems.BLOCK_BY_BLOCK_MUSIC_DISC.get());
                        output.accept(ModItems.CUBIC_GROOVE_MUSIC_DISC.get());
                        output.accept(ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC.get());
                        output.accept(ModItems.IN_THE_BLOCK_MUSIC_DISC.get());
                        output.accept(ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC.get());
                        output.accept(ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC.get());
                        output.accept(ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC.get());
                        output.accept(ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC.get());
                        output.accept(ModItems.NETHER_NIGHTS_MUSIC_DISC.get());
                        output.accept(ModItems.REDSTONE_PULSE_MUSIC_DISC.get());
                        output.accept(ModItems.SERENE_ECHO_MUSIC_DISC.get());
                    })
                    .withSearchBar().build());

    public static final RegistryObject<CreativeModeTab> DARK_STORM = CREATIVE_MODE_TABS.register("dark_storm",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STORM_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.dark_storm"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.STORM_FRAGMENT.get());
                        output.accept(ModItems.HARDENED_INGOT.get());
                        output.accept(ModBlocks.DARK_LOG.get());
                        output.accept(ModBlocks.DARK_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_DARK_LOG.get());
                        output.accept(ModBlocks.STRIPPED_DARK_WOOD.get());
                        output.accept(ModBlocks.DARK_PLANKS.get());
                        output.accept(ModBlocks.DARK_LEAVES.get());
                        output.accept(ModItems.DARK_SIGN.get());
                        output.accept(ModItems.DARK_HANGING_SIGN.get());
                        output.accept(ModItems.DARK_BOAT.get());
                        output.accept(ModItems.DARK_CHEST_BOAT.get());
                        output.accept(ModBlocks.DARK_SAPLING.get());
                        output.accept(ModItems.WIND_CHARGED_INGOT.get());
                        output.accept(ModItems.QUANTUM_CORE.get());
                        output.accept(ModItems.QUANTUM_DUST.get());
                        output.accept(ModItems.STABILIZED_QUANTUM_CORE.get());
                        output.accept(ModItems.QUANTUM_STAFF.get());
                        output.accept(ModBlocks.MYSTERIOUS_ALTAR.get());
                        output.accept(ModItems.MAGIC_SHARD.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> LIGHTNING = CREATIVE_MODE_TABS.register("lightning",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIGHTNING_UPGRADE.get()))
                    .title(Component.translatable("creativetab.lightning"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LIGHTNING_UPGRADE.get());
                        output.accept(ModItems.FIRE_DIAMOND.get());
                        output.accept(ModItems.FIRE_STICK.get());
                        output.accept(ModItems.FIRE_SWORD.get());
                        output.accept(ModItems.LIGHTNING_SWORD.get());
                        output.accept(ModItems.FIRE_SEEDS.get());
                        output.accept(ModBlocks.RUBINIUM_BLOCK.get());
                        output.accept(ModItems.RUBIS.get());
                        output.accept(ModItems.RUBINIUM.get());
                        output.accept(ModItems.RUBINIUM_SWORD.get());
                        output.accept(ModItems.ALLOYED_SWORD.get());
                        output.accept(ModItems.SUPER_CHARGED_INGOT.get());
                        output.accept(ModBlocks.SUPER_CHARGED_BLOCK.get());
                        output.accept(ModItems.SUPER_CHARGED_BALL.get());
                        output.accept(ModItems.CONTROL_PANEL.get());
                        output.accept(ModBlocks.INCUBATOR.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> BALLS = CREATIVE_MODE_TABS.register("balls",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIAMOND_BALL.get()))
                    .title(Component.translatable("creativetab.balls"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.IRON_BALL.get());
                        output.accept(ModItems.COPPER_BALL.get());
                        output.accept(ModItems.GOLD_BALL.get());
                        output.accept(ModItems.DIAMOND_BALL.get());
                        output.accept(ModItems.SUPER_CHARGED_BALL.get());

                        output.accept(ModItems.IRON_CATALYZER.get());
                        output.accept(ModItems.WIND_CHARGED_CATALYZER.get());
                        output.accept(ModItems.GOLD_CATALYZER.get());
                        output.accept(ModItems.DIAMOND_CATALYZER.get());
                        output.accept(ModItems.NETHERITE_CATALYZER.get());
                        output.accept(ModItems.SUPER_CHARGED_CATALYZER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RUNES = CREATIVE_MODE_TABS.register("runes",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EMPTY_RUNE.get()))
                    .title(Component.translatable("creativetab.runes"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EMPTY_RUNE.get());
                        output.accept(ModItems.XP_RUNE.get());
                        output.accept(ModItems.MONEY_RUNE.get());
                        output.accept(ModItems.OAK_RUNE.get());
                        output.accept(ModItems.SPRUCE_RUNE.get());
                    })
                    .withSearchBar().build());

    public static final RegistryObject<CreativeModeTab> DIRT_THINGS = CREATIVE_MODE_TABS.register("dirt_things",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.COMPRESSED_DIRT.get()))
                    .title(Component.translatable("creativetab.dirt_things"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.COMPRESSED_DIRT.get());
                        output.accept(ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get());
                        output.accept(ModItems.PIECE_OF_DIRT.get());
                        output.accept(ModItems.WET_DIRT.get());
                        output.accept(ModItems.EXTREMELY_DRY_DIRT.get());
                        output.accept(ModItems.RESOURCE_DIRT.get());
                        output.accept(ModItems.PIECE_OF_EXTREMELY_DRY_DIRT.get());
                        output.accept(ModBlocks.RESOURCE_DIRT_BLOCK.get());
                    })
                    .build());

            public static void register(IEventBus eventBus) {
                CREATIVE_MODE_TABS.register(eventBus);
            }
}
