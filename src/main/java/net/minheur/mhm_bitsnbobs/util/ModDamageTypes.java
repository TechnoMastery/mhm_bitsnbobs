package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModDamageTypes {
    public static final ResourceLocation BRAIN_FREEZE_ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "brain_freeze");
    public static final ResourceLocation ELECTROCUTED_ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "electrocuted");

    public static DamageSource brainFreeze(Level level) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, BRAIN_FREEZE_ID)));
    }
    public static DamageSource electrocuted(Level level) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ELECTROCUTED_ID)));
    }

}
