package net.minheur.mhm_bitsnbobs.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import static net.minheur.mhm_bitsnbobs.util.Utils.*;

/**
 * The custom builder for advancements
 */
public class AdvancementBuilder {
    private final String id;
    private final String group;
    private final boolean isRoot;
    private final JsonObject json = new JsonObject();
    private final String modid;

    public AdvancementBuilder(String id, String group, boolean isRoot, String modid) {
        this.group = group;
        this.id = id;
        this.isRoot = isRoot;
        this.modid = modid;
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
        if (rewards != null) {
            JsonObject rewardJson = rewards.serializeToJson().getAsJsonObject();
            json.add("rewards", rewardJson);
        }
        return this;
    }

    private void check() {
        if (!isRoot && !this.json.has("parent")) throw new IllegalStateException("Not giving a parent for non-root advancement!");
        if (!this.json.has("requirements")) throw new IllegalStateException("Missing requirements on advancement!");
        if (!this.json.has("criteria")) throw new IllegalStateException("Missing criteria on advancement!");
    }


    public String getId() {
        check();
        return group + "/" + id;
    }
    public JsonObject getJson() {
        check();
        return json;
    }

    public ResourceLocation getLoc() {
        return new ResourceLocation(modid, group + "/" + id);
    }

}
