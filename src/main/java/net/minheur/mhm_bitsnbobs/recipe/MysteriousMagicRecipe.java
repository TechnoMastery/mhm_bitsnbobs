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

public class MysteriousMagicRecipe implements Recipe<SimpleContainer> {
    private final ItemStack primaryInput;
    private final ItemStack upInput;
    private final ItemStack downInput;
    private final ItemStack leftInput;
    private final ItemStack rightInput;
    private final int fuelAmount;
    private final ItemStack output;
    private final ResourceLocation id;

    protected MysteriousMagicRecipe(ItemStack primaryInput, ItemStack upInput, ItemStack downInput, ItemStack leftInput, ItemStack rightInput, int fullAmount, ItemStack output, ResourceLocation id) {
        this.primaryInput = primaryInput;
        this.upInput = upInput;
        this.downInput = downInput;
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.fuelAmount = fullAmount;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;

        ItemStack fuel = pContainer.getItem(1);
        int fuelRemaining = fuel.getMaxDamage() - fuel.getDamageValue();
        if (fuelRemaining < this.fuelAmount) return false;

        System.out.println("FUEL PASSED");

        ItemStack primaryStack = pContainer.getItem(2);
        ItemStack upStack = pContainer.getItem(3);
        ItemStack rightStack = pContainer.getItem(4);
        ItemStack downStack = pContainer.getItem(5);
        ItemStack leftStack = pContainer.getItem(6);

        return
                areStacksEqualEnough(primaryInput, primaryStack) &&
                        areStacksEqualEnough(upInput, upStack) &&
                        areStacksEqualEnough(downInput, downStack) &&
                        areStacksEqualEnough(leftInput, leftStack) &&
                        areStacksEqualEnough(rightInput, rightStack);
    }

    private boolean areStacksEqualEnough(ItemStack expected, ItemStack actual) {
        System.out.println("CHECKING : expected " + expected + " having " + actual);
        return expected.getItem() == actual.getItem()
                && actual.getCount() >= expected.getCount();
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public ItemStack getUpInput() {
        return upInput.copy();
    }
    public ItemStack getDownInput() {
        return downInput.copy();
    }
    public ItemStack getLeftInput() {
        return leftInput.copy();
    }
    public ItemStack getRightInput() {
        return rightInput.copy();
    }
    public ItemStack getPrimaryInput() {
        return primaryInput.copy();
    }
    
    public int getFuelAmount() {
        return fuelAmount;
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

    public static class Type implements RecipeType<MysteriousMagicRecipe> {
        public static final MysteriousMagicRecipe.Type INSTANCE = new MysteriousMagicRecipe.Type();
        public static final String ID = "mysterious_magic";
    }

    public static class Serializer implements RecipeSerializer<MysteriousMagicRecipe> {
        public static final MysteriousMagicRecipe.Serializer INSTANCE = new MysteriousMagicRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "mysterious_magic");

        @Override
        public MysteriousMagicRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            try {
                ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
                ItemStack primaryInput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "primary"));
                int fuelAmount = GsonHelper.getAsInt(pSerializedRecipe, "fuelAmount");
                ItemStack upInput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "upItem"));
                ItemStack downInput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "downItem"));
                ItemStack leftInput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "leftItem"));
                ItemStack rightInput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "rightItem"));

                return new MysteriousMagicRecipe(primaryInput, upInput, downInput, leftInput, rightInput, fuelAmount, output, pRecipeId);
            } catch (Exception e) {
                System.err.println("Failed to parse recipe: " + pRecipeId);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public @Nullable MysteriousMagicRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ItemStack output = pBuffer.readItem();
            ItemStack primaryInput = pBuffer.readItem();
            int fuelAmount = pBuffer.readInt();
            ItemStack upInput = pBuffer.readItem();
            ItemStack downInput = pBuffer.readItem();
            ItemStack leftInput = pBuffer.readItem();
            ItemStack rightInput = pBuffer.readItem();
            return new MysteriousMagicRecipe(primaryInput, upInput, downInput, leftInput, rightInput, fuelAmount, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, MysteriousMagicRecipe pRecipe) {
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            pBuffer.writeItemStack(pRecipe.getPrimaryInput(), false);
            pBuffer.writeInt(pRecipe.fuelAmount);
            pBuffer.writeItemStack(pRecipe.getUpInput(), false);
            pBuffer.writeItemStack(pRecipe.getDownInput(), false);
            pBuffer.writeItemStack(pRecipe.getLeftInput(), false);
            pBuffer.writeItemStack(pRecipe.getRightInput(), false);
        }
        
    }
}
