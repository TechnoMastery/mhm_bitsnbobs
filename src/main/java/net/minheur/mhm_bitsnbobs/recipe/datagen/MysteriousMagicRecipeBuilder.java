package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public class MysteriousMagicRecipeBuilder extends AResultRecipeBuilder {
    private final JsonObject primaryIngredient;
    private final JsonObject leftIngredient;
    private final JsonObject rightIngredient;
    private final JsonObject upIngredient;
    private final JsonObject downIngredient;
    private final int fuelAmount;

    public MysteriousMagicRecipeBuilder(JsonObject primaryIngredient, JsonObject leftIngredient, JsonObject rightIngredient, JsonObject upIngredient, JsonObject downIngredient, JsonObject result, int fuelAmount) {
        super(MhmBitsnbobs.MOD_ID, "mysterious_magic", result);
        this.primaryIngredient = primaryIngredient;
        this.leftIngredient = leftIngredient;
        this.rightIngredient = rightIngredient;
        this.upIngredient = upIngredient;
        this.downIngredient = downIngredient;
        this.fuelAmount = fuelAmount;
    }

    public static MysteriousMagicRecipeBuilder magic(JsonObject primaryIngredient, JsonObject leftIngredient, JsonObject rightIngredient, JsonObject upIngredient, JsonObject downIngredient, JsonObject result, int fuelAmount) {
        return new MysteriousMagicRecipeBuilder(primaryIngredient, leftIngredient, rightIngredient, upIngredient, downIngredient, result, fuelAmount);
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                primaryIngredient.isJsonNull() ||
                upIngredient.isJsonNull() ||
                downIngredient.isJsonNull() ||
                leftIngredient.isJsonNull() ||
                rightIngredient.isJsonNull() ||
                fuelAmount <= 0;
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), primaryIngredient, leftIngredient, rightIngredient, upIngredient, downIngredient,
                result, fuelAmount, advancement, getFullRecipeId(resourceLocation)));
    }

    public static class Result extends ResultRecipeResult {
        private final JsonObject primaryItem;
        private final JsonObject leftItem;
        private final JsonObject rightItem;
        private final JsonObject upItem;
        private final JsonObject downItem;
        private final int fuelAmount;

        public Result(ResourceLocation id, JsonObject primaryItem, JsonObject leftItem, JsonObject rightItem, JsonObject upItem, JsonObject downItem, JsonObject result, int fuelAmount, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result);
            this.primaryItem = primaryItem;
            this.leftItem = leftItem;
            this.rightItem = rightItem;
            this.upItem = upItem;
            this.downItem = downItem;
            this.fuelAmount = fuelAmount;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("primary", primaryItem);
            pJson.add("leftItem", leftItem);
            pJson.add("rightItem", rightItem);
            pJson.add("upItem", upItem);
            pJson.add("downItem", downItem);
            pJson.add("output", result);
            pJson.addProperty("fuelAmount", fuelAmount);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.MYSTERIOUS_MAGIC_SERIALIZER.get();
        }
    }
}
