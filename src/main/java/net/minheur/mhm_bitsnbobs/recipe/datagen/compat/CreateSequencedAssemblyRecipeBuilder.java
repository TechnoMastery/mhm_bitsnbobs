package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CreateSequencedAssemblyRecipeBuilder extends AJsonIngredientResultRecipeBuilder {
    private final JsonObject transitionalItem;
    private final List<JsonObject> steps = new ArrayList<>();
    private final int loops;

    public CreateSequencedAssemblyRecipeBuilder(JsonObject ingredient, JsonObject result, JsonObject transitionalItem, int loops) {
        super(MhmBitsnbobs.MOD_ID, "create/sequence", result, ingredient);
        this.transitionalItem = transitionalItem;
        this.loops = loops;
    }

    public static CreateSequencedAssemblyRecipeBuilder sequence(JsonObject ingredient, JsonObject result, JsonObject transitionalItem, int loops) {
        return new CreateSequencedAssemblyRecipeBuilder(ingredient, result, transitionalItem, loops);
    }

    public CreateSequencedAssemblyRecipeBuilder addStep(JsonObject step) {
        steps.add(step);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject step : steps) if (step.isJsonNull()) return true;
        if (transitionalItem.isJsonNull()) return true;
        if (loops < 1) return true;

        return super.isRecipeEmpty();
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredient, result, transitionalItem, steps, loops, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        private final JsonObject transitionalItem;
        private final List<JsonObject> steps;
        private final int loops;

        public Result(ResourceLocation id, JsonObject ingredient, JsonObject result, JsonObject transitionalItem, List<JsonObject> steps, int loops, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result, ingredient);
            this.transitionalItem = transitionalItem;
            this.steps = steps;
            this.loops = loops;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredient", ingredient);
            pJson.add("transitionalItem", transitionalItem);
            pJson.addProperty("loops", loops);
            JsonArray steps = new JsonArray();
            JsonArray results = new JsonArray();
            results.add(result);
            pJson.add("results", results);
            for (JsonObject step : this.steps) {
                steps.add(step);
            }
            pJson.add("sequence", steps);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.SEQUENCED_ASSEMBLY.getSerializer();
        }
    }

}
