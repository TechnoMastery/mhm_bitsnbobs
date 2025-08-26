package net.minheur.mhm_bitsnbobs.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.advancement.AdvancementBuilder;
import net.minheur.techno_lib.advancement.AdvancementGenProvider;

import static net.minheur.mhm_bitsnbobs.advancement.ModAdvancements.*;

/**
 * We call them with {@link #build(AdvancementBuilder...)} in {@link #addAdvancement()}.
 */
public class ModAdvancementGen extends AdvancementGenProvider {
    protected ModAdvancementGen(PackOutput output) {
        super(output, MhmBitsnbobs.MOD_ID);
    }

    /**
     * Remember to add the advancements here to actually add them here
     */
    @Override
    protected void addAdvancement() {
        build(
                creative_THE_ESSENCE_root,
                creative_THE_INGOT,
                creative_THE_EGG,
                creative_PACKAGE,

                adventure_ITS_CHARGED,
                adventure_LIGTHNING_UPGRADE,
                adventure_STORM_SWORD,

                ae2_SINGULARITY,
                ae2_QUANTUM_CORE,
                ae2_STABLE_POWER,
                ae2_QUANTUM_STAFF,

                dirt_DIRT_root,
                dirt_VERY_COMPRESSED,
                dirt_RESOURCE_PACK,
                dirt_ATE_DIRT,
                dirt_PLACED_RUBIS_ORE,
                dirt_PLACED_RUBINIUM,
                dirt_HOT_SWORD,
                dirt_STRONG_SWORD,

                story_HARD_IRON,
                story_FIRE_POWER,
                story_GROWING_FIRE,
                story_BUTTONS,
                story_MORE_BUTTONS
        );
    }

    /**
     * Custom method to build the advancements easier.
     * @param advancements
     */
    public void build(AdvancementBuilder... advancements) {
        for (AdvancementBuilder advancement : advancements) {
            String id = advancement.getFullId();
            JsonObject json = advancement.getJson();
            this.registerAdvancement(id, json);
        }
    }
}
