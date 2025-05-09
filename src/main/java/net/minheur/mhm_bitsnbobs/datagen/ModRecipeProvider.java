package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
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

    // lists pour recettes (si y a plusieurs items / blocks, planches par exemple)
    private static final List<ItemLike> SAPPHIRE_SMELTABLE = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_BLOCK.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
            ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModBlocks.END_SAPPHIRE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // call for smelting ore : SMELTING = BLASTING but put x2 time in SMELTING
        oreSmelting(pWriter, SAPPHIRE_SMELTABLE, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, SAPPHIRE_SMELTABLE, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");
        oreSmelting(pWriter, List.of(ModItems.IRON_BALL.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.COPPER_BALL.get()), RecipeCategory.MISC, ModItems.LITTLE_COPPER_NUGGET.get(), 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.GOLD_BALL.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(ModItems.DIAMOND_BALL.get()), RecipeCategory.MISC, Items.DIAMOND, 0.1f, 200, "balls");
        oreSmelting(pWriter, List.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, ModItems.ROTTEN_LEATHER.get(), 0.1f, 200, "rotten");
        oreSmelting(pWriter, List.of(ModBlocks.COMPRESSED_DIRT.get()), RecipeCategory.MISC, ModItems.EXTREMELY_DRY_DIRT.get(), 0.1f, 600, "dirt");
        oreSmelting(pWriter, List.of(ModItems.WET_DIRT.get()), RecipeCategory.MISC, ModItems.EXTREMELY_DRY_DIRT.get(), 0.1f, 1800, "dirt");

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
        simpleBlockCrafting(pWriter, ModBlocks.RESSOURCE_DIRT_BLOCK.get(), ModItems.RESSOURCE_DIRT.get());

        simpleBlockCrafting(pWriter, ModItems.CREATIVE_INGOT.get(), ModItems.CREATIVE_NUGGET.get());
        simpleBlockCrafting(pWriter, ModBlocks.CREATIVE_BLOCK.get(), ModItems.CREATIVE_INGOT.get());
        simpleBlockCrafting(pWriter, ModBlocks.SUPER_CHARGED_BLOCK.get(), ModItems.SUPER_CHARGED_INGOT.get());

        // call craft armor → chacun le leur
        simpleHelmetCrafting(pWriter, ModItems.SAPPHIRE_HELMET.get(), ModItems.SAPPHIRE.get());
        simpleChestplateCrafting(pWriter, ModItems.SAPPHIRE_CHESTPLATE.get(), ModItems.SAPPHIRE.get());
        simpleLeggingsCrafting(pWriter, ModItems.SAPPHIRE_LEGGINGS.get(), ModItems.SAPPHIRE.get());
        simpleBootsCrafting(pWriter, ModItems.SAPPHIRE_BOOTS.get(), ModItems.SAPPHIRE.get());

        // tools & weapons : chacun le leur (préciser le stick pls)
        simpleSwordCrafting(pWriter, ModItems.SAPPHIRE_SWORD.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simplePickaxeCrafting(pWriter, ModItems.SAPPHIRE_PICKAXE.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleAxesCrafting(pWriter, ModItems.SAPPHIRE_AXE.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleShovelCrafting(pWriter, ModItems.SAPPHIRE_SHOVEL.get(), ModItems.SAPPHIRE.get(), Items.STICK);
        simpleHoesCrafting(pWriter, ModItems.SAPPHIRE_HOE.get(), ModItems.SAPPHIRE.get(), Items.STICK);

        // slab, stair & pressure plate ont leurs propres mod
        simpleSlabCrafting(pWriter, ModBlocks.SAPPHIRE_SLAB.get(), ModBlocks.SAPPHIRE_BLOCK.get());
        simpleStairsCrafting(pWriter, ModBlocks.SAPPHIRE_STAIRS.get(), ModBlocks.SAPPHIRE_BLOCK.get());
        simplePressurePlateCrafting(pWriter, ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), ModBlocks.SAPPHIRE_BLOCK.get());

        // simple shapeless pattern → utiliser le bon selon la qté d'items diferents
        simpleShappelessCraftingOne(pWriter, ModBlocks.SAPPHIRE_BUTTON.get(), ModItems.SAPPHIRE.get(), 1, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get(), 9, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.FIRE_DIAMOND.get(), ModItems.FIRE_SEEDS.get(), 5, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.CREATIVE_NUGGET.get(), ModItems.CREATIVE_INGOT.get(), 9, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.CREATIVE_INGOT.get(), ModBlocks.CREATIVE_BLOCK.get(), 9, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.SUPER_CHARGED_INGOT.get(), ModBlocks.SUPER_CHARGED_BLOCK.get(), 9, 1);
        simpleShappelessCraftingOne(pWriter, ModItems.EMPTY_RUNE.get(), Items.EMERALD, 5, 1);
        simpleShappelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_LOG.get(), 4, 1);
        simpleShappelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.DARK_WOOD.get(), 4, 1);
        simpleShappelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_WOOD.get(), 4, 1);
        simpleShappelessCraftingOne(pWriter, ModBlocks.DARK_PLANKS.get(), ModBlocks.STRIPPED_DARK_LOG.get(), 4, 1);

        simpleShappelessCraftingTwo(pWriter, ModItems.XP_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, Items.GOLD_INGOT, 1);
        simpleShappelessCraftingTwo(pWriter, ModItems.MONEY_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, Items.COPPER_INGOT, 1);
        simpleShappelessCraftingTwo(pWriter, ModItems.TREE_GROWER.get(), 1, Items.FLOWER_POT, 1, Blocks.DIRT, 1);

        simpleShappelessCraftingThree(pWriter, ModItems.OAK_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, ModItems.TREE_GROWER.get(), 1, Items.OAK_SAPLING, 1);
        simpleShappelessCraftingThree(pWriter, ModItems.SPRUCE_RUNE.get(), 1, ModItems.EMPTY_RUNE.get(), 1, ModItems.TREE_GROWER.get(), 1, Items.SPRUCE_SAPLING, 1);
        simpleShappelessCraftingThree(pWriter, ModItems.LITTLE_HUMID_POTION.get(), 2, ModItems.HUMID_POTION.get(), 1, ModItems.TRANSFER_FLASK.get(), 1, ModItems.EMPTY_LITTLE_FLASK.get(), 2);
        simpleShappelessCraftingThree(pWriter, ModItems.HUMID_POTION.get(), 1, ModItems.LITTLE_HUMID_POTION.get(), 1, ModItems.TRANSFER_FLASK.get(), 1, ModItems.EMPTY_BIG_FLASK.get(), 1);
        simpleShappelessCraftingThree(pWriter, ModItems.RESSOURCE_DIRT.get(), 1, ModItems.HUMID_POTION.get(), 1, ModItems.TRANSFER_FLASK.get(), 1, ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(), 1);

        // crafting log → wood
        simpleWoodCrafting(pWriter, ModBlocks.DARK_WOOD.get(), ModBlocks.DARK_LOG.get());

        // simple music disc → second item is what item you want it to be made with (center always = basedisk tag)
        simpleDiscCrafting(pWriter, ModItems.DARK_SOUL_MUSIC_DISC.get(), Items.ECHO_SHARD);
        simpleDiscCrafting(pWriter, ModItems.END_OF_THE_START_MUSIC_DISC.get(), ModItems.HARDENED_INGOT.get());
        simpleDiscCrafting(pWriter, ModItems.BAR_BRAWL_MUSIC_DISC.get(), ModItems.SAPPHIRE.get());

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

        // stone cutting : use 'simpleStoneCutting' from our libraries.
        // use it with pWriter, recipeCategory, result, ingredient → you can add, at last parameter, the amount of results. Not needed : don't set to use 1
        simpleStoneCutting(pWriter, RecipeCategory.MISC, ModItems.EMPTY_LITTLE_FLASK.get(), ModItems.EMPTY_BIG_FLASK.get(), 2);

        // head crafting
        simpleHeadCrafting(pWriter, Items.SKELETON_SKULL, Items.BONE, Items.BONE);
        simpleHeadCrafting(pWriter, Items.WITHER_SKELETON_SKULL, Items.BONE, Items.WITHER_ROSE);
        simpleHeadCrafting(pWriter, Items.CREEPER_HEAD, Items.GUNPOWDER, Items.GUNPOWDER);
        simpleHeadCrafting(pWriter, Items.ZOMBIE_HEAD, Items.ROTTEN_FLESH, ModItems.ROTTEN_LEATHER.get());

        // no pattern
        // TODO: change minecraft crafting to only one "", so don't have "" + "".
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

        // dirt
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.DIRT)
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" G ")
                .define('G', Blocks.GRAVEL)
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "gravel_crafting");

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
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "player_head_crafting");
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
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "dragon_head_crafting");

        // emerald
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.EMERALD)
                .pattern("DDD")
                .pattern("DED")
                .pattern("DDD")
                .define('D', Blocks.DEEPSLATE)
                .define('E', ModItems.CONTROLLED_PICKAXE.get())
                .unlockedBy(getHasName(ModItems.CONTROLLED_PICKAXE.get()), has(ModItems.CONTROLLED_PICKAXE.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "emerald_crafting");

        // controlled pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONTROLLED_PICKAXE.get())
                .pattern("  P")
                .pattern(" S ")
                .pattern("C  ")
                .define('P', Items.GOLDEN_PICKAXE)
                .define('S', ModItems.QUATER_STICK.get())
                .define('C', ModItems.CONTROL_PANEL.get())
                .unlockedBy(getHasName(ModItems.CONTROL_PANEL.get()), has(ModItems.CONTROL_PANEL.get()))
                .save(pWriter);

        // controled stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONTROLLED_STICK.get())
                .pattern(" P")
                .pattern("S ")
                .define('P', ModItems.CONTROL_PANEL.get())
                .define('S', ModItems.HALF_STICK.get())
                .unlockedBy(getHasName(ModItems.CONTROL_PANEL.get()), has(ModItems.CONTROL_PANEL.get()))
                .save(pWriter);

        // skulkery tool
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SKULLKERY_TOOL.get())
                .pattern("  P")
                .pattern(" S ")
                .pattern("C  ")
                .define('P', ModItems.QUATER_STICK.get())
                .define('S', Tags.Items.HEADS)
                .define('C', ModItems.CONTROLLED_STICK.get())
                .unlockedBy(getHasName(ModItems.CONTROLLED_STICK.get()), has(ModItems.CONTROLLED_STICK.get()))
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

        // transfer flask
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TRANSFER_FLASK.get())
                .pattern(" F ")
                .pattern("G B")
                .pattern(" V ")
                .define('F', ModItems.EMPTY_BIG_FLASK.get())
                .define('V', Blocks.GLASS)
                .define('B', Items.OAK_BUTTON)
                .define('G', Blocks.GLASS_PANE)
                .unlockedBy(getHasName(ModItems.EMPTY_BIG_FLASK.get()), has(ModItems.EMPTY_BIG_FLASK.get()))
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

        // dupli lightning upgrade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIGHTNING_UPGRADE.get(), 2)
                .pattern("DID")
                .pattern("DGD")
                .pattern("DDD")
                .define('I', ModItems.LIGHTNING_UPGRADE.get())
                .define('D', Items.DIAMOND)
                .define('G', ModItems.SUPER_CHARGED_INGOT.get())
                .unlockedBy(getHasName(ModItems.LIGHTNING_UPGRADE.get()), has(ModItems.LIGHTNING_UPGRADE.get()))
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "lightning_upgrade_duplication");

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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBINIUM.get())
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
                .save(pWriter, MhmBitsnbobs.MOD_ID + ":" + "spawner_crafting");

        // fire stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FIRE_STICK.get())
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .define('S', ModItems.FIRE_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.FIRE_DIAMOND.get()), has(ModItems.FIRE_DIAMOND.get()))
                .save(pWriter);
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

    protected static void simpleWoodCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SS")
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    protected static void simpleDiscCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item resultDisc, Item discIngretient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultDisc)
                .pattern(" S ")
                .pattern("SBS")
                .pattern(" S ")
                .define('S', discIngretient)
                .define('B', ModTags.Items.BASEDISK)
                .unlockedBy(getHasName(discIngretient), has(discIngretient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(resultDisc) + "_with_" + getItemName(discIngretient));
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

    // en dessous : liste des shappeless crafting (1 to 9 ingredient. only the used ones wrote, cause very big xD). l'item 1 est le plus important, c'est celuis qui unlock
    // la recette et qui s'affiche en nom
    protected static void simpleShappelessCraftingOne(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, Integer resultQuantity, Integer ingredientQuantity) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient, ingredientQuantity)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_one_" + getItemName(ingredient));
    }
    protected static void simpleShappelessCraftingTwo(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQ, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQ)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result) + "_from_shappeless_of_two_base_" + getItemName(ingredient1));
    }
    protected static void simpleShappelessCraftingThree(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQuantity, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q, ItemLike ingredient3, Integer ingredient3Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .requires(ingredient3, ingredient3Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(result)+"_from_shappeless_of_three_base_"+getItemName(ingredient1));
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
