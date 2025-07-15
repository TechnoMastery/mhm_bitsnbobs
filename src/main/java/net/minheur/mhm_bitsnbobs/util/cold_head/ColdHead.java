package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;

public class ColdHead implements IColdHead {
    private float cold = 0f;
    private long lastUpdateTick = 0L;
    private int damageCooldown = 0;

    @Override
    public float getColdLevel() {
        return cold;
    }

    @Override
    public void setColdLevel(float level) {
        this.cold = Mth.clamp(level, 0f, 1f);
    }

    @Override
    public void addCold(long currentTick, float amount) {
        this.cold = Mth.clamp(this.cold + amount, 0f, 1f);
        this.lastUpdateTick = currentTick;
    }

    @Override
    public void tick(long currentTick, LivingEntity entity) {
        if (currentTick - lastUpdateTick > 60) {
            this.cold = Math.max(0f, this.cold - 0.01f);
        }

        float level = this.cold;

        if (level < 0.25f) {
            damageCooldown = 0;
            return;
        }

        int damageInterval = 0;

        if (level < 0.5f) {
            damageInterval = 60;
        } else if (level < 0.75f) {
            damageInterval = 20;
        } else {
            damageInterval = 10;
        }

        damageCooldown++;
        if (damageCooldown >= damageInterval) {
            entity.hurt(ModDamageTypes.brainFreeze(entity.level()), 1.0f);
        }

    }
}
