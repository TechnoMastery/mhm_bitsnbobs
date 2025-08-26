package net.minheur.mhm_bitsnbobs.advancement;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.compat.OtherModItems;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.techno_lib.advancement.AdvancementBuilder;
import net.minheur.techno_lib.advancement.CriterionBuilder;

/**
 * The advancement registering class. To add them to the datagen, go to {@link net.minheur.mhm_bitsnbobs.datagen.ModAdvancementGen} and add it to the {@code build} function.
 */
public class ModAdvancements {
    private static final ResourceLocation inventoryChanged = new ResourceLocation("minecraft", "inventory_changed");
    public static final ResourceLocation placedBlock = new ResourceLocation("minecraft", "placed_block");

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
            .display(ModItems.BASE_EGG.get(), null, FrameType.GOAL,
                    true, true, false)
            .criterion("has_the_egg", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.BASE_EGG.get()).build())
            .requirements(new String[][]{
                    {"has_the_egg"}
            });
    public static final AdvancementBuilder creative_PACKAGE = addWithParent("creative_package", creativeGroup)
            .parent(creative_THE_ESSENCE_root.getLoc())
            .display(ModBlocks.CREATIVE_BLOCK.get(), null, FrameType.CHALLENGE,
                    true, true, true)
            .criterion("creative_package", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.SMALL_CREATIVE_NUGGET.get())
                    .itemCondition(ModItems.CREATIVE_NUGGET.get())
                    .itemCondition(ModItems.CREATIVE_ESSENCE.get())
                    .itemCondition(ModItems.CREATIVE_INGOT.get())
                    .itemCondition(ModBlocks.CREATIVE_BLOCK.get()).build())
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
            .display(ModItems.LIGHTNING_UPGRADE.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("lightning_upgrade", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.LIGHTNING_UPGRADE.get()).build())
            .requirements(new String[][]{
                    {"lightning_upgrade"}
            });
    public static final AdvancementBuilder adventure_STORM_SWORD = addWithParent("storm_sword", adventureGroup)
            .parent(adventure_LIGTHNING_UPGRADE.getLoc())
            .display(ModItems.LIGHTNING_SWORD.get(), null, FrameType.GOAL,
                    true, true, false)
            .criterion("sword", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.LIGHTNING_SWORD.get()).build())
            .requirements(new String[][]{
                    {"sword"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(5000).build());

    // ae2
    public static final String ae2Group = "ae2";
    public static final AdvancementBuilder ae2_SINGULARITY = addWithParent("singularity", ae2Group)
            .parent(new ResourceLocation("ae2", "main/glass_cable"))
            .display(OtherModItems.Ae2.SINGULARITY.getAsRawItem(), null, FrameType.TASK,
                    true, true, false)
            .criterion("singularity", simpleCriterion(
                    inventoryChanged).itemCondition(OtherModItems.Ae2.SINGULARITY.getAsRawItem()).build())
            .requirements(new String[][]{
                    {"singularity"}
            });
    public static final AdvancementBuilder ae2_QUANTUM_CORE = addWithParent("quantum_core", ae2Group)
            .parent(ae2_SINGULARITY.getLoc())
            .display(ModItems.QUANTUM_CORE.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("core", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.QUANTUM_CORE.get()).build())
            .requirements(new String[][]{
                    {"core"}
            });
    public static final AdvancementBuilder ae2_STABLE_POWER = addWithParent("stable_power", ae2Group)
            .parent(ae2_QUANTUM_CORE.getLoc())
            .display(ModItems.STABILIZED_QUANTUM_CORE.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("core", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.STABILIZED_QUANTUM_CORE.get()).build())
            .requirements(new String[][]{
                    {"core"}
            });
    public static final AdvancementBuilder ae2_QUANTUM_STAFF = addWithParent("quantum_staff", ae2Group)
            .parent(ae2_STABLE_POWER.getLoc())
            .display(ModItems.QUANTUM_STAFF.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("staff", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.QUANTUM_STAFF.get()).build())
            .requirements(new String[][]{
                    {"staff"}
            });

    // dirt
    public static final String dirtGroup = "dirt";
    public static final AdvancementBuilder dirt_DIRT_root = addRoot("dirt", dirtGroup)
            .display(Items.DIRT, new ResourceLocation("minecraft", "textures/block/dirt.png"),
                    FrameType.TASK, true, true, false)
            .criterion("dirt", simpleCriterion(
                    inventoryChanged).tagCondition(ItemTags.DIRT).build())
            .requirements(new String[][]{
                    {"dirt"}
            });
    public static final AdvancementBuilder dirt_VERY_COMPRESSED = addWithParent("very_compressed", dirtGroup)
            .parent(dirt_DIRT_root.getLoc())
            .display(ModBlocks.COMPRESSED_DIRT.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("dirt", simpleCriterion(
                    inventoryChanged).itemCondition(ModBlocks.COMPRESSED_DIRT.get()).build())
            .requirements(new String[][]{
                    {"dirt"}
            });
    public static final AdvancementBuilder dirt_RESOURCE_PACK = addWithParent("resource_pack", dirtGroup)
            .parent(dirt_VERY_COMPRESSED.getLoc())
            .display(ModBlocks.RESOURCE_DIRT_BLOCK.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("iron", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.IRON_BALL.get()).build())
            .criterion("copper", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.COPPER_BALL.get()).build())
            .criterion("gold", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.GOLD_BALL.get()).build())
            .criterion("diamond", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.DIAMOND_BALL.get()).build())
            .criterion("sapphire", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.SAPPHIRE_BALL.get()).build())
            .requirements(new String[][]{
                    {"iron"},
                    {"copper"},
                    {"gold"},
                    {"diamond"},
                    {"sapphire"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(15).build());
    public static final AdvancementBuilder dirt_ATE_DIRT = addWithParent("ate_dirt", dirtGroup)
            .parent(dirt_VERY_COMPRESSED.getLoc())
            .display(ModItems.WET_DIRT.get(), null, FrameType.GOAL,
                    true, true, false)
            .criterion("dirt", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.WET_DIRT.get()).build())
            .requirements(new String[][]{
                    {"dirt"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(150).build());
    public static final AdvancementBuilder dirt_PLACED_RUBIS_ORE = addWithParent("placed_rubis_ore", dirtGroup)
            .parent(dirt_RESOURCE_PACK.getLoc())
            .display(ModBlocks.RUBIS_ORE.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("place_ore", simpleCriterion(
                    placedBlock).blockPlacedCondition(ModBlocks.RUBIS_ORE.get()).build())
            .requirements(new String[][]{
                    {"place_ore"}
            });
    public static final AdvancementBuilder dirt_PLACED_RUBINIUM = addWithParent("placed_rubinium", dirtGroup)
            .parent(dirt_PLACED_RUBIS_ORE.getLoc())
            .display(ModBlocks.RUBINIUM_BLOCK.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("place_block", simpleCriterion(
                    placedBlock).blockPlacedCondition(ModBlocks.RUBINIUM_BLOCK.get()).build())
            .requirements(new String[][]{
                    {"place_block"}
            });
    public static final AdvancementBuilder dirt_HOT_SWORD = addWithParent("hot_sword", dirtGroup)
            .parent(dirt_PLACED_RUBIS_ORE.getLoc())
            .display(ModItems.FIRE_SWORD.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("get_sword", simpleCriterion(
                    placedBlock).itemCondition(ModItems.FIRE_SWORD.get()).build())
            .requirements(new String[][]{
                    {"get_sword"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(5).build());
    public static final AdvancementBuilder dirt_STRONG_SWORD = addWithParent("strong_sword", dirtGroup)
            .parent(dirt_HOT_SWORD.getLoc())
            .display(ModItems.FIRE_SWORD.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("get_sword", simpleCriterion(
                    placedBlock).itemCondition(ModItems.RUBINIUM_SWORD.get()).build())
            .requirements(new String[][]{
                    {"get_sword"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(150).build());

    // story
    public static final String storyGroup = "story";
    public static final AdvancementBuilder story_HARD_IRON = addWithParent("hard_iron", storyGroup)
            .parent(new ResourceLocation("minecraft", "story/smelt_iron"))
            .display(ModItems.HARDENED_INGOT.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("hardened_ingot", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.HARDENED_INGOT.get()).build())
            .requirements(new String[][]{
                    {"hardened_ingot"}
            });
    public static final AdvancementBuilder story_FIRE_POWER = addWithParent("fire_power", storyGroup)
            .parent(new ResourceLocation("minecraft", "story/mine_diamond"))
            .display(ModItems.FIRE_DIAMOND.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("get_diamond", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.FIRE_DIAMOND.get()).build())
            .requirements(new String[][]{
                    {"get_diamond"}
            });
    public static final AdvancementBuilder story_GROWING_FIRE = addWithParent("growing_fire", storyGroup)
            .parent(story_FIRE_POWER.getLoc())
            .display(ModItems.FIRE_SEEDS.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("seeds", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.FIRE_SEEDS.get()).build())
            .requirements(new String[][]{
                    {"seeds"}
            });
    public static final AdvancementBuilder story_BUTTONS = addWithParent("buttons", storyGroup)
            .parent(new ResourceLocation("minecraft", "story/root"))
            .display(Items.OAK_BUTTON, null, FrameType.GOAL,
                    true, true, true)
            .criterion("buttons", simpleCriterion(
                    inventoryChanged).tagCondition(ItemTags.BUTTONS, 64).build())
            .requirements(new String[][]{
                    {"buttons"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(150).addLootTable(
                    new ResourceLocation(MhmBitsnbobs.MOD_ID, "blocks/resource_dirt")
            ).build());
    public static final AdvancementBuilder story_MORE_BUTTONS = addWithParent("more_buttons", storyGroup)
            .parent(story_BUTTONS.getLoc())
            .display(Items.DARK_OAK_BUTTON, null, FrameType.GOAL,
                    true, true, true)
            .criterion("buttons", simpleCriterion(
                    inventoryChanged).tagCondition(ItemTags.BUTTONS, 128).build())
            .requirements(new String[][]{
                    {"buttons"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(350).addLootTable(
                    new ResourceLocation(MhmBitsnbobs.MOD_ID, "blocks/resource_dirt")
            ).build());

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
    /**
     * Creates a builder for criterion
     * @param trigger the id of the criterion's trigger
     * @return a new criterion builder
     */
    protected static CriterionBuilder simpleCriterion(ResourceLocation trigger) {
        return new CriterionBuilder(trigger);
    }
}
