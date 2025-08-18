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

public class CreateCompactingRecipeBuilder {
    private final List<JsonObject> ingredients = new ArrayList<>();
    private final List<JsonObject> results = new ArrayList<>();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateCompactingRecipeBuilder() {}

    public static CreateCompactingRecipeBuilder createCompacting() {
        return new CreateCompactingRecipeBuilder();
    }

    public CreateCompactingRecipeBuilder addIngredient(ItemLike item) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", getBuiltInItemRegistry(item));
        ingredients.add(ingredient);
        return this;
    }
    public CreateCompactingRecipeBuilder addFluidIngredient(String fluid, int amount) {
        JsonObject fluidIngredient = new JsonObject();
        fluidIngredient.addProperty("fluid", fluid);
        fluidIngredient.addProperty("amount", amount);
        ingredients.add(fluidIngredient);
        return this;
    }

    public CreateCompactingRecipeBuilder addTagIngredient(TagKey<Item> tag) {
        JsonObject tagIngredient = new JsonObject();
        tagIngredient.addProperty("tag", tag.location().toString());
        ingredients.add(tagIngredient);
        return this;
    }

    public CreateCompactingRecipeBuilder addResult(ItemLike item, int count) {
        JsonObject result = new JsonObject();
        result.addProperty("item", getBuiltInItemRegistry(item));
        if (count > 1) result.addProperty("count", count);
        results.add(result);
        return this;
    }
    public CreateCompactingRecipeBuilder addResult(ItemLike item) {
        this.addResult(item, 1);
        return this;
    }
    public CreateCompactingRecipeBuilder addFluidResult(String fluid, int amount) {
        JsonObject fluidResult = new JsonObject();
        fluidResult.addProperty("fluid", fluid);
        fluidResult.addProperty("amount", amount);
        results.add(fluidResult);
        return this;
    }

    public CreateCompactingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        for (JsonObject ingredient : ingredients) {
            if (ingredient == null) throw new IllegalStateException("Invalid recipe for compacting recipe " + pId + "!");
        }
        for (JsonObject result : results) {
            if (result == null) throw new IllegalStateException("Invalid recipe for compacting recipe " + pId + "!");
        }
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/compacting/"), this.ingredients, this.results, this.advancement, id.withPrefix("recipes/create/compacting/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<JsonObject> ingredients;
        private final List<JsonObject> results;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> result, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.results = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);

            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) results.add(result);

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.COMPACTING.getSerializer();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
