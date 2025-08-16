package net.minheur.mhm_bitsnbobs.compat.compatItemlike;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import static com.google.gson.JsonParser.parseString;

/**
 * Represents an item from another mod for compat recipes.
 * <p>
 *     This class allow to create an {@link Ingredient} for recipes even if the target mod isn't loaded in the
 *     dev environment.
 *     The item can be referenced with his {@link ResourceLocation}.
 * </p>
 */
public class OtherModItem {
    /** The item's mod ID (ex. "ae2") */
    private final String MODID;
    /** The item's ID (ex. "calculation_processor") */
    private final String ID;
    /**
     * The raw item. Warning : id the mod isn't loaded, it'll be null.
     * <p>Only for hard dependencies.
     */
    private final Item RAW_ITEM;

    /**
     * Construct an object which represents the other mod item.
     * @param modid the item's mod ID (ex. "ae2")
     * @param id the item's ID (ex. "calculation_processor")
     */
    public OtherModItem(String modid, String id) {
        this.MODID = modid;
        this.ID = id;
        this.RAW_ITEM = ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, ID));
    }

    /**
     * @return the item's mod ID
     */
    public String getModId() {
        return MODID;
    }

    /**
     * @return the item's ID
     */
    public String getId() {
        return ID;
    }

    /**
     * Returns a {@link Ingredient} which represents the item.
     * <p>
     *     The Ingredient can be used in recipes for compat.
     *     This method work even if the mod isn't loaded in the dev environment.
     * </p>
     *
     * @return a recipe-usable {@link Ingredient}
     */
    public Ingredient getAsIngredient() {
        if (RAW_ITEM != null) return Ingredient.of(RAW_ITEM);

        String json = "{ \"item\": \"" + MODID + ":" + ID + "\" }";
        return Ingredient.fromJson(parseString(json));
    }

    /**
     * Only use it if you are sure the mod is loaded.
     * Prefer using {@link #getAsIngredient()} when the mod is optional.
     * @return the raw item or an exeption if null
     */
    public Item getAsRawItem() {
        if (RAW_ITEM == null) {
            throw new NullPointerException("Compat item not found: \"" + MODID + ":" + ID + "\"");
        }

        return RAW_ITEM;
    }
}
