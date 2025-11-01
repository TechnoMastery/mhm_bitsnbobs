package net.minheur.mhm_bitsnbobs.datagen;

import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipeBuilder;
import com.google.gson.JsonObject;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.compat.OtherModItems;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.recipe.datagen.*;
import net.minheur.mhm_bitsnbobs.recipe.datagen.compat.*;
import net.minheur.mhm_bitsnbobs.util.ModTags;
import net.minheur.techno_lib.builders.JsonBuilder;
import net.minheur.techno_lib.builders.RecipeNbtBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * This provider create the recipes.
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    /**
     * Called method : it generates recipes.
     * @param pWriter it is the consumer. Used in every recipe.
     */
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // call for smelting ore : SMELTING = BLASTING but put x2 time in SMELTING
        oreSmelting(pWriter, List.of(ModItems.IRON_BALL.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.COPPER_BALL.get()), RecipeCategory.MISC, OtherModItems.Create.COPPER_NUGGET.getAsRawItem(), 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.GOLD_BALL.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.DIAMOND_BALL.get()), RecipeCategory.MISC, Items.DIAMOND, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.SAPPHIRE_BALL.get()), RecipeCategory.MISC, ModItems.SAPPHIRE_NUGGET.get(), 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, ModItems.ROTTEN_LEATHER.get(), 0.1f, 200, "rotten");
        oreSmelting(pWriter, List.of(ModBlocks.COMPRESSED_DIRT.get()), RecipeCategory.MISC, ModItems.EXTREMELY_DRY_DIRT.get(), 0.1f, 600, "dirt");
        oreSmelting(pWriter, List.of(ModItems.WET_DIRT.get()), RecipeCategory.MISC, ModItems.EXTREMELY_DRY_DIRT.get(), 0.1f, 1800, "dirt");
        oreSmelting(pWriter, List.of(ModBlocks.RED_CLAY.get()), RecipeCategory.MISC, Blocks.RED_TERRACOTTA, 0.1f, 200, "clay");

        oreBlasting(pWriter, List.of(ModItems.CREATIVE_ESSENCE.get()), RecipeCategory.MISC, ModItems.CREATIVE_RESIDUE.get(), 1f, 24000, "creative");

        // smoking
        itemSmoking(pWriter, List.of(Items.IRON_INGOT), RecipeCategory.MISC, ModItems.HARDENED_INGOT.get(), 0.0f, 500, "hardened");

        // shaped recipe pattern → 3x3 crafting
        simpleBlockCrafting(pWriter, ModBlocks.SAPPHIRE_BLOCK.get(), ModItems.SAPPHIRE.get());
        simpleBlockCrafting(pWriter, ModBlocks.RAW_SAPPHIRE_BLOCK.get(), ModItems.RAW_SAPPHIRE.get());
        simpleBlockCrafting(pWriter, ModBlocks.CREATIVE_RESIDUE_BLOCK.get(), ModItems.CREATIVE_RESIDUE.get());
        simpleBlockCrafting(pWriter, ModItems.FIRE_DIAMOND.get(), ModItems.FIRE_SEEDS.get());
        simpleBlockCrafting(pWriter, ModItems.RUBINIUM.get(), ModItems.RUBIS.get());
        simpleBlockCrafting(pWriter, ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(), ModItems.EXTREMELY_DRY_DIRT.get());
        simpleBlockCrafting(pWriter, ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(), ModItems.PIECE_OF_EXTREMELY_DRY_DIRT.get());
        simpleBlockCrafting(pWriter, ModBlocks.RESOURCE_DIRT_BLOCK.get(), ModItems.RESOURCE_DIRT.get());
        simpleBlockCrafting(pWriter, ModItems.SAPPHIRE.get(), ModItems.SAPPHIRE_NUGGET.get());

        simpleBlockCrafting(pWriter, ModItems.CREATIVE_INGOT.get(), ModItems.CREATIVE_NUGGET.get());
        simpleBlockCrafting(pWriter, ModBlocks.CREATIVE_BLOCK.get(), ModItems.CREATIVE_INGOT.get());
        simpleBlockCrafting(pWriter, ModBlocks.SUPER_CHARGED_BLOCK.get(), ModItems.SUPER_CHARGED_INGOT.get());

        // call craft armor → chacun le leur
        simpleHelmetCrafting(pWriter, ModItems.SAPPHIRE_HELMET.get(), ModItems.SAPPHIRE.get());
        simpleChestplateCrafting(pWriter, ModItems.SAPPHIRE_CHESTPLATE.get(), ModItems.SAPPHIRE.get());
        simpleLeggingsCrafting(pWriter, ModItems.SAPPHIRE_LEGGINGS.get(), ModItems.SAPPHIRE.get());
        simpleBootsCrafting(pWriter, ModItems.SAPPHIRE_BOOTS.get(), ModItems.SAPPHIRE.get());

        // tools & weapons : chacun le leur (préciser le stick pls)
        simpleSwordCrafting(pWriter, ModItems.SLIME_SWORD.get(), ModItems.SLIMY_INGOT.get(), ModItems.SLIMY_STICK.get());
        simpleSwordCrafting(pWriter, ModItems.SAPPHIRE_SWORD.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simplePickaxeCrafting(pWriter, ModItems.SAPPHIRE_PICKAXE.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleAxesCrafting(pWriter, ModItems.SAPPHIRE_AXE.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleShovelCrafting(pWriter, ModItems.SAPPHIRE_SHOVEL.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleHoesCrafting(pWriter, ModItems.SAPPHIRE_HOE.get(), ModItems.SAPPHIRE.get(), Items.STICK);

        // slab, stair & pressure plate ont leurs propres mod
        simpleSlabCrafting(pWriter, ModBlocks.SAPPHIRE_SLAB.get(), ModBlocks.SAPPHIRE_BLOCK.get());
        simpleStairsCrafting(pWriter, ModBlocks.SAPPHIRE_STAIRS.get(), ModBlocks.SAPPHIRE_BLOCK.get());
        simplePressurePlateCrafting(pWriter, ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), ModBlocks.SAPPHIRE_BLOCK.get());

        // simple shapeless pattern → utiliser le bon selon la qté d'items different
        simpleShapelessCraftingOne(pWriter, ModItems.SAPPHIRE_NUGGET.get(), ModItems.SAPPHIRE.get(), 9, 1);
        simpleShapelessCraftingOne(pWriter, ModBlocks.SAPPHIRE_BUTTON.get(), ModItems.SAPPHIRE.get(), 1, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get(), 9, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.FIRE_SEEDS.get(), ModItems.FIRE_DIAMOND.get(), 5, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.CREATIVE_NUGGET.get(), ModItems.CREATIVE_INGOT.get(), 9, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.CREATIVE_INGOT.get(), ModBlocks.CREATIVE_BLOCK.get(), 9, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.SUPER_CHARGED_INGOT.get(), ModBlocks.SUPER_CHARGED_BLOCK.get(), 9, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.EMPTY_RUNE.get(), Items.EMERALD, 5, 1);
        // simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_LOG.get(), 4, 1);
        // simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_WOOD.get(), 4, 1);
        // simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_WOOD.get(), 4, 1);
        // simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_LOG.get(), 4, 1);
        simpleShapelessCraftingOne(pWriter, ModItems.DICE.get(), Blocks.WHITE_CONCRETE, 16, 1);

        simpleShapelessCraftingTwo(pWriter, ModItems.SLIMY_INGOT.get(), 1, Items.SLIME_BALL, 1, ModItems.HARDENED_INGOT.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.XP_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, Items.GOLD_INGOT, 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.MONEY_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, Items.COPPER_INGOT, 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.TREE_GROWER.get(), 1, Items.FLOWER_POT, 1, Blocks.DIRT, 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.LITTLE_HUMID_POTION.get(), 2, ModItems.HUMID_POTION.get(), 1, ModItems.EMPTY_LITTLE_FLASK.get(), 2);
        simpleShapelessCraftingTwo(pWriter, ModItems.HUMID_POTION.get(), 1, ModItems.LITTLE_HUMID_POTION.get(), 1, ModItems.EMPTY_BIG_FLASK.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.RESOURCE_DIRT.get(), 1, ModItems.HUMID_POTION.get(), 1,ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.VANILLA_ICE_CREAM.get(), 1, ModItems.CONE.get(), 1, ModItems.VANILLA_SCOOP.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.CHOCOLATE_ICE_CREAM.get(), 1, ModItems.CONE.get(), 1, ModItems.CHOCOLATE_SCOOP.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.STRAWBERRY_ICE_CREAM.get(), 1, ModItems.CONE.get(), 1, ModItems.STRAWBERRY_SCOOP.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.SWEET_BERRIES_ICE_CREAM.get(), 1, ModItems.CONE.get(), 1, ModItems.SWEET_BERRIES_SCOOP.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.CHOCOLATE_SORBET.get(), 1, ModItems.CONE.get(), 1, ModItems.SCOOP_OF_CHOCOLATE_SORBET.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.VANILLA_SORBET.get(), 1, ModItems.CONE.get(), 1, ModItems.SCOOP_OF_VANILLA_SORBET.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.STRAWBERRIES_SORBET.get(), 1, ModItems.CONE.get(), 1, ModItems.SCOOP_OF_STRAWBERRIES_SORBET.get(), 1);
        simpleShapelessCraftingTwo(pWriter, ModItems.SWEET_BERRIES_SORBET.get(), 1, ModItems.CONE.get(), 1, ModItems.SCOOP_OF_SWEET_BERRIES_SORBET.get(), 1);

        simpleShapelessCraftingThree(pWriter, ModItems.OAK_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, ModItems.TREE_GROWER.get(), 1, Items.OAK_SAPLING, 1);
        simpleShapelessCraftingThree(pWriter, ModItems.SPRUCE_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, ModItems.TREE_GROWER.get(), 1, Items.SPRUCE_SAPLING, 1);
        simpleShapelessCraftingThree(pWriter, ModItems.HOLY_BREAD.get(), 1, ModItems.YEAST.get(),3, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.BREAD, 2);
        simpleShapelessCraftingThree(pWriter,ModItems.DEVIL_BREAD.get(),3, ModItems.HOLY_BREAD.get(),1, Items.LAVA_BUCKET, 2, ModItems.YEAST.get(),6);

        // crafting log → wood
        // simpleWoodCrafting(pWriter, ModBlocks.DARK_WOOD.get(), ModBlocks.DARK_LOG.get());

        // catalyzers
        catalyzerCrafting(pWriter, ModItems.IRON_CATALYZER.get(), ModItems.CONTROL_PANEL.get(), ModItems.IRON_BALL.get());
        catalyzerCrafting(pWriter, ModItems.WIND_CHARGED_CATALYZER.get(), ModItems.IRON_CATALYZER.get(), ModItems.WIND_CHARGED_INGOT.get());
        catalyzerCrafting(pWriter, ModItems.GOLD_CATALYZER.get(), ModItems.IRON_CATALYZER.get(), ModItems.GOLD_BALL.get());
        catalyzerCrafting(pWriter, ModItems.DIAMOND_CATALYZER.get(), ModItems.GOLD_CATALYZER.get(), ModItems.DIAMOND_BALL.get());
        catalyzerCrafting(pWriter, ModItems.SUPER_CHARGED_CATALYZER.get(), ModItems.NETHERITE_CATALYZER.get(), ModItems.SUPER_CHARGED_BALL.get());

        // simple music disc → second item is what item you want it to be made with (center always = base disk tag)
        simpleDiscCrafting(pWriter, ModItems.DARK_SOUL_MUSIC_DISC.get(), Items.ECHO_SHARD);
        simpleDiscCrafting(pWriter, ModItems.END_OF_THE_START_MUSIC_DISC.get(), ModItems.HARDENED_INGOT.get());
        simpleDiscCrafting(pWriter, ModItems.BAR_BRAWL_MUSIC_DISC.get(), ModItems.SAPPHIRE.get());
        advancedDiscCrafting(pWriter, ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC.get(), Items.DIRT, Items.IRON_BOOTS);
        advancedDiscCrafting(pWriter, ModItems.BLOCK_BY_BLOCK_MUSIC_DISC.get(), Items.DIRT, Items.COBBLESTONE);
        simpleDiscCrafting(pWriter, ModItems.CUBIC_GROOVE_MUSIC_DISC.get(), Items.SCULK_CATALYST);
        advancedDiscCrafting(pWriter, ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC.get(), Items.OBSIDIAN, Items.ENDER_EYE);
        advancedDiscCrafting(pWriter, ModItems.IN_THE_BLOCK_MUSIC_DISC.get(), ModItems.PINE_CONE.get(), ModItems.WIND_CHARGED_INGOT.get());
        advancedDiscCrafting(pWriter, ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC.get(), ModItems.WET_DIRT.get(), ModItems.SAPPHIRE.get());
        advancedDiscCrafting(pWriter, ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC.get(), Items.GOLD_INGOT, ModItems.QUANTUM_CORE.get());
        simpleSmithing(pWriter, ModItems.BASE_OF_DISC.get(), ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC.get(), ModItems.CREATIVE_ESSENCE.get(), RecipeCategory.MISC, ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC.get());
        advancedDiscCrafting(pWriter, ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC.get(), Items.NETHERITE_INGOT, ModItems.STORM_FRAGMENT.get());
        advancedDiscCrafting(pWriter, ModItems.NETHER_NIGHTS_MUSIC_DISC.get(), Items.NETHERRACK, Items.NETHER_GOLD_ORE);
        advancedDiscCrafting(pWriter, ModItems.REDSTONE_PULSE_MUSIC_DISC.get(), Items.REDSTONE_BLOCK, Items.FIRE_CHARGE);
        advancedDiscCrafting(pWriter, ModItems.SERENE_ECHO_MUSIC_DISC.get(), Items.ECHO_SHARD, Items.DIAMOND);

        // simple smithing
        // eggs
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.WHEAT, RecipeCategory.MISC, Items.COW_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Blocks.WHITE_WOOL, RecipeCategory.MISC, Items.SHEEP_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.FEATHER, RecipeCategory.MISC, Items.CHICKEN_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.PORKCHOP, RecipeCategory.MISC, Items.PIG_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.PIG_SPAWN_EGG, Items.GOLD_INGOT, RecipeCategory.MISC, Items.PIGLIN_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.PIG_SPAWN_EGG, Items.GOLDEN_SWORD, RecipeCategory.MISC, Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.EMERALD, RecipeCategory.MISC, Items.VILLAGER_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.VILLAGER_SPAWN_EGG, Items.ROTTEN_FLESH, RecipeCategory.MISC, Items.ZOMBIE_VILLAGER_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.SALMON, RecipeCategory.MISC, Items.CAT_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.BONE, RecipeCategory.MISC, Items.SKELETON_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.SKELETON_SPAWN_EGG, Items.WITHER_SKELETON_SKULL, RecipeCategory.MISC, Items.WITHER_SKELETON_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.LEATHER_HORSE_ARMOR, RecipeCategory.MISC, Items.HORSE_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.HORSE_SPAWN_EGG, Items.ROTTEN_FLESH, RecipeCategory.MISC, Items.ZOMBIE_HORSE_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.HORSE_SPAWN_EGG, Items.BONE, RecipeCategory.MISC, Items.SKELETON_HORSE_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.PUFFERFISH, RecipeCategory.MISC, Items.PUFFERFISH_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.SPIDER_EYE, RecipeCategory.MISC, Items.SPIDER_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.SPIDER_SPAWN_EGG, Items.FERMENTED_SPIDER_EYE, RecipeCategory.MISC, Items.CAVE_SPIDER_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.INK_SAC, RecipeCategory.MISC, Items.SQUID_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.SQUID_SPAWN_EGG, Items.GLOW_INK_SAC, RecipeCategory.MISC, Items.GLOW_SQUID_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.BLAZE_ROD, RecipeCategory.MISC, Items.BLAZE_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.ENDER_EYE, RecipeCategory.MISC, Items.ENDERMAN_SPAWN_EGG);
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.ENDERMAN_SPAWN_EGG, Items.ENDER_PEARL, RecipeCategory.MISC, Items.ENDERMITE_SPAWN_EGG);

        simpleSmithing(pWriter, ModItems.LIGHTNING_UPGRADE.get(), ModItems.ALLOYED_SWORD.get(), ModItems.SUPER_CHARGED_INGOT.get(), RecipeCategory.COMBAT, ModItems.LIGHTNING_SWORD.get());
        simpleSmithing(pWriter, ModItems.LIGHTNING_UPGRADE.get(), ModItems.DIAMOND_BALL.get(), Items.DIAMOND, RecipeCategory.MISC, ModItems.SUPER_CHARGED_BALL.get());
        simpleSmithing(pWriter, Blocks.EMERALD_BLOCK, Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD, RecipeCategory.MISC, Items.BUDDING_AMETHYST);
        simpleSmithing(pWriter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIAMOND_CATALYZER.get(), Items.NETHERITE_INGOT, RecipeCategory.MISC, ModItems.NETHERITE_CATALYZER.get());
        simpleSmithing(pWriter, Items.EMERALD, OtherModItems.Ae2.CERTUS_QUARTZ_BLOCK.getAsRawItem(), OtherModItems.Ae2.CERTUS_QUARTZ_CRYSTAL.getAsRawItem(), RecipeCategory.MISC, OtherModItems.Ae2.FLAWLESS_BUDDING_CERTUS_QUARTZ.getAsRawItem());

        // stone cutting : use 'simpleStoneCutting' from our libraries.
        // use it with pWriter, recipeCategory, result, ingredient → you can add, at last parameter, the amount of results. Not needed : don't set to use 1
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.EMPTY_LITTLE_FLASK.get(), ModItems.EMPTY_BIG_FLASK.get(), 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.HALF_STICK.get(), Items.STICK, 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.QUARTER_STICK.get(), ModItems.HALF_STICK.get(), 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.QUARTER_STICK.get(), Items.STICK, 4);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, Items.COBBLED_DEEPSLATE, Items.COBBLESTONE, 1);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.SYRINGE_TUBE.get(), Items.GLASS, 8);

        simpleWoodCrafting(pWriter, Items.MOSS_BLOCK, ModItems.BIOMASS.get());

        // freezing
        freezingRecipe(pWriter, ModItems.CHOCOLATE_SCOOP.get(), ModItems.BUCKET_OF_LIQUID_CHOCOLATE_ICE_CREAM.get(), 8);
        freezingRecipe(pWriter, ModItems.STRAWBERRY_SCOOP.get(), ModItems.BUCKET_OF_LIQUID_STRAWBERRIES_ICE_CREAM.get(), 8);
        freezingRecipe(pWriter, ModItems.SWEET_BERRIES_SCOOP.get(), ModItems.BUCKET_OF_LIQUID_SWEET_BERRIES_ICE_CREAM.get(), 8);
        freezingRecipe(pWriter, ModItems.VANILLA_SCOOP.get(), ModItems.BUCKET_OF_LIQUID_VANILLA_ICE_CREAM.get(), 8);
        freezingRecipe(pWriter, ModItems.SCOOP_OF_CHOCOLATE_SORBET.get(), ModItems.CHOCOLATE_SNOWBALL.get(), 1);
        freezingRecipe(pWriter, ModItems.STRAWBERRIES_SORBET.get(), ModItems.STRAWBERRIES_SNOWBALL.get(), 1);
        freezingRecipe(pWriter, ModItems.SWEET_BERRIES_SORBET.get(), ModItems.SWEET_BERRIES_SNOWBALL.get(), 1);
        freezingRecipe(pWriter, ModItems.VANILLA_SORBET.get(), ModItems.VANILLA_SNOWBALL.get(), 1);

        // gem polishing
        gemPolishingRecipe(pWriter, Items.EMERALD, ItemTags.EMERALD_ORES, "has_emerald_ore", 5);
        gemPolishingRecipe(pWriter, ModItems.SAPPHIRE.get(), ModItems.RAW_SAPPHIRE.get(), 3);

        // atomical stabilizator
        atomicalStabilizatorRecipe(pWriter, ModItems.QUANTUMITE_CHUNK.get(), ModItems.QUANTUMITE_CHUNK.get(), Items.GHAST_TEAR, ModItems.QUANTUMITE_INGOT.get(), 1);

        // incubator
        //basique
        incubationRecipe(pWriter, Items.IRON_INGOT, ModItems.GOLD_CATALYZER.get(), ModItems.WIND_CHARGED_INGOT.get(), 1);
        // simple
        incubationRecipe(pWriter, ModItems.ROTTEN_LEATHER.get(), ModItems.IRON_CATALYZER.get(), Items.LEATHER, 1);
        // supercharged
        incubationRecipe(pWriter, Items.NETHERITE_INGOT, ModItems.SUPER_CHARGED_CATALYZER.get(), ModItems.SUPER_CHARGED_INGOT.get(), 1);
        incubationRecipe(pWriter, ModItems.STABILIZED_QUANTUM_CORE.get(), ModItems.SUPER_CHARGED_CATALYZER.get(), ModItems.SUPER_CHARGED_INGOT.get(), 5, "super_charged_ingot_bis");
        // wind charged
        incubationRecipe(pWriter, Items.SAND, ModItems.WIND_CHARGED_CATALYZER.get(), Items.DIRT, 1);

        // mysterious magic
        mysteriousMagicRecipe(pWriter, Items.ENDER_PEARL, 16, ModItems.STORM_FRAGMENT.get(), 4, ModItems.STORM_FRAGMENT.get(), 4, Items.GLOWSTONE_DUST, 32, ModItems.QUANTUM_DUST.get(), 1, OtherModItems.Ae2.SINGULARITY.getAsRawItem(), 16, 500);

        // inscibe
        inscriberRecipe(pWriter, ModTags.Items.QUANTUMITE_INGOTS, ModItems.INSCRIBER_QUANTUM_PRESS.get(), ModItems.PRINTED_QUANTUM_CIRCUIT.get(), 1, InscriberProcessType.INSCRIBE);
        inscriberRecipe(pWriter, Items.IRON_BLOCK, ModItems.INSCRIBER_QUANTUM_PRESS.get(), ModItems.INSCRIBER_QUANTUM_PRESS.get(), 1, InscriberProcessType.INSCRIBE);
        inscriberRecipe(pWriter, OtherModItems.Ae2.FLUIX_DUST.getAsRawItem(), ModItems.PRINTED_QUANTUM_CIRCUIT.get(), OtherModItems.Ae2.PRINTED_SILICON.getAsRawItem(), ModItems.QUANTUM_PROCESSOR.get(), 1, InscriberProcessType.PRESS);
        inscriberRecipe(pWriter, Items.QUARTZ, ModItems.QUARTZ_SHARD.get(), 1, InscriberProcessType.INSCRIBE);

        // create
        // compacting
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.SNOWBALL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addFluid("create:chocolate", 100).build())
                .addResult(JsonBuilder.json().addItem(ModItems.CHOCOLATE_SNOWBALL.get()).build())
                .unlocks(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(pWriter, "chocolate_snowball");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.DIRT).build())
                .addIngredient(JsonBuilder.json().addItem(Items.DIRT).build())
                .addIngredient(JsonBuilder.json().addItem(Items.DIRT).build())
                .addIngredient(JsonBuilder.json().addItem(Items.DIRT).build())
                .addResult(JsonBuilder.json().addItem(ModBlocks.COMPRESSED_DIRT.get()).build())
                .unlocks(getHasName(Items.DIRT), has(Items.DIRT))
                .save(pWriter, "compressed_dirt");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.SNOWBALL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(ModItems.VANILLA_EXTRACT.get()).build())
                .addResult(JsonBuilder.json().addItem(ModItems.VANILLA_SNOWBALL.get()).build())
                .unlocks(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(pWriter, "vanilla_snowball");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.SNOWBALL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(ModItems.STRAWBERRY.get()).build())
                .addResult(JsonBuilder.json().addItem(ModItems.STRAWBERRIES_SNOWBALL.get()).build())
                .unlocks(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(pWriter, "strawberries_snowball");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.SNOWBALL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SUGAR).build())
                .addIngredient(JsonBuilder.json().addItem(Items.SWEET_BERRIES).build())
                .addResult(JsonBuilder.json().addItem(ModItems.SWEET_BERRIES_SNOWBALL.get()).build())
                .unlocks(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(pWriter, "sweet_berries_snowball");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.STONE).build())
                .addIngredient(JsonBuilder.json().addItem(OtherModItems.Ae2.FLUIX_CRYSTAL.getAsRawItem()).build())
                .addIngredient(JsonBuilder.json().addTag(ModTags.Items.OBSIDIAN_DUSTS).build())
                .addIngredient(JsonBuilder.json().addTag(ItemTags.SAND).build())
                .addResult(JsonBuilder.json().addItem(Items.END_STONE).build())
                .unlocks(getHasName(Items.STONE), has(Items.STONE))
                .save(pWriter, "endstone");
        CreateCompactingRecipeBuilder.createCompacting()
                .addIngredient(JsonBuilder.json().addItem(Items.NETHERRACK).build())
                .addIngredient(JsonBuilder.json().addItem(Items.NETHERRACK).build())
                .addIngredient(JsonBuilder.json().addItem(Items.NETHERRACK).build())
                .addIngredient(JsonBuilder.json().addItem(Items.NETHERRACK).build())
                .addResult(JsonBuilder.json().addItem(Items.MAGMA_BLOCK).build())
                .unlocks(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(pWriter, "magma_block");
        CreateCompactingRecipeBuilder.createCompacting()
                .addHeatCondition(HeatCondition.SUPERHEATED)
                .addIngredient(JsonBuilder.json().addItem(Items.CHARCOAL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.CHARCOAL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.CHARCOAL).build())
                .addIngredient(JsonBuilder.json().addItem(Items.CHARCOAL).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:lava", 750).build())
                .addResult(JsonBuilder.json().addItem(Items.COAL).build())
                .unlocks(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
                .save(pWriter, "coal_compacting_extreme");
        CreateCompactingRecipeBuilder.createCompacting()
                .addHeatCondition(HeatCondition.SUPERHEATED)
                .addIngredient(JsonBuilder.json().addItem(ModItems.BIOMASS.get()).build())
                .addIngredient(JsonBuilder.json().addItem(ModItems.BIOMASS.get()).build())
                .addIngredient(JsonBuilder.json().addItem(ModItems.BIOMASS.get()).build())
                .addResult(JsonBuilder.json().addItem(ModItems.BLUE_BIOMASS.get()).build())
                .unlocks(getHasName(ModItems.BIOMASS.get()), has(ModItems.BIOMASS.get()))
                .save(pWriter, "blue_compacting");

        // mixing
        CreateMixingRecipeBuilder.mix()
                .addIngredient(JsonBuilder.json().addTag(ModTags.Items.FUELS).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:water", 1000).build())
                .addResult(JsonBuilder.json().addItem(Items.KELP).build())
                .unlocks("has_fuels", has(ModTags.Items.FUELS))
                .save(pWriter, "kelp_mixing");
        CreateMixingRecipeBuilder.mix()
                .addIngredient(JsonBuilder.json().addItem(OtherModItems.Ae2.ENDER_DUST.getAsRawItem()).addCount(4).build())
                .addIngredient(JsonBuilder.json().addItem(Items.AMETHYST_SHARD).build())
                .addResult(JsonBuilder.json().addItem(Items.ENDER_PEARL).build())
                .unlocks(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pWriter, "ender_pearl_mixing");
        CreateMixingRecipeBuilder.mix()
                .addIngredient(JsonBuilder.json().addItem(Items.SAND).build())
                .addIngredient(JsonBuilder.json().addItem(Items.CLAY_BALL).build())
                .addResult(JsonBuilder.json().addItem(Items.RED_SAND).build())
                .unlocks(getHasName(Items.SAND), has(Items.SAND))
                .save(pWriter, "red_sand_mixing");
        CreateMixingRecipeBuilder.mix()
                .addIngredient(JsonBuilder.json().addItem(Items.CLAY_BALL).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:water", 500).build())
                .addResult(JsonBuilder.json().addItem(ModItems.RED_CLAY_BALL.get()).addCount(2).build())
                .addResult(JsonBuilder.json().addItem(ModItems.RED_CLAY_BALL.get()).addChance(0.25f).build())
                .unlocks(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL))
                .save(pWriter, "red_clay_ball_mixing");

        JsonObject temporary = JsonBuilder.json().addItem(ModItems.MAGIC_SHARD.get()).build(); // TODO: use new impl
        temporary.add("nbt", RecipeNbtBuilder.getNbt().addNbt("Damage", 100).getPropertiesJson());
        CreateMixingRecipeBuilder.mix()
                .addIngredient(JsonBuilder.json().addItem(Items.AMETHYST_SHARD).build())
                .addIngredient(JsonBuilder.json().addItem(Items.ECHO_SHARD).build())
                .addResult(temporary)
                .unlocks(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)) //
                .save(pWriter, "magic_shard_mixing");
        CreateMixingRecipeBuilder.mix(HeatCondition.SUPERHEATED)
                .addIngredient(JsonBuilder.json().addTag(ModTags.Items.ROTTEN_MEATS).build())
                .addIngredient(JsonBuilder.json().addItem(Items.LAPIS_LAZULI).addCount(3).build())
                .addIngredient(JsonBuilder.json().addItem(Items.BLAZE_POWDER).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:water", 1000).build())
                .addResult(JsonBuilder.json().addFluid("create_enchantment_industry:experience", 5).build())
                .unlocks(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
                .save(pWriter, "experience_mixing");
        CreateMixingRecipeBuilder.mix(HeatCondition.HEATED)
                .addIngredient(JsonBuilder.json().addItem(Items.CALCITE).build())
                .addIngredient(JsonBuilder.json().addItem(Items.CLAY_BALL).addCount(2).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:water", 250).build())
                .addResult(JsonBuilder.json().addItem(Items.BONE).build())
                .addResult(JsonBuilder.json().addItem(Items.BONE_MEAL).addChance(0.2f).build())
                .unlocks(getHasName(Items.CALCITE), has(Items.CALCITE))
                .save(pWriter, "bone_mixing");
        CreateMixingRecipeBuilder.mix(HeatCondition.HEATED)
                .addIngredient(JsonBuilder.json().addItem(ModItems.YEAST.get()).build())
                .addIngredient(JsonBuilder.json().addItem(OtherModItems.Create.DOUGH.getAsRawItem()).build())
                .addResult(JsonBuilder.json().addItem(ModItems.CONE.get()).build())
                .unlocks(getHasName(OtherModItems.Create.DOUGH.getAsRawItem()), has(OtherModItems.Create.DOUGH.getAsRawItem()))
                .save(pWriter, "cone_mixing");
        CreateMixingRecipeBuilder.mix(HeatCondition.SUPERHEATED)
                .addIngredient(JsonBuilder.json().addItem(Items.SAND).addCount(3).build())
                .addIngredient(JsonBuilder.json().addItem(Items.COBBLESTONE).build())
                .addIngredient(JsonBuilder.json().addItem(OtherModItems.Ae2.FLUIX_CRYSTAL.getAsRawItem()).addCount(2).build())
                .addIngredient(JsonBuilder.json().addTag(ModTags.Items.OBSIDIAN_DUSTS).build())
                .addResult(JsonBuilder.json().addItem(OtherModItems.Ae2.ENDER_DUST.getAsRawItem()).build())
                .unlocks(getHasName(OtherModItems.Ae2.FLUIX_CRYSTAL.getAsRawItem()), has(OtherModItems.Ae2.FLUIX_CRYSTAL.getAsRawItem()))
                .save(pWriter, "ender_dust_mixing");
        CreateMixingRecipeBuilder.mix(HeatCondition.HEATED)
                .addIngredient(JsonBuilder.json().addItem(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()).build())
                .addIngredient(JsonBuilder.json().addItem(Items.BLAZE_POWDER).build())
                .addIngredient(JsonBuilder.json().addFluid("minecraft:lava", 150).build())
                .addResult(JsonBuilder.json().addItem(Items.REDSTONE).build())
                .unlocks(getHasName(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()), has(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()))
                .save(pWriter, "redstone_mixing");

        // crate filling
        fillingRecipe(pWriter, ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get(), "create:chocolate", 100, ModItems.BUCKET_OF_LIQUID_CHOCOLATE_ICE_CREAM.get());
        fillingRecipe(pWriter, Items.IRON_NUGGET, "create_enchantment_industry:experience", 3, OtherModItems.Create.XP_NUGGET.getAsRawItem());
        fillingRecipe(pWriter, OtherModItems.Create.ZINC.getAsRawItem(), "minecraft:water", 300, ModItems.OXIDIZED_ZINC.get());
        fillingRecipe(pWriter, ModItems.PIECE_OF_DIRT.get(), "minecraft:water", 275, ModItems.BIOMASS.get());
        fillingRecipe(pWriter, ModItems.PIECE_OF_DIRT.get(), "minecraft:water", 15, ModItems.WET_DIRT.get());

        // create pressing
        pressingRecipe(pWriter, Items.BAKED_POTATO, ModItems.EXPLODED_POTATO.get());
        pressingRecipe(pWriter, ModItems.OXIDIZED_ZINC.get(), OtherModItems.Tfmg.NICKEL_INGOT.getAsRawItem());

        // create haunting
        hauntingRecipe(pWriter, Items.IRON_INGOT, OtherModItems.Tfmg.LEAD_INGOT.getAsRawItem());
        hauntingRecipe(pWriter, ModItems.BIOMASS.get(), ModItems.DARKENED_BIOMASS.get());

        // create sequence
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(Items.GLASS_BOTTLE).build(), JsonBuilder.json().addItem(Items.INK_SAC).build(),
                        JsonBuilder.json().addItem(ModItems.INK_BOTTLE.get()).build(), 1)
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.INK_BOTTLE.get(), JsonBuilder.json().addFluid("minecraft:water", 150).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.INK_BOTTLE.get(),
                        JsonBuilder.json().addItem(ModItems.DARKENED_BIOMASS.get()).build()))
                .unlocks(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                .save(pWriter, "ink_sac_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(ModItems.QUANTUMITE_INGOT.get()).build(),
                        JsonBuilder.json().addItem(ModItems.QUANTUMITE_SHEET.get()).build(),
                        JsonBuilder.json().addItem(ModItems.HALF_QUANTUMITE_SHEET.get()).build(), 1)
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.HALF_QUANTUMITE_SHEET.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.HALF_QUANTUMITE_SHEET.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.HALF_QUANTUMITE_SHEET.get()))
                .unlocks(getHasName(ModItems.QUANTUMITE_INGOT.get()), has(ModItems.QUANTUMITE_INGOT.get()))
                .save(pWriter, "quantumite_sheet_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()).build(),
                        JsonBuilder.json().addItem(Items.BLAZE_POWDER).build(),
                        JsonBuilder.json().addItem(ModItems.UNFINISHED_BLAZE_POWDER.get()).build(), 4)
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNFINISHED_BLAZE_POWDER.get(), JsonBuilder.json().addFluid("minecraft:lava", 150).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_BLAZE_POWDER.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_BLAZE_POWDER.get()))
                .unlocks(getHasName(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()), has(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()))
                .save(pWriter, "blaze_powder_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(Items.DIAMOND).build(),
                        JsonBuilder.json().addItem(ModItems.STORM_FRAGMENT.get()).build(),
                        JsonBuilder.json().addItem(ModItems.UNFINISHED_STORM_FRAGMENT.get()).build(), 4)
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNFINISHED_STORM_FRAGMENT.get(), JsonBuilder.json().addFluid("create_enchantment_industry:hyper_experience", 150).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_STORM_FRAGMENT.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_STORM_FRAGMENT.get()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_STORM_FRAGMENT.get(),
                        JsonBuilder.json().addItem(Items.LAPIS_LAZULI).build()))
                .unlocks(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter, "storm_fragment_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(ModItems.QUANTUM_CORE.get()).build(),
                        JsonBuilder.json().addItem(ModItems.STABILIZED_QUANTUM_CORE.get()).build(),
                        JsonBuilder.json().addItem(ModItems.UNPROCESSED_QUANTUM_CORE.get()).build(), 4)
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addItem(OtherModItems.Ae2.SINGULARITY.getAsRawItem()).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addFluid("minecraft:water", 1000).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addFluid("minecraft:lava", 1000).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addItem(OtherModItems.Ae2.SINGULARITY.getAsRawItem()).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addItem(Items.OBSIDIAN).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get()))
                .unlocks(getHasName(ModItems.QUANTUM_CORE.get()), has(ModItems.QUANTUM_CORE.get()))
                .save(pWriter, "stabilized_quantum_core_recipe");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(ModItems.SMALL_CREATIVE_NUGGET.get()).build(),
                        JsonBuilder.json().addItem(ModItems.CREATIVE_NUGGET.get()).build(),
                        JsonBuilder.json().addItem(ModItems.UNPROCESSED_CREATIVE_NUGGET.get()).build(), 50)
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_CREATIVE_NUGGET.get(),
                        JsonBuilder.json().addItem(ModItems.CREATIVE_ESSENCE.get()).build()))
                .unlocks(getHasName(ModItems.SMALL_CREATIVE_NUGGET.get()), has(ModItems.SMALL_CREATIVE_NUGGET.get()))
                .save(pWriter, "creative_nugget_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addTag(ModTags.Items.FUELS).build(),
                        JsonBuilder.json().addItem(Items.SEA_PICKLE).build(),
                        JsonBuilder.json().addItem(ModItems.UNPROCESSED_SEA_PICKLE.get()).build(), 5)
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_SEA_PICKLE.get(),
                        JsonBuilder.json().addTag(ModTags.Items.GLOWING_UTILITY).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNPROCESSED_SEA_PICKLE.get(),
                        JsonBuilder.json().addFluid("minecraft:water", 250).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_SEA_PICKLE.get(),
                        JsonBuilder.json().addTag(ModTags.Items.GLOWING_UTILITY).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_SEA_PICKLE.get()))
                .unlocks("fuels", has(ModTags.Items.FUELS))
                .save(pWriter, "sea_pickle_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(OtherModItems.Ae2.SINGULARITY.getAsRawItem()).build(),
                        JsonBuilder.json().addItem(ModItems.QUANTUM_CORE.get()).build(),
                        JsonBuilder.json().addItem(ModItems.UNPROCESSED_QUANTUM_CORE.get()).build(), 15)
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addFluid("minecraft:lava", 500).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addItem(OtherModItems.Ae2.SINGULARITY.getAsRawItem()).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addFluid("minecraft:water", 1000).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_QUANTUM_CORE.get(),
                        JsonBuilder.json().addItem(ModItems.STORM_FRAGMENT.get()).build()))
                .unlocks(getHasName(OtherModItems.Ae2.SINGULARITY.getAsRawItem()), has(OtherModItems.Ae2.SINGULARITY.getAsRawItem()))
                .save(pWriter, "quantum_core_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(Items.BUCKET).build(),
                        JsonBuilder.json().addItem(ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get()).build(),
                        JsonBuilder.json().addItem(ModItems.MILK_BUCKET_WITH_EGG.get()).build(), 6)
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.MILK_BUCKET_WITH_EGG.get(),
                        JsonBuilder.json().addFluid("minecraft:milk", 50).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.MILK_BUCKET_WITH_EGG.get(),
                        JsonBuilder.json().addTag(Tags.Items.EGGS).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.MILK_BUCKET_WITH_EGG.get(),
                        JsonBuilder.json().addItem(Items.SUGAR).build()))
                .unlocks(getHasName(Items.BUCKET), has(Items.BUCKET))
                .save(pWriter, getItemName(ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get()) + "_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(ModItems.SAPPHIRE.get()).build(),
                        JsonBuilder.json().addItem(Items.EMERALD).build(),
                        JsonBuilder.json().addItem(ModItems.UNFINISHED_EMERALD.get()).build(), 3)
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_EMERALD.get(),
                        JsonBuilder.json().addItem(Items.LIME_DYE).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(ModItems.UNFINISHED_EMERALD.get(),
                        JsonBuilder.json().addFluid("create_enchantment_industry:experience", 15).build()))
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNFINISHED_EMERALD.get(),
                        JsonBuilder.json().addItem(Items.IRON_NUGGET).build()))
                .unlocks(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter, "emerald_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(Items.COBBLESTONE).build(),
                        JsonBuilder.json().addItem(Items.NETHERRACK).build(),
                        JsonBuilder.json().addItem(Items.COBBLESTONE).build(), 3)
                .addStep(CreateFillingRecipeProvider.getSequenceStep(Items.COBBLESTONE,
                        JsonBuilder.json().addFluid("minecraft:lava", 250).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(Items.COBBLESTONE,
                        JsonBuilder.json().addFluid("minecraft:lava", 250).build()))
                .addStep(CreateFillingRecipeProvider.getSequenceStep(Items.COBBLESTONE,
                        JsonBuilder.json().addFluid("minecraft:lava", 250).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(Items.COBBLESTONE))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(Items.COBBLESTONE))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(Items.COBBLESTONE))
                .unlocks(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .save(pWriter, "netherrack_sequence");
        CreateSequencedAssemblyRecipeBuilder.sequence(JsonBuilder.json().addItem(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()).build(),
                        JsonBuilder.json().addItem(Items.GLOWSTONE).build(),
                        JsonBuilder.json().addItem(ModItems.UNPROCESSED_DUST.get()).build(), 1)
                .addStep(CreateDeployingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_DUST.get(),
                        JsonBuilder.json().addItem(Items.BLAZE_POWDER).build()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_DUST.get()))
                .addStep(CreatePressingRecipeBuilder.getSequenceStep(ModItems.UNPROCESSED_DUST.get()))
                .unlocks(getHasName(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()), has(OtherModItems.Create.CINDER_FLOUR.getAsRawItem()))
                .save(pWriter, "glowstone_sequence");

        // create mech crafting
        CreateMechanicalCraftingBuilder.shaped(false, JsonBuilder.json().addItem(ModItems.FIRE_SWORD.get()).build())
                .pattern("  BD")
                .pattern(" BDB")
                .pattern("SSB ")
                .pattern("ES  ")
                .define('D', JsonBuilder.json().addItem(ModItems.FIRE_DIAMOND.get()).build())
                .define('S', JsonBuilder.json().addItem(ModItems.FIRE_STICK.get()).build())
                .define('B', JsonBuilder.json().addItem(Items.FLINT_AND_STEEL).build())
                .define('E', JsonBuilder.json().addItem(Items.NETHERITE_SWORD).build())
                .unlocks(getHasName(ModItems.FIRE_DIAMOND.get()), has(ModItems.FIRE_DIAMOND.get()))
                .save(pWriter, "fire_sword");
        CreateMechanicalCraftingBuilder.shaped(false, JsonBuilder.json().addItem(OtherModItems.Create.CREATIVE_FLUID_TANK.getAsRawItem()).build())
                .pattern("AAAAA")
                .pattern("ABDBA")
                .pattern("ADCDA")
                .pattern("ABDBA")
                .pattern("AAAAA")
                .define('A', JsonBuilder.json().addItem(ModBlocks.CREATIVE_BLOCK.get()).build())
                .define('B', JsonBuilder.json().addItem(ModItems.CREATIVE_INGOT.get()).build())
                .define('D', JsonBuilder.json().addItem(ModItems.HARDENED_INGOT.get()).build())
                .define('C', JsonBuilder.json().addItem(OtherModItems.Create.FLUID_TANK.getAsRawItem()).build())
                .unlocks(getHasName(OtherModItems.Create.FLUID_TANK.getAsRawItem()), has(OtherModItems.Create.FLUID_TANK.getAsRawItem()))
                .save(pWriter, "creative/fluid_tank");
        CreateMechanicalCraftingBuilder.shaped(false, JsonBuilder.json().addItem(OtherModItems.Create.CREATIVE_MOTOR.getAsRawItem()).build())
                .pattern("AAAAA")
                .pattern("ABBBD")
                .pattern("ADCCC")
                .pattern("ABBBD")
                .pattern("AAAAA")
                .define('A', JsonBuilder.json().addItem(ModBlocks.CREATIVE_BLOCK.get()).build())
                .define('B', JsonBuilder.json().addItem(ModItems.CREATIVE_INGOT.get()).build())
                .define('D', JsonBuilder.json().addItem(ModItems.HARDENED_INGOT.get()).build())
                .define('C', JsonBuilder.json().addItem(OtherModItems.Create.SHAFT.getAsRawItem()).build())
                .unlocks(getHasName(OtherModItems.Create.SHAFT.getAsRawItem()), has(OtherModItems.Create.SHAFT.getAsRawItem()))
                .save(pWriter, "creative/motor");

        // create milling
        CreateMillingRecipeBuilder.milling(JsonBuilder.json().addItem(Items.MOSSY_COBBLESTONE).build(), 50)
                .addResult(JsonBuilder.json().addItem(Items.COBBLESTONE).addChance(0.5f).build())
                .addResult(JsonBuilder.json().addItem(Items.ROTTEN_FLESH).addChance(0.2f).build())
                .addResult(JsonBuilder.json().addItem(Items.ROTTEN_FLESH).addChance(0.05f).build())
                .addResult(JsonBuilder.json().addItem(Items.VINE).addChance(0.25f).build())
                .unlocks(getHasName(Items.MOSSY_COBBLESTONE), has(Items.MOSSY_COBBLESTONE))
                .save(pWriter, "mossy_cobblestone_milling");
        millingRecipe(pWriter, ModBlocks.RED_CLAY.get(), ModItems.RED_CLAY_BALL.get(), 50, 3);

        // create crushing
        crushingRecipe(pWriter, ModItems.DARKENED_BIOMASS.get(), Items.BROWN_MUSHROOM, 500);
        crushingRecipe(pWriter, ModItems.BLUE_BIOMASS.get(), Items.LAPIS_LAZULI, 500);
        crushingRecipe(pWriter, ModItems.RED_BIOMASS.get(), Items.RED_MUSHROOM, Items.REDSTONE, 500, 1f, 0.2f);
        crushingRecipe(pWriter, ModItems.STABILIZED_QUANTUM_CORE.get(), ModItems.QUANTUM_DUST.get(), 500, 0.2f);
        crushingRecipe(pWriter, ModItems.QUANTUM_CORE.get(), ModItems.QUANTUM_DUST.get(), 500, 0.2f);
        crushingRecipe(pWriter, Items.RED_TERRACOTTA, Items.RED_SAND, 500, 3);
        crushingRecipe(pWriter, ModBlocks.RED_CLAY.get(), ModItems.RED_CLAY_BALL.get(), 500, 3);
        CreateCrushingRecipeBuilder.crush(50)
                .addIngredient(JsonBuilder.json().addItem(Items.SOUL_SOIL).build())
                .addResult(JsonBuilder.json().addItem(Items.BONE).addChance(0.5f).build())
                .addResult(JsonBuilder.json().addItem(Items.BONE_MEAL).addChance(0.5f).build())
                .addResult(JsonBuilder.json().addItem(Items.SOUL_SAND).addChance(0.8f).build())
                .unlocks(getHasName(Items.SOUL_SOIL), has(Items.SOUL_SOIL))
                .save(pWriter, "soul_soil_crushing");
        crushingRecipe(pWriter, ModBlocks.CREATIVE_RESIDUE_BLOCK.get(), ModItems.SMALL_CREATIVE_NUGGET.get(), 0.02f, 500);
        crushingRecipe(pWriter, ModBlocks.COMPRESSED_DIRT.get(), ModItems.PIECE_OF_DIRT.get(), 15);
        CreateCrushingRecipeBuilder.crush(5)
                .addIngredient(JsonBuilder.json().addItem(ModBlocks.RESOURCE_DIRT_BLOCK.get()).build())
                .addResult(JsonBuilder.json().addItem(ModItems.COPPER_BALL.get()).addCount(5).build())
                .addResult(JsonBuilder.json().addItem(ModItems.COPPER_BALL.get()).addCount(3).addChance(0.325f).build())
                .addResult(JsonBuilder.json().addItem(ModItems.IRON_BALL.get()).addCount(3).build())
                .addResult(JsonBuilder.json().addItem(ModItems.IRON_BALL.get()).addCount(2).addChance(0.3f).build())
                .addResult(JsonBuilder.json().addItem(ModItems.GOLD_BALL.get()).addCount(5).addChance(0.25f).build())
                .addResult(JsonBuilder.json().addItem(ModItems.DIAMOND_BALL.get()).addChance(0.125f).build())
                .addResult(JsonBuilder.json().addItem(ModItems.SAPPHIRE_BALL.get()).addCount(2).addChance(0.13f).build())
                .addResult(JsonBuilder.json().addItem(ModItems.CREATIVE_ESSENCE.get()).addChance(0.02f).build())
                .unlocks(getHasName(ModBlocks.RESOURCE_DIRT_BLOCK.get()), has(ModBlocks.RESOURCE_DIRT_BLOCK.get()))
                .save(pWriter, "resource_dirt_crushing");

        // deploying
        deployingRecipe(pWriter, OtherModItems.Tfmg.CIRCUIT_BOARD.getAsRawItem(), ModItems.QUANTUM_PROCESSOR.get(), ModItems.QUANTUM_ASSISTED_CIRCUIT_BOARD.get());
        deployingRecipe(pWriter, ModItems.DIRTY_HUMID_POTION.get(), Items.AMETHYST_SHARD, ModItems.HUMID_POTION.get());
        deployingRecipe(pWriter, OtherModItems.Tfmg.CIRCUIT_BOARD.getAsRawItem(), OtherModItems.Ae2.LOGIC_PROCESSOR.getAsRawItem(), ModItems.LOGIC_ASSISTED_CIRCUIT_BOARD.get());
        deployingRecipe(pWriter, OtherModItems.Tfmg.CIRCUIT_BOARD.getAsRawItem(), OtherModItems.Ae2.ENGINEERING_PROCESSOR.getAsRawItem(), ModItems.ENGINEERING_ASSISTED_CIRCUIT_BOARD.get());
        deployingRecipe(pWriter, ModItems.EMPTY_BIG_FLASK.get(), ModItems.WET_DIRT.get(), ModItems.DIRTY_HUMID_POTION.get());
        deployingRecipe(pWriter, OtherModItems.Tfmg.CIRCUIT_BOARD.getAsRawItem(), OtherModItems.Ae2.CALCULATION_PROCESSOR.getAsRawItem(), ModItems.CALCULATION_ASSISTED_CIRCUIT_BOARD.get());
        deployingRecipe(pWriter, ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get(), ModItems.VANILLA_EXTRACT.get(), ModItems.BUCKET_OF_LIQUID_VANILLA_ICE_CREAM.get());
        deployingRecipe(pWriter, ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get(), Items.SWEET_BERRIES, ModItems.BUCKET_OF_LIQUID_SWEET_BERRIES_ICE_CREAM.get());
        deployingRecipe(pWriter, ModItems.BUCKET_OF_LIQUID_ICE_CREAM.get(), ModItems.STRAWBERRY.get(), ModItems.BUCKET_OF_LIQUID_STRAWBERRIES_ICE_CREAM.get());
        deployingRecipe(pWriter, OtherModItems.Tfmg.CIRCUIT_BOARD.getAsRawItem(), OtherModItems.MegaCells.ACCUMULATION_PROCESSOR.getAsRawItem(), ModItems.ACCUMULATION_ASSISTED_CIRCUIT_BOARD.get());
        deployingRecipe(pWriter, ModItems.DARKENED_BIOMASS.get(), Items.REDSTONE, ModItems.RED_BIOMASS.get());

        // chest crafting
        // simpleBoatCrafting(pWriter, ModItems.DARK_BOAT.get(), ModBlocks.DARK_PLANKS.get(), false);
        // simpleBoatCrafting(pWriter, ModItems.DARK_CHEST_BOAT.get(), ModBlocks.DARK_PLANKS.get(), true);

        // head crafting
        simpleHeadCrafting(pWriter, Items.SKELETON_SKULL, Items.BONE, Items.BONE);
        simpleHeadCrafting(pWriter, Items.WITHER_SKELETON_SKULL, Items.BONE, Items.WITHER_ROSE);
        simpleHeadCrafting(pWriter, Items.CREEPER_HEAD, Items.GUNPOWDER, Items.GUNPOWDER);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ZOMBIE_HEAD)
                .pattern("IUI")
                .pattern("ISI")
                .pattern("HHH")
                .define('I', ModTags.Items.ROTTEN_MEATS)
                .define('U', ModItems.ROTTEN_LEATHER.get())
                .define('S', ModItems.SKULLKERY_TOOL.get())
                .define('H', Tags.Items.HEADS)
                .unlockedBy(getHasName(ModItems.ROTTEN_LEATHER.get()), has(ModItems.ROTTEN_LEATHER.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + Items.ZOMBIE_HEAD + "_head_crafting");

        // signs & hanging signs
        // signCrafting(pWriter, ModBlocks.DARK_SIGN.get(), ModBlocks.DARK_PLANKS.get());
        // signHangingCrafting(pWriter, ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.STRIPPED_DARK_LOG.get());

        // no pattern
        // wind stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIND_STICK.get())
                .pattern("P")
                .pattern("S")
                .define('P', ModItems.WIND_CHARGED_INGOT.get())
                .define('S',Items.STICK)
                .unlockedBy(getHasName(ModItems.WIND_CHARGED_INGOT.get()), has(ModItems.WIND_CHARGED_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATOMICAL_STABILIZATOR.get())
                .pattern("IEI")
                .pattern("CAC")
                .pattern("III")
                .define('I', Blocks.IRON_BLOCK)
                .define('E', ModItems.ENGINEERING_ASSISTED_CIRCUIT_BOARD.get())
                .define('C', OtherModItems.Ae2.CALCULATION_PROCESSOR.getAsIngredient())
                .define('A', OtherModItems.MegaCells.ACCUMULATION_PROCESSOR.getAsIngredient())
                .unlockedBy(getHasName(ModItems.ENGINEERING_ASSISTED_CIRCUIT_BOARD.get()), has(ModItems.ENGINEERING_ASSISTED_CIRCUIT_BOARD.get()))
                .save(pWriter);

        // red clay
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RED_CLAY.get())
                .pattern("CC")
                .pattern("CC")
                .define('C', ModItems.RED_CLAY_BALL.get())
                .unlockedBy(getHasName(ModItems.RED_CLAY_BALL.get()), has(ModItems.RED_CLAY_BALL.get()))
                .save(pWriter);

        // slimy stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SLIMY_STICK.get(), 4)
                .pattern("S")
                .pattern("S")
                .define('S', ModItems.SLIMY_INGOT.get())
                .unlockedBy(getHasName(ModItems.SLIMY_INGOT.get()), has(ModItems.SLIMY_INGOT.get()))
                .save(pWriter);

        // freezer
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FREEZER.get())
                .pattern("GIG")
                .pattern("GCG")
                .pattern("QQQ")
                .define('C', ModItems.CONTROL_PANEL.get())
                .define('Q', Items.QUARTZ_BLOCK)
                .define('I', Items.IRON_INGOT)
                .define('G', Items.ICE)
                .unlockedBy(getHasName(ModItems.CONTROL_PANEL.get()), has(ModItems.CONTROL_PANEL.get()))
                .save(pWriter);

        // mysterious altar
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MYSTERIOUS_ALTAR.get())
                .pattern("ALA")
                .pattern("DRD")
                .pattern("OOO")
                .define('A', Items.AMETHYST_SHARD)
                .define('L', Items.LIGHTNING_ROD)
                .define('D', Items.DIAMOND)
                .define('O', Items.OBSIDIAN)
                .define('R', ModTags.Items.ROTTEN_MEATS)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pWriter);

        // Quantum stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.QUANTUM_STAFF.get())
                .pattern(" Q ")
                .pattern("SCS")
                .pattern("SCS")
                .define('Q', ModItems.STABILIZED_QUANTUM_CORE.get())
                .define('S', ModItems.SAPPHIRE.get())
                .define('C', Items.COPPER_INGOT)
                .unlockedBy(getHasName(ModItems.STABILIZED_QUANTUM_CORE.get()), has(ModItems.STABILIZED_QUANTUM_CORE.get()))
                .save(pWriter);

        // sapphire staff
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_STAFF.get())
                .pattern("G")
                .pattern("S")
                .pattern("S")
                .define('G', ModItems.STORM_FRAGMENT.get())
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);

        // base de dique
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_OF_DISC.get())
                .pattern(" H ")
                .pattern("HIH")
                .pattern(" H ")
                .define('H', ModItems.HARDENED_INGOT.get())
                .define('I', Tags.Items.INGOTS)
                .unlockedBy(getHasName(ModItems.HARDENED_INGOT.get()), has(ModItems.HARDENED_INGOT.get()))
                .save(pWriter);

        // incubator
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INCUBATOR.get())
                .pattern("III")
                .pattern("IDC")
                .pattern("III")
                .define('I', Items.IRON_BLOCK)
                .define('D', Items.DIAMOND)
                .define('C', ModItems.CONTROL_PANEL.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        // levure
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.YEAST.get())
                .pattern("PPP")
                .pattern("PSP")
                .pattern("PPP")
                .define('P', Items.PAPER)
                .define('S',Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pWriter);

        // metal detector
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" S ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        // gem polishing station
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEM_POLISHING_STATION.get())
                .pattern("QQQ")
                .pattern("BCB")
                .pattern("QQQ")
                .define('Q', Blocks.QUARTZ_BLOCK)
                .define('C', ModItems.CONTROL_PANEL.get())
                .define('B', Blocks.BLACKSTONE)
                .unlockedBy(getHasName(Blocks.BLACKSTONE), has(Blocks.BLACKSTONE))
                .save(pWriter);

        // rubis ore
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBIS_ORE.get())
                .pattern("DGD")
                .pattern("GSG")
                .pattern("DGD")
                .define('D', ModItems.DIAMOND_BALL.get())
                .define('G', ModItems.GOLD_BALL.get())
                .define('S', Blocks.STONE)
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .save(pWriter);

        // dirt
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.DIRT)
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" G ")
                .define('G', Blocks.GRAVEL)
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":gravel_crafting");

        // heads
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PLAYER_HEAD)
                .pattern("III")
                .pattern("IGI")
                .pattern("SVS")
                .define('I', ItemTags.PLANKS)
                .define('S', Items.DIAMOND)
                .define('G', Blocks.BLACK_CONCRETE)
                .define('V', ModItems.SKULLKERY_TOOL.get())
                .unlockedBy(getHasName(ModItems.SKULLKERY_TOOL.get()), has(ModItems.SKULLKERY_TOOL.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":player_head_crafting");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DRAGON_HEAD)
                .pattern("IFI")
                .pattern("JGJ")
                .pattern("SSS")
                .define('I', Items.DIAMOND)
                .define('J', Blocks.PURPLE_CONCRETE)
                .define('F', Items.DRAGON_BREATH)
                .define('S', Tags.Items.HEADS)
                .define('G', ModItems.SKULLKERY_TOOL.get())
                .unlockedBy(getHasName(ModItems.SKULLKERY_TOOL.get()), has(ModItems.SKULLKERY_TOOL.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":dragon_head_crafting");

        // controlled stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONTROLLED_STICK.get())
                .pattern(" P")
                .pattern("S ")
                .define('P', ModItems.CONTROL_PANEL.get())
                .define('S', ModItems.HALF_STICK.get())
                .unlockedBy(getHasName(ModItems.CONTROL_PANEL.get()), has(ModItems.CONTROL_PANEL.get()))
                .save(pWriter);

        // skullkery tool
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SKULLKERY_TOOL.get())
                .pattern("  P")
                .pattern(" S ")
                .pattern("C  ")
                .define('P', ModItems.QUARTER_STICK.get())
                .define('S', Tags.Items.HEADS)
                .define('C', ModItems.CONTROLLED_STICK.get())
                .unlockedBy(getHasName(ModItems.CONTROLLED_STICK.get()), has(ModItems.CONTROLLED_STICK.get()))
                .save(pWriter);

        // sound block craft
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOUND_BLOCK.get())
                .pattern("MMM")
                .pattern("MDM")
                .pattern("MJM")
                .define('M', Items.NOTE_BLOCK)
                .define('D', Items.DIAMOND)
                .define('J', Items.JUKEBOX)
                .unlockedBy(getHasName(Items.JUKEBOX), has(Items.JUKEBOX))
                .save(pWriter);

        // lightning upgrade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIGHTNING_UPGRADE.get())
                .pattern("DTD")
                .pattern("DND")
                .pattern("CCC")
                .define('C', Items.SCULK_CATALYST)
                .define('D', Items.DIAMOND_BLOCK)
                .define('N', Items.NETHERITE_INGOT)
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy(getHasName(Items.SCULK_CATALYST), has(Items.SCULK_CATALYST))
                .save(pWriter);

        // control panel craft
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONTROL_PANEL.get())
                .pattern(" B ")
                .pattern("BFB")
                .pattern(" B ")
                .define('B', ModItems.IRON_BALL.get())
                .define('F', Blocks.BLAST_FURNACE)
                .unlockedBy(getHasName(Blocks.BLAST_FURNACE), has(Blocks.BLAST_FURNACE))
                .save(pWriter);

        // empty big pot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPTY_BIG_FLASK.get())
                .pattern(" B ")
                .pattern("G G")
                .pattern("VVV")
                .define('B', Blocks.OAK_BUTTON)
                .define('G', Blocks.GLASS_PANE)
                .define('V', Blocks.GLASS)
                .unlockedBy(getHasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(pWriter);

        // duplicating lightning upgrade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIGHTNING_UPGRADE.get(), 2)
                .pattern("DID")
                .pattern("DGD")
                .pattern("DDD")
                .define('I', ModItems.LIGHTNING_UPGRADE.get())
                .define('D', Items.DIAMOND)
                .define('G', ModItems.SUPER_CHARGED_INGOT.get())
                .unlockedBy(getHasName(ModItems.LIGHTNING_UPGRADE.get()), has(ModItems.LIGHTNING_UPGRADE.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":lightning_upgrade_duplication");

        // egg base craft
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASE_EGG.get(), 4)
                .pattern("XIX")
                .pattern("IDI")
                .pattern("XIX")
                .define('D', Items.DIAMOND)
                .define('I', ModItems.CREATIVE_INGOT.get())
                .define('X', Items.EXPERIENCE_BOTTLE)
                .unlockedBy(getHasName(ModItems.CREATIVE_INGOT.get()), has(ModItems.CREATIVE_INGOT.get()))
                .save(pWriter);

        // rubinium sword
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBINIUM_SWORD.get())
                .pattern(" IX")
                .pattern("IDI")
                .pattern("BI ")
                .define('D', ModItems.FIRE_SWORD.get())
                .define('X', ModItems.RUBIS.get())
                .define('I', ModItems.RUBINIUM.get())
                .define('B', ModItems.FIRE_STICK.get())
                .unlockedBy(getHasName(ModItems.RUBINIUM.get()), has(ModItems.RUBINIUM.get()))
                .save(pWriter);

        // alloyed sword
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALLOYED_SWORD.get())
                .pattern("HVX")
                .pattern("IDS")
                .pattern("BIH")
                .define('D', ModItems.RUBINIUM_SWORD.get())
                .define('X', ModItems.DIAMOND_BALL.get())
                .define('I', Items.GOLDEN_SWORD)
                .define('H', ModItems.HARDENED_INGOT.get())
                .define('V', Items.DIAMOND_SWORD)
                .define('S', Items.IRON_SWORD)
                .define('B', ModItems.FIRE_STICK.get())
                .unlockedBy(getHasName(ModItems.RUBINIUM.get()), has(ModItems.RUBINIUM.get()))
                .save(pWriter);

        // fire diamond
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FIRE_DIAMOND.get())
                .pattern("BSB")
                .pattern("SDS")
                .pattern("BSB")
                .define('D', Items.DIAMOND)
                .define('S', Items.LAVA_BUCKET)
                .define('B', Items.FLINT_AND_STEEL)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        // spawner part
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPAWNER_PART.get(), 2)
                .pattern(" S ")
                .pattern("SDS")
                .pattern(" S ")
                .define('S', ModItems.HARDENED_INGOT.get())
                .define('D', Items.IRON_BARS)
                .unlockedBy(getHasName(ModItems.HARDENED_INGOT.get()), has(ModItems.HARDENED_INGOT.get()))
                .save(pWriter);

        // syringe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SYRINGE.get())
                .pattern("  N")
                .pattern(" T ")
                .pattern("I  ")
                .define('N', Items.IRON_NUGGET)
                .define('T', ModItems.SYRINGE_TUBE.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.SYRINGE_TUBE.get()), has(ModItems.SYRINGE_TUBE.get()))
                .save(pWriter);

        // spawner craft
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SPAWNER)
                .pattern("SSS")
                .pattern("SDS")
                .pattern("SSS")
                .define('S', ModItems.SPAWNER_PART.get())
                .define('D', ModItems.BASE_EGG.get())
                .unlockedBy(getHasName(ModItems.HARDENED_INGOT.get()), has(ModItems.HARDENED_INGOT.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":spawner_crafting");

        // fire stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FIRE_STICK.get())
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .define('S', ModItems.FIRE_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.FIRE_DIAMOND.get()), has(ModItems.FIRE_DIAMOND.get()))
                .save(pWriter);
    }

    /**
     * This creates a boat recipe.
     * @param isChestBoat if true, the recipe will have a chest in the middle.
     */
    protected static void simpleBoatCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike boatResult, ItemLike ingredient, boolean isChestBoat) {
        if(isChestBoat) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boatResult)
                    .pattern("PCP")
                    .pattern("PPP")
                    .define('P', ingredient)
                    .define('C', Blocks.CHEST)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(pFinishedRecipeConsumer);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boatResult)
                    .pattern("P P")
                    .pattern("PPP")
                    .define('P', ingredient)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(pFinishedRecipeConsumer);
        }
    }

    /**
     * this creates a recipe template for the mob heads.
     * @param pFinishedRecipeConsumer the consumer
     * @param headResult the result
     * @param ingredient1 the main ingredient
     * @param ingredient2 the secondary ingredient
     */
    protected static void simpleHeadCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike headResult, ItemLike ingredient1, ItemLike ingredient2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, headResult)
                .pattern("IUI")
                .pattern("ISI")
                .pattern("HHH")
                .define('I', ingredient1)
                .define('U', ingredient2)
                .define('S', ModItems.SKULLKERY_TOOL.get())
                .define('H', Tags.Items.HEADS)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + headResult + "_head_crafting");
    }

    /**
     * This creates a recipe for 9 items to one. Template for block like iron.
     */
    protected static void simpleBlockCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_nine_" + getItemName(ingredient));
    }

    /**
     * This is a template to craft a sign.
     */
    protected static void signCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike signResult, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, signResult, 3)
                .pattern("SSS")
                .pattern(" B ")
                .define('S', ingredient)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }
    /**
     * This recipe is a template for the hanging sign.
     */
    protected static void signHangingCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike hangingSignResult, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, hangingSignResult, 6)
                .pattern("C C")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ingredient)
                .define('C', Items.CHAIN)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * This is a template for a 4 to 1 recipe. Used for creating wood from logs
     */
    protected static void simpleWoodCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 3)
                .pattern("SS")
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * The template for simple discs. Using a discBase.
     * @param pFinishedRecipeConsumer the consumer
     * @param resultDisc the result
     * @param discIngredient the ingredient
     */
    protected static void simpleDiscCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item resultDisc, Item discIngredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultDisc)
                .pattern(" S ")
                .pattern("SBS")
                .pattern(" S ")
                .define('S', discIngredient)
                .define('B', ModTags.Items.BASEDISK)
                .unlockedBy(getHasName(discIngredient), has(discIngredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(resultDisc) + "_with_" + getItemName(discIngredient));
    }
    /**
     * The template for advanced discs. Using a discBase.
     * @param pFinishedRecipeConsumer the consumer
     * @param resultDisc the result
     * @param discIngredient the ingredient
     * @param discAddition the addition
     */
    protected static void advancedDiscCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item resultDisc, ItemLike discIngredient, ItemLike discAddition) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultDisc)
                .pattern("XSX")
                .pattern("SBS")
                .pattern("XSX")
                .define('S', discIngredient)
                .define('X', discAddition)
                .define('B', ModTags.Items.BASEDISK)
                .unlockedBy(getHasName(discIngredient), has(discIngredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(resultDisc) + "_with_" + getItemName(discIngredient));
    }

    /**
     * Template for stairs.
     */
    protected static void simpleStairsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * Helmet crafting
     */
    protected static void simpleHelmetCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * chestplate crafting
     */
    protected static void simpleChestplateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * leggings crafting
     */
    protected static void simpleLeggingsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * boots crafting
     */
    protected static void simpleBootsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * Recipe template for a sword
     */
    protected static void simpleSwordCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S")
                .pattern("S")
                .pattern("P")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a pickaxe
     */
    protected static void simplePickaxeCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SSS")
                .pattern(" P ")
                .pattern(" P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a shovel
     */
    protected static void simpleShovelCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("S")
                .pattern("P")
                .pattern("P")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for an axe
     */
    protected static void simpleAxesCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SS")
                .pattern("PS")
                .pattern("P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a hoe
     */
    protected static void simpleHoesCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SS")
                .pattern("P ")
                .pattern("P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The recipe template for the catalyzer
     * @param pFinishedRecipeConsumer the consumer
     * @param catalyzerResult the catalizer you want to craft
     * @param base the base of the catalizer
     * @param surround the item you add to the base
     */
    protected static void catalyzerCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike catalyzerResult, ItemLike base, ItemLike surround) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, catalyzerResult)
                .pattern(" B ")
                .pattern("BIB")
                .pattern(" B ")
                .define('I', base)
                .define('B', surround)
                .unlockedBy(getHasName(base), has(base))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * The slab template
     */
    protected static void simpleSlabCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The pressure plate template
     */
    protected static void simplePressurePlateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The shapeless of one item
     */
    protected static void simpleShapelessCraftingOne(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, Integer resultQuantity, Integer ingredientQuantity) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient, ingredientQuantity)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_one_" + getItemName(ingredient));
    }
    /**
     * The shapeless of two items
     */
    protected static void simpleShapelessCraftingTwo(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQ, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQ)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_two_base_" + getItemName(ingredient1));
    }
    /**
     * The shapeless of three items
     */
    protected static void simpleShapelessCraftingThree(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQuantity, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q, ItemLike ingredient3, Integer ingredient3Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .requires(ingredient3, ingredient3Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result)+"_from_shapeless_of_three_base_"+getItemName(ingredient1));
    }

    /**
     * The ore smelting recipe
     */
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    /**
     * The ore blasting
     */
    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    /**
     * The item smoking
     */
    protected static void itemSmoking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }
    /**
     * Ore cooking : it's the base of {@link #oreBlasting}, {@link #oreBlasting} and {@link #itemSmoking}.
     */
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    /**
     * The recipe for the freezer.
     */
    protected static void freezingRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, int resultAmount) {
        FreezingRecipeBuilder.freezing(
                        JsonBuilder.json().addItem(result).addCount(resultAmount).build(),
                        JsonBuilder.json().addItem(ingredient).build()
                ).unlocks(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, result.toString());
    }

    /**
     * The recipe for the gem polishing. Takes an {@link ItemLike} as ingredient.
     */
    protected static void gemPolishingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, ItemLike ingredient, int resultAmount) {
        GemPolishingRecipeBuilder.gemPolishing(
                        JsonBuilder.json().addItem(ingredient).build(),
                        JsonBuilder.json().addItem(result).addCount(resultAmount).build()
                ).unlocks(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer, result.toString());
    }
    /**
     * The recipe for the gem polishing. Takes a {@link TagKey} as ingredient
     */
    protected static void gemPolishingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike result, TagKey<Item> ingredient, String getHasName, int resultAmount) {
        GemPolishingRecipeBuilder.gemPolishing(
                        JsonBuilder.json().addTag(ingredient).build(),
                        JsonBuilder.json().addItem(result).addCount(resultAmount).build()
                ).unlocks(getHasName, has(ingredient))
                .save(finishedRecipeConsumer, result.toString());
    }

    /**
     * The recipe for the Incubation. The recipe ID is the result id.
     */
    protected static void incubationRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike ingredient, ItemLike catalyzer, ItemLike result, int count) {
        IncubatorRecipeBuilder.incubation(
                        JsonBuilder.json().addItem(ingredient).build(),
                        JsonBuilder.json().addItem(catalyzer).build(),
                        JsonBuilder.json().addItem(result).addCount(count).build()
                ).unlocks(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer, result.toString());
    }
    /**
     * The recipe for the Incubation. You can choose the ID.
     */
    protected static void incubationRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike ingredient, ItemLike catalyzer, ItemLike result, int count, String id) {
        IncubatorRecipeBuilder.incubation(
                        JsonBuilder.json().addItem(ingredient).build(),
                        JsonBuilder.json().addItem(catalyzer).build(),
                        JsonBuilder.json().addItem(result).addCount(count).build()
                ).unlocks(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer, id);
    }

    /**
     * Recipe for the atomical stabilization
     */
    protected static void atomicalStabilizatorRecipe(Consumer<FinishedRecipe> consumer, ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int count) {
        AtomicalStabilizationRecipeBuilder.stabilization(
                        JsonBuilder.json().addItem(leftIngredient).build(),
                        JsonBuilder.json().addItem(rightIngredient).build(),
                        JsonBuilder.json().addItem(glueIngredient).build(),
                        JsonBuilder.json().addItem(result).addCount(count).build()
                ).unlocks(getHasName(glueIngredient), has(glueIngredient))
                .save(consumer, result.toString());
    }

    /**
     * Recipe for mysterious magic
     */
    protected static void mysteriousMagicRecipe(Consumer<FinishedRecipe> consumer, ItemLike primary, int primaryCount, ItemLike left, int leftCount, ItemLike right, int rightCount, ItemLike up, int upCount, ItemLike down, int downCount, ItemLike result, int resultCount, int fuelAmount) {
        MysteriousMagicRecipeBuilder.magic(
                        JsonBuilder.json().addItem(primary).addCount(primaryCount).build(),
                        JsonBuilder.json().addItem(left).addCount(leftCount).build(),
                        JsonBuilder.json().addItem(right).addCount(rightCount).build(),
                        JsonBuilder.json().addItem(up).addCount(upCount).build(),
                        JsonBuilder.json().addItem(down).addCount(downCount).build(),
                        JsonBuilder.json().addItem(result).addCount(resultCount).build(),
                        fuelAmount
                ).unlocks(getHasName(primary), has(primary))
                .save(consumer, primary.toString());
    }
    protected static void mysteriousMagicRecipe(Consumer<FinishedRecipe> consumer, ItemLike primary, ItemLike left, ItemLike right, ItemLike up, ItemLike down, ItemLike result, int fuelAmount) {
        mysteriousMagicRecipe(consumer, primary, 1, left, 1, right, 1, up, 1, down, 1, result, 1, fuelAmount);
    }

    /**
     * Recipe for stone cutting
     */
    protected static void simpleStoneCutting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{pMaterial}), pCategory, pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial));
        String var10002 = getConversionRecipeName(pResult, pMaterial);
        var10000.save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + var10002 + "_stonecutting");
    }

    /**
     * Recipe for inscriber. Takes a {@link TagKey} as {@code middle} ingredient. Has only {@code middle} and {@code top} ingredients.
     * You can choose the result's count.
     */
    protected static void inscriberRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, TagKey<Item> middle, ItemLike top, ItemLike result, int count, InscriberProcessType mode) {
        InscriberRecipeBuilder.inscribe(Ingredient.of(middle), result, count).setTop(Ingredient.of(top)).setMode(mode).save(finishedRecipeConsumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, "inscribe_" + middle.location().getPath()));
    }
    /**
     * Recipe for inscriber. Takes {@link ItemLike} everywhere. Has only {@code middle} and {@code top} ingredients.
     * You can choose the result's count.
     */
    protected static void inscriberRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike middle, ItemLike top, ItemLike result, int count, InscriberProcessType mode) {
        InscriberRecipeBuilder.inscribe(middle, result, count).setTop(Ingredient.of(top)).setMode(mode).save(finishedRecipeConsumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, "inscribe_" + ForgeRegistries.ITEMS.getKey(middle.asItem()).getPath()));
    }
    /**
     * Recipe for inscriber. Takes {@link ItemLike} everywhere. Has the three inputs.
     * You can choose the result's count.
     */
    protected static void inscriberRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike middle, ItemLike top, ItemLike bottom, ItemLike result, int count, InscriberProcessType mode) {
        InscriberRecipeBuilder.inscribe(middle, result, count).setTop(Ingredient.of(top)).setBottom(Ingredient.of(bottom)).setMode(mode).save(finishedRecipeConsumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, "inscribe_" + ForgeRegistries.ITEMS.getKey(middle.asItem()).getPath()));
    }
    /**
     * Recipe for inscriber. Takes {@link ItemLike} everywhere. Has only {@code middle} input.
     * You can choose result's count.
     */
    protected static void inscriberRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike middle, ItemLike result, int count, InscriberProcessType mode) {
        InscriberRecipeBuilder.inscribe(middle, result, count).setMode(mode).save(finishedRecipeConsumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, "inscribe_" + ForgeRegistries.ITEMS.getKey(middle.asItem()).getPath()));
    }

    /**
     * Creates a recipe for filling : give 1 item and 1 fluid with his amount
     * @param ingredient the ingredient you need to spout in
     * @param fluid the fluid you need to spout
     * @param fluidAmount the amount needed
     * @param result the result you get
     */
    protected static void fillingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, String fluid, int fluidAmount, ItemLike result) {
        CreateFillingRecipeProvider.fill(result)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addIngredient(JsonBuilder.json().addFluid(fluid, fluidAmount).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(result) + "_filling");
    }
    /**
     * Fill a potion on an item
     * @param ingredient the item you need to spout on
     * @param potionName the potion name you need
     * @param potionAmount the amount of potion needed
     * @param result the result you get from this
     */
    protected static void potionFillingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, String potionName, int potionAmount, ItemLike result) {
        CreateFillingRecipeProvider.fill(result)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addIngredient(JsonBuilder.json().addPotion(potionName, potionAmount).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(result) + "_potion_filling");
    }

    /**
     * Crushing recipe
     * @param ingredient the item to crush
     * @param result the item you get from crushing
     * @param processingTime the time it takes
     * @param resultAmount the count of item you get
     */
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime, int resultAmount) {
        CreateCrushingRecipeBuilder.crush(processingTime)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addResult(JsonBuilder.json().addItem(result).addCount(resultAmount).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_crushing");
    }
    /**
     * Crushing recipe. Result amount is 1.
     * @param ingredient the item to crush
     * @param result the item you get from crushing
     * @param processingTime the time it takes
     */
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime) {
        crushingRecipe(consumer, ingredient, result, processingTime, 1);
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime, int mainResultCount, int bonusResultCount, float bonusResultChance) {
        CreateCrushingRecipeBuilder.crush(processingTime)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addResult(JsonBuilder.json().addItem(result).addCount(mainResultCount).build())
                .addResult(JsonBuilder.json().addItem(result).addCount(bonusResultCount).addChance(bonusResultChance).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_crushing");
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime, int mainResultCount, float bonusResultChance) {
        crushingRecipe(consumer, ingredient, result, processingTime, mainResultCount, 1, bonusResultChance);
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime, float bonusResultChance, int bonusResultCount) {
        crushingRecipe(consumer, ingredient, result, processingTime, 1, bonusResultCount, bonusResultChance);
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processingTime, float bonusResultChance) {
        crushingRecipe(consumer, ingredient, result, processingTime, 1, 1, bonusResultChance);
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, float resultChance, int processingTime) {
        CreateCrushingRecipeBuilder.crush(processingTime)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addResult(JsonBuilder.json().addItem(result).addChance(resultChance).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_crushing");
    }
    protected static void crushingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, ItemLike otherResult, int processingTime, float mainChance, float otherChance) {
        CreateCrushingRecipeBuilder.crush(processingTime)
                .addIngredient(JsonBuilder.json().addItem(ingredient).build())
                .addResult(JsonBuilder.json().addItem(result).addChance(mainChance).build())
                .addResult(JsonBuilder.json().addItem(otherResult).addChance(otherChance).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_crushing");
    }

    protected static void hauntingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result) {
        CreateHauntingRecipeBuilder.haunt(
                JsonBuilder.json().addItem(ingredient).build()
                )
                .addResult(
                        JsonBuilder.json().addItem(result).build()
                )
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(result) + "_haunting");
    }

    protected static void pressingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result) {
        CreatePressingRecipeBuilder.press(
                JsonBuilder.json().addItem(ingredient).build(),
                JsonBuilder.json().addItem(result).build()
                )
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_pressing");
    }

    protected static void deployingRecipe(Consumer<FinishedRecipe> consumer, ItemLike mainIngredient, ItemLike deployIngredient, ItemLike result) {
        CreateDeployingRecipeBuilder.deploy(JsonBuilder.json().addItem(mainIngredient).build(),
                        JsonBuilder.json().addItem(deployIngredient).build(),
                        JsonBuilder.json().addItem(result).build())
                .unlocks(getHasName(mainIngredient), has(mainIngredient))
                .save(consumer, getItemName(result) + "_from_deploying");
    }

    protected static void millingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processTime, int amount, float chance) {
        CreateMillingRecipeBuilder.milling(JsonBuilder.json().addItem(ingredient).build(), processTime)
                .addResult(JsonBuilder.json().addItem(result).addCount(amount).addChance(chance).build())
                .unlocks(getHasName(ingredient), has(ingredient))
                .save(consumer, getItemName(ingredient) + "_milling");
    }
    protected static void millingRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, int processTime, int amount) {
        millingRecipe(consumer, ingredient, result, processTime, amount, 1f);
    }

    /**
     * Recipe for smithing transform.
     */
    protected static void simpleSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pTemplate, ItemLike pIngredientItem, ItemLike pAddition, RecipeCategory pCategory, ItemLike pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(new ItemLike[]{pTemplate}), Ingredient.of(new ItemLike[]{pIngredientItem}), Ingredient.of(new ItemLike[]{pAddition}), pCategory, (Item) pResultItem).unlocks(getHasName(pIngredientItem), has(pIngredientItem)).save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(pResultItem) + "_smithing");
    }
}
