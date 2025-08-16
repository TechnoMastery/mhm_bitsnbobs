package net.minheur.mhm_bitsnbobs.compat;

import net.minheur.mhm_bitsnbobs.compat.compatItemlike.OtherModItem;

/**
 * This class contains the creation of {@link OtherModItem} instances.
 * It also has the other mod IDs
 * <p> It is used to reference them.
 */
public class OtherModItems {
    public static final String CREATE = "create";
    public static final String MEGACELLS = "megacells";
    public static final String AE2 = "ae2";

    public static final OtherModItem CALCULATION_PROCESSOR =
            new OtherModItem(AE2, "calculation_processor", true);
    public static final OtherModItem ACCUMULATION_PROCESSOR =
            new OtherModItem(MEGACELLS, "accumulation_processor", true);
    public static final OtherModItem COPPER_NUGGET =
            new OtherModItem(CREATE, "copper_nugget", true);
    public static final OtherModItem CERTUS_QUARTZ_BLOCK =
            new OtherModItem(AE2, "quartz_block", true);
}
