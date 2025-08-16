package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Here is the builder to datagen freezing recipes
 */
public class FreezingRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public FreezingRecipeBuilder(Ingredient ingredient, ItemLike result, int count) {
        this.ingredient = ingredient;
        this.result = result;
        this.count = count;
    }

    public static FreezingRecipeBuilder freezing(Ingredient ingredient, ItemLike result) {
        return new FreezingRecipeBuilder(ingredient, result, 1);
    }
    public static FreezingRecipeBuilder freezing(Ingredient ingredient, ItemLike result, int pCount) {
        return new FreezingRecipeBuilder(ingredient, result, pCount);
    }

    public FreezingRecipeBuilder unlocks(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.ingredient.isEmpty()) throw new IllegalStateException("No ingredients for freezing recipe " + pId + "!");
        if (this.result == null) throw new IllegalStateException("No result for freezing recipe " + pId + "!");
        if (this.count == 0) throw new IllegalStateException("Result count is 0 for freezing recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("freezing/"), this.result, this.count, this.ingredient, this.advancement, id.withPrefix("recipes/freezing/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final ItemLike result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, ItemLike result, int pCount, Ingredient ingredient, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.count = pCount;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient.toJson());
            JsonObject result1 = new JsonObject();
            result1.addProperty("item", BuiltInRegistries.ITEM.getKey(((Item) this.result)).toString());
            if (count > 1) {
                result1.addProperty("count", count);
            }
            pJson.add("output", result1);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.FREEZING_SERIALIZER.get();
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
