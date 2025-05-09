package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModWoodTypes {
    public static final WoodType DARK = WoodType.register(new WoodType(MhmBitsnbobs.MOD_ID + ":dark", BlockSetType.OAK));
}
