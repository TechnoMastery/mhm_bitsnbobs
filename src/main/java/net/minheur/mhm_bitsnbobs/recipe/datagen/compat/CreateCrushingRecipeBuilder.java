package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientsResultsRecipeBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public class CreateCrushingRecipeBuilder extends AJsonIngredientsResultsRecipeBuilder {
    /**
     * the time needed for the recipe to finish
     */
    private final int processTime;

    public CreateCrushingRecipeBuilder(int processTime) {
        super(MhmBitsnbobs.MOD_ID, "create/crushing");
        this.processTime = processTime;
    }

    public static CreateCrushingRecipeBuilder crush(int processTime) {
        return new CreateCrushingRecipeBuilder(processTime);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                processTime < 1;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredients, results, advancement, getFullAdvancementId(resourceLocation), processTime));
    }

    /**
     * The {@link CreateCrushingRecipeBuilder.Result} is a subclass to manage the recipe once finished.
     */
    public static class Result extends IngredientResult {
        /**
         * The time needed for the recipe to finish
         */
        private final int processTime;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> result, Advancement.Builder advancement, ResourceLocation advancementId, int processTime) {
            super(id, advancement, advancementId, result, ingredients);
            this.processTime = processTime;
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

            pJson.addProperty("processingTime", processTime);

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
        }

        /**
         * @return the recipe serializer (NOT type)
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.CRUSHING.getSerializer();
        }
    }

}
