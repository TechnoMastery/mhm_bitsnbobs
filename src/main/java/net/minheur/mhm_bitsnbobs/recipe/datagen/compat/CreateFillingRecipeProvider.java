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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Datagen creation class for filling recipe from Create
 */
public class CreateFillingRecipeProvider {
    /**
     * The List of ingredient items
     */
    private final List<JsonObject> ingredients = new ArrayList<>();
    /**
     * The list of result items
     */
    private final JsonObject result;
    /**
     * The bound advancement
     */
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateFillingRecipeProvider(JsonObject result) {
        this.result = result;
    }

    public static CreateFillingRecipeProvider fill(ItemLike result) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("item", getBuiltInItemRegistry(result));
        return new CreateFillingRecipeProvider(resultJson);
    }

    /**
     * Adding an ingredient to the list
     * @param item the ItemLike you want to add
     * @return the current recipe
     */
    public CreateFillingRecipeProvider addIngredient(ItemLike item) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", getBuiltInItemRegistry(item));
        ingredients.add(ingredient);
        return this;
    }
    public CreateFillingRecipeProvider addFluidIngredient(String fluid, int amount) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("fluid", fluid);
        ingredient.addProperty("amount", amount);
        ingredients.add(ingredient);
        return this;
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

    /**
     * Set the condition you need to unlock the recipe
     * @param pKey the name
     * @param pCriterion the criterion
     * @return the current recipe
     */
    public CreateFillingRecipeProvider unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    /**
     * Make sure there is no {@code null} ingredient / result, and that you set a criterion
     * @param pId the recipe id
     */
    private void ensureValid(ResourceLocation pId) {
        for (JsonObject ingredient : ingredients) {
            if (ingredient == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        }
        if (result == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    /**
     * Saves the recipe
     * @param consumer the recipe consumer
     * @param id the id / name of the recipe - asks for a {@link ResourceLocation}
     */
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/filling/"), this.ingredients, this.result, this.advancement, id.withPrefix("recipes/create/filling/")));
    }
    /**
     * Saves the recipe
     * @param consumer the recipe consumer
     * @param id the id / name of the recipe - asks for a {@link String}. It will create a {@link ResourceLocation} with modid {@code mhm_bitsnbobs} and the id.
     */
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, this.ingredients, this.result, null, null);
    }

    /**
     * The {@link CreateFillingRecipeProvider.Result} is a subclass to manage the recipe once finished.
     */
    public static class Result implements FinishedRecipe {
        /**
         * the recipe name / id
         */
        private final ResourceLocation id;
        /**
         * The recipe ingredients list
         */
        private final List<JsonObject> ingredients;
        /**
         * The recipe results list
         */
        private final JsonObject results;
        /**
         * The recipe advancement
         */
        private final Advancement.Builder advancement;
        /**
         * The recipe advancement's id
         */
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<JsonObject> ingredients, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.results = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        /**
         * This will take all the parts of your recipe and add them to your finished json recipe file
         * @param pJson the json where your recipe will be saved
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);

            pJson.add("results", this.results);
            pJson.add("ingredients", ingredients);
        }

        /**
         * @return the recipe name / id
         */
        @Override
        public ResourceLocation getId() {
            return id;
        }

        /**
         * @return the recipe serializer (NOT type)
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.FILLING.getSerializer();
        }

        /**
         * @return your recipe's serialized advancement
         */
        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        /**
         * @return your recipe's advancement id
         */
        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }

}
