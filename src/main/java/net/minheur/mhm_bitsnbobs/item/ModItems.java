package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.entity.ModEntities;
import net.minheur.mhm_bitsnbobs.entity.custom.ModBoatEntity;
import net.minheur.mhm_bitsnbobs.item.custom.*;
import net.minheur.mhm_bitsnbobs.item.custom.runes.*;
import net.minheur.mhm_bitsnbobs.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MhmBitsnbobs.MOD_ID);

    // create item
    public static final RegistryObject<Item> YEAST = ITEMS.register("yeast",
            () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> WIND_CHARGED_INGOT = ITEMS.register("wind_charged_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS. register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_NUGGET = ITEMS. register("sapphire_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BASE_EGG = ITEMS. register("base_egg",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPAWNER_PART = ITEMS. register("spawner_part",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONTROL_PANEL = ITEMS. register("control_panel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HALF_STICK = ITEMS. register("half_stick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUARTER_STICK = ITEMS. register("quarter_stick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_LEATHER = ITEMS. register("rotten_leather",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EXTREMELY_DRY_DIRT = ITEMS. register("extremely_dry_dirt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIECE_OF_EXTREMELY_DRY_DIRT = ITEMS. register("piece_of_extremely_dry_dirt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RESOURCE_DIRT = ITEMS.register("resource_dirt",
            () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> WET_DIRT = ITEMS. register("wet_dirt",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PIECE_OF_DIRT = ITEMS. register("piece_of_dirt",
            () -> new ReturnOtherWhenEatedItem(new Item.Properties().food(ModFoods.PIECE_OF_DIRT), ModItems.WET_DIRT.get()));
    public static final RegistryObject<Item> SKULLKERY_TOOL = ITEMS. register("skullkery_tool",
            () -> new SkullkeryToolItem(new Item.Properties().stacksTo(1).durability(59)));
    public static final RegistryObject<Item> CONTROLLED_STICK = ITEMS. register("controlled_stick",
            () -> new Item(new Item.Properties().stacksTo(8)));

    // to see
    public static final RegistryObject<Item> BASE_OF_DISC = ITEMS.register("base_of_disc",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> QUANTUM_CORE = ITEMS.register("quantum_core",
            () -> new QuantumCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> UNPROCESSED_QUANTUM_CORE = ITEMS.register("unprocessed_quantum_core",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> STABILIZED_QUANTUM_CORE = ITEMS.register("stabilized_quantum_core",
            () -> new StabilizedQuantumCoreItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> TREE_GROWER = ITEMS.register("tree_grower",
            () -> new CraftingDamageItem(new Item.Properties().stacksTo(1).durability(50)));

    public static final RegistryObject<Item> SLIMY_INGOT = ITEMS.register("slimy_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SLIMY_STICK = ITEMS.register("slimy_stick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CREATIVE_ESSENCE = ITEMS. register("creative_essence",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CREATIVE_NUGGET = ITEMS. register("creative_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> UNPROCESSED_CREATIVE_NUGGET = ITEMS. register("unprocessed_creative_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CREATIVE_INGOT = ITEMS. register("creative_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CREATIVE_RESIDUE = ITEMS. register("creative_residue",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SMALL_CREATIVE_NUGGET = ITEMS. register("small_creative_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));public static final RegistryObject<Item> SUPER_CHARGED_BALL = ITEMS. register("super_charged_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_UPGRADE = ITEMS. register("lightning_upgrade",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> SUPER_CHARGED_INGOT = ITEMS.register("super_charged_ingot",
            () -> new GlowingItem(new Item.Properties().fireResistant().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ROTTEN_BEEF = ITEMS.register("rotten_beef",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_PORKCHOP = ITEMS.register("rotten_porkchop",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_MUTTON = ITEMS.register("rotten_mutton",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_CHICKEN = ITEMS.register("rotten_chicken",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_RABBIT = ITEMS.register("rotten_rabbit",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_COD = ITEMS.register("rotten_cod",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_SALMON = ITEMS.register("rotten_salmon",
            () -> new Item(new Item.Properties()));

    // runes
    public static final RegistryObject<Item> EMPTY_RUNE = ITEMS.register("empty_rune",
            () -> new EmptyRuneItem(new Item.Properties()));

    public static final RegistryObject<Item> XP_RUNE = ITEMS.register("xp_rune",
            () -> new XpRuneItem(new Item.Properties()));
    public static final RegistryObject<Item> MONEY_RUNE = ITEMS.register("money_rune",
            () -> new MoneyRuneItem(new Item.Properties()));
    public static final RegistryObject<Item> OAK_RUNE = ITEMS.register("oak_rune",
            () -> new OakRuneItem(new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_RUNE = ITEMS.register("spruce_rune",
            () -> new SpruceRuneItem(new Item.Properties()));

    // catalyzers
    public static final RegistryObject<Item> IRON_CATALYZER = ITEMS.register("iron_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(250), "iron"));
    public static final RegistryObject<Item> WIND_CHARGED_CATALYZER = ITEMS.register("wind_charged_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(150), "wind_charged"));
    public static final RegistryObject<Item> GOLD_CATALYZER = ITEMS.register("gold_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(32), "gold"));
    public static final RegistryObject<Item> DIAMOND_CATALYZER = ITEMS.register("diamond_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(1561), "diamond"));
    public static final RegistryObject<Item> NETHERITE_CATALYZER = ITEMS.register("netherite_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(2031), "netherite"));
    public static final RegistryObject<Item> SUPER_CHARGED_CATALYZER = ITEMS.register("super_charged_catalyzer",
            () -> new CatalyzerItem(new Item.Properties().stacksTo(1).durability(3000), "super_charged"));

    public static final RegistryObject<Item> IRON_BALL = ITEMS. register("iron_ball",
            () -> new BallItem(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_BALL = ITEMS. register("gold_ball",
            () -> new BallItem(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BALL = ITEMS. register("copper_ball",
            () -> new BallItem(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_BALL = ITEMS. register("diamond_ball",
            () -> new BallItem(new Item.Properties()));

    public static final RegistryObject<Item> UNFINISHED_EMERALD = ITEMS.register("unfinished_emerald",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNFINISHED_BLAZE_POWDER = ITEMS.register("unfinished_blaze_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNFINISHED_STORM_FRAGMENT = ITEMS.register("unfinished_storm_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNPROCESSED_SEA_PICKLE = ITEMS.register("unprocessed_sea_pickle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ZOMBIE_ARM = ITEMS.register("zombie_arm",
            () -> new ZombieArmItem(new Item.Properties()));
    public static final RegistryObject<Item> BIOMASS = ITEMS.register("biomass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_CLAY_BALL = ITEMS.register("red_clay_ball",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMPTY_BIG_FLASK = ITEMS. register("empty_big_flask",
            () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> EMPTY_LITTLE_FLASK = ITEMS. register("empty_little_flask",
            () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DIRTY_HUMID_POTION = ITEMS. register("dirty_humid_potion",
            () -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> HUMID_POTION = ITEMS. register("humid_potion",
            () -> new CraftingKeepOtherItem(new Item.Properties(), new ItemStack(ModItems.EMPTY_BIG_FLASK.get())));
    public static final RegistryObject<Item> LITTLE_HUMID_POTION = ITEMS. register("little_humid_potion",
            () -> new CraftingKeepOtherItem(new Item.Properties(), new ItemStack(ModItems.EMPTY_LITTLE_FLASK.get())));

    public static final RegistryObject<Item> STORM_FRAGMENT = ITEMS.register("storm_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> ALLOYED_SWORD = ITEMS.register("alloyed_sword",
            () -> new AlloyedSwordItem(new Item.Properties().stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> QUANTUM_STAFF = ITEMS.register("quantum_staff",
            () -> new QuantumStaffItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).durability(50000)));
    public static final RegistryObject<Item> WIND_STICK = ITEMS.register("wind_stick",
            () -> new WindStickItem(new Item.Properties().stacksTo(1).durability(600)));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HARDENED_INGOT = ITEMS.register("hardened_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DICE = ITEMS.register("dice",
            () -> new DiceItem(new Item.Properties()));

    public static final RegistryObject<Item> RUBIS = ITEMS.register("rubis",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> RUBINIUM = ITEMS.register("rubinium",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(40)));

    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new RecordItem(6, ModSounds.BAR_BRAWL, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> DARK_SOUL_MUSIC_DISC = ITEMS.register("dark_soul_music_disc",
            () -> new RecordItem(6, ModSounds.DARK_SOUL, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 3240));
    public static final RegistryObject<Item> END_OF_THE_START_MUSIC_DISC = ITEMS.register("end_of_the_start_music_disc",
            () -> new RecordItem(6, ModSounds.END_OF_THE_START, new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 2680));

    public static final RegistryObject<Item> BALLAD_OF_THE_BLOCKS_MUSIC_DISC = ITEMS.register("ballad_of_the_blocks_music_disc",
            () -> new RecordItem(6, ModSounds.BALLAD_OF_THE_BLOCKS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2380));
    public static final RegistryObject<Item> BLOCK_BY_BLOCK_MUSIC_DISC = ITEMS.register("block_by_block_music_disc",
            () -> new RecordItem(6, ModSounds.BLOCK_BY_BLOCK, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3120));
    public static final RegistryObject<Item> CUBIC_GROOVE_MUSIC_DISC = ITEMS.register("cubic_groove_music_disc",
            () -> new RecordItem(6, ModSounds.CUBIC_GROOVE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3100));
    public static final RegistryObject<Item> IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC = ITEMS.register("in_the_world_of_minecraft_music_disc",
            () -> new RecordItem(6, ModSounds.IN_THE_WORLD_OF_MINECRAFT, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2440));
    public static final RegistryObject<Item> IN_THE_BLOCK_MUSIC_DISC = ITEMS.register("in_the_block_music_disc",
            () -> new RecordItem(6, ModSounds.IN_THE_BLOCK, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3000));
    public static final RegistryObject<Item> THE_WORLD_OF_CUBES_MUSIC_DISC = ITEMS.register("the_world_of_cubes_music_disc",
            () -> new RecordItem(6, ModSounds.THE_WORLD_OF_CUBES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2540));
    public static final RegistryObject<Item> LEGENDS_AWAKEN_V1_MUSIC_DISC = ITEMS.register("legends_awaken_v1_music_disc",
            () -> new RecordItem(6, ModSounds.LEGENDS_AWAKEN_V1, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2360));
    public static final RegistryObject<Item> LEGENDS_AWAKEN_V2_MUSIC_DISC = ITEMS.register("legends_awaken_v2_music_disc",
            () -> new RecordItem(6, ModSounds.LEGENDS_AWAKEN_V2, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
    public static final RegistryObject<Item> MY_MINECRAFT_WORLD_MUSIC_DISC = ITEMS.register("my_minecraft_world_music_disc",
            () -> new RecordItem(6, ModSounds.MY_MINECRAFT_WORLD, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3920));
    public static final RegistryObject<Item> NETHER_NIGHTS_MUSIC_DISC = ITEMS.register("nether_nights_music_disc",
            () -> new RecordItem(6, ModSounds.NETHER_NIGHTS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2540));
    public static final RegistryObject<Item> REDSTONE_PULSE_MUSIC_DISC = ITEMS.register("redstone_pulse_music_disc",
            () -> new RecordItem(6, ModSounds.REDSTONE_PULSE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3080));
    public static final RegistryObject<Item> SERENE_ECHO_MUSIC_DISC = ITEMS.register("serene_echo_music_disc",
            () -> new RecordItem(6, ModSounds.SERENE_ECHO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4800));

    // fuel item
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> FIRE_DIAMOND = ITEMS. register("fire_diamond",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.RARE).fireResistant(), 1600));
    public static final RegistryObject<Item> FIRE_STICK = ITEMS. register("fire_stick",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.RARE).fireResistant().stacksTo(16), 1600));

    // tools
    // swords
    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> FIRE_SWORD = ITEMS.register("fire_sword",
            () -> new FireSwordItem(ModToolTiers.FIRE, 1, 1, new Item.Properties().durability(1600).fireResistant()));

    public static final RegistryObject<Item> LIGHTNING_SWORD = ITEMS.register("lightning_sword",
            () -> new LightningSwordItem(ModToolTiers.LIGHTNING, 60, 1, new Item.Properties().durability(15000).fireResistant()));

    public static final RegistryObject<Item> RUBINIUM_SWORD = ITEMS.register("rubinium_sword",
            () -> new SwordItem(Tiers.NETHERITE, 35, 1, new Item.Properties().durability(10000).fireResistant()));
    // TODO: add a tool tier

    public static final RegistryObject<Item> SLIME_SWORD = ITEMS.register("slime_sword",
            () -> new SlimeSwordItem(Tiers.IRON, 10,5, new Item.Properties().fireResistant().durability(750)));

    // pickaxes
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, 4, 2, new Item.Properties()));

    // shovels
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE, 0, 0, new Item.Properties()));

    // axes
    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, 7, 1, new Item.Properties()));

    // hoes
    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE, 0, 0, new Item.Properties()));

    // armors
    // change just ModArmorMaterials and ArmorItem.Type

    // sapphire
    public static final RegistryObject<Item> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // bouffe
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static final RegistryObject<Item> BURGER = ITEMS.register("burger",
            () -> new Item(new Item.Properties().food(ModFoods.BURGER)));

    public static final RegistryObject<Item> EXPLODED_POTATO = ITEMS.register("exploded_potato",
            () -> new Item(new Item.Properties().food(ModFoods.EXPLODED_POTATO)));
    public static final RegistryObject<Item> HOLY_BREAD = ITEMS.register("holy_bread",
            () -> new Item(new Item.Properties().food(ModFoods.HOLY_BREAD)));
    public static final RegistryObject<Item> DEVIL_BREAD = ITEMS.register("devil_bread",
            () -> new Item(new Item.Properties().food(ModFoods.DEVIl_BREAD)));

    // boats
    public static final RegistryObject<Item> DARK_BOAT = ITEMS.register("dark_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.DARK, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DARK_CHEST_BOAT = ITEMS.register("dark_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.DARK, new Item.Properties().stacksTo(1)));

    // seeds
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIRE_SEEDS = ITEMS.register("fire_seeds",
            () -> new ItemNameBlockItem(ModBlocks.FIRE_PLANT.get(), new Item.Properties().fireResistant()));

    // spawn eggs
    public static final RegistryObject<Item> RHINO_SPAWN_EGG = ITEMS. register("rhino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RHINO, 0x7e9680, 0xc5d1c5,
                    new Item.Properties()));

    // signs
    public static final RegistryObject<Item> DARK_SIGN = ITEMS.register("dark_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.DARK_SIGN.get(), ModBlocks.DARK_WALL_SIGN.get()));
    public static final RegistryObject<Item> DARK_HANGING_SIGN = ITEMS.register("dark_hanging_sign",
            () -> new HangingSignItem(ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    // end item create




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
