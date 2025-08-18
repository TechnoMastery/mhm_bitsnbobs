package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

/**
 * Recipe builder for create's compacting recipe
 */
public class CreateCompactingRecipeBuilder {
    /**
     * The List of ingredient items
     */
    private final List<JsonObject> ingredients = new ArrayList<>();
    /**
     * The list of result items
     */
    private final List<JsonObject> results = new ArrayList<>();
    /**
     * The bound advancement
     */
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    /**
     * The heatRequirement
     */
    private HeatCondition heatCondition = HeatCondition.NONE;

    public CreateCompactingRecipeBuilder() {}

    public static CreateCompactingRecipeBuilder createCompacting() {
        return new CreateCompactingRecipeBuilder();
    }

    /**
     * Adding an ingredient to the list
     * @param item the ItemLike you want to add
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addIngredient(ItemLike item) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", getBuiltInItemRegistry(item));
        ingredients.add(ingredient);
        return this;
    }
    /**
     * Adding a fluid to the ingredient list
     * @param fluid the fluid ID you want to add (ex. {@code minecraft:lava})
     * @param amount quantity of fluid needed (in mB)
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addFluidIngredient(String fluid, int amount) {
        JsonObject fluidIngredient = new JsonObject();
        fluidIngredient.addProperty("fluid", fluid);
        fluidIngredient.addProperty("amount", amount);
        ingredients.add(fluidIngredient);
        return this;
    }

    /**
     * Adding a whole tag to the ingredients
     * @param tag the tag you want to add
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addTagIngredient(TagKey<Item> tag) {
        JsonObject tagIngredient = new JsonObject();
        tagIngredient.addProperty("tag", tag.location().toString());
        ingredients.add(tagIngredient);
        return this;
    }

    /**
     * Adding a result to your recipe
     * @param item the result you want
     * @param count the amount you want
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addResult(ItemLike item, int count) {
        JsonObject result = new JsonObject();
        result.addProperty("item", getBuiltInItemRegistry(item));
        if (count > 1) result.addProperty("count", count);
        results.add(result);
        return this;
    }
    /**
     * Adding a result to your recipe. Count is 1
     * @param item the result you want
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addResult(ItemLike item) {
        this.addResult(item, 1);
        return this;
    }
    /**
     * Adding a fluid result
     * @param fluid the fluis you want (ex. {@code minecraft:lava})
     * @param amount quantity of fluid given
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addFluidResult(String fluid, int amount) {
        JsonObject fluidResult = new JsonObject();
        fluidResult.addProperty("fluid", fluid);
        fluidResult.addProperty("amount", amount);
        results.add(fluidResult);
        return this;
    }

    /**
     * Adding a heat requirement. If not set, {@code HeatCondition.NONE} is used.
     * @param condition the heat needed
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder addHeatCondition(HeatCondition condition) {
        this.heatCondition = condition;
        return this;
    }

    /**
     * Set the condition you need to unlock the recipe
     * @param pKey the name
     * @param pCriterion the criterion
     * @return the current recipe
     */
    public CreateCompactingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    /**
     * Make sure there is no {@code null} ingredient / result, and that you set a criterion
     * @param pId the recipe id
     */
    private void ensureValid(ResourceLocation pId) {
        for (JsonObject ingredient : ingredients) {
            if (ingredient == null) throw new IllegalStateException("Invalid recipe for compacting recipe " + pId + "!");
        }
        for (JsonObject result : results) {
            if (result == null) throw new IllegalStateException("Invalid recipe for compacting recipe " + pId + "!");
        }
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
        consumer.accept(new Result(id.withPrefix("create/compacting/"), this.ingredients, this.results, this.advancement, id.withPrefix("recipes/create/compacting/"), heatCondition));
    }
    /**
     * Saves the recipe
     * @param consumer the recipe consumer
     * @param id the id / name of the recipe - asks for a {@link String}. It will create a {@link ResourceLocation} with modid {@code mhm_bitsnbobs} and the id.
     */
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
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
        private final List<JsonObject> ingredients;
        /**
         * The recipe results list
         */
        private final List<JsonObject> results;
        /**
         * The recipe advancement
         */
        private final Advancement.Builder advancement;
        /**
         * The recipe advancement's id
         */
        private final ResourceLocation advancementId;
        /**
         * The recipe heat requirement
         */
        private final HeatCondition heatCondition;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> result, Advancement.Builder advancement, ResourceLocation advancementId, HeatCondition heatCondition) {
            this.id = id;
            this.ingredients = ingredients;
            this.results = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.heatCondition = heatCondition;
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

            if (heatCondition != HeatCondition.NONE) {
                if (heatCondition == HeatCondition.HEATED) pJson.addProperty("heatRequirement", "heated");
                else pJson.addProperty("heatRequirement", "superHeated");
            }

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
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
            return AllRecipeTypes.COMPACTING.getSerializer();
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
