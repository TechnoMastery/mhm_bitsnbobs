package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.function.Consumer;

/**
 * Here is the builder to datagen atomical stabilizator recipes
 */
public class AtomicalStabilizationRecipeBuilder extends AResultRecipeBuilder {
    private final JsonObject leftIngredient;
    private final JsonObject rightIngredient;
    private final JsonObject glueIngredient;

    public AtomicalStabilizationRecipeBuilder(JsonObject leftIngredient, JsonObject rightIngredient, JsonObject glueIngredient, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "atomical_stabilization", result);
        this.leftIngredient = leftIngredient;
        this.rightIngredient = rightIngredient;
        this.glueIngredient = glueIngredient;
    }

    public static AtomicalStabilizationRecipeBuilder stabilization(JsonObject leftIngredient, JsonObject rightIngredient, JsonObject glueIngredient, JsonObject result) {
        return new AtomicalStabilizationRecipeBuilder(leftIngredient, rightIngredient, glueIngredient, result);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                leftIngredient.isJsonNull() ||
                rightIngredient.isJsonNull() ||
                glueIngredient.isJsonNull();
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), leftIngredient, rightIngredient, glueIngredient, result, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends ResultRecipeResult {
        private final JsonObject leftIngredient;
        private final JsonObject rightIngredient;
        private final JsonObject glueIngredient;

        public Result(ResourceLocation id, JsonObject leftIngredient, JsonObject rightIngredient, JsonObject glueIngredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result);
            this.leftIngredient = leftIngredient;
            this.rightIngredient = rightIngredient;
            this.glueIngredient = glueIngredient;
        }

        /**
         * serialize your recipe data
         * @param pJson your finished recipe json
         */
        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("left", leftIngredient);
            pJson.add("right", rightIngredient);
            pJson.add("glue", glueIngredient);
            pJson.add("output", result);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.ATOMICAL_STABILIZATOR_SERIALIZER.get();
        }
    }
}
