package net.minheur.mhm_bitsnbobs.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.recipe.*;
import net.minheur.mhm_bitsnbobs.screen.FreezerScreen;
import net.minheur.mhm_bitsnbobs.screen.GemPolishingStationScreen;
import net.minheur.mhm_bitsnbobs.screen.IncubatorScreen;

import java.util.List;

/**
 * JEI plugin for Mhm bitsnbobs.
 * <p> Here we register the categories for JEI.
 */
@JeiPlugin
public class JEIMhmBitsnbobsPlugin implements IModPlugin {
    /**
     * @return the ID of the plugin. ({@code mhm_bitsnbobs:jei_plugin})
     */
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MhmBitsnbobs.MOD_ID, "jei_plugin");
    }

    /**
     * Here we add our categories
     * @param registration used to registrate the categories.
     */
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AtomicalStabilizatorCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FreezingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new GemPolishingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IncubatingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MysteriousMagicCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    /**
     * The catalysts are blocks / items that are displayed next to the recipes.
     * <p>Often the block that supports the recipe, so the player knows what to use for the recipe.
     * @param registration used to registrate the catalysts.
     */
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.ATOMICAL_STABILIZATOR.get(), AtomicalStabilizatorCategory.ATOMICAL_STABILIZATOR_TYPE);
        registration.addRecipeCatalyst(ModBlocks.FREEZER.get(), FreezingCategory.FREEZING_TYPE);
        registration.addRecipeCatalyst(ModBlocks.INCUBATOR.get(), IncubatingCategory.INCUBATING_TYPE);
        registration.addRecipeCatalyst(ModBlocks.GEM_POLISHING_STATION.get(), GemPolishingCategory.GEM_POLISHING_TYPE);
        registration.addRecipeCatalyst(ModBlocks.MYSTERIOUS_ALTAR.get(), MysteriousMagicCategory.MYSTERIOUS_MAGIC_TYPE);
        IModPlugin.super.registerRecipeCatalysts(registration);
    }

    /**
     * This tells JEI which recipe type is which category
     * @param registration used to registrate the recipes.
     */
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AtomicalStabilizatorRecipe> atomicalStabilizatorRecipes = recipeManager.getAllRecipesFor(AtomicalStabilizatorRecipe.Type.INSTANCE);
        registration.addRecipes(AtomicalStabilizatorCategory.ATOMICAL_STABILIZATOR_TYPE, atomicalStabilizatorRecipes);

        List<FreezingRecipe> freezingRecipes = recipeManager.getAllRecipesFor(FreezingRecipe.Type.INSTANCE);
        registration.addRecipes(FreezingCategory.FREEZING_TYPE, freezingRecipes);

        List<GemPolishingRecipe> polishingRecipes = recipeManager.getAllRecipesFor(GemPolishingRecipe.Type.INSTANCE);
        registration.addRecipes(GemPolishingCategory.GEM_POLISHING_TYPE, polishingRecipes);

        List<IncubatorRecipe> incubatorRecipes = recipeManager.getAllRecipesFor(IncubatorRecipe.Type.INSTANCE);
        registration.addRecipes(IncubatingCategory.INCUBATING_TYPE, incubatorRecipes);

        List<MysteriousMagicRecipe> mysteriousMagicRecipes = recipeManager.getAllRecipesFor(MysteriousMagicRecipe.Type.INSTANCE);
        registration.addRecipes(MysteriousMagicCategory.MYSTERIOUS_MAGIC_TYPE, mysteriousMagicRecipes);
    }

    /**
     * This allows us to add different things to the screen, like a clickArea on our GUI to get directly to the recipes of out block
     * @param registration used to registrate the handlers.
     */
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FreezerScreen.class, 80, 30, 16, 22, FreezingCategory.FREEZING_TYPE);
        registration.addRecipeClickArea(GemPolishingStationScreen.class, 80, 30, 16, 22, GemPolishingCategory.GEM_POLISHING_TYPE);

        registration.addRecipeClickArea(IncubatorScreen.class, 61, 24, 54, 15, IncubatingCategory.INCUBATING_TYPE);
    }
}
