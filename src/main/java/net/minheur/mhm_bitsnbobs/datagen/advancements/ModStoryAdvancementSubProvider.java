package net.minheur.mhm_bitsnbobs.datagen.advancements;

import net.minecraft.advancements.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.util.AdvancementBuilderHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;
import static net.minheur.mhm_bitsnbobs.util.AdvancementBuilderHelper.has;

public class ModStoryAdvancementSubProvider implements AdvancementSubProvider {
    /**
     * Generates the advancements.
     * @param pRegistries the registry thing
     * @param pWriter the saver
     */
    @Override
    public void generate(HolderLookup.Provider pRegistries, Consumer<Advancement> pWriter) {
        AdvancementBuilderHelper.advancementWithParent(
                pWriter,
                "smelt_hardened",
                "story",
                new ResourceLocation("minecraft", "story/smelt_iron"),
                ModItems.HARDENED_INGOT.get(),
                FrameType.TASK,
                true,
                true,
                false,
                Map.of("has_hardened_ingot", has(ModItems.HARDENED_INGOT.get())),
                AdvancementRewards.EMPTY,
                RequirementsStrategy.OR
        );
    }
}
