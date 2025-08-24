package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.lang.ModLanguageProvider;

public class ModLangGen extends ModLanguageProvider {
    protected ModLangGen(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void addTranslation() {
        addTooltipCredit("zombie_arm")
                .en_us("Made by Jema")
                .fr_fr("Créer par Jema");

        addTooltip("catalyzer-wind_charged")
                .en_us("This catalyzer can handle incubations that need the power of the nature.")
                .fr_fr("Ce catalyseur peut gérer des incubations qui demandent la puissance de la nature.");
        addTooltip("catalyzer-super_charged")
                .en_us("This catalyzer can handle the most power-needed incubations.")
                .fr_fr("Ce catalyseur peut gérer les incubations qui demandent le plus de puissance.");
        addTooltip("catalyzer-netherite")
                .en_us("This catalyzer can handle ultimate incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations ultimes.");
        addTooltip("catalyzer-gold")
                .en_us("This catalyzer can handle basic incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations basiques.");
        addTooltip("catalyzer-iron")
                .en_us("This catalyzer can handle simple incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations simple.");
        addTooltip("catalyzer-diamond")
                .en_us("This catalyzer can handle advanced incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations avancées.");

        addTooltip("rotten_item")
                .en_us("This item is disabled : insall \"createdieselgenerators\" to enable it.")
                .fr_fr("Cet objet est désactivé : installez \"createdieselgenerators\" pour l'activer.");
        addTooltip("runes")
                .en_us("Right click to use")
                .fr_fr("Clique droit pour utiliser");
        addTooltip("balls")
                .en_us("Get from crushing resource dirt")
                .fr_fr("Récupérez en concassant de la terre de resources");
    }

    /**
     * Add an advancement's title. You need to override it because of the namespace.
     *
     * @param group the branch of your advancement (ex. story, adventure)
     * @param key   the id of the advancement
     * @return a built translation
     */
    @Override
    public TranslationBuilder addAdvancementTitle(String group, String key) {
        return add("advancements.mhm_bitsnbobs." + group + "." + key + ".title");
    }

    /**
     * Add an advancement's description. You need to override it because of the namespace.
     *
     * @param group the branch of your advancement (ex. story, adventure)
     * @param key   the id of the advancement
     * @return a built translation
     */
    @Override
    public TranslationBuilder addAdvancementDesc(String group, String key) {
        return add("advancements.mhm_bitsnbobs." + group + "." + key + ".desc");
    }
    /**
     * Add a tooltip. You need to override it because of the namespace.
     * @param tooltipId the id of your tooltip
     * @return a built translation
     */
    @Override
    public TranslationBuilder addTooltip(String tooltipId) {
        return add("tooltip.mhm_bitsnbobs." + tooltipId + ".tooltip");
    }
    /**
     * Add a credit as a tooltip. You need to override it because of the namespace.
     *
     * @param creditId the id of your credit
     * @return a built translation
     */
    @Override
    public TranslationBuilder addTooltipCredit(String creditId) {
        return add("tooltip.mhm_bitsnbobs." + creditId + ".credit");
    }
}
