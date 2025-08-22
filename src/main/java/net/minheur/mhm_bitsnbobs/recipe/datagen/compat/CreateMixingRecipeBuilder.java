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
import java.util.Map;
import java.util.function.Consumer;

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

public class CreateMixingRecipeBuilder {
    private final List<JsonObject> ingredients = new ArrayList<>();
    private final List<JsonObject> results = new ArrayList<>();
    private final HeatCondition heatCondition;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateMixingRecipeBuilder(HeatCondition heatCondition) {
        this.heatCondition = heatCondition;
    }

    public static CreateMixingRecipeBuilder mix(HeatCondition heatCondition) {
        return new CreateMixingRecipeBuilder(heatCondition);
    }
    public static CreateMixingRecipeBuilder mix() {
        return new CreateMixingRecipeBuilder(HeatCondition.NONE);
    }

    public CreateMixingRecipeBuilder addIngredient(ItemLike ingredient, int count) {
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(ingredient));
        if (count > 1) json.addProperty("count", count);
        ingredients.add(json);
        return this;
    }
    public CreateMixingRecipeBuilder addIngredient(TagKey<Item> tag, int count) {
        JsonObject json = new JsonObject();
        json.addProperty("tag", tag.location().toString());
        if (count > 1) json.addProperty("count", count);
        ingredients.add(json);
        return this;
    }
    public CreateMixingRecipeBuilder addIngredient(ItemLike ingredient) {
        return addIngredient(ingredient, 1);
    }
    public CreateMixingRecipeBuilder addIngredient(TagKey<Item> tag) {
        return addIngredient(tag, 1);
    }
    public CreateMixingRecipeBuilder addFluidIngredient(String fluid, int amount) {
        JsonObject json = new JsonObject();
        json.addProperty("fluid", fluid);
        json.addProperty("amount", amount);
        ingredients.add(json);
        return this;
    }

    public CreateMixingRecipeBuilder addResult(ItemLike result, int count, float chance) {
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(result));
        if (count > 1) json.addProperty("count", count);
        if (chance <= 0) throw new IllegalStateException("Chance is equal or lower than 0 !");
        if (!(chance >= 1)) json.addProperty("chance", chance);
        results.add(json);
        return this;
    }
    public CreateMixingRecipeBuilder addResult(ItemLike result, int count) {
        return addResult(result, count, 1f);
    }
    public CreateMixingRecipeBuilder addResult(ItemLike result, float chance) {
        return addResult(result, 1, chance);
    }
    public CreateMixingRecipeBuilder addResult(ItemLike result){
        return addResult(result, 1, 1f);
    }
    public CreateMixingRecipeBuilder addResult(ItemLike result, JsonObject nbt) {
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(result));
        json.add("nbt", nbt);
        results.add(json);
        return this;
    }
    public CreateMixingRecipeBuilder addFluidResult(String fluid, int amount, float chance) {
        JsonObject json = new JsonObject();
        json.addProperty("fluid", fluid);
        json.addProperty("amount", amount);
        if (chance <= 0) throw new IllegalStateException("Chance is equal or lower than 0 !");
        if (!(chance >= 1)) json.addProperty("chance", chance);
        results.add(json);
        return this;
    }
    public CreateMixingRecipeBuilder addFluidResult(String fluid, int amount) {
        return addFluidResult(fluid, amount, 1f);
    }

    public CreateMixingRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        for (JsonObject json : results) {
            if (json == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        }
        for (JsonObject json : ingredients) {
            if (json == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        }
        if (heatCondition == null) throw new IllegalStateException("Invalid recipe for crushing recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/mixing/"), this.ingredients, this.results, this.heatCondition, this.advancement, id.withPrefix("recipes/create/mixing/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<JsonObject> ingredients;
        private final List<JsonObject> results;
        private final HeatCondition heatCondition;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<JsonObject> ingredients, List<JsonObject> results, HeatCondition heatCondition, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.results = results;
            this.heatCondition = heatCondition;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            JsonArray results = new JsonArray();
            for (JsonObject ingredient : this.ingredients) ingredients.add(ingredient);
            for (JsonObject result : this.results) results.add(result);

            if (!(heatCondition == HeatCondition.NONE)) {
                pJson.addProperty("heatRequirement", heatCondition == HeatCondition.HEATED ? "heated" : "superheated");
            }

            pJson.add("ingredients", ingredients);
            pJson.add("results", results);
        }

        /**
         * Gets the ID for the recipe.
         */
        @Override
        public ResourceLocation getId() {
            return id;
        }

        /**
         * Get the recipe serializer, not the type !
         * @return the recipe serializer
         */
        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MIXING.getSerializer();
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
