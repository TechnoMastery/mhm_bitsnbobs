package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.AbstractSingleIngredientRecipeBuilder;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Here is the builder to datagen freezing recipes
 */
public class FreezingRecipeBuilder extends AbstractSingleIngredientRecipeBuilder {

    public FreezingRecipeBuilder(Ingredient ingredient, ItemLike result, int count) {
        super("freezing", result, count, ingredient);
    }

    public static FreezingRecipeBuilder freezing(Ingredient ingredient, ItemLike result) {
        return new FreezingRecipeBuilder(ingredient, result, 1);
    }
    public static FreezingRecipeBuilder freezing(Ingredient ingredient, ItemLike result, int pCount) {
        return new FreezingRecipeBuilder(ingredient, result, pCount);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Advancement.Builder builder, ResourceLocation resourceLocation1) {
        consumer.accept(new Result(resourceLocation, this.result, this.count, this.ingredient, this.advancement, resourceLocation));
    }

    public static class Result extends SingleIngredientResult {
        public Result(ResourceLocation id, ItemLike result, int pCount, Ingredient ingredient, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, pCount, advancement, advancementId, ingredient);
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient.toJson());
            JsonObject result1 = new JsonObject();
            result1.addProperty("item", getBuiltInItemRegistry(this.result));
            if (count > 1) result1.addProperty("count", count);
            pJson.add("output", result1);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.FREEZING_SERIALIZER.get();
        }
    }

}
