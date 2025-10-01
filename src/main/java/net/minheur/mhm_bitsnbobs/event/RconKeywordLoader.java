package net.minheur.mhm_bitsnbobs.event;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The RCON keywords loader. Disabled
 * @see net.minheur.mhm_bitsnbobs.commands.RconAnswers
 * @see net.minheur.mhm_bitsnbobs.commands.RconInputHandler
 */
public class RconKeywordLoader extends SimpleJsonResourceReloadListener {

    public static final Map<String, List<String>> KEYWORDS = new HashMap<>();
    private static final Gson GSON = new Gson();

    public RconKeywordLoader() {
        super(GSON, "rcon_keywords");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonElements, ResourceManager resourceManager, ProfilerFiller profiler) {
        KEYWORDS.clear();

        // Le fichier sera accessible via la clé : mhm_bitsnbobs:rcon_keywords
        ResourceLocation expectedLocation = new ResourceLocation(MhmBitsnbobs.MOD_ID, "rcon_keywords");

        JsonElement jsonElement = jsonElements.get(expectedLocation);
        if (jsonElement == null) {
            MhmBitsnbobs.LOGGER.error("Rcon keywords file not found ! (data/mhm_bitsnbobs/rcon_keywords.json)");
            return;
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonArray keywordsArray = entry.getValue().getAsJsonArray();

            List<String> keywords = new ArrayList<>();
            for (JsonElement element : keywordsArray) {
                keywords.add(element.getAsString().toLowerCase());
            }

            KEYWORDS.put(key, keywords);
        }

        MhmBitsnbobs.LOGGER.info("Rcon keywords loaded successfully.");
    }
}

