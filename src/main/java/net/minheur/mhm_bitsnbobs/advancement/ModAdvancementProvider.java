package net.minheur.mhm_bitsnbobs.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
     * @param pOutput
     * @return
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

    public AdvancementBuilder addRoot(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        return new AdvancementBuilder(id + "-root", group, true);
    }

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

    }
}
