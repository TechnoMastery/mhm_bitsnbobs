package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.advancement.ModAdvancementProvider;
import net.minheur.mhm_bitsnbobs.item.ModItems;

/**
 * We create instances of {@link net.minheur.mhm_bitsnbobs.advancement.ModAdvancementProvider.AdvancementBuilder}
 * to be able to access them from everywhere. We then call them with {@code .built()} in {@link #addAdvancement()}.
 */
public class ModAdvancementGen extends ModAdvancementProvider {
    protected ModAdvancementGen(PackOutput output) {
        super(output, MhmBitsnbobs.MOD_ID);
    }

    private static final ResourceLocation inventoryChanged = new ResourceLocation("minecraft", "inventory_changed");

    public final AdvancementBuilder creativeRoot = addRoot("the_essence", "creative")
            .display(ModItems.CREATIVE_ESSENCE.get(), new ResourceLocation(MhmBitsnbobs.MOD_ID, "textures/block/creative_block.png"),
                    FrameType.TASK, true, true, false)
            .criterion("has_creative_essence", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.CREATIVE_ESSENCE.get()).build()
            )
            .requirements(new String[][]{
                    {"has_creative_essence"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(150).build());
    public final AdvancementBuilder creativeIngot = addWithParent("the_ingot", "creative")
            .display(ModItems.CREATIVE_INGOT.get(), null, FrameType.TASK,
                    true, true, false)
            .criterion("has_creative_ingot", simpleCriterion(
                    inventoryChanged).itemCondition(ModItems.CREATIVE_INGOT.get()).build()
            )
            .requirements(new String[][]{
                    {"has_creative_ingot"}
            })
            .rewards(new AdvancementRewards.Builder().addExperience(300).build());


    @Override
    protected void addAdvancement() {
        creativeRoot.build();

    }
}
