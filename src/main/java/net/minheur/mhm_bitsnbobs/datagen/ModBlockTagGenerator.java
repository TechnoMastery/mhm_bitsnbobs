package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MhmBitsnbobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // METAL DETECTOR detection list
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(
                        ModBlocks.SAPPHIRE_ORE.get()
                )
                .addTag(
                        Tags.Blocks.ORES
                );
        // IRON TOOL
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.SAPPHIRE_ORE.get(),
                        ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                        ModBlocks.SAPPHIRE_BLOCK.get(),
                        ModBlocks.RAW_SAPPHIRE_BLOCK.get(),
                        ModBlocks.SAPPHIRE_SLAB.get(),
                        ModBlocks.SAPPHIRE_TRAPDOOR.get(),
                        ModBlocks.SAPPHIRE_DOOR.get(),
                        ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(),
                        ModBlocks.SAPPHIRE_FENCE_GATE.get(),
                        ModBlocks.SAPPHIRE_FENCE.get(),
                        ModBlocks.SAPPHIRE_STAIRS.get(),
                        ModBlocks.COMPRESSED_DIRT.get(),
                        ModBlocks.RESSOURCE_DIRT_BLOCK.get(),
                        ModBlocks.GEM_POLISHING_STATION.get()
                );
        // NETHERITE TOOL
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(
                        ModBlocks.END_SAPPHIRE_ORE.get()
                );
        // sapphire tools
        this.tag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(
                        ModBlocks.SOUND_BLOCK.get(),
                        ModBlocks.CREATIVE_RESIDUE_BLOCK.get(),
                        ModBlocks.CREATIVE_BLOCK.get()
                );
        // stone tool
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        ModBlocks.NETHER_SAPPHIRE_ORE.get()
                );
        // diamond tool
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        ModBlocks.SUPER_CHARGED_BLOCK.get(),
                        ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get()
                );
        // fences ==> not wood fences
        this.tag(BlockTags.FENCES)
                .add(
                        ModBlocks.SAPPHIRE_FENCE.get()
                );
        // fences gate
        this.tag(BlockTags.FENCE_GATES)
                .add(
                        ModBlocks.SAPPHIRE_FENCE_GATE.get()
                );
        // walls
        this.tag(BlockTags.WALLS)
                .add(
                        ModBlocks.SAPPHIRE_WALL.get()
                );
        // mineable/pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.SAPPHIRE_BLOCK.get(),
                        ModBlocks.RAW_SAPPHIRE_BLOCK.get(),
                        ModBlocks.SAPPHIRE_ORE.get(),
                        ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                        ModBlocks.END_SAPPHIRE_ORE.get(),
                        ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                        ModBlocks.SOUND_BLOCK.get(),
                        ModBlocks.SAPPHIRE_SLAB.get(),
                        ModBlocks.SAPPHIRE_TRAPDOOR.get(),
                        ModBlocks.SAPPHIRE_DOOR.get(),
                        ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(),
                        ModBlocks.SAPPHIRE_FENCE_GATE.get(),
                        ModBlocks.SAPPHIRE_FENCE.get(),
                        ModBlocks.CREATIVE_RESIDUE_BLOCK.get(),
                        ModBlocks.SUPER_CHARGED_BLOCK.get(),
                        ModBlocks.GEM_POLISHING_STATION.get(),
                        ModBlocks.CREATIVE_BLOCK.get()
                );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(
                        ModBlocks.COMPRESSED_DIRT.get(),
                        ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(),
                        ModBlocks.RESSOURCE_DIRT_BLOCK.get()
                );
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        ModBlocks.DARK_LOG.get(),
                        ModBlocks.DARK_WOOD.get(),
                        ModBlocks.STRIPPED_DARK_LOG.get(),
                        ModBlocks.STRIPPED_DARK_WOOD.get()
                );
        this.tag(BlockTags.PLANKS)
                .add(
                        ModBlocks.DARK_PLANKS.get()
                );
    }
}
