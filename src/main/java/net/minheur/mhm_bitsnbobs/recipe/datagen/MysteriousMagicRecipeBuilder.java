package net.minheur.mhm_bitsnbobs.recipe.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.recipe.ModRecipes;
import net.minheur.techno_lib.datagen.recipe.AbstractResultRecipeBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public class MysteriousMagicRecipeBuilder extends AbstractResultRecipeBuilder {
    private final ItemLike primaryIngredient;
    private final int primaryCount;
    private final ItemLike leftIngredient;
    private final int leftCount;
    private final ItemLike rightIngredient;
    private final int rightCount;
    private final ItemLike upIngredient;
    private final int upCount;
    private final ItemLike downIngredient;
    private final int downCount;
    private final int fuelAmount;

    public MysteriousMagicRecipeBuilder(ItemLike primaryIngredient, int primaryCount, ItemLike leftIngredient, int leftCount, ItemLike rightIngredient, int rightCount, ItemLike upIngredient, int upCount, ItemLike downIngredient, int downCount, ItemLike result, int resultCount, int fuelAmount) {
        super(MhmBitsnbobs.MOD_ID, "mysterious_magic", result, resultCount);
        this.primaryIngredient = primaryIngredient;
        this.primaryCount = primaryCount;
        this.leftIngredient = leftIngredient;
        this.leftCount = leftCount;
        this.rightIngredient = rightIngredient;
        this.rightCount = rightCount;
        this.upIngredient = upIngredient;
        this.upCount = upCount;
        this.downIngredient = downIngredient;
        this.downCount = downCount;
        this.fuelAmount = fuelAmount;
    }

    public static MysteriousMagicRecipeBuilder magic(ItemLike primaryIngredient, int primaryCount, ItemLike leftIngredient, int leftCount, ItemLike rightIngredient, int rightCount, ItemLike upIngredient, int upCount, ItemLike downIngredient, int downCount, ItemLike result, int resultCount,int fuelAmount) {
        return new MysteriousMagicRecipeBuilder(primaryIngredient, primaryCount, leftIngredient, leftCount, rightIngredient, rightCount, upIngredient, upCount, downIngredient, downCount, result, resultCount, fuelAmount);
    }
    public static MysteriousMagicRecipeBuilder magic(ItemLike primaryIngredient, ItemLike leftIngredient, ItemLike rightIngredient, ItemLike upIngredient, ItemLike downIngredient, ItemLike result, int resultCount, int fuelAmount) {
        return magic(primaryIngredient, 1, leftIngredient, 1, rightIngredient, 1, upIngredient, 1, downIngredient, 1, result, resultCount, fuelAmount);
    }
    public static MysteriousMagicRecipeBuilder magic(ItemLike primaryIngredient, ItemLike leftIngredient, ItemLike rightIngredient, ItemLike upIngredient, ItemLike downIngredient, ItemLike result, int fuelAmount) {
        return magic(primaryIngredient, leftIngredient, rightIngredient, upIngredient, downIngredient, result, 1, fuelAmount);
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(resourceLocation.withPrefix("mysterious_magic/"), primaryIngredient, leftIngredient, rightIngredient, upIngredient, downIngredient,
                result, primaryCount, leftCount, rightCount, upCount, downCount, count, fuelAmount, advancement, resourceLocation.withPrefix("recipes/mysterious_magic/")));
    }

    public static class Result extends ResultRecipeResult {
        private final ItemLike primaryItem;
        private final ItemLike leftItem;
        private final ItemLike rightItem;
        private final ItemLike upItem;
        private final ItemLike downItem;
        private final int primaryCount;
        private final int leftCount;
        private final int rightCount;
        private final int upCount;
        private final int downCount;
        private final int fuelAmount;

        public Result(ResourceLocation id, ItemLike primaryItem, ItemLike leftItem, ItemLike rightItem, ItemLike upItem, ItemLike downItem, ItemLike result, int primaryCount, int leftCount, int rightCount, int upCount, int downCount, int resultCount, int fuelAmount, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, result, resultCount, advancement, advancementId);
            this.primaryItem = primaryItem;
            this.leftItem = leftItem;
            this.rightItem = rightItem;
            this.upItem = upItem;
            this.downItem = downItem;
            this.primaryCount = primaryCount;
            this.leftCount = leftCount;
            this.rightCount = rightCount;
            this.upCount = upCount;
            this.downCount = downCount;
            this.fuelAmount = fuelAmount;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonObject primaryIngredient = new JsonObject();
            JsonObject leftIngredient = new JsonObject();
            JsonObject rightIngredient = new JsonObject();
            JsonObject upIngredient = new JsonObject();
            JsonObject downIngredient = new JsonObject();
            JsonObject resultIngredient = new JsonObject();
            primaryIngredient.addProperty("item", getBuiltInItemRegistry(this.primaryItem));
            leftIngredient.addProperty("item", getBuiltInItemRegistry(this.leftItem));
            rightIngredient.addProperty("item", getBuiltInItemRegistry(this.rightItem));
            upIngredient.addProperty("item", getBuiltInItemRegistry(this.upItem));
            downIngredient.addProperty("item", getBuiltInItemRegistry(this.downItem));
            resultIngredient.addProperty("item", getBuiltInItemRegistry(this.result));
            if (primaryCount > 1) primaryIngredient.addProperty("count", primaryCount);
            if (leftCount > 1) leftIngredient.addProperty("count", leftCount);
            if (rightCount > 1) rightIngredient.addProperty("count", rightCount);
            if (upCount > 1) upIngredient.addProperty("count", upCount);
            if (downCount > 1) downIngredient.addProperty("count", downCount);
            if (count > 1) resultIngredient.addProperty("count", count);
            pJson.add("primary", primaryIngredient);
            pJson.add("leftItem", leftIngredient);
            pJson.add("rightItem", rightIngredient);
            pJson.add("upItem", upIngredient);
            pJson.add("downItem", downIngredient);
            pJson.add("output", resultIngredient);
            pJson.addProperty("fuelAmount", fuelAmount);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.MYSTERIOUS_MAGIC_SERIALIZER.get();
        }
    }
}
