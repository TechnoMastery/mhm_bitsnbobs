package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BURGER = new FoodProperties.Builder().nutrition(10)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 0.7f).build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(10)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1f).build();

    public static final FoodProperties EXPLODED_POTATO = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.5f).build();

    public static final FoodProperties PIECE_OF_DIRT = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).alwaysEat().build();
}
