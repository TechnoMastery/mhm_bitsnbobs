package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.AbstractSingleIngredientRecipeBuilder;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Here is the builder to datagen freezing recipes
 */
public class GemPolishingRecipeBuilder extends AbstractSingleIngredientRecipeBuilder {
    public GemPolishingRecipeBuilder(Ingredient ingredient, ItemLike result, int count) {
        super(MhmBitsnbobs.MOD_ID, "gem_polishing", result, count, ingredient);
    }

    public static GemPolishingRecipeBuilder gemPolishing(Ingredient ingredient, ItemLike result) {
        return new GemPolishingRecipeBuilder(ingredient, result, 1);
    }
    public static GemPolishingRecipeBuilder gemPolishing(Ingredient ingredient, ItemLike result, int pCount) {
        return new GemPolishingRecipeBuilder(ingredient, result, pCount);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), this.ingredient, this.result, this.count, this.advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends SingleIngredientResult {
        public Result(ResourceLocation id, Ingredient ingredient, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, count, advancement, advancementId, ingredient);
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredients", ingredient.toJson());
            JsonObject result = new JsonObject();
            result.addProperty("item", getBuiltInItemRegistry(this.result));
            if (count > 1) result.addProperty("count", count);
            pJson.add("output", result);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.GEM_POLISHING_SERIALIZER.get();
        }
    }

}
