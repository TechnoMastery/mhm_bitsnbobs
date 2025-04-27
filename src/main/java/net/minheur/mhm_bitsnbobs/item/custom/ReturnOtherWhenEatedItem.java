package net.minheur.mhm_bitsnbobs.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ReturnOtherWhenEatedItem extends Item {
    private final Item returnItem;

    public ReturnOtherWhenEatedItem(Properties pProperties, Item returnItem) {
        super(pProperties);
        this.returnItem = returnItem;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack retval = new ItemStack(returnItem);
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if(pStack.isEmpty()) {
            return retval;
        } else {
            if(pLivingEntity instanceof Player player && !player.getAbilities().instabuild) {
                if(!player.getInventory().add(retval))
                    player.drop(retval, false);
            }
            return pStack;
        }
    }
}
