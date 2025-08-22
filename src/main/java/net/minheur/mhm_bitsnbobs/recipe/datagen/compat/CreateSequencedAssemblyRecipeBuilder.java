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

public class CreateSequencedAssemblyRecipeBuilder {
    private final JsonObject ingredients;
    private final JsonObject results;
    private final JsonObject transitionalItem;
    private final List<JsonObject> steps = new ArrayList<>();
    private final int loops;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateSequencedAssemblyRecipeBuilder(JsonObject ingredients, JsonObject results, JsonObject transitionalItem, int loops) {
        this.ingredients = ingredients;
        this.results = results;
        this.transitionalItem = transitionalItem;
        this.loops = loops;
    }

    public static CreateSequencedAssemblyRecipeBuilder sequence(ItemLike ingredient, ItemLike result, ItemLike transitionalItem, int loops) {
        JsonObject ingredientObject = new JsonObject();
        JsonObject resultObject = new JsonObject();
        JsonObject transitionalItemObject = new JsonObject();
        ingredientObject.addProperty("item", getBuiltInItemRegistry(ingredient));
        resultObject.addProperty("item", getBuiltInItemRegistry(result));
        transitionalItemObject.addProperty("item", getBuiltInItemRegistry(transitionalItem));
        return new CreateSequencedAssemblyRecipeBuilder(ingredientObject, resultObject, transitionalItemObject, loops);
    }
    public static CreateSequencedAssemblyRecipeBuilder sequence(TagKey<Item> ingredient, ItemLike result, ItemLike transitionalItem, int loops) {
        JsonObject ingredientObject = new JsonObject();
        JsonObject resultObject = new JsonObject();
        JsonObject transitionalItemObject = new JsonObject();
        ingredientObject.addProperty("tag", ingredient.location().toString());
        resultObject.addProperty("item", getBuiltInItemRegistry(result));
        transitionalItemObject.addProperty("item", getBuiltInItemRegistry(transitionalItem));
        return new CreateSequencedAssemblyRecipeBuilder(ingredientObject, resultObject, transitionalItemObject, loops);
    }

    public CreateSequencedAssemblyRecipeBuilder addStep(JsonObject step) {
        steps.add(step);
        return this;
    }

    public CreateSequencedAssemblyRecipeBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        for (JsonObject step : steps) {
            if (step == null) throw new IllegalStateException("Invalid recipe for sequence recipe " + pId + "!");
        }
        if (ingredients == null) throw new IllegalStateException("Invalid recipe for sequence recipe " + pId + "!");
        if (results == null) throw new IllegalStateException("Invalid recipe for sequence recipe " + pId + "!");
        if (transitionalItem == null) throw new IllegalStateException("Invalid recipe for sequence recipe " + pId + "!");
        if (loops == 0) throw new IllegalStateException("Invalid recipe for sequence recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/sequence/"), this.ingredients, this.results, this.transitionalItem, this.steps, this.loops, this.advancement, id.withPrefix("recipe/create/sequence/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final JsonObject ingredient;
        private final JsonObject result;
        private final JsonObject transitionalItem;
        private final List<JsonObject> steps;
        private final int loops;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, JsonObject ingredient, JsonObject result, JsonObject transitionalItem, List<JsonObject> steps, int loops, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.transitionalItem = transitionalItem;
            this.steps = steps;
            this.loops = loops;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }


        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredient", ingredient);
            pJson.add("results", result);
            pJson.add("transitionalItem", transitionalItem);
            pJson.addProperty("loops", loops);
            JsonArray steps = new JsonArray();
            for (JsonObject step : this.steps) {
                steps.add(step);
            }
            pJson.add("sequence", steps);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.SEQUENCED_ASSEMBLY.getSerializer();
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
