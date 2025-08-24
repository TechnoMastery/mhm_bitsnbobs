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

    public CriterionBuilder itemCondition(ItemLike item) {
        if (item == null) throw new IllegalStateException("You need to give at least one item!");
        JsonObject condition = criterion.has("conditions") ? criterion.getAsJsonObject("conditions") : new JsonObject();
        JsonArray mainItemsArray = condition.has("items") ? condition.getAsJsonArray("items") : new JsonArray();
            JsonObject object = new JsonObject();
            JsonArray array = new JsonArray();
            array.add(getBuiltInItemRegistry(item));
            object.add("items", array);
            if (mainItemsArray.contains(object)) throw new IllegalStateException("Duplicate item condition found!");
            mainItemsArray.add(object);

        condition.add("items", mainItemsArray);
        criterion.add("conditions", condition);
        return this;
    }
    public CriterionBuilder itemCondition(TagKey<Item> tag) {
        if (tag == null) throw new IllegalStateException("You need to give at least one tag!");
        JsonObject condition = criterion.has("conditions") ? criterion.getAsJsonObject("conditions") : new JsonObject();
        JsonArray mainItemsArray = condition.has("items") ? condition.getAsJsonArray("items") : new JsonArray();
            JsonObject object = new JsonObject();
            object.addProperty("tag", tag.location().toString());
            if (mainItemsArray.contains(object)) throw new IllegalStateException("Duplicate tag condition found!");
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

