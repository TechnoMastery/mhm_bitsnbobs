package net.minheur.mhm_bitsnbobs.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class OtherModItems {
    public static final Item NUMISMATICS_SPUR = ForgeRegistries.ITEMS.getValue(
            new ResourceLocation("numismatics", "spur")
    );
}
