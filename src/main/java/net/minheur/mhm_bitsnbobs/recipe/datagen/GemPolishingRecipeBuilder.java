package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientResultRecipeBuilder;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Here is the builder to datagen freezing recipes
 */
public class GemPolishingRecipeBuilder extends AJsonIngredientResultRecipeBuilder {
    public GemPolishingRecipeBuilder(JsonObject ingredient, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "gem_polishing", result, ingredient);
    }

    public static GemPolishingRecipeBuilder gemPolishing(JsonObject ingredient, JsonObject result) {
        return new GemPolishingRecipeBuilder(ingredient, result);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), this.ingredient, this.result, this.advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        public Result(ResourceLocation id, JsonObject ingredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result, ingredient);
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient);
            pJson.add("output", result);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.GEM_POLISHING_SERIALIZER.get();
        }
    }

}
