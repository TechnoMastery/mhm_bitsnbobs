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

public class IncubatorRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemLike catalyzer;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public IncubatorRecipeBuilder(Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count) {
        this.ingredient = ingredient;
        this.catalyzer = catalyzer;
        this.result = result;
        this.count = count;
    }

    public static IncubatorRecipeBuilder incubation(Ingredient ingredient, ItemLike catalyzer, ItemLike result) {
        return new IncubatorRecipeBuilder(ingredient, catalyzer, result, 1);
    }
    public static IncubatorRecipeBuilder incubation(Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count) {
        return new IncubatorRecipeBuilder(ingredient, catalyzer, result, count);
    }

    public IncubatorRecipeBuilder unlocks(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.ingredient.isEmpty() ||
        this.result == null ||
        this.count == 0 ||
        this.catalyzer == null) throw new IllegalStateException("Invalid recipe for incubating recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("incubating/"), this.ingredient, this.catalyzer, this.result, this.count, this.advancement, id.withPrefix("recipes/incubation/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final ItemLike catalyzer;
        private final ItemLike result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.catalyzer = catalyzer;
            this.result = result;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }


        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient.toJson());
            JsonObject catalyzer = new JsonObject();
            catalyzer.addProperty("item", BuiltInRegistries.ITEM.getKey(((Item) this.catalyzer)).toString());
            pJson.add("catalyzer", catalyzer);
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
            return ModRecipes.INCUBATING_SERIALIZER.get();
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
