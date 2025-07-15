package net.minheur.mhm_bitsnbobs.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GemPolishingRecipe>> GEM_POLISHING_SERIALIZER =
            SERIALIZERS.register("gem_polishing", () -> GemPolishingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<IncubatorRecipe>> INCUBATING_SERIALIZER =
            SERIALIZERS.register("incubating", () -> IncubatorRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MysteriousMagicRecipe>> MYSTERIOUS_MAGIC_SERIALIZER =
            SERIALIZERS.register("mysterious_magic", () -> MysteriousMagicRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<FreezingRecipe>> FREEZING_SERIALIZER =
            SERIALIZERS.register("freezing", () -> FreezingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
