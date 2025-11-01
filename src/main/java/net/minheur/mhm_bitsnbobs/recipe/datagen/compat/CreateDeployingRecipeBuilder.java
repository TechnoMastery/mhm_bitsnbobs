package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.builders.JsonBuilder;
import net.minheur.techno_lib.datagen.recipe.ICreateSequenceRecipe;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.function.Consumer;

public class CreateDeployingRecipeBuilder extends AResultRecipeBuilder implements ICreateSequenceRecipe {
    private final JsonObject mainIngredient;
    private final JsonObject deployIngredient;

    public CreateDeployingRecipeBuilder(JsonObject mainIngredient, JsonObject deployIngredient, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "create/deploying", result);
        this.mainIngredient = mainIngredient;
        this.deployIngredient = deployIngredient;
    }

    public static CreateDeployingRecipeBuilder deploy(JsonObject mainIngredient, JsonObject deployIngredient, JsonObject result) {
        return new CreateDeployingRecipeBuilder(mainIngredient, deployIngredient, result);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                mainIngredient.isJsonNull() ||
                deployIngredient.isJsonNull();
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), mainIngredient, deployIngredient, result, advancement, getFullAdvancementId(resourceLocation)));
    }

    public static JsonObject getSequenceStep(ItemLike transitional, JsonObject deploy) {
        JsonObject trans = JsonBuilder.json().getSimpleItem(transitional);
        return new CreateDeployingRecipeBuilder(trans, deploy, trans).getSequencedRecipe();
    }

    @Override
    public FinishedRecipe getFinishedRecipe() {
        return new Result(null, mainIngredient, deployIngredient, result, null, null);
    }

    public static class Result extends ResultRecipeResult {
        private final JsonObject mainIngredient;
        private final JsonObject deployIngredient;

        public Result(ResourceLocation id, JsonObject mainIngredient, JsonObject deployIngredient, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result);
            this.mainIngredient = mainIngredient;
            this.deployIngredient = deployIngredient;
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

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.DEPLOYING.getSerializer();
        }
    }

}
