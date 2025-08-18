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

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

/**
 * Here is the builder to datagen atomical stabilizator recipes
 */
public class AtomicalStabilizationRecipeBuilder {
    private final ItemLike leftIngredient;
    private final ItemLike rightIngredient;
    private final ItemLike glueIngredient;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public AtomicalStabilizationRecipeBuilder(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int count) {
        this.leftIngredient = leftIngredient;
        this.rightIngredient = rightIngredient;
        this.glueIngredient = glueIngredient;
        this.result = result;
        this.count = count;
    }

    public static AtomicalStabilizationRecipeBuilder stabilization(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result) {
        return new AtomicalStabilizationRecipeBuilder(leftIngredient, rightIngredient, glueIngredient, result, 1);
    }
    public static AtomicalStabilizationRecipeBuilder stabilization(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int pCount) {
        return new AtomicalStabilizationRecipeBuilder(leftIngredient, rightIngredient, glueIngredient, result, pCount);
    }

    public AtomicalStabilizationRecipeBuilder unlocks(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    /**
     * Make sure your recipe is valid
     * @param pId your recipe's id / name
     */
    private void ensureValid(ResourceLocation pId) {
        if (this.leftIngredient == null ||
        this.rightIngredient == null ||
        this.glueIngredient == null ||
        this.result == null ||
        this.count == 0) throw new IllegalStateException("Invalid recipe for atomical stabilization recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    /**
     * Saves your recipe.
     * @param consumer recipe consumer
     * @param id your recipe id / name. Take a {@link ResourceLocation}.
     */
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("atomical_stabilization/"), this.leftIngredient, this.rightIngredient, this.glueIngredient, this.result, this.count, this.advancement, id.withPrefix("recipes/atomical_stabilization/")));
    }
    /**
     * Saves your recipe.
     * @param consumer recipe consumer
     * @param id your recipe id / name. Takes a {@link String} and will make a {@link ResourceLocation} taking in {@code mhm_bitsnbobs} and your id.
     */
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        this.save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemLike leftIngredient;
        private final ItemLike rightIngredient;
        private final ItemLike glueIngredient;
        private final ItemLike result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.leftIngredient = leftIngredient;
            this.rightIngredient = rightIngredient;
            this.glueIngredient = glueIngredient;
            this.result = result;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        /**
         * serialize your recipe data
         * @param pJson your finished recipe json
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonObject leftIngredient = new JsonObject();
            JsonObject rightIngredient = new JsonObject();
            JsonObject glueIngredient = new JsonObject();
            JsonObject result = new JsonObject();
            leftIngredient.addProperty("item", getBuiltInItemRegistry(this.leftIngredient));
            rightIngredient.addProperty("item", getBuiltInItemRegistry(this.rightIngredient));
            glueIngredient.addProperty("item", getBuiltInItemRegistry(this.glueIngredient));
            result.addProperty("item", getBuiltInItemRegistry(this.result));
            if (count > 1) result.addProperty("count", this.count);
            pJson.add("left", leftIngredient);
            pJson.add("right", rightIngredient);
            pJson.add("glue", glueIngredient);
            pJson.add("output", result);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.ATOMICAL_STABILIZATOR_SERIALIZER.get();
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
