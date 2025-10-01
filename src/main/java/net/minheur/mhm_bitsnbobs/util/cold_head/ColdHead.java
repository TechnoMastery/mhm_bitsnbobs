package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;
import net.minheur.mhm_bitsnbobs.util.ModNetworking;

/**
 * Cold head main class
 */
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

        if (!entity.level().isClientSide() && entity instanceof ServerPlayer pPlayer) {
            ModNetworking.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> pPlayer),
                    new SyncColdHeadPacket(this.cold)
            );
        }

        float level = this.cold;

        if (level < 0.15f) {
            damageCooldown = 0;
            return;
        }

        int damageInterval = 0;

        if (level < 0.5f) {
            damageInterval = 80;
        } else if (level < 0.75f) {
            damageInterval = 40;
        } else {
            damageInterval = 20;
        }

        damageCooldown++;
        if (damageCooldown >= damageInterval) {
            entity.hurt(ModDamageTypes.brainFreeze(entity.level()), 1.0f);
        }
    }
}
