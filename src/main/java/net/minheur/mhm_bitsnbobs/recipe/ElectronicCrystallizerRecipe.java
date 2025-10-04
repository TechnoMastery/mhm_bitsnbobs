package net.minheur.mhm_bitsnbobs.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import org.jetbrains.annotations.Nullable;

public class ElectronicCrystallizerRecipe implements Recipe<SimpleContainer> {
    private final ItemStack ingredient;
    private final ItemStack ingredientPlaceholder;
    private final ItemStack result;
    private final int energy;
    private final ResourceLocation id;

    public ElectronicCrystallizerRecipe(ItemStack ingredient, ItemStack ingredientPlaceholder, ItemStack result, int energy, ResourceLocation id) {
        this.ingredient = ingredient;
        this.ingredientPlaceholder = ingredientPlaceholder;
        this.result = result;
        this.energy = energy;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;
        return ingredient == pContainer.getItem(0);
    }

    public ItemStack getIngredient() {
        return ingredient.copy();
    }
    public ItemStack getIngredientPlaceholder() {
        return ingredientPlaceholder.copy();
    }
    public int getEnergyNeed() {
        return energy;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ElectronicCrystallizerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "electronic_crystallizer";
    }

    public static class Serializer implements RecipeSerializer<ElectronicCrystallizerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "electronic_crystallizer");

        @Override
        public ElectronicCrystallizerRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
            try {
                ItemStack input = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredient"));
                ItemStack inputPlaceholder = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "placeholder"));
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));
                int energyNeeded = GsonHelper.getAsInt(jsonObject, "energy");

                return new ElectronicCrystallizerRecipe(input, inputPlaceholder, output, energyNeeded, pRecipeId);
            } catch (Exception e) {
                System.err.println("Failed to parse recipe : " + pRecipeId);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public @Nullable ElectronicCrystallizerRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ItemStack input = pBuffer.readItem();
            ItemStack inputPlaceholder = pBuffer.readItem();
            ItemStack output = pBuffer.readItem();
            int energy = pBuffer.readInt();

            return new ElectronicCrystallizerRecipe(input, inputPlaceholder, output, energy, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ElectronicCrystallizerRecipe pRecipe) {
            pBuffer.writeItemStack(pRecipe.getIngredient(), false);
            pBuffer.writeItemStack(pRecipe.getIngredientPlaceholder(), false);
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            pBuffer.writeInt(pRecipe.getEnergyNeed());
        }
    }
}
