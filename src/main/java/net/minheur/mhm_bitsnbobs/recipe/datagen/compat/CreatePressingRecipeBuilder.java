package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

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

import java.util.function.Consumer;

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

public class CreatePressingRecipeBuilder {
    /**
     * The ingredient item
     */
    private final JsonObject ingredient;
    /**
     * The result item
     */
    private final JsonObject result;
    /**
     * The bound advancement
     */
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreatePressingRecipeBuilder(JsonObject ingredient, JsonObject result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    public static CreatePressingRecipeBuilder press(ItemLike ingredient, ItemLike result) {
        JsonObject resultJson = new JsonObject();
        JsonObject ingredientJson = new JsonObject();
        resultJson.addProperty("item", getBuiltInItemRegistry(result));
        ingredientJson.addProperty("item", getBuiltInItemRegistry(ingredient));
        return new CreatePressingRecipeBuilder(ingredientJson, resultJson);
    }

    /**
     * Set the condition you need to unlock the recipe
     * @param pKey the name
     * @param pCriterion the criterion
     * @return the current recipe
     */
    public CreatePressingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    /**
     * Make sure there is no {@code null} ingredient / result, and that you set a criterion
     * @param pId the recipe id
     */
    private void ensureValid(ResourceLocation pId) {
        if (result == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        if (ingredient == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
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
        consumer.accept(new Result(id.withPrefix("create/pressing/"), this.ingredient, this.result, this.advancement, id.withPrefix("recipes/create/pressing/")));
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
        return new Result(null, this.ingredient, this.result, null, null);
    }

    /**
     * The {@link Result} is a subclass to manage the recipe once finished.
     */
    public static class Result implements FinishedRecipe {
        /**
         * the recipe name / id
         */
        private final ResourceLocation id;
        /**
         * The recipe ingredients list
         */
        private final JsonObject ingredient;
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

        public Result(ResourceLocation id, JsonObject ingredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
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
            pJson.add("ingredients", this.ingredient);
            pJson.add("results", this.results);
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
            return AllRecipeTypes.PRESSING.getSerializer();
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
