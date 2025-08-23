package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

import java.util.Map;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class AdvancementBuilderHelper {
    public static Advancement.Builder rootAdvancement(Consumer<Advancement> consumer, String pAdvancementId, String pGroupId, ItemLike pDisplayItem, ResourceLocation pBackgroundPath, FrameType pFrameType, boolean showToast, boolean announce, boolean hidden, Map<String, CriterionTriggerInstance> pCriteria, AdvancementRewards pRewards, RequirementsStrategy pStrategy) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(
                        pDisplayItem,
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + "-root.title"),
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + "-root.desc"),
                        pBackgroundPath, pFrameType, showToast, announce, hidden
                )
                .requirements(pStrategy)
                .rewards(pRewards);

        for (Map.Entry<String, CriterionTriggerInstance> entry : pCriteria.entrySet()) {
            builder.addCriterion(entry.getKey(), entry.getValue());
        }
        builder.save(consumer, MhmBitsnbobs.MOD_ID + ":" + pGroupId + "/" + pAdvancementId + "-root");
        return builder;
    }

    public static Advancement.Builder advancementWithParent(Consumer<Advancement> consumer, String pAdvancementId, String pGroupId, Advancement pParent, ItemLike pDisplayItem, FrameType pFrameType, boolean showToast, boolean announce, boolean hidden, Map<String, CriterionTriggerInstance> pCriteria, AdvancementRewards pRewards, RequirementsStrategy pStrategy) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(pParent)
                .display(
                        pDisplayItem,
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + ".title"),
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + ".desc"),
                        null, pFrameType, showToast, announce, hidden
                )
                .requirements(pStrategy)
                .rewards(pRewards);

        for (Map.Entry<String, CriterionTriggerInstance> entry : pCriteria.entrySet()) {
            builder.addCriterion(entry.getKey(), entry.getValue());
        }
        builder.save(consumer, MhmBitsnbobs.MOD_ID + ":" + pGroupId + "/" + pAdvancementId);
        return builder;
    }
    public static Advancement.Builder advancementWithParent(Consumer<Advancement> consumer, String pAdvancementId, String pGroupId, ResourceLocation pParent, Item pDisplayItem, FrameType pFrameType, boolean showToast, boolean announce, boolean hidden, Map<String, CriterionTriggerInstance> pCriteria, AdvancementRewards pRewards, RequirementsStrategy pStrategy) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(pParent)
                .display(
                        pDisplayItem,
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + ".title"),
                        Component.translatable("advancement.mhm_bitsnbobs." + pGroupId + "." + pAdvancementId + ".desc"),
                        null, pFrameType, showToast, announce, hidden
                )
                .requirements(pStrategy)
                .rewards(pRewards);

        for (Map.Entry<String, CriterionTriggerInstance> entry : pCriteria.entrySet()) {
            builder.addCriterion(entry.getKey(), entry.getValue());
        }
        builder.save(consumer, MhmBitsnbobs.MOD_ID + ":" + pGroupId + "/" + pAdvancementId);
        return builder;
    }

    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item.
     */
    public static InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }

}
