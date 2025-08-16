package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.block.custom.CornCropBlock;
import net.minheur.mhm_bitsnbobs.block.custom.StrawberryCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MhmBitsnbobs.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);

        blockWithItem(ModBlocks.RUBIS_ORE);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.CREATIVE_BLOCK);
        blockWithItem(ModBlocks.CREATIVE_RESIDUE_BLOCK);
        blockWithItem(ModBlocks.RUBINIUM_BLOCK);
        blockWithItem(ModBlocks.SUPER_CHARGED_BLOCK);
        blockWithItem(ModBlocks.COMPRESSED_DIRT);
        blockWithItem(ModBlocks.EXTREMELY_DRY_DIRT_BLOCK);
        blockWithItem(ModBlocks.RESOURCE_DIRT_BLOCK);
        blockWithItem(ModBlocks.SOUND_BLOCK);

        // signs
        signBlock(((StandingSignBlock) ModBlocks.DARK_SIGN.get()), ((WallSignBlock) ModBlocks.DARK_WALL_SIGN.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        hangingSignBlock(ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.DARK_PLANKS.get()));

        // wood
        logBlock(((RotatedPillarBlock) ModBlocks.DARK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.DARK_WOOD.get()), blockTexture(ModBlocks.DARK_LOG.get()), blockTexture(ModBlocks.DARK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_LOG.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/stripped_dark_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_WOOD.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()));
        blockItem(ModBlocks.DARK_LOG);
        blockItem(ModBlocks.DARK_WOOD);
        blockItem(ModBlocks.STRIPPED_DARK_LOG);
        blockItem(ModBlocks.STRIPPED_DARK_WOOD);
        blockWithItem(ModBlocks.DARK_PLANKS);
        leavesBlock(ModBlocks.DARK_LEAVES);
        saplingBlock(ModBlocks.DARK_SAPLING);

        // stairs : to a .cast after the first .get to make it ok, .cast for [ slab , stairs , button, pressure_plate , fence , fence_gate , wall ] too (or duplicate)
        stairsBlock(((StairBlock) ModBlocks.SAPPHIRE_STAIRS.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        // slab : 2 textures
        slabBlock(((SlabBlock) ModBlocks.SAPPHIRE_SLAB.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.SAPPHIRE_BUTTON.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SAPPHIRE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.SAPPHIRE_FENCE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.SAPPHIRE_FENCE_GATE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.SAPPHIRE_WALL.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        // warning for the door : 2 texture block and "cutout" at end
        doorBlockWithRenderType(((DoorBlock) ModBlocks.SAPPHIRE_DOOR.get()), modLoc("block/sapphire_door_bottom"), modLoc("block/sapphire_door_top"), "cutout");
        // then trapdoor too but 1 texture plus an orientable value
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.SAPPHIRE_TRAPDOOR.get()), modLoc("block/sapphire_trapdoor"), true, "cutout");

        // flowers      paté de 2 simpleBlockWithItem (flower + potted_flower)
        simpleBlockWithItem(ModBlocks.CATMINT.get(), models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(), models().singleTexture("potted_catmint", new ResourceLocation("flower_pot_cross"),
                "plant", blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.FIRE_PLANT.get(), models().cross(blockTexture(ModBlocks.FIRE_PLANT.get()).getPath(),
                blockTexture(ModBlocks.FIRE_PLANT.get())).renderType("cutout"));

        // quand paté de crop fini, l'appeler ici
        makeStrawberryCrop((CropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
        makeCornCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_stage_", "corn_stage_");

        // block entity
        simpleBlockWithItem(ModBlocks.GEM_POLISHING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));
        simpleBlockWithItem(ModBlocks.FREEZER.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/freezer")));
        simpleBlockWithItem(ModBlocks.ATOMICAL_STABILIZATOR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/atomical_stabilizator")));
        simpleBlockWithItem(ModBlocks.MYSTERIOUS_ALTAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/mysterious_altar")));
        simpleBlockWithItem(ModBlocks.INCUBATOR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/incubator")));
    }

    // Ce paté de public void + private est à dupliquer pour les crop blocks. changer les 2 cast dans le second + les noms et l'use dans le 1er : actuellement StrawberryCropBlock.
    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    // cornCropBlock
    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(MhmBitsnbobs.MOD_ID + ":block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(), models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }
    public void hangingSignBlock(Block signBloc, Block wallSignBloc, ModelFile sign) {
        simpleBlock(signBloc, sign);
        simpleBlock(wallSignBloc, sign);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
