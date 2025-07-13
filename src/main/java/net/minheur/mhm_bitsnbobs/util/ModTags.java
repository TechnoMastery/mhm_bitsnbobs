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
        public static final TagKey<Item> ROTTEN_MEATS = tag("rotten_meats");
        public static final TagKey<Item> GLOWING_UTILITY = tag("glowing_utility");
        public static final TagKey<Item> CATALYZERS = tag("catalyzers");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MhmBitsnbobs.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_DARK_DIMENSION = tag("is_dark_dimension");


        private static TagKey<Biome> tag(String name) {
            return biomeTagCreate(new ResourceLocation(MhmBitsnbobs.MOD_ID, name));
        }
        private static TagKey<Biome> biomeTagCreate(ResourceLocation pName) {
            return TagKey.create(Registries.BIOME, pName);
        }
    }
}