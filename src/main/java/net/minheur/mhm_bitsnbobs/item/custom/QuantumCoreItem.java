package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.procedures.QuantumCoreProcedures;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuantumCoreItem extends Item {
    private static int level;

    public QuantumCoreItem(Properties pProperties, int level) {
        super(pProperties);
        QuantumCoreItem.level = level;
    }
    public static int getItemLevel() { return level; }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        if (isFinished()) {
            return UseAnim.BOW;
        } else {
            return super.getUseAnimation(pStack);
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        QuantumCoreProcedures.executeTickInInventory(pEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(MhmBitsnbobs.isNotAe2()) {
            pTooltipComponents.add(Component.translatable("nomod.ae2.item"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (isFinished()) {
            ItemStack returnValue = super.finishUsingItem(pStack, pLevel, pLivingEntity);
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();
            QuantumCoreProcedures.executeFinishedUsing(pLevel, x, y, z, pLivingEntity);
            return returnValue;
        } else {
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        if(isFinished()) {
            return 30;
        } else {
            return super.getUseDuration(pStack);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(isFinished()) {
            InteractionResultHolder<ItemStack> ar = super.use(pLevel, pPlayer, pUsedHand);
            pPlayer.startUsingItem(pUsedHand);
            return ar;
        } else {
            return super.use(pLevel, pPlayer, pUsedHand);
        }
    }

    public static boolean isFinished() {
        return level == 0;
    }
}
