package net.minheur.mhm_bitsnbobs.datagen;

import com.simibubi.create.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.nbt.Tag;
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
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // call for smelting ore : SMELTING = BLASTING but put x2 time in SMELTING
        oreSmelting(pWriter, List.of(ModItems.IRON_BALL.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.COPPER_BALL.get()), RecipeCategory.MISC, AllItems.COPPER_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.GOLD_BALL.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.DIAMOND_BALL.get()), RecipeCategory.MISC, Items.DIAMOND, 0.1f, 200, "balls");
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
        simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_LOG.get(), 4, 1);
        simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_WOOD.get(), 4, 1);
        simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_WOOD.get(), 4, 1);
        simpleShapelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_LOG.get(), 4, 1);
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
        simpleWoodCrafting(pWriter, ModBlocks.DARK_WOOD.get(), ModBlocks.DARK_LOG.get());

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
        simpleSmithing(pWriter, ModItems.BASE_EGG.get(), Items.DIAMOND, Items.FEATHER, RecipeCategory.MISC, Items.CHICKEN);
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

        // stone cutting : use 'simpleStoneCutting' from our libraries.
        // use it with pWriter, recipeCategory, result, ingredient → you can add, at last parameter, the amount of results. Not needed : don't set to use 1
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.EMPTY_LITTLE_FLASK.get(), ModItems.EMPTY_BIG_FLASK.get(), 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.HALF_STICK.get(), Items.STICK, 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.QUARTER_STICK.get(), ModItems.HALF_STICK.get(), 2);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.QUARTER_STICK.get(), Items.STICK, 4);
        simpleStoneCutting(pWriter, RecipeCategory.MISC, Items.COBBLED_DEEPSLATE, Items.COBBLESTONE, 1);

        simpleWoodCrafting(pWriter, Items.MOSS_BLOCK, ModItems.BIOMASS.get());

        // chest crafting
        simpleBoatCrafting(pWriter, ModItems.DARK_BOAT.get(), ModBlocks.DARK_PLANKS.get(), false);
        simpleBoatCrafting(pWriter, ModItems.DARK_CHEST_BOAT.get(), ModBlocks.DARK_PLANKS.get(), true);

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
        signCrafting(pWriter, ModBlocks.DARK_SIGN.get(), ModBlocks.DARK_PLANKS.get());
        signHangingCrafting(pWriter, ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.STRIPPED_DARK_LOG.get());

        // no pattern
        // wind stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIND_STICK.get())
                .pattern("P")
                .pattern("S")
                .define('P', ModItems.WIND_CHARGED_INGOT.get())
                .define('S',Items.STICK)
                .unlockedBy(getHasName(ModItems.WIND_CHARGED_INGOT.get()), has(ModItems.WIND_CHARGED_INGOT.get()))
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

    protected static void simpleBlockCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_nine_" + getItemName(ingredient));
    }

    protected static void signCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike signResult, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, signResult, 3)
                .pattern("SSS")
                .pattern(" B ")
                .define('S', ingredient)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }
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

    protected static void simpleWoodCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 3)
                .pattern("SS")
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

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

    protected static void simpleStairsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    // armor making
    protected static void simpleHelmetCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    protected static void simpleChestplateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    protected static void simpleLeggingsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    protected static void simpleBootsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    // tools and weapons
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

    protected static void simpleSlabCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    protected static void simplePressurePlateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    // en dessous : liste des shapeless crafting (1 to 9 ingredient. only the used ones wrote, cause very big xD). l'item 1 est le plus important, c'est celui qui unlock
    // la recette et qui s'affiche en nom
    protected static void simpleShapelessCraftingOne(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, Integer resultQuantity, Integer ingredientQuantity) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient, ingredientQuantity)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_one_" + getItemName(ingredient));
    }
    protected static void simpleShapelessCraftingTwo(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQ, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQ)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_two_base_" + getItemName(ingredient1));
    }
    protected static void simpleShapelessCraftingThree(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQuantity, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q, ItemLike ingredient3, Integer ingredient3Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .requires(ingredient3, ingredient3Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result)+"_from_shapeless_of_three_base_"+getItemName(ingredient1));
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void itemSmoking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    protected static void simpleStoneCutting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{pMaterial}), pCategory, pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial));
        String var10002 = getConversionRecipeName(pResult, pMaterial);
        var10000.save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + var10002 + "_stonecutting");
    }

    protected static void simpleSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pTemplate, ItemLike pIngredientItem, ItemLike pAddition, RecipeCategory pCategory, ItemLike pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(new ItemLike[]{pTemplate}), Ingredient.of(new ItemLike[]{pIngredientItem}), Ingredient.of(new ItemLike[]{pAddition}), pCategory, (Item) pResultItem).unlocks(getHasName(pIngredientItem), has(pIngredientItem)).save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(pResultItem) + "_smithing");
    }
}
