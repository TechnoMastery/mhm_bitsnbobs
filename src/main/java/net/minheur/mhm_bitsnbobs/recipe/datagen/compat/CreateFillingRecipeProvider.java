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
import net.minheur.techno_lib.datagen.recipe.jsonIngredient.AJsonIngredientsResultRecipeBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Datagen creation class for filling recipe from Create
 */
public class CreateFillingRecipeProvider extends AJsonIngredientsResultRecipeBuilder {

    public CreateFillingRecipeProvider(JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "create/filling", result);
    }

    public static CreateFillingRecipeProvider fill(ItemLike result) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("item", getBuiltInItemRegistry(result));
        return new CreateFillingRecipeProvider(resultJson);
    }

    public CreateFillingRecipeProvider addPotionIngredient(String potionName, int amount) {
        JsonObject ingredient = new JsonObject();
        JsonObject nbt = new JsonObject();
        nbt.addProperty("Bottle", "REGULAR");
        nbt.addProperty("Potion", potionName);
        ingredient.addProperty("fluid", "create:potion");
        ingredient.addProperty("amount", amount);
        ingredient.add("nbt", nbt);
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {

    }

    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, this.ingredients, this.result, null, null);
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
