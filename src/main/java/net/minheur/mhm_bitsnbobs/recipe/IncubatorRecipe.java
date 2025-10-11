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

public class IncubatorRecipe extends AbstractMultipleIngredientRecipe {
    private final ItemStack catalyzer;

    public IncubatorRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ItemStack catalyzer, ResourceLocation id) {
        super(id, inputItems, output);
        this.catalyzer = catalyzer;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return ingredients.get(0).test(simpleContainer.getItem(0)) && catalyzer.is(simpleContainer.getItem(2).getItem());
    }

    public ItemStack getCatalyzerItem() {
        return catalyzer.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }


    public static class Type implements RecipeType<IncubatorRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "incubating";
    }

    public static class Serializer implements RecipeSerializer<IncubatorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "incubating");

        @Override
        public IncubatorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            ItemStack catalyzer = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "catalyzer"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new IncubatorRecipe(inputs, output, catalyzer, pRecipeId);
        }

        @Override
        public @Nullable IncubatorRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output = pBuffer.readItem();
            ItemStack catalyzer = pBuffer.readItem();
            return new IncubatorRecipe(inputs, output, catalyzer, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, IncubatorRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.ingredients.size());
            for (Ingredient ingredients : pRecipe.getIngredients()) {
                ingredients.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            pBuffer.writeItemStack(pRecipe.getCatalyzerItem(), false);
        }
    }
}
