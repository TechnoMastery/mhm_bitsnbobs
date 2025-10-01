package net.minheur.mhm_bitsnbobs.procedures.effects;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;

/**
 * Procedure for ticking of effect quantum chocked and for tick condition
 */
public class QuantumChockedProcedures {
    public static boolean executeActiveTickCondition(double amplifier, double duration) {
        double baseRate = 0;
        double rateWithAmplifier = 0;
        boolean valid = false;
        baseRate = 2;
        rateWithAmplifier = baseRate / Math.pow(2, amplifier);
        if (Math.floor(rateWithAmplifier) > 0) {
            return duration % Math.floor(rateWithAmplifier) == 0;
        }
        return true;
    }
    public static void executeActiveTickEffect(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player) {
            if (!((Player) entity).getInventory().contains(new ItemStack(Items.TOTEM_OF_UNDYING))) {
                entity.hurt(ModDamageTypes.electrocuted(entity.level()), 1.0f);
            }
        } else {
            entity.hurt(ModDamageTypes.electrocuted(entity.level()), 1.0f);
        }
    }

}
