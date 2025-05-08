package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MhmBitsnbobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // dupli = auto get model item gen
        simpleitem(ModItems.SAPPHIRE);
        simpleitem(ModItems.RAW_SAPPHIRE);
        simpleitem(ModItems.EXTREMELY_DRY_DIRT);
        simpleitem(ModItems.PIECE_OF_EXTREMELY_DRY_DIRT);
        simpleitem(ModItems.RESSOURCE_DIRT);
        simpleitem(ModItems.CORN);
        simpleitem(ModItems.STORM_FRAGMENT);
        simpleitem(ModItems.WET_DIRT);
        simpleitem(ModItems.QUANTUM_CORE);
        simpleitem(ModItems.PIECE_OF_DIRT);
        simpleitem(ModItems.HARDENED_INGOT);
        simpleitem(ModItems.BASE_EGG);
        simpleitem(ModItems.SPAWNER_PART);
        simpleitem(ModItems.CREATIVE_ESSENCE);
        simpleitem(ModItems.CREATIVE_NUGGET);
        simpleitem(ModItems.CREATIVE_INGOT);
        simpleitem(ModItems.SMALL_CREATIVE_NUGGET);
        simpleitem(ModItems.CREATIVE_RESIDUE);
        simpleitem(ModItems.LITTLE_COPPER_NUGGET);
        simpleitem(ModItems.LIGHTNING_UPGRADE);
        simpleitem(ModItems.TREE_GROWER);
        simpleitem(ModItems.FIRE_DIAMOND);
        simpleitem(ModItems.ROTTEN_LEATHER);
        simpleitem(ModItems.FIRE_STICK);
        simpleitem(ModItems.FIRE_SEEDS);
        simpleitem(ModItems.RUBIS);
        simpleitem(ModItems.RUBINIUM);
        simpleitem(ModItems.SUPER_CHARGED_INGOT);
        simpleitem(ModItems.SUPER_CHARGED_BALL);
        simpleitem(ModItems.COPPER_BALL);
        simpleitem(ModItems.IRON_BALL);
        simpleitem(ModItems.GOLD_BALL);
        simpleitem(ModItems.DIAMOND_BALL);
        simpleitem(ModItems.TRANSFER_FLASK);
        simpleitem(ModItems.EMPTY_BIG_FLASK);
        simpleitem(ModItems.EMPTY_LITTLE_FLASK);
        simpleitem(ModItems.DIRTY_HUMID_POTION);
        simpleitem(ModItems.HUMID_POTION);
        simpleitem(ModItems.LITTLE_HUMID_POTION);
        simpleitem(ModItems.EXPLODED_POTATO);
        simpleitem(ModItems.CONTROL_PANEL);
        simpleitem(ModItems.HALF_STICK);
        simpleitem(ModItems.QUATER_STICK);
        simpleitem(ModItems.YEAST);

        simpleitem(ModItems.BURGER);
        simpleitem(ModItems.STRAWBERRY);
        simpleitem(ModItems.HOLY_BREAD);
        simpleitem(ModItems.DEVIL_BREAD);

        simpleitem(ModItems.STRAWBERRY_SEEDS);
        simpleitem(ModItems.CORN_SEEDS);

        simpleitem(ModItems.BAR_BRAWL_MUSIC_DISC);
        simpleitem(ModItems.DARK_SOUL_MUSIC_DISC);
        simpleitem(ModItems.END_OF_THE_START_MUSIC_DISC);

        simpleitem(ModItems.METAL_DETECTOR);

        // runes
        runeItem(ModItems.XP_RUNE);
        runeItem(ModItems.MONEY_RUNE);
        runeItem(ModItems.EMPTY_RUNE);
        runeItem(ModItems.OAK_RUNE);
        runeItem(ModItems.SPRUCE_RUNE);

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

        // tools
        handheldItem(ModItems.SAPPHIRE_SWORD);
        handheldItem(ModItems.SAPPHIRE_PICKAXE);
        handheldItem(ModItems.SAPPHIRE_AXE);
        handheldItem(ModItems.SAPPHIRE_SHOVEL);
        handheldItem(ModItems.SAPPHIRE_HOE);
        handheldItem(ModItems.FIRE_SWORD);
        handheldItem(ModItems.LIGHTNING_SWORD);
        handheldItem(ModItems.RUBINIUM_SWORD);
        handheldItem(ModItems.ALLOYED_SWORD);
        handheldItem(ModItems.CONTROLLED_PICKAXE);
        handheldItem(ModItems.CONTROLLED_STICK);
        handheldItem(ModItems.SKULLKERY_TOOL);

        // armor
        trimmedArmorItem(ModItems.SAPPHIRE_HELMET);
        trimmedArmorItem(ModItems.SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(ModItems.SAPPHIRE_LEGGINGS);
        trimmedArmorItem(ModItems.SAPPHIRE_BOOTS);

        // flowers
        simpleBlockItemBlockTexture(ModBlocks.CATMINT);

        // spawn egg
        withExistingParent(ModItems.RHINO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = MhmBitsnbobs.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder simpleitem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder runeItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/rune"));
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

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID,"block/" + item.getId().getPath()));
    }
}
