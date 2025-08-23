package net.minheur.mhm_bitsnbobs.lang;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class ModLanguageProvider implements DataProvider {
    private final Map<String, Map<String, String>> data = new HashMap<>();
    private final PackOutput output;
    private final String modid;

    protected ModLanguageProvider(PackOutput output, String modid) {
        this.output = output;
        this.modid = modid;
    }

    protected abstract void addTranslation();

    /**
     * Executes when the datagen is running
     * @param pOutput the output for saving
     * @return the list of {@link CompletableFuture}
     */
    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (SupportedLanguages lang : SupportedLanguages.values()) {
            data.put(lang.name(), new HashMap<>());
        }

        addTranslation();

        for (Map.Entry<String, Map<String, String>> langEntry : data.entrySet()) {
            String langId = langEntry.getKey();
            Map<String, String> translations = langEntry.getValue();

            if (!translations.isEmpty()) {
                futures.add(save(pOutput, translations, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.modid).resolve("lang").resolve(langId + ".json")));
            }
        }

        if (!futures.isEmpty()) return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        return CompletableFuture.allOf();
    }

    /**
     * Saving one lang file from its {@link Map}
     * @param cache the buimder
     * @param data the {@link Map} with data
     * @param target the path to set the file in
     * @return the single {@link CompletableFuture} of this lang
     */
    private @NotNull CompletableFuture<?> save(CachedOutput cache, @NotNull Map<String, String> data, Path target) {
        JsonObject json = new JsonObject();
        data.forEach(json::addProperty);

        return DataProvider.saveStable(cache, json, target);
    }


    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "Mhm languages: generating";
    }

    /**
     * Main function to add a traduction
     * @param key the long id of the translation
     * @return a built translation
     */
    public TranslationBuilder add(String key) {
        return new TranslationBuilder(key);
    }
    public TranslationBuilder addBlock(@NotNull Supplier<? extends Block> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull Block key) {
        return add(key.getDescriptionId());
    }
    public TranslationBuilder addItem(@NotNull Supplier<? extends Item> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull Item key) {
        return add(key.getDescriptionId());
    }
    public TranslationBuilder addItemStack(@NotNull Supplier<ItemStack> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull ItemStack key) {
        return add(key.getDescriptionId());
    }
    public TranslationBuilder addEnchantment(@NotNull Supplier<? extends Enchantment> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull Enchantment key) {
        return add(key.getDescriptionId());
    }
    public TranslationBuilder addEffect(@NotNull Supplier<? extends MobEffect> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull MobEffect key) {
        return add(key.getDescriptionId());
    }
    public TranslationBuilder addEntityType(@NotNull Supplier<? extends EntityType<?>> key) {
        return add(key.get());
    }
    public TranslationBuilder add(@NotNull EntityType<?> key) {
        return add(key.getDescriptionId());
    }
    public abstract TranslationBuilder addAdvancementTitle(String group, String key);
    public abstract TranslationBuilder addAdvancementDesc(String group, String key);
    public abstract TranslationBuilder addTooltip(String tooltipId);

    /**
     * The class that handles adding languages.
     * <p>Remember to add the method with the correct name when adding a new language to supported ones.</p>
     */
    public class TranslationBuilder {
        /**
         * The id of the language the instance is handling.
         */
        private final String key;

        private TranslationBuilder(String key) {
            this.key = key;
        }

        /**
         * Adding a translation for a specific language a value.
         * @param langId the id of the lang you are adding a traduction in
         * @param value the value of this translation
         * @return the actual translation pack you are creating
         */
        public TranslationBuilder lang(String langId, String value) {
            Map<String, String> translations = data.get(langId);
            if (translations.put(key, value) != null) {
                throw new IllegalStateException("Duplicate translation key " + key + " for lang " + langId);
            }
            return this;
        }

        /**
         * Adding a translation for the en_us lang file.
         * @param value the value of the translation
         * @return the actual translation pack you are creating
         */
        public TranslationBuilder en_us(String value) {
            return lang("en_us", value);
        }
        /**
         * Adding a translation for the fr_fr lang file.
         * @param value the value of the translation
         * @return the actual translation pack you are creating
         */

        public TranslationBuilder fr_fr(String value) {
            return lang("fr_fr", value);
        }
    }

}
