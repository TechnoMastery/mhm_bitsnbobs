package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraft.world.entity.LivingEntity;

public interface IColdHead {
    float getColdLevel();
    void setColdLevel(float level);
    void addCold(long currentTick, float amount);
    void tick(long currentTick, LivingEntity entity);
}
