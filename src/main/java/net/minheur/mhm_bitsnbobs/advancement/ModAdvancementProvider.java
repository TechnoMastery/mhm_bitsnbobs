package net.minheur.mhm_bitsnbobs.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.minheur.mhm_bitsnbobs.util.Utils.getBuiltInItemRegistry;

public abstract class ModAdvancementProvider implements DataProvider {
    private final PackOutput output;
    private final String modid;
    private final Map<ResourceLocation, JsonObject> advancements = new HashMap<>();

    protected ModAdvancementProvider(PackOutput output, String modid) {
        this.output = output;
        this.modid = modid;
    }

    protected abstract void addAdvancement();

    /**
     * Adding a single advancement to the map.
     * @param id the name of your advancement. Also, the file name.
     * @param advancement the {@link JsonObject} of your advancement.
     */
    protected void registerAdvancement(String id, JsonObject advancement) {
        if (advancements.containsKey(id)) throw new IllegalStateException("Duplicate advancement id: " + id);

        advancements.put(new ResourceLocation(modid, id), advancement);
    }

    /**
     * Executes when the datagen is launched
     * @return the listing of advancements done
     */
    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        advancements.clear();
        addAdvancement();

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (Map.Entry<ResourceLocation, JsonObject> entry : advancements.entrySet()) {
            ResourceLocation id = entry.getKey();
            Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve(id.getNamespace())
                    .resolve("advancements")
                    .resolve(id.getPath() + ".json");
            futures.add(DataProvider.saveStable(pOutput, entry.getValue(), path));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return modid + " advancement provider";
    }

    /**
     * Adding a root advancement
     * @param id the id of your advancement
     * @param group the group of your advancement (ex. story, adventure...)
     * @return a new advancement builder
     */
    public AdvancementBuilder addRoot(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id + "-root", group, true);
    }

    /**
     * Adding an advancement with a parent
     * @param id the id of your advancement
     * @param group the group pf your advancement (ex. story, adventure...). It should be the same as the root !
     * @return the new advancement builder
     */
    public AdvancementBuilder addWithParent(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id, group, false);
    }

    /**
     * The custom builder for advancements
     */
    public class AdvancementBuilder {
        private final String id;
        private final String group;
        private final boolean isRoot;
        private final JsonObject json = new JsonObject();

        public AdvancementBuilder(String id, String group, boolean isRoot) {
            this.group = group;
            this.id = id;
            this.isRoot = isRoot;
        }

        public AdvancementBuilder display(ItemLike iconItem, @Nullable ResourceLocation background, FrameType frameType, boolean showToast, boolean announce, boolean hidden) {
            JsonObject display = new JsonObject();

            JsonObject icon = new JsonObject();
            icon.addProperty("item", getBuiltInItemRegistry(iconItem));
            JsonObject title = new JsonObject();
            title.addProperty("translate", "advancements." + modid + "." + group + "." + id + ".title");
            JsonObject description = new JsonObject();
            description.addProperty("translate", "advancements." + modid + "." + group + "." + id + ".description");

            display.add("icon", icon);
            display.add("title", title);
            display.add("description", description);

            if (background == null && isRoot) throw new IllegalStateException("Missing background for root advancement!");
            if (background != null && !isRoot) throw new IllegalStateException("Adding background for non-root advancement!");
            if (background != null) display.addProperty("background", background.toString());

            display.addProperty("frame", frameType.getName());
            display.addProperty("show_toast", showToast);
            display.addProperty("announce_to_chat", announce);
            display.addProperty("hidden", hidden);

            json.add("display", display);

            return this;
        }

        public AdvancementBuilder parent(ResourceLocation parentId) {
            if (isRoot) throw new IllegalStateException("Giving a parent for root advancement!");
            if (parentId == null) throw new IllegalStateException("Giving null parent advancement!");
            json.addProperty("parent", parentId.toString());
            return this;
        }

        public AdvancementBuilder criterion(String name, JsonObject trigger) {
            JsonObject criteria = json.has("criteria") ? json.getAsJsonObject("criteria") : new JsonObject();
            criteria.add(name, trigger);
            json.add("criteria", criteria);
            return this;
        }

        /**
         * You need to add you requirement. {@code reqs} is a double array, so you need to follow this to use it :
         * <p>
         *     In the array, you need to accomplish one sub-array. In each sub-array, you need to complete all
         *     to get it. (ex. {@code [["a", "b"], ["c"]]} is {@code (a && b) || c}.
         * </p>
         * @param reqs the double array
         * @return the building advancement
         */
        public AdvancementBuilder requirements(String[][] reqs) {
            var arr = new JsonArray();
            for (String[] group : reqs) {
                var inner = new JsonArray();
                for (String s : group) inner.add(s);
                arr.add(inner);
            }
            json.add("requirements", arr);
            return this;
        }

        public AdvancementBuilder rewards(AdvancementRewards rewards) {
            if (rewards == null) throw new IllegalStateException("You need to give a reward!");
            JsonObject rewardJson = rewards.serializeToJson().getAsJsonObject();
            json.add("rewards", rewardJson);
            return this;
        }

        public AdvancementBuilder build() {
            if (!isRoot && !json.has("parent")) throw new IllegalStateException("Not giving a parent for non-root advancement!");
            if (!json.has("requirements")) throw new IllegalStateException("Missing requirements on advancement!");
            if (!json.has("criteria")) throw new IllegalStateException("Missing criteria on advancement!");

            registerAdvancement(id, json);
            return this;
        }

        public ResourceLocation getLoc() {
            return new ResourceLocation(modid, id);
        }

    }

    public CriterionBuilder simpleCriterion(ResourceLocation trigger) {
        return new CriterionBuilder(trigger);
    }

    public static class CriterionBuilder {
        private final JsonObject criterion = new JsonObject();

        public CriterionBuilder(ResourceLocation trigger) {
            if (trigger == null) throw new IllegalStateException("Invalid trigger for criterion!");
            criterion.addProperty("trigger", trigger.toString());
        }

        public CriterionBuilder itemCondition(ItemLike... items) {
            if (items.length < 1) throw new IllegalStateException("You need to give at least one item!");
            JsonObject condition = criterion.has("conditions") ? criterion.getAsJsonObject("conditions") : new JsonObject();
            JsonArray mainItemsArray = condition.has("items") ? condition.getAsJsonArray("items") : new JsonArray();
            for (ItemLike item : items) {
                JsonObject object = new JsonObject();
                JsonArray array = new JsonArray();
                array.add(getBuiltInItemRegistry(item));
                object.add("items", array);
                if (mainItemsArray.contains(object)) throw new IllegalStateException("Duplicate item condition found!");
                mainItemsArray.add(object);
            }

            return this;
        }

        public JsonObject build() {
            return criterion;
        }
    }
}
