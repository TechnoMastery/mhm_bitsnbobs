package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
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

        // pour une porte call simpleBlockItem sans
        simpleBlockItem(ModBlocks.SAPPHIRE_DOOR);

        // fence : call fence, add baseBlock texture at second parameter ==> same pour button & wall but change method
        fenceItem(ModBlocks.SAPPHIRE_FENCE, ModBlocks.SAPPHIRE_BLOCK);
        buttonItem(ModBlocks.SAPPHIRE_BUTTON, ModBlocks.SAPPHIRE_BLOCK);
        wallItem(ModBlocks.SAPPHIRE_WALL, ModBlocks.SAPPHIRE_BLOCK);

        // staire, slab, pressure_plate and gate
        eventSimplerBlockItem(ModBlocks.SAPPHIRE_STAIRS);
        eventSimplerBlockItem(ModBlocks.SAPPHIRE_SLAB);
        eventSimplerBlockItem(ModBlocks.SAPPHIRE_PRESSURE_PLATE);
        eventSimplerBlockItem(ModBlocks.SAPPHIRE_FENCE_GATE);

        // trapdoor
        trapdoorItem(ModBlocks.SAPPHIRE_TRAPDOOR);
    }

    private ItemModelBuilder simpleitem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void eventSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(MhmBitsnbobs.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID,"item/" + item.getId().getPath()));
    }
}
