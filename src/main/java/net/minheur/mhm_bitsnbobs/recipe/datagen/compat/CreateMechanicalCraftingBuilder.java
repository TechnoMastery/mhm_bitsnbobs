package net.minheur.mhm_bitsnbobs.recipe.datagen.compat;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.*;

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

    /*
    Save
    Result.
     */

}
