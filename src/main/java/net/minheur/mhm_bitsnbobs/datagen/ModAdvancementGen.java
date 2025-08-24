package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.advancement.ModAdvancementProvider;

public class ModAdvancementGen extends ModAdvancementProvider {
    protected ModAdvancementGen(PackOutput output) {
        super(output, MhmBitsnbobs.MOD_ID);
    }

    @Override
    protected void addAdvancement() {

    }
}
