package net.minheur.mhm_bitsnbobs.procedures;

import net.minecraft.world.entity.Entity;

public class FireSwordHitEvent {
    public static void execute(Entity pEntity) {
        if(pEntity == null)
            return;
        pEntity.setSecondsOnFire(30);
    }
}
