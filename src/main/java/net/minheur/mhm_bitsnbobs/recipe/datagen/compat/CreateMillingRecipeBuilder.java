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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class CreateMillingRecipeBuilder extends AJsonIngredientResultsRecipeBuilder {
    private final int processTime;

    public CreateMillingRecipeBuilder(JsonObject ingredient, int processTime) {
        super(MhmBitsnbobs.MOD_ID, "create/milling", ingredient);
        this.processTime = processTime;
    }

    public static CreateMillingRecipeBuilder milling(JsonObject ingredient, int processTime) {
        return new CreateMillingRecipeBuilder(ingredient, processTime);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || processTime < 1;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredient, results, processTime, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        private final int processTime;

        public Result(ResourceLocation id, JsonObject ingredient, List<JsonObject> results, int processTime, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, results, ingredient);
            this.processTime = processTime;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) {
                results.add(result);
            }

            JsonArray ingredient = new JsonArray();
            ingredient.add(this.ingredient);

            pJson.add("results", results);
            pJson.addProperty("processingTime", processTime);
            pJson.add("ingredients", ingredient);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MILLING.getSerializer();
        }
    }
}
