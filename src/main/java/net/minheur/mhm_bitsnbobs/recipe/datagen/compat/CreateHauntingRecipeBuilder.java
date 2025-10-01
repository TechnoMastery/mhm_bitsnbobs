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

public class CreateHauntingRecipeBuilder {
    private final JsonObject ingredient = new JsonObject();
    private final List<JsonObject> results = new ArrayList<>();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public static CreateHauntingRecipeBuilder haunt() {
        return new CreateHauntingRecipeBuilder();
    }

    public CreateHauntingRecipeBuilder addIngredient(ItemLike ingredient) {
        this.ingredient.addProperty("item", getBuiltInItemRegistry(ingredient));
        return this;
    }

    public CreateHauntingRecipeBuilder addResult(ItemLike result, int count, float chance) {
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(result));
        if (count > 1) json.addProperty("count", count);
        if ((chance > 0) && !(chance >= 1)) json.addProperty("chance", chance);
        results.add(json);
        return this;
    }
    public CreateHauntingRecipeBuilder addResult(ItemLike result, int count) {
        return addResult(result, count, 1f);
    }
    public CreateHauntingRecipeBuilder addResult(ItemLike result, float chance) {
        return addResult(result, 1, chance);
    }
    public CreateHauntingRecipeBuilder addResult(ItemLike result) {
        return addResult(result, 1, 1f);
    }

    public CreateHauntingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation id) {
        for (JsonObject result : results) {
            if (result == null) throw new IllegalStateException("Recipe invalid : " + id);
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/haunting/"), this.ingredient, this.results, this.advancement, id.withPrefix("recipes/create/haunting/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final JsonObject ingredient;
        private final List<JsonObject> results;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;


        public Result(ResourceLocation id, JsonObject ingredient, List<JsonObject> results, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.results = results;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray results = new JsonArray();
            for (JsonObject result : this.results) {
                results.add(result);
            }

            JsonArray ingredientArray = new JsonArray();
            ingredientArray.add(ingredient);

            pJson.add("ingredients", ingredientArray);
            pJson.add("results", results);
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
            return AllRecipeTypes.HAUNTING.getSerializer();
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
