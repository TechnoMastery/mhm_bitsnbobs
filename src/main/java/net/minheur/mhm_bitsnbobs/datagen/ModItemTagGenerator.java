package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MhmBitsnbobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        // add armor here to make trimmable
                        ModItems.SAPPHIRE_HELMET.get(),
                        ModItems.SAPPHIRE_CHESTPLATE.get(),
                        ModItems.SAPPHIRE_LEGGINGS.get(),
                        ModItems.SAPPHIRE_BOOTS.get()
                );
        this.tag(ModTags.Items.BASEDISK)
                .add(
                        ModItems.BASE_OF_DISC.get()
                );
        this.tag(ModTags.Items.ROTTEN_MEATS)
                .add(
                        Items.ROTTEN_FLESH,
                        ModItems.ZOMBIE_ARM.get()
                );
        this.tag(ModTags.Items.GLOWING_UTILITY)
                .add(
                        Items.GLOWSTONE,
                        Items.GLOWSTONE_DUST,
                        Items.GLOW_INK_SAC,
                        Items.GLOW_BERRIES,
                        Items.GLOW_LICHEN
                );
        this.tag(ItemTags.MUSIC_DISCS)
                .add(
                        ModItems.BAR_BRAWL_MUSIC_DISC.get(),
                        ModItems.DARK_SOUL_MUSIC_DISC.get(),
                        ModItems.END_OF_THE_START_MUSIC_DISC.get(),
                        ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC.get(),
                        ModItems.BLOCK_BY_BLOCK_MUSIC_DISC.get(),
                        ModItems.CUBIC_GROOVE_MUSIC_DISC.get(),
                        ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC.get(),
                        ModItems.IN_THE_BLOCK_MUSIC_DISC.get(),
                        ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC.get(),
                        ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC.get(),
                        ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC.get(),
                        ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC.get(),
                        ModItems.NETHER_NIGHTS_MUSIC_DISC.get(),
                        ModItems.REDSTONE_PULSE_MUSIC_DISC.get(),
                        ModItems.SERENE_ECHO_MUSIC_DISC.get()
                );
        this.tag(Tags.Items.INGOTS)
                .add(
                        ModItems.HARDENED_INGOT.get()
                );
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(
                        ModBlocks.DARK_LOG.get().asItem(),
                        ModBlocks.DARK_WOOD.get().asItem(),
                        ModBlocks.STRIPPED_DARK_LOG.get().asItem(),
                        ModBlocks.STRIPPED_DARK_WOOD.get().asItem()
                );
        this.tag(ItemTags.PLANKS)
                .add(
                        ModBlocks.DARK_PLANKS.get().asItem()
                );
    }
}
