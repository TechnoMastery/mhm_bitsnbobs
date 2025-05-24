package net.minheur.mhm_bitsnbobs.compat;

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
import net.minheur.mhm_bitsnbobs.recipe.GemPolishingRecipe;
import net.minheur.mhm_bitsnbobs.recipe.IncubatorRecipe;
import net.minheur.mhm_bitsnbobs.screen.GemPolishingStationScreen;
import net.minheur.mhm_bitsnbobs.screen.IncubatorScreen;

import java.util.List;

@JeiPlugin
public class JEIMhmBitsnbobsPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MhmBitsnbobs.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

           registration.addRecipeCategories(new GemPolishingCategory(registration.getJeiHelpers().getGuiHelper()));
           registration.addRecipeCategories(new IncubatingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.INCUBATOR.get(), IncubatingCategory.INCUBATING_TYPE);
        registration.addRecipeCatalyst(ModBlocks.GEM_POLISHING_STATION.get(), GemPolishingCategory.GEM_POLISHING_TYPE);
        IModPlugin.super.registerRecipeCatalysts(registration);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<GemPolishingRecipe> polishingRecipes = recipeManager.getAllRecipesFor(GemPolishingRecipe.Type.INSTANCE);
        registration.addRecipes(GemPolishingCategory.GEM_POLISHING_TYPE, polishingRecipes);

        List<IncubatorRecipe> incubatorRecipes = recipeManager.getAllRecipesFor(IncubatorRecipe.Type.INSTANCE);
        registration.addRecipes(IncubatingCategory.INCUBATING_TYPE, incubatorRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GemPolishingStationScreen.class, 80, 30, 16, 26, GemPolishingCategory.GEM_POLISHING_TYPE);

        registration.addRecipeClickArea(IncubatorScreen.class, 61, 24, 54, 15, IncubatingCategory.INCUBATING_TYPE);
    }
}
