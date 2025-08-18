package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModTags {
    public static class Blocks {
        // ligne suivante = create of tag blocks
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = tag("needs_sapphire_tool");
        public static final TagKey<Block> NEEDS_LIGHTNING_TOOL = tag("needs_lightning_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MhmBitsnbobs.MOD_ID, name));
        }
    }

    public static class Items {
        // model item tags
        public static final TagKey<Item> BASEDISK = tag("basedisk");
        public static final TagKey<Item> ROTTEN_MEATS = forgeTag("rotten_meats");
        public static final TagKey<Item> GLOWING_UTILITY = tag("glowing_utility");
        public static final TagKey<Item> CATALYZERS = tag("catalyzers");
        public static final TagKey<Item> MAGIC_FUELS = tag("magic_fuel");
        public static final TagKey<Item> CHOCOLATE_SCOOPS = forgeTag("chocolate_scoops");
        public static final TagKey<Item> VANILLA_SCOOPS = forgeTag("vanilla_scoops");
        public static final TagKey<Item> STRAWBERRY_SCOOPS = forgeTag("strawberry_scoops");
        public static final TagKey<Item> FUELS = forgeTag("fuels");
        public static final TagKey<Item> QUANTUMITE_INGOTS = forgeTag("ingot/quantumite");
        public static final TagKey<Item> ATOMICAL_STABILIZATOR_GLUES = tag("atomical_stabilizator_glues");
        public static final TagKey<Item> OBSIDIAN_DUSTS = forgeTag("dusts/obsidian");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MhmBitsnbobs.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
