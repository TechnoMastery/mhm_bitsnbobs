package net.minheur.mhm_bitsnbobs.advancement;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;

public class ModAdvancements {
    private static final ResourceLocation inventoryChanged = new ResourceLocation("minecraft", "inventory_changed");

    // creative
    public static final String creativeGroup = "creative";
    public static final AdvancementBuilder creative_THE_ESSENCE_root = addRoot("the_essence", creativeGroup)
            .display(ModItems.CREATIVE_ESSENCE.get(), new ResourceLocation(MhmBitsnbobs.MOD_ID, "textures/block/creative_block.png"),
                    FrameType.TASK, true, true, false)
            .criterion("has_creative_essence", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.CREATIVE_ESSENCE.get()).build())
            .requirements(new String[][]{
                    {"has_creative_essence"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(150).build());
    public static final AdvancementBuilder creative_THE_INGOT = addWithParent("the_ingot", creativeGroup)
            .parent(creative_THE_ESSENCE_root.getLoc())
            .display(ModItems.CREATIVE_INGOT.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("has_creative_ingot", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.CREATIVE_INGOT.get()).build())
            .requirements(new String[][]{
                    {"has_creative_ingot"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(300).build());
    public static final AdvancementBuilder creative_THE_EGG = addWithParent("the_egg", creativeGroup)
            .parent(creative_THE_INGOT.getLoc())
            .display(ModItems.BASE_EGG.get(), null, FrameType.GOAL, true, true, false)
            .criterion("has_the_egg", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.BASE_EGG.get()).build())
            .requirements(new String[][]{
                    {"has_the_egg"}
            });
    public static final AdvancementBuilder creative_PACKAGE = addWithParent("creative_package", creativeGroup)
            .parent(creative_THE_ESSENCE_root.getLoc())
            .display(ModBlocks.CREATIVE_BLOCK.get(), null, FrameType.CHALLENGE, true, true, true)
            .criterion("creative_package", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.SMALL_CREATIVE_NUGGET.get(),
                    ModItems.CREATIVE_NUGGET.get(),
                    ModItems.CREATIVE_ESSENCE.get(),
                    ModItems.CREATIVE_INGOT.get(),
                    ModBlocks.CREATIVE_BLOCK.get()).build())
            .requirements(new String[][]{
                    {"creative_package"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(15000).addLootTable(
                    new ResourceLocation(MhmBitsnbobs.MOD_ID, "blocks/creative_block")
            ).build());

    // adventure
    public static final String adventureGroup = "adventure";
    public static final AdvancementBuilder adventure_ITS_CHARGED = addWithParent("its_charged", adventureGroup)
            .parent(new ResourceLocation("minecraft", "adventure/avoid_vibration"))
            .display(ModItems.SUPER_CHARGED_INGOT.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("super_charged_ingot", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.SUPER_CHARGED_INGOT.get()).build())
            .requirements(new String[][]{
                    {"super_charged_ingot"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(350).build());
    public static final AdvancementBuilder adventure_LIGTHNING_UPGRADE = addWithParent("lightning_upgrade", adventureGroup)
            .parent(new ResourceLocation("minecraft", "adventure/avoid_vibration"))
            .display(ModItems.LIGHTNING_UPGRADE.get(), null, FrameType.TASK, true, true, false)
            .criterion("lightning_upgrade", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.LIGHTNING_UPGRADE.get()).build())
            .requirements(new String[][]{
                    {"lightning_upgrade"}
            });
    public static final AdvancementBuilder adventure_STORM_SWORD = addWithParent("storm_sword", adventureGroup)
            .parent(adventure_LIGTHNING_UPGRADE.getLoc())
            .display(ModItems.LIGHTNING_SWORD.get(), null, FrameType.GOAL, true, true, false)
            .criterion("sword", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.LIGHTNING_SWORD.get()).build())
            .requirements(new String[][]{
                    {"sword"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(5000).build());

    // methods
    /**
     * Adding a root advancement
     * @param id the id of your advancement
     * @param group the group of your advancement (ex. story, adventure...)
     * @return a new advancement builder
     */
    protected static AdvancementBuilder addRoot(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id + "-root", group, true, MhmBitsnbobs.MOD_ID);
    }
    /**
     * Adding an advancement with a parent
     * @param id the id of your advancement
     * @param group the group pf your advancement (ex. story, adventure...). It should be the same as the root !
     * @return the new advancement builder
     */
    protected static AdvancementBuilder addWithParent(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id, group, false, MhmBitsnbobs.MOD_ID);
    }

    protected static CriterionBuilder simpleCriterion(ResourceLocation trigger) {
        return new CriterionBuilder(trigger);
    }
}
