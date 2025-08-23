package net.minheur.mhm_bitsnbobs.datagen.advancements;

import net.minecraft.advancements.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.util.AdvancementBuilderHelper;

import java.util.Map;
import java.util.function.Consumer;

import static net.minheur.mhm_bitsnbobs.util.AdvancementBuilderHelper.*;

public class ModStoryAdvancementSubProvider implements AdvancementSubProvider {
    /**
     * Generates the advancements.
     * @param pRegistries the registry thing
     * @param pWriter the saver
     */
    @Override
    public void generate(HolderLookup.Provider pRegistries, Consumer<Advancement> pWriter) {
        advancementWithParent(
                pWriter,
                "smelt_hardened",
                "story",
                new ResourceLocation("minecraft", "story/smelt_iron"),
                ModItems.HARDENED_INGOT.get(),
                FrameType.TASK,
                true, true, false,
                Map.of("has_hardened_ingot", has(ModItems.HARDENED_INGOT.get())),
                AdvancementRewards.EMPTY,
                RequirementsStrategy.AND
        );
        advancementWithParent(
                pWriter,
                "buttons",
                "story",
                new ResourceLocation("minecraft", "story/root"),
                Items.OAK_BUTTON,
                FrameType.GOAL,
                true, true, true,
                Map.of("has_buttons", has(Items.OAK_BUTTON)),
                AdvancementRewards.Builder.experience(50).build(), // TODO: add loot table
                RequirementsStrategy.OR
        );
        AdvancementBuilderHelper.advancementWithParent(
                pWriter,
                "more_buttons",
                "story",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "story/buttons"),
                Items.OAK_BUTTON,
                FrameType.GOAL,
                true, true, true,
                Map.of("has_buttons", hasExactly(Items.OAK_BUTTON, 128)),
                AdvancementRewards.Builder.experience(150).build(), // TODO: add loot table
                RequirementsStrategy.AND
        );
        AdvancementBuilderHelper.advancementWithParent(
                pWriter,
                "fire_diamond",
                "story",
                new ResourceLocation("minecraft", "story/mine_diamond"),
                ModItems.FIRE_DIAMOND.get(),
                FrameType.TASK,
                true, true, false,
                Map.of("has_diamond", has(ModItems.FIRE_DIAMOND.get())),
                AdvancementRewards.EMPTY,
                RequirementsStrategy.OR
        );
        AdvancementBuilderHelper.advancementWithParent(
                pWriter,
                "fire_grow",
                "story",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "story/fire_diamond"),
                ModItems.FIRE_SEEDS.get(),
                FrameType.TASK,
                true, true, false,
                Map.of("has_fire_seeds", has(ModItems.FIRE_SEEDS.get())),
                AdvancementRewards.EMPTY,
                RequirementsStrategy.OR
        );
        AdvancementBuilderHelper.advancementWithParent(
                pWriter,
                "fire_power",
                "story",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "story/fire_diamond"),
                ModItems.FIRE_SWORD.get(),
                FrameType.GOAL,
                true, true, false,
                Map.of("has_fire_sword", has(ModItems.FIRE_SWORD.get())),
                AdvancementRewards.Builder.experience(5).build(),
                RequirementsStrategy.OR
        );
    }
}
