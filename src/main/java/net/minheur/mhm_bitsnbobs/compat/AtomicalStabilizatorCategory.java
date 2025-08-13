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
import net.minecraft.world.item.crafting.Ingredient;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.recipe.AtomicalStabilizatorRecipe;
import org.jetbrains.annotations.Nullable;

public class AtomicalStabilizatorCategory implements IRecipeCategory<AtomicalStabilizatorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "atomical_stabilizator");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MhmBitsnbobs.MOD_ID,
            "textures/gui/atomical_stabilizator_jei.png");

    public static final RecipeType<AtomicalStabilizatorRecipe> ATOMICAL_STABILIZATOR_TYPE =
            new RecipeType<>(UID, AtomicalStabilizatorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AtomicalStabilizatorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ATOMICAL_STABILIZATOR.get()));
    }

    @Override
    public RecipeType<AtomicalStabilizatorRecipe> getRecipeType() {
        return ATOMICAL_STABILIZATOR_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipe.mhm_bitsnbobs.atomical_stabilizator");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AtomicalStabilizatorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 11).addItemStack(recipe.getInputLeft());
        builder.addSlot(RecipeIngredientRole.INPUT, 108, 11).addItemStack(recipe.getInputRight());
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addItemStack(recipe.getGlue());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}
