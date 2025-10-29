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

/**
 * Recipe builder for create's compacting recipe
 */
public class CreateCompactingRecipeBuilder extends AJsonIngredientsResultsRecipeBuilder {
    /**
     * The heatRequirement
     */
    private HeatCondition heatCondition = HeatCondition.NONE;

    public CreateCompactingRecipeBuilder() {
        super(MhmBitsnbobs.MOD_ID, "create/compacting");
    }

    public static CreateCompactingRecipeBuilder createCompacting() {
        return new CreateCompactingRecipeBuilder();
    }

    /**
     * Adding a heat requirement. If not set, {@code HeatCondition.NONE} is used.
     * @param condition the heat needed
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addHeatCondition(HeatCondition condition) {
        this.heatCondition = condition;
        return this;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredients, results, advancement, getFullAdvancementId(resourceLocation), heatCondition));
    }

    /**
     * The {@link Result} is a subclass to manage the recipe once finished.
     */
    public static class Result extends IngredientResult {
        /**
         * The recipe heat requirement
         */
        private final HeatCondition heatCondition;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> results, Advancement.Builder advancement, ResourceLocation advancementId, HeatCondition heatCondition) {
            super(id, advancement, advancementId, results, ingredients);
            this.heatCondition = heatCondition;
        }

        /**
         * This will take all the parts of your recipe and add them to your finished json recipe file
         * @param pJson the json where your recipe will be saved
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);

            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) results.add(result);

            if (heatCondition != HeatCondition.NONE) {
                if (heatCondition == HeatCondition.HEATED) pJson.addProperty("heatRequirement", "heated");
                else pJson.addProperty("heatRequirement", "superHeated");
            }

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
        }

        /**
         * @return the recipe serializer (NOT type)
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.COMPACTING.getSerializer();
        }
    }
}
