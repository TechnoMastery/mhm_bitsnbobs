package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
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

    private void ensureValid(ResourceLocation pId) {
        if (this.ingredient.isEmpty()) throw new IllegalStateException("No ingredients for freezing recipe " + pId + "!");
        if (this.result == null) throw new IllegalStateException("No result for freezing recipe " + pId + "!");
        if (this.count == 0) throw new IllegalStateException("Result count is 0 for freezing recipe " + pId + "!");
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        consumer.accept(new Result(id, this.result, this.count, this.ingredient));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final ItemLike result;
        private final int count;

        public Result(ResourceLocation id, ItemLike result, int pCount, Ingredient ingredient) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.count = pCount;
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
            return null; // TODO: add this !!!!!
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }

}
