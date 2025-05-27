package net.minheur.mhm_bitsnbobs.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.recipe.IncubatorRecipe;
import org.jetbrains.annotations.Nullable;

public class IncubatingCategory implements IRecipeCategory<IncubatorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "incubating");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MhmBitsnbobs.MOD_ID,
            "textures/gui/incubator_jei.png");

    public static final RecipeType<IncubatorRecipe> INCUBATING_TYPE =
            new RecipeType<>(UID, IncubatorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public IncubatingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.INCUBATOR.get()));
    }

    @Override
    public RecipeType<IncubatorRecipe> getRecipeType() {
        return INCUBATING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipe.mhm_bitsnbobs.incubator");
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IncubatorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 24).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.CATALYST, 80, 56).addItemStack(recipe.getCatalyzerItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 128, 24).addItemStack(recipe.getResultItem(null));
    }
}
