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

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public class CreateDeployingRecipeBuilder {
    private final JsonObject mainIngredient = new JsonObject();
    private final JsonObject deployIngredient = new JsonObject();
    private final JsonObject result = new JsonObject();
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public static CreateDeployingRecipeBuilder deploy() {
        return new CreateDeployingRecipeBuilder();
    }

    public CreateDeployingRecipeBuilder addMainIngredient(ItemLike ingredient) {
        mainIngredient.addProperty("item", getBuiltInItemRegistry(ingredient));
        return this;
    }
    public CreateDeployingRecipeBuilder addDeployIngredient(ItemLike ingredient) {
        deployIngredient.addProperty("item", getBuiltInItemRegistry(ingredient));
        return this;
    }
    public CreateDeployingRecipeBuilder addDeployIngredient(TagKey<Item> ingredient) {
        deployIngredient.addProperty("tag", ingredient.location().toString());
        return this;
    }

    public CreateDeployingRecipeBuilder addResult(ItemLike result) {
        this.result.addProperty("item", getBuiltInItemRegistry(result));
        return this;
    }

    public CreateDeployingRecipeBuilder unlock(String key, CriterionTriggerInstance pCriterion) {
        advancement.addCriterion(key, pCriterion);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/deploying/"), this.mainIngredient, this.deployIngredient, this.result, this.advancement, id.withPrefix("recipes/create/deploying/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, this.mainIngredient, this.deployIngredient, this.result, null, null);
    }

    private void ensureValid(ResourceLocation pId) {
        if (mainIngredient.isJsonNull()) throw new IllegalStateException("Invalid recipe for deploying recipe " + pId + "!");
        if (deployIngredient.isJsonNull()) throw new IllegalStateException("Invalid recipe for deploying recipe " + pId + "!");
        if (result.isJsonNull()) throw new IllegalStateException("Invalid recipe for deploying recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + pId);
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final JsonObject mainIngredient;
        private final JsonObject deployIngredient;
        private final JsonObject result;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, JsonObject mainIngredient, JsonObject deployIngredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.mainIngredient = mainIngredient;
            this.deployIngredient = deployIngredient;
            this.result = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }


        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray ingredients = new JsonArray();
            ingredients.add(mainIngredient);
            ingredients.add(deployIngredient);

            JsonArray result = new JsonArray();
            result.add(this.result);

            pJson.add("ingredients", ingredients);
            pJson.add("results", result);
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
            return AllRecipeTypes.DEPLOYING.getSerializer();
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
