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
import net.minheur.mhm_bitsnbobs.item.custom.*;
import net.minheur.mhm_bitsnbobs.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MhmBitsnbobs.MOD_ID);

    // create item
    public static final RegistryObject<Item> SAPPHIRE = ITEMS. register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BASE_EGG = ITEMS. register("base_egg",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPAWNER_PART = ITEMS. register("spawner_part",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CREATIVE_ESSENCE = ITEMS. register("creative_essence",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CREATIVE_NUGGET = ITEMS. register("creative_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CREATIVE_INGOT = ITEMS. register("creative_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CREATIVE_RESIDUE = ITEMS. register("creative_residue",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SMALL_CREATIVE_NUGGET = ITEMS. register("small_creative_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> LITTLE_COPPER_NUGGET = ITEMS. register("little_copper_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUPER_CHARGED_BALL = ITEMS. register("super_charged_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_UPGRADE = ITEMS. register("lightning_upgrade",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> XP_RUNE = ITEMS.register("xp_rune",
            () -> new XpRuneItem(new Item.Properties()));
    public static final RegistryObject<Item> SUPER_CHARGED_INGOT = ITEMS.register("super_charged_ingot",
            () -> new GlowingItem(new Item.Properties().fireResistant().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> IRON_BALL = ITEMS. register("iron_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_BALL = ITEMS. register("gold_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BALL = ITEMS. register("copper_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_BALL = ITEMS. register("diamond_ball",
            () -> new Item(new Item.Properties()));         // to finish

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

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BASE_OF_DISC = ITEMS.register("base_of_disc",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HARDENED_INGOT = ITEMS.register("hardened_ingot",
            () -> new Item(new Item.Properties()));

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
            () -> new SwordItem(Tiers.DIAMOND, 15, 1, new Item.Properties().durability(1600).fireResistant()));

    public static final RegistryObject<Item> LIGHTNING_SWORD = ITEMS.register("lightning_sword",
            () -> new SwordItem(Tiers.NETHERITE, 60, 1, new Item.Properties().durability(15000).fireResistant()));

    public static final RegistryObject<Item> RUBINIUM_SWORD = ITEMS.register("rubinium_sword",
            () -> new SwordItem(Tiers.NETHERITE, 35, 1, new Item.Properties().durability(10000).fireResistant()));

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

    // end item create




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
