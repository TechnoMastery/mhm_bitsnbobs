package net.minheur.mhm_bitsnbobs.compat;

import net.minheur.techno_lib.otherItem.OtherModItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class contains the creation of {@link OtherModItem} instances.
 * It also has the other mod IDs
 * <p> It is used to reference them.
 */
public class OtherModItems {
    /**
     * The is ae2's mod OtherItem registration
     */
    public static class Ae2 {
        public static final OtherModItem CALCULATION_PROCESSOR = ae2Item("calculation_processor");
        public static final OtherModItem CERTUS_QUARTZ_BLOCK = ae2Item("quartz_block");
        public static final OtherModItem CERTUS_QUARTZ_CRYSTAL = ae2Item("certus_quartz_crystal");
        public static final OtherModItem FLAWLESS_BUDDING_CERTUS_QUARTZ = ae2Item("flawless_budding_quartz");
        public static final OtherModItem SINGULARITY = ae2Item("singularity");
        public static final OtherModItem PRINTED_SILICON = ae2Item("printed_silicon");
        public static final OtherModItem FLUIX_DUST = ae2Item("fluix_dust");
        public static final OtherModItem FLUIX_CRYSTAL = ae2Item("fluix_crystal");
        public static final OtherModItem LOGIC_PROCESSOR = ae2Item("logic_processor");
        public static final OtherModItem ENGINEERING_PROCESSOR = ae2Item("engineering_processor");
        public static final OtherModItem ENDER_DUST = ae2Item("ender_dust");


        private static OtherModItem ae2Item(String id) {
            return new OtherModItem("ae2", id);
        }
    }

    /**
     * The is Create's mod OtherItem registration
     */
    public static class Create {
        public static final OtherModItem COPPER_NUGGET = createItem("copper_nugget");
        public static final OtherModItem XP_NUGGET = createItem("experience_nugget");
        public static final OtherModItem CINDER_FLOUR = createItem("cinder_flour");
        public static final OtherModItem DOUGH = createItem("dough");
        public static final OtherModItem ZINC = createItem("zinc_ingot");
        public static final OtherModItem FLUID_TANK = createItem("fluid_tank");
        public static final OtherModItem CREATIVE_FLUID_TANK = createItem("creative_fluid_tank");
        public static final OtherModItem CREATIVE_MOTOR = createItem("creative_motor");
        public static final OtherModItem SHAFT = createItem("shaft");


        private static OtherModItem createItem(String id) {
            return new OtherModItem("create", id);
        }
    }

    /**
     * The is megacells's mod OtherItem registration
     */
    public static class MegaCells {
        public static final OtherModItem ACCUMULATION_PROCESSOR = megaCellsItem("accumulation_processor");


        private static OtherModItem megaCellsItem(String id) {
            return new OtherModItem("megacells", id);
        }
    }

    public static class Tfmg {
        public static final OtherModItem NICKEL_INGOT = tfmgItem("nickel_ingot");
        public static final OtherModItem LEAD_INGOT = tfmgItem("lead_ingot");
        public static final OtherModItem CIRCUIT_BOARD = tfmgItem("circuit_board");


        private static OtherModItem tfmgItem(String id) {
            return new OtherModItem("tfmg", id);
        }
    }
}
