package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.*;
import java.util.function.Consumer;

public class CreateMechanicalCraftingBuilder extends AResultRecipeBuilder {
    private final List<String> rows = new ArrayList<>();
    private final Map<Character, JsonObject> keys = new LinkedHashMap<>();
    private final boolean acceptMirrored;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CreateMechanicalCraftingBuilder(boolean acceptMirrored, JsonObject result) {
        super(MhmBitsnbobs.MOD_ID, "create/mechanical_crafting", result);
        this.acceptMirrored = acceptMirrored;
    }

    public static CreateMechanicalCraftingBuilder shaped(boolean acceptMirrored, JsonObject result) {
        return new CreateMechanicalCraftingBuilder(acceptMirrored, result);
    }

    private void checkKey(Character key) {
        if (this.keys.containsKey(key)) throw new IllegalArgumentException("Symbol '" + key + "' is already defined!");
        if (key == ' ') throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
    }

    public CreateMechanicalCraftingBuilder define(Character key, JsonObject ingredient) {
        checkKey(key);
        this.keys.put(key, ingredient);
        return this;
    }

    public CreateMechanicalCraftingBuilder pattern(String line) {
        if (!this.rows.isEmpty() && line.length() != this.rows.get(0).length()) throw new IllegalArgumentException("Pattern must be the same width on every line!");
        this.rows.add(line);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        if (rows.isEmpty()) return true;

        Set<Character> set = Sets.newHashSet(this.keys.keySet());
        set.remove(' ');

        for(String s : this.rows) {
            for(int i = 0; i < s.length(); ++i) {
                char c0 = s.charAt(i);
                if (!this.keys.containsKey(c0) && c0 != ' ') return true;

                set.remove(c0);
            }
        }

        if (!set.isEmpty()) return true;
        else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) return true;
        else if (this.advancement.getCriteria().isEmpty()) return true;

        return super.isRecipeEmpty();
    }

    @Override
    protected void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(getFullRecipeId(resourceLocation), result, rows, keys, acceptMirrored, advancement, getFullAdvancementId(resourceLocation)));
    }

    private static class Result extends ResultRecipeResult {
        private final List<String> rows;
        private final Map<Character, JsonObject> keys;
        private final boolean acceptMirrored;

        private Result(ResourceLocation id, JsonObject result, List<String> rows, Map<Character, JsonObject> keys, boolean acceptMirrored, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId, result);
            this.rows = rows;
            this.keys = keys;
            this.acceptMirrored = acceptMirrored;
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

        @Override
        public RecipeSerializer<?> getType() {
            return AllRecipeTypes.MECHANICAL_CRAFTING.getSerializer();
        }
    }

}
