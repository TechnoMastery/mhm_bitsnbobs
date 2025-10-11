package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.AbstractResultRecipeBuilder;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

/**
 * Here is the builder to datagen atomical stabilizator recipes
 */
public class AtomicalStabilizationRecipeBuilder extends AbstractResultRecipeBuilder {
    private final ItemLike leftIngredient;
    private final ItemLike rightIngredient;
    private final ItemLike glueIngredient;

    public AtomicalStabilizationRecipeBuilder(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int count) {
        super(MhmBitsnbobs.MOD_ID, "atomical_stabilization", result, count);
        this.leftIngredient = leftIngredient;
        this.rightIngredient = rightIngredient;
        this.glueIngredient = glueIngredient;
    }

    public static AtomicalStabilizationRecipeBuilder stabilization(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result) {
        return new AtomicalStabilizationRecipeBuilder(leftIngredient, rightIngredient, glueIngredient, result, 1);
    }
    public static AtomicalStabilizationRecipeBuilder stabilization(ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int pCount) {
        return new AtomicalStabilizationRecipeBuilder(leftIngredient, rightIngredient, glueIngredient, result, pCount);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                leftIngredient == null ||
                rightIngredient == null ||
                glueIngredient == null;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), leftIngredient, rightIngredient, glueIngredient, result, count, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static class Result extends ResultRecipeResult {
        private final ItemLike leftIngredient;
        private final ItemLike rightIngredient;
        private final ItemLike glueIngredient;

        public Result(ResourceLocation id, ItemLike leftIngredient, ItemLike rightIngredient, ItemLike glueIngredient, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, count, advancement, advancementId);
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
        public RecipeSerializer<?> getType() {
            return ModRecipes.ATOMICAL_STABILIZATOR_SERIALIZER.get();
        }
    }
}
