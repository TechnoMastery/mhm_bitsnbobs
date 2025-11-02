package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.builders.JsonBuilder;
import net.minheur.techno_lib.datagen.recipe.ICreateSequenceRecipe;
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientResultRecipeBuilder;

import java.util.function.Consumer;

public class CreatePressingRecipeBuilder extends AJsonIngredientResultRecipeBuilder implements ICreateSequenceRecipe {

    public CreatePressingRecipeBuilder(JsonObject ingredient, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "create/pressing", result, ingredient);
    }

    public static CreatePressingRecipeBuilder press(JsonObject ingredient, JsonObject result) {
        return new CreatePressingRecipeBuilder(ingredient, result);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredient, result, advancement, getFullAdvancementId(resourceLocation)));
    }

    @Override
    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, ingredient, result, null, null);
    }

    public static JsonObject getSequenceStep(ItemLike transitional) {
        JsonObject trans = JsonBuilder.getSimpleItem(transitional);
        CreatePressingRecipeBuilder step = new CreatePressingRecipeBuilder(trans, trans);
        return step.getSequencedRecipe();
    }

    /**
     * The {@link Result} is a subclass to manage the recipe once finished.
     */
    public static class Result extends IngredientResult {

        public Result(ResourceLocation id, JsonObject ingredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result, ingredient);
        }

        /**
         * This will take all the parts of your recipe and add them to your finished json recipe file
         * @param pJson the json where your recipe will be saved
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray result = new JsonArray();
            JsonArray ingredient = new JsonArray();

            result.add(this.result);
            ingredient.add(this.ingredient);

            pJson.add("ingredients", ingredient);
            pJson.add("results", result);
        }

        /**
         * @return the recipe serializer (NOT type)
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.PRESSING.getSerializer();
        }
    }

}
