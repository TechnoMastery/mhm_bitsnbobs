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
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.item.custom.MagicFuelItem;
import net.minheur.mhm_bitsnbobs.recipe.MysteriousMagicRecipe;
import org.jetbrains.annotations.Nullable;

public class MysteriousMagicCategory implements IRecipeCategory<MysteriousMagicRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MhmBitsnbobs.MOD_ID, "mysterious_magic");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MhmBitsnbobs.MOD_ID,
            "textures/gui/mysterious_magic_jei.png");

    public static final RecipeType<MysteriousMagicRecipe> MYSTERIOUS_MAGIC_TYPE =
            new RecipeType<>(UID, MysteriousMagicRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MysteriousMagicCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 81);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.MAGIC_SHARD.get()));
    }

    @Override
    public RecipeType<MysteriousMagicRecipe> getRecipeType() {
        return MYSTERIOUS_MAGIC_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipe.mhm_bitsnbobs.mysterious_magic");
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
    public void setRecipe(IRecipeLayoutBuilder builder, MysteriousMagicRecipe recipe, IFocusGroup iFocusGroup) {
        ItemStack power = ModItems.MAGIC_SHARD.get().getDefaultInstance().setHoverName(
                Component.translatable("recipe.mhm_bitsnbobs.mysterious_magic.power").append(Component.literal(" : " + recipe.getFuelAmount()))
        );
        power.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 17, 32).addItemStack(power);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 33).addItemStack(recipe.getResultItem(null));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 32).addItemStack(recipe.getPrimaryInput());
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 7).addItemStack(recipe.getUpInput());
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 57).addItemStack(recipe.getDownInput());
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 32).addItemStack(recipe.getLeftInput());
        builder.addSlot(RecipeIngredientRole.INPUT, 105, 32).addItemStack(recipe.getRightInput());
    }
}
