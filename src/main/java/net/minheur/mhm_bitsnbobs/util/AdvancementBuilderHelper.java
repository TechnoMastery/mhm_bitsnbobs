package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

import java.util.Map;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

/**
 * Registers helper methods to create bits'n'bobs advancements
 */
public class AdvancementBuilderHelper {
    /**
     * Creates a root advancement.
     * @param consumer the recipe registering consumer
     * @param pAdvancementId the advancement ID
     * @param pGroupId the group it gets in. (ex. {@code story} or {@code adventure})
     * @param pDisplayItem the item to get displayed on the advancement tile
     * @param pBackgroundPath the path to the background texture
     * @param pFrameType the outer line of the tile. Also tell you players what type of advancement it is
     * @param showToast handles if a toast should appear on the player's screen when he earns the advancement
     * @param announce if a chat message should be sent
     * @param hidden if the player should be able to see the advancement until he earns it
     * @param pCriteria the criteria the player should achieve to earn this advancement
     * @param pRewards the rewards the player gets when earning the advancement
     * @param pStrategy the {@link RequirementsStrategy} used. Should be {@code AND} / {@code OR}
     * @return the finished advancement
     */
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

    /**
     * Creates an advancement with a parent.
     * @param consumer the recipe registering consumer
     * @param pAdvancementId the advancement ID
     * @param pGroupId the group it gets in. (ex. {@code story} or {@code adventure})
     * @param pParent the parent advancement
     * @param pDisplayItem the item to get displayed on the advancement tile
     * @param pFrameType the outer line of the tile. Also tell you players what type of advancement it is
     * @param showToast handles if a toast should appear on the player's screen when he earns the advancement
     * @param announce if a chat message should be sent
     * @param hidden if the player should be able to see the advancement until he earns it
     * @param pCriteria the criteria the player should achieve to earn this advancement
     * @param pRewards the rewards the player gets when earning the advancement
     * @param pStrategy the {@link RequirementsStrategy} used. Should be {@code AND} / {@code OR}
     * @return the finished advancement
     */
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
        System.out.println(pCriteria);
        for (Map.Entry<String, CriterionTriggerInstance> entry : pCriteria.entrySet()) {
            builder.addCriterion(entry.getKey(), entry.getValue());
        }
        builder.save(consumer, MhmBitsnbobs.MOD_ID + ":" + pGroupId + "/" + pAdvancementId);
        return builder;
    }

    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item.
     */
    public static InventoryChangeTrigger.TriggerInstance hasExactly(ItemLike pItemLike, int count) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).withCount(MinMaxBounds.Ints.exactly(count)).build());
    }
    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having more than a fixed amount of a certain item.
     */
    public static InventoryChangeTrigger.TriggerInstance hasMinimum(ItemLike pItemLike, int minimumCount) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).withCount(MinMaxBounds.Ints.atLeast(minimumCount)).build());
    }
    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having less than a fixed amount of a certain item.
     */
    public static InventoryChangeTrigger.TriggerInstance hasMaximum(ItemLike pItemLike, int maximumCount) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).withCount(MinMaxBounds.Ints.atMost(maximumCount)).build());
    }
    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item between 2 counts.
     */
    public static InventoryChangeTrigger.TriggerInstance hasBetween(ItemLike pItemLike, int minimumCount, int maximumCount) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).withCount(MinMaxBounds.Ints.between(minimumCount, maximumCount)).build());
    }
    /**
     * Creates a new {@link InventoryChangeTrigger} that checks for a player having a certain item.
     */
    public static InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(pItemLike);
    }

}
