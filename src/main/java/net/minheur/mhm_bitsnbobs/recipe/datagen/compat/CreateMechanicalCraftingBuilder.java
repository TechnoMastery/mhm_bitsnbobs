package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

import static net.minheur.mhm_bitsnbobs.util.Utils.*;

public class CreateMechanicalCraftingBuilder {
    private final JsonObject result = new JsonObject();
    private final List<String> rows = new ArrayList<>();
    private final Map<Character, JsonObject> keys = new LinkedHashMap<>();
    private final boolean acceptMirrored;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateMechanicalCraftingBuilder(boolean acceptMirrored) {
        this.acceptMirrored = acceptMirrored;
    }

    public static CreateMechanicalCraftingBuilder shaped(boolean acceptMirrored) {
        return new CreateMechanicalCraftingBuilder(acceptMirrored);
    }

    private void checkKey(Character key) {
        if (this.keys.containsKey(key)) throw new IllegalArgumentException("Symbol '" + key + "' is already defined!");
        if (key == ' ') throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
    }

    public CreateMechanicalCraftingBuilder define(Character key, ItemLike ingredient) {
        checkKey(key);
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(ingredient));
        this.keys.put(key, json);
        return this;
    }
    public CreateMechanicalCraftingBuilder define(Character key, TagKey<Item> tag) {
        checkKey(key);
        JsonObject json = new JsonObject();
        json.addProperty("tag", tag.location().toString());
        this.keys.put(key, json);
        return this;
    }

    public CreateMechanicalCraftingBuilder pattern(String line) {
        if (!this.rows.isEmpty() && line.length() != this.rows.get(0).length()) throw new IllegalArgumentException("Pattern must be the same width on every line!");
        this.rows.add(line);
        return this;
    }

    public CreateMechanicalCraftingBuilder addResult(ItemLike result, int count) {
        this.result.addProperty("item", getBuiltInItemRegistry(result));
        if (count > 1) this.result.addProperty("count", count);
        return this;
    }
    public CreateMechanicalCraftingBuilder addResult(ItemLike result) {
        return addResult(result, 1);
    }

    public CreateMechanicalCraftingBuilder unlock(String pKey, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(pKey, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.rows.isEmpty()) throw new IllegalStateException("No pattern is defined for shaped recipe " + pId + "!");
        Set<Character> set = Sets.newHashSet(this.keys.keySet());
        set.remove(' ');

        for(String s : this.rows) {
            for(int i = 0; i < s.length(); ++i) {
                char c0 = s.charAt(i);
                if (!this.keys.containsKey(c0) && c0 != ' ') {
                    throw new IllegalStateException("Pattern in recipe " + pId + " uses undefined symbol '" + c0 + "'");
                }

                set.remove(c0);
            }
        }

        if (!set.isEmpty()) {
            throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + pId);
        } else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
            throw new IllegalStateException("Shaped recipe " + pId + " only takes in a single item - should it be a shapeless recipe instead?");
        } else if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id.withPrefix("create/mechanical_crafting/"), this.result, this.rows, this.keys, this.acceptMirrored, this.advancement, id.withPrefix("recipes/create/mechanical_crafting/")));
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID, id));
    }

    private static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final JsonObject result;
        private final List<String> rows;
        private final Map<Character, JsonObject> keys;
        private final boolean acceptMirrored;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        private Result(ResourceLocation id, JsonObject result, List<String> rows, Map<Character, JsonObject> keys, boolean acceptMirrored, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.rows = rows;
            this.keys = keys;
            this.acceptMirrored = acceptMirrored;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonObject key = new JsonObject();
            JsonArray pattern = new JsonArray();

            for (Map.Entry<Character, JsonObject> entry : this.keys.entrySet()) {
                key.add(String.valueOf(entry.getKey()), entry.getValue());
            }

            for (String line : rows) {
                pattern.add(line);
            }

            pJson.add("key", key);
            pJson.add("pattern", pattern);
            pJson.addProperty("acceptMirrored", acceptMirrored);
            pJson.add("result", result);
        }

        /**
         * Gets the ID for the recipe.
         */
        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MECHANICAL_CRAFTING.getSerializer();
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement()} is
         * non-null.
         */
        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }

}
