package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class IncubatorRecipeBuilder {
    private final Ingredient ingredient;
    private final ItemLike catalyzer;
    private final ItemLike result;
    private final int count;


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

    private void ensureValid(ResourceLocation pId) {
        if (this.ingredient.isEmpty()) throw new IllegalStateException("No ingredients for incubating recipe " + pId + "!");
        if (this.result == null) throw new IllegalStateException("No result for incubating recipe " + pId + "!");
        if (this.count == 0) throw new IllegalStateException("Result count is 0 for incubating recipe " + pId + "!");
        if (this.catalyzer == null) throw new IllegalStateException("No catalyzer for incubating recipe " + pId + "!");
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        consumer.accept(new Result(id, this.ingredient, this.catalyzer, this.result, this.count));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final ItemLike catalyzer;
        private final ItemLike result;
        private final int count;

        public Result(ResourceLocation id, Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count) {
            this.id = id;
            this.ingredient = ingredient;
            this.catalyzer = catalyzer;
            this.result = result;
            this.count = count;
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
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }

}
