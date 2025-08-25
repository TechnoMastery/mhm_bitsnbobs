package net.minheur.mhm_bitsnbobs.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import static net.minheur.mhm_bitsnbobs.util.Utils.*;

public class CriterionBuilder {
    private final JsonObject criterion = new JsonObject();

    public CriterionBuilder(ResourceLocation trigger) {
        if (trigger == null) throw new IllegalStateException("Invalid trigger for criterion!");
        criterion.addProperty("trigger", trigger.toString());
    }

    /**
     * create a condition with an {@link ItemLike} and min / max count. Set count to 0 to not set it.
     * @param item the {@link ItemLike} you want to add
     * @param minCount the minimum amount of item you need
     * @param maxCount the maximum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder itemCondition(ItemLike item, int minCount, int maxCount) {
        return condition(getBuiltInItemRegistry(item), "item", minCount, maxCount);
    }
    /**
     * create a condition with an {@link ItemLike} and min count. Remember to set the int AFTER the item.
     * @param item the {@link ItemLike} you want to add
     * @param minCount the minimum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder itemCondition(ItemLike item, int minCount) {
        return itemCondition(item, minCount, 0);
    }
    /**
     * create a condition with an {@link ItemLike} and min count. Remember to set the int BEFORE the item.
     * @param item the {@link ItemLike} you want to add
     * @param maxCount the maximum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder itemCondition(int maxCount, ItemLike item) {
        return itemCondition(item, 0, maxCount);
    }
    /**
     * create a condition with an {@link ItemLike}.
     * @param item the {@link ItemLike} you want to add
     * @return the built condition
     */
    public CriterionBuilder itemCondition(ItemLike item) {
        return itemCondition(item, 0, 0);
    }

    /**
     * create a condition with a tag and min / max count. Set count to 0 to not set it.
     * @param tag the tag you want to add
     * @param minCount the minimum amount of item you need
     * @param maxCount the maximum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder tagCondition(TagKey<Item> tag, int minCount, int maxCount) {
        return condition(tag.location().toString(), "tag", minCount, maxCount);
    }
    /**
     * create a condition with a tag and min count. Remember to set the int AFTER the item.
     * @param tag the tag you want to add
     * @param minCount the minimum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder tagCondition(TagKey<Item> tag, int minCount) {
        return tagCondition(tag, minCount, 0);
    }
    /**
     * create a condition with a tag and min count. Remember to set the int BEFORE the item.
     * @param tag the tag you want to add
     * @param maxCount the maximum amount of item you need
     * @return the built condition.
     */
    public CriterionBuilder tagCondition(int maxCount, TagKey<Item> tag) {
        return tagCondition(tag, 0, maxCount);
    }
    /**
     * create a condition with a tag.
     * @param tag the tag you want to add
     * @return the built condition
     */
    public CriterionBuilder tagCondition(TagKey<Item> tag) {
        return tagCondition(tag, 0, 0);
    }

    /**
     * the main condition builder.
     * @param item the item id you want to add (ex. minecraft:stone)
     * @param type the type. Only {@code item} and {@code tag} allowed.
     * @param minCount the minimum amount you need. Set 0 to not set.
     * @param maxCount the maximum amount you need. Set 0 to net set.
     * @return a built condition
     */
    public CriterionBuilder condition(String item, String type, int minCount, int maxCount) {
        if (item == null) throw new IllegalStateException("You need to give at least one tag!");
        JsonObject condition = criterion.has("conditions") ? criterion.getAsJsonObject("conditions") : new JsonObject();
        JsonArray mainItemsArray = condition.has("items") ? condition.getAsJsonArray("items") : new JsonArray();

        JsonObject object = new JsonObject();
        if (type.equals("tag")) object.addProperty(type, item);
        else if (type.equals("item")) {
            JsonArray array = new JsonArray();
            array.add(item);
            object.add("items", array);
        }

        JsonObject count = new JsonObject();
        if (minCount != 0) count.addProperty("min", minCount);
        if (maxCount != 0) count.addProperty("max", maxCount);
        if (minCount != 0 && maxCount != 0) object.add("count", count);
        mainItemsArray.add(object);

        condition.add("items", mainItemsArray);
        criterion.add("conditions", condition);
        return this;
    }

    public CriterionBuilder blockPlacedCondition(Block pBlock) {
        if (pBlock == null) throw new IllegalStateException("You need to give at least one block!");
        JsonObject condition = criterion.has("conditions") ? criterion.getAsJsonObject("conditions") : new JsonObject();
        JsonArray mainItemsArray = condition.has("items") ? condition.getAsJsonArray("items") : new JsonArray();
        JsonObject object = new JsonObject();
        object.addProperty("block", getBuiltInItemRegistry(pBlock));
        if (mainItemsArray.contains(object)) throw new IllegalStateException("Duplicate tag condition found!");
        mainItemsArray.add(object);

        condition.add("items", mainItemsArray);
        criterion.add("conditions", condition);
        return this;
    }

    public JsonObject build() {
        return criterion;
    }
}

