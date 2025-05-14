package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.packs.VanillaStoryAdvancements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvencementProvider extends ForgeAdvancementProvider {
    public ModAdvencementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ModStoryAdvancementsGenerator()));
    }

    public static class ModStoryAdvancementsGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
            Advancement hardIngot = Advancement.Builder.advancement().parent(new ResourceLocation("story/smelt_iron"))
                    .display(ModItems.HARDENED_INGOT.get(), Component.translatable("advancements.story.hardened_ingot.title"),
                            Component.translatable("advancements.story.hardened_ingot.description"),
                            null, FrameType.TASK, true, true, false)
                    .requirements(RequirementsStrategy.AND)
                    .addCriterion("hardened", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HARDENED_INGOT.get()))
                    .save(consumer, new ResourceLocation(MhmBitsnbobs.MOD_ID + ":story/hardened_ingot"), existingFileHelper);
        }
    }
}
