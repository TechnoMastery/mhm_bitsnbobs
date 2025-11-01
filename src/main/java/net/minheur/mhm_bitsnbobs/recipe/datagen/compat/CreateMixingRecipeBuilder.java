package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientsResultsRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

public class CreateMixingRecipeBuilder extends AJsonIngredientsResultsRecipeBuilder {
    private final HeatCondition heatCondition;

    public CreateMixingRecipeBuilder(HeatCondition heatCondition) {
        super(MhmBitsnbobs.MOD_ID, "create/mixing");
        this.heatCondition = heatCondition;
    }

    public static CreateMixingRecipeBuilder mix(HeatCondition heatCondition) {
        return new CreateMixingRecipeBuilder(heatCondition);
    }
    public static CreateMixingRecipeBuilder mix() {
        return new CreateMixingRecipeBuilder(HeatCondition.NONE);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                heatCondition == null;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredients, results, heatCondition, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        private final HeatCondition heatCondition;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> results, HeatCondition heatCondition, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, results, ingredients);
            this.heatCondition = heatCondition;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            JsonArray results = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);
            for (JsonObject result : this.results) results.add(result);

            if (!(heatCondition == HeatCondition.NONE)) {
                pJson.addProperty("heatRequirement", heatCondition == HeatCondition.HEATED ? "heated" : "superheated");
            }

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
        }

        /**
         * Get the recipe serializer, not the type !
         * @return the recipe serializer
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MIXING.getSerializer();
        }
    }
}
