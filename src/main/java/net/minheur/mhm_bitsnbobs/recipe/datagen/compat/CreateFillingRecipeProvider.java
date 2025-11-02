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
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientsResultRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Datagen creation class for filling recipe from Create
 */
public class CreateFillingRecipeProvider extends AJsonIngredientsResultRecipeBuilder implements ICreateSequenceRecipe {

    public CreateFillingRecipeProvider(JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "create/filling", result);
    }

    public static CreateFillingRecipeProvider fill(JsonObject result) {
        return new CreateFillingRecipeProvider(result);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), ingredients, result, advancement, getFullAdvancementId(resourceLocation)));
    }

    @Override
    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, this.ingredients, this.result, null, null);
    }

    public static JsonObject getSequenceStep(ItemLike transitional, JsonObject fluid) {
        JsonObject trans = JsonBuilder.getSimpleItem(transitional);
        CreateFillingRecipeProvider step = new CreateFillingRecipeProvider(trans);
        step.addIngredient(trans)
                .addIngredient(fluid);
        return step.getSequencedRecipe();
    }

    /**
     * The {@link CreateFillingRecipeProvider.Result} is a subclass to manage the recipe once finished.
     */
    public static class Result extends IngredientResult {

        public Result(ResourceLocation id, List<JsonObject> ingredients, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, advancement, advancementId, ingredients);
        }

        /**
         * This will take all the parts of your recipe and add them to your finished json recipe file
         * @param pJson the json where your recipe will be saved
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);

            JsonArray result = new JsonArray();
            result.add(this.result);

            pJson.add("results", result);
            pJson.add("ingredients", ingredients);
        }

        /**
         * @return the recipe serializer (NOT type)
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.FILLING.getSerializer();
        }
    }

}
