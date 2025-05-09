package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier SAPPHIRE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_SAPPHIRE_TOOL, () -> Ingredient.of(ModItems.SAPPHIRE.get())),
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "sapphire"), List.of(Tiers.NETHERITE), List.of());
    public static final Tier FIRE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1600, 8f, 11f, 9,
                    BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.FIRE_DIAMOND.get())),
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "fire"), List.of(Tiers.DIAMOND), List.of());

}
