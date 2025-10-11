package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.AbstractSingleIngredientRecipeBuilder;

import java.util.function.Consumer;

public class IncubatorRecipeBuilder extends AbstractSingleIngredientRecipeBuilder {
    private final ItemLike catalyzer;

    public IncubatorRecipeBuilder(Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count) {
        super(MhmBitsnbobs.MOD_ID, "incubation", result, count, ingredient);
        this.catalyzer = catalyzer;
    }

    public static IncubatorRecipeBuilder incubation(Ingredient ingredient, ItemLike catalyzer, ItemLike result) {
        return new IncubatorRecipeBuilder(ingredient, catalyzer, result, 1);
    }
    public static IncubatorRecipeBuilder incubation(Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count) {
        return new IncubatorRecipeBuilder(ingredient, catalyzer, result, count);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || catalyzer == null;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(resourceLocation.withPrefix("incubating/"), this.ingredient, this.catalyzer, this.result, this.count, this.advancement, resourceLocation.withPrefix("recipes/incubation/")));
    }

    public static class Result extends SingleIngredientResult {
        private final ItemLike catalyzer;

        public Result(ResourceLocation id, Ingredient ingredient, ItemLike catalyzer, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, count, advancement, advancementId, ingredient);
            this.catalyzer = catalyzer;
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
        public RecipeSerializer<?> getType() {
            return ModRecipes.INCUBATING_SERIALIZER.get();
        }
    }

}
