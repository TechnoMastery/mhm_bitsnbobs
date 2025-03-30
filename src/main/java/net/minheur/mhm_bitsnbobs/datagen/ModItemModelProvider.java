package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MhmBitsnbobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // dupli = auto get model item gen
        simpleitem(ModItems.SAPPHIRE);
        simpleitem(ModItems.RAW_SAPPHIRE);

        simpleitem(ModItems.BURGER);
        simpleitem(ModItems.STRAWBERRY);

        simpleitem(ModItems.METAL_DETECTOR);

        simpleitem(ModItems.PINE_CONE);
    }

    private ItemModelBuilder simpleitem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/" + item.getId().getPath()));
    }
}
