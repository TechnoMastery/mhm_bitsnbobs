package net.minheur.mhm_bitsnbobs.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.techno_lib.custom.recipe.AbstractResultRecipe;
import org.jetbrains.annotations.Nullable;

import static net.minheur.techno_lib.Utils.areStacksEqualEnough;

public class AtomicalStabilizatorRecipe extends AbstractResultRecipe {
    private final ItemStack inputLeft;
    private final ItemStack inputRight;
    private final ItemStack glue;

    public AtomicalStabilizatorRecipe(ItemStack inputLeft, ItemStack inputRight, ItemStack glue, ItemStack output, ResourceLocation id) {
        super(id, output);
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
        this.glue = glue;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide()) return false;

        ItemStack inLeft = simpleContainer.getItem(0);
        ItemStack inRight = simpleContainer.getItem(1);
        ItemStack glueSlot = simpleContainer.getItem(2);

        return areStacksEqualEnough(inputLeft, inLeft) &&
                areStacksEqualEnough(inputRight, inRight) &&
                areStacksEqualEnough(glue, glueSlot);
    }

    public ItemStack getInputLeft() {
        return inputLeft.copy();
    }
    public ItemStack getInputRight() {
        return inputRight.copy();
    }
    public ItemStack getGlue() {
        return glue.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AtomicalStabilizatorRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return AtomicalStabilizatorRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<AtomicalStabilizatorRecipe> {
        public static final AtomicalStabilizatorRecipe.Type INSTANCE = new AtomicalStabilizatorRecipe.Type();
        public static final String ID = "atomical_stabilizator";
    }

    public static class Serializer implements RecipeSerializer<AtomicalStabilizatorRecipe> {
        public static AtomicalStabilizatorRecipe.Serializer INSTANCE = new AtomicalStabilizatorRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "atomical_stabilizator");

        @Override
        public AtomicalStabilizatorRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            try {
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));

                ItemStack inLeft = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "left"));
                ItemStack inRight = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "right"));
                ItemStack glueItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "glue"));

                return new AtomicalStabilizatorRecipe(inLeft, inRight, glueItem, output, resourceLocation);
            } catch (Exception e) {
                System.err.println("Failed to parse recipe : " + resourceLocation);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public @Nullable AtomicalStabilizatorRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf pBuffer) {
            ItemStack output = pBuffer.readItem();
            ItemStack inLeft = pBuffer.readItem();
            ItemStack inRight = pBuffer.readItem();
            ItemStack glueItem = pBuffer.readItem();

            return new AtomicalStabilizatorRecipe(inLeft, inRight, glueItem, output, resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, AtomicalStabilizatorRecipe pRecipe) {
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            pBuffer.writeItemStack(pRecipe.getInputLeft(), false);
            pBuffer.writeItemStack(pRecipe.inputRight, false);
            pBuffer.writeItemStack(pRecipe.getGlue(), false);
        }
    }


}
