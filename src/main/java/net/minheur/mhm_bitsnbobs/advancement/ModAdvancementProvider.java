package net.minheur.mhm_bitsnbobs.advancement;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
    public void registerAdvancement(String id, JsonObject advancement) {
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
}
