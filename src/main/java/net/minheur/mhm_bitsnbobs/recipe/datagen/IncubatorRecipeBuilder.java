package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientResultRecipeBuilder;

import java.util.function.Consumer;

public class IncubatorRecipeBuilder extends AJsonIngredientResultRecipeBuilder {
    private final JsonObject catalyzer;

    public IncubatorRecipeBuilder(JsonObject ingredient, JsonObject catalyzer, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "incubation", result, ingredient);
        this.catalyzer = catalyzer;
    }

    public static IncubatorRecipeBuilder incubation(JsonObject ingredient, JsonObject catalyzer, JsonObject result) {
        return new IncubatorRecipeBuilder(ingredient, catalyzer, result);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || catalyzer.isJsonNull();
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), this.ingredient, this.catalyzer, this.result, this.advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        private final JsonObject catalyzer;

        public Result(ResourceLocation id, JsonObject ingredient, JsonObject catalyzer, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result, ingredient);
            this.catalyzer = catalyzer;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient);
            pJson.add("catalyzer", catalyzer);
            pJson.add("output", result);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.INCUBATING_SERIALIZER.get();
        }
    }

}
