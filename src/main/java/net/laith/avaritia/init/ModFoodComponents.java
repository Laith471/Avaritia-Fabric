package net.laith.avaritia.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents {

    public static final FoodProperties ULTIMATE_STEW = new FoodProperties.Builder().saturationMod(20.0f)
            .nutrition(20).effect(new MobEffectInstance(MobEffects.REGENERATION, 6000, 1), 1).build();

    public static final FoodProperties COSMIC_MEATBALLS = new FoodProperties.Builder().saturationMod(20.0f)
            .nutrition(20).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 1), 1).build();
}
