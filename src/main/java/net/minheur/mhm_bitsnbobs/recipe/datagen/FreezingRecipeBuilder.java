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

/**
 * Here is the builder to datagen freezing recipes
 */
public class FreezingRecipeBuilder extends AJsonIngredientResultRecipeBuilder {

    public FreezingRecipeBuilder(JsonObject ingredient, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID,"freezing", result, ingredient);
    }

    public static FreezingRecipeBuilder freezing(JsonObject ingredient, JsonObject result) {
        return new FreezingRecipeBuilder(result, ingredient);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), this.result, this.ingredient, this.advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends IngredientResult {
        public Result(ResourceLocation id, JsonObject result, JsonObject ingredient, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result, ingredient);
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient);
            pJson.add("output", result);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.FREEZING_SERIALIZER.get();
        }
    }

}
