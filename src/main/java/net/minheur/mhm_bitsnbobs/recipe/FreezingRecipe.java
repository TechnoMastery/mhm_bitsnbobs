package net.minheur.mhm_bitsnbobs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.custom.recipe.AbstractMultipleIngredientRecipe;
import org.jetbrains.annotations.Nullable;

public class FreezingRecipe extends AbstractMultipleIngredientRecipe {
    public FreezingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        super(id, inputItems, output);
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        return this.ingredients.get(0).test(simpleContainer.getItem(0));
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FreezingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return FreezingRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<FreezingRecipe> {
        public static final FreezingRecipe.Type INSTANCE = new FreezingRecipe.Type();
        public static final String ID = "freezing";
    }

    public static class Serializer implements RecipeSerializer<FreezingRecipe> {
        public static FreezingRecipe.Serializer INSTANCE = new FreezingRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "freezing");

        @Override
        public FreezingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            // the size changes depending on the amount of inputs the recipe have
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i=0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new FreezingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public @Nullable FreezingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for(int i=0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output = pBuffer.readItem();
            return new FreezingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FreezingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.ingredients.size());
            for(Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }













}
