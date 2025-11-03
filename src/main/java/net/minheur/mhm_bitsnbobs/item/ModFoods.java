package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;

/**
 * Registration of food types
 */
public class ModFoods {
    public static final FoodProperties BURGER = new FoodProperties.Builder().nutrition(10)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 0.7f).build();

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(10)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1f).build();

    public static final FoodProperties EXPLODED_POTATO = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.5f).build();

    public static final FoodProperties PIECE_OF_DIRT = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).alwaysEat().build();

    public static final FoodProperties FRIES_BOWL = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.1f).fast().build();

    public static final FoodProperties HOLY_BREAD = new FoodProperties.Builder().nutrition(20)
            .saturationMod(20f).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600,50), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 3600,4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 9), 1f).build();

    public static final FoodProperties DEVIl_BREAD = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.WITHER, 2000, 3), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 2000, 20), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 2000), 1f).build();

    public static final FoodProperties POLENTA_BOWL = new FoodProperties.Builder().nutrition(4)
            .saturationMod(8).build();
    public static final FoodProperties POLENTA_CUBE = new FoodProperties.Builder().nutrition(1)
            .saturationMod(2).fast().build();

    public static final FoodProperties VANILLA_PODS = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.HUNGER, 50, 3), 1f).build();

    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().nutrition(6)
            .saturationMod(0.6f).alwaysEat().fast().effect(() -> new MobEffectInstance(ModEffects.ICED.get(), 200, 0), 0.3f).build();
    public static final FoodProperties SORBET = new FoodProperties.Builder().nutrition(3)
            .saturationMod(0.2f).alwaysEat().fast().effect(() -> new MobEffectInstance(ModEffects.ICED.get(), 200, 0), 0.3f).build();
}
