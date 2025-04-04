package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
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

        // shaped recipe pattern ==> 3x3 crafting
        simpleBlockCrafting(pWriter, ModBlocks.SAPPHIRE_BLOCK.get(), ModItems.SAPPHIRE.get());
        simpleBlockCrafting(pWriter, ModBlocks.RAW_SAPPHIRE_BLOCK.get(), ModItems.RAW_SAPPHIRE.get());

        // call craft armor ==> chacun le leur
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

        // simple shapeless pattern ==> 1 item
        simpleShappelessCrafting(pWriter, ModBlocks.SAPPHIRE_BUTTON.get(), ModItems.SAPPHIRE.get(), 1);
        simpleShappelessCrafting(pWriter, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get(), 9);

        // no pattern
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" S ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
    }

    protected static void simpleBlockCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Block block, Item item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', item)
                .unlockedBy(getHasName(item), has(item))
                .save(pFinishedRecipeConsumer);
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

    protected static void simpleShappelessCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, Integer quantity) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, quantity)
                .requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MhmBitsnbobs.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
