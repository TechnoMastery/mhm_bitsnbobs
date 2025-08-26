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

public class CreateMillingRecipeBuilder {
    private final JsonObject ingredient;
    private final List<JsonObject> results = new ArrayList<>();
    private final int processTime;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateMillingRecipeBuilder(JsonObject ingredient, int processTime) {
        this.ingredient = ingredient;
        this.processTime = processTime;
    }

    public static CreateMillingRecipeBuilder milling(ItemLike ingredient, int processTime) {
        JsonObject ingredientJson = new JsonObject();
        ingredientJson.addProperty("item", getBuiltInItemRegistry(ingredient));
        return new CreateMillingRecipeBuilder(ingredientJson, processTime);
    }

    public CreateMillingRecipeBuilder addResult(ItemLike result, int amount, float chance) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("item", getBuiltInItemRegistry(result));
        if (amount > 1) resultJson.addProperty("count", amount);
        if (chance <= 0)  throw new IllegalStateException("Chance is equal or lower than 0 !");
        if (!(chance >= 1)) resultJson.addProperty("chance", chance);
        results.add(resultJson);
        return this;
    }
    public CreateMillingRecipeBuilder addResult(ItemLike result, int amount) {
        addResult(result, amount, 1f);
        return this;
    }
    public CreateMillingRecipeBuilder addResult(ItemLike result, float chance) {
        addResult(result, 1, chance);
        return this;
    }
    public CreateMillingRecipeBuilder addResult(ItemLike result) {
        addResult(result, 1, 1f);
        return this;
    }

    public CreateMillingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        for (JsonObject json : results) {
            if (json == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        }
        if (ingredient == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/milling/"), this.ingredient, this.results, this.processTime, this.advancement, id.withPrefix("recipes/create/milling/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final JsonObject ingredient;
        private final List<JsonObject> results;
        private final int processTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, JsonObject ingredient, List<JsonObject> results, int processTime, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.results = results;
            this.processTime = processTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) {
                results.add(result);
            }

            pJson.add("results", results);
            pJson.addProperty("processingTime", processTime);
            pJson.add("ingredients", ingredient);
        }

        /**
         * Gets the ID for the recipe.
         */
        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MILLING.getSerializer();
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement()} is
         * non-null.
         */
        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
