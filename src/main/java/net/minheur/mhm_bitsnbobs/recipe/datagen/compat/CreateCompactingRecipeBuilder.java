package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

public class CreateCompactingRecipeBuilder {
    private final List<JsonObject> ingredients = new ArrayList<>();
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateCompactingRecipeBuilder(ItemLike result, int count) {
        this.result = result;
        this.count = count;
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

    public CreateCompactingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        for (JsonObject ingredient : ingredients) {
            if (ingredient == null) throw new IllegalStateException("Invalid recipe for compacting recipe " + pId + "!");
            if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<JsonObject> ingredients;
        private final ItemLike result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<JsonObject> ingredients, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);
            JsonObject result = new JsonObject();
            result.addProperty("item", getBuiltInItemRegistry(this.result));
            if (this.count > 1) result.addProperty("count", );
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
