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
public class GemPolishingRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public GemPolishingRecipeBuilder(Ingredient ingredient, ItemLike result, int count) {
        this.ingredient = ingredient;
        this.result = result;
        this.count = count;
    }

    public static GemPolishingRecipeBuilder gemPolishing(Ingredient ingredient, ItemLike result) {
        return new GemPolishingRecipeBuilder(ingredient, result, 1);
    }
    public static GemPolishingRecipeBuilder gemPolishing(Ingredient ingredient, ItemLike result, int pCount) {
        return new GemPolishingRecipeBuilder(ingredient, result, pCount);
    }

    public GemPolishingRecipeBuilder unlocks(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.ingredient.isEmpty()) throw new IllegalStateException("No ingredients for gem polishing recipe " + pId + "!");
        if (this.result == null) throw new IllegalStateException("No result for gem polishing recipe " + pId + "!");
        if (this.count == 0) throw new IllegalStateException("Result count is 0 for gem polishing recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("gem_polishing/"), this.ingredient, this.result, this.count, this.advancement, id.withPrefix("recipes/gem_polishing/")));
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

        public Result(ResourceLocation id, Ingredient ingredient, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient.toJson());
            JsonObject result = new JsonObject();
            result.addProperty("item", BuiltInRegistries.ITEM.getKey(((Item) this.result)).toString());
            if (count > 1) result.addProperty("count", count);
            pJson.add("output", result);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.GEM_POLISHING_SERIALIZER.get();
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
