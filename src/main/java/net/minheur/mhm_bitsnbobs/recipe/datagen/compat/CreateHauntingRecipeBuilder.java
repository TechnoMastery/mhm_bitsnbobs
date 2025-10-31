package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientResultsRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

public class CreateHauntingRecipeBuilder extends AJsonIngredientResultsRecipeBuilder {

    public CreateHauntingRecipeBuilder(String modid, String recipeName, JsonObject ingredient) {
        super(modid, recipeName, ingredient);
    }

    public static CreateHauntingRecipeBuilder haunt(JsonObject ingredient) {
        return new CreateHauntingRecipeBuilder(MhmBitsnbobs.MOD_ID, "create/haunting", ingredient);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredient, results, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {

        public Result(ResourceLocation id, JsonObject ingredient, List<JsonObject> results, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, results, ingredient);
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) {
                results.add(result);
            }

            JsonArray ingredientArray = new JsonArray();
            ingredientArray.add(ingredient);

            pJson.add("ingredients", ingredientArray);
            pJson.add("results", results);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.HAUNTING.getSerializer();
        }
    }
}
