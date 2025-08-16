package net.minheur.mhm_bitsnbobs.compat.jei;

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
import net.minheur.mhm_bitsnbobs.recipe.FreezingRecipe;

public class FreezingCategory implements IRecipeCategory<FreezingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "freezing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MhmBitsnbobs.MOD_ID,
            "textures/gui/gem_polishing_station_jei.png");

    public static final RecipeType<FreezingRecipe> FREEZING_TYPE =
            new RecipeType<>(UID, FreezingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FreezingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FREEZER.get()));
    }

    @Override
    public RecipeType<FreezingRecipe> getRecipeType() {
        return FREEZING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipe.mhm_bitsnbobs.gem_polishing_station");
    }

    @Override
    public IDrawable getBackground() {    // warning
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FreezingRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }

}
