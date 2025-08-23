package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.lang.ModLanguageProvider;

public class ModLangGen extends ModLanguageProvider {
    protected ModLangGen(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void addTranslation() {


    }

    @Override
    public TranslationBuilder addAdvancementTitle(String group, String key) {
        return add("advancements.mhm_bitsnbobs." + group + "." + key + ".title");
    }

    @Override
    public TranslationBuilder addAdvancementDesc(String group, String key) {
        return add("advancements.mhm_bitsnbobs." + group + "." + key + ".desc");
    }

    @Override
    public TranslationBuilder addTooltip(String tooltipId) {
        return add("tooltip.mhm_bitsnbobs." + tooltipId + ".tooltip");
    }
}
