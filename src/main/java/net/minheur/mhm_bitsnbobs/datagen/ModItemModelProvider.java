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
        // duplicate = auto get model item gen
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.WIND_CHARGED_INGOT);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.EXTREMELY_DRY_DIRT);
        simpleItem(ModItems.PIECE_OF_EXTREMELY_DRY_DIRT);
        simpleItem(ModItems.RESOURCE_DIRT);
        simpleItem(ModItems.CORN);
        simpleItem(ModItems.STORM_FRAGMENT);
        simpleItem(ModItems.DICE);
        simpleItem(ModItems.WET_DIRT);
        simpleItem(ModItems.PIECE_OF_DIRT);
        simpleItem(ModItems.HARDENED_INGOT);
        simpleItem(ModItems.SPAWNER_PART);
        simpleItem(ModItems.CREATIVE_ESSENCE);
        simpleItem(ModItems.CREATIVE_NUGGET);
        simpleItem(ModItems.UNPROCESSED_CREATIVE_NUGGET);
        simpleItem(ModItems.CREATIVE_INGOT);
        simpleItem(ModItems.SMALL_CREATIVE_NUGGET);
        simpleItem(ModItems.CREATIVE_RESIDUE);
        simpleItem(ModItems.LIGHTNING_UPGRADE);
        simpleItem(ModItems.BIOMASS);
        simpleItem(ModItems.SAPPHIRE_NUGGET);
        simpleItem(ModItems.TREE_GROWER);
        simpleItem(ModItems.FIRE_DIAMOND);
        simpleItem(ModItems.ROTTEN_LEATHER);
        simpleItem(ModItems.FIRE_STICK);
        simpleItem(ModItems.CHOCOLATE_SCOOP);
        simpleItem(ModItems.STRAWBERRY_SCOOP);
        simpleItem(ModItems.VANILLA_SCOOP);
        simpleItem(ModItems.SWEET_BERRIES_SCOOP);
        simpleItem(ModItems.CHOCOLATE_SNOWBALL);
        simpleItem(ModItems.SWEET_BERRIES_SNOWBALL);
        simpleItem(ModItems.STRAWBERRIES_SNOWBALL);
        simpleItem(ModItems.VANILLA_SNOWBALL);
        simpleItem(ModItems.MILK_BUCKET_WITH_EGG);
        simpleItem(ModItems.BUCKET_OF_LIQUID_CHOCOLATE_ICE_CREAM);
        simpleItem(ModItems.BUCKET_OF_LIQUID_VANILLA_ICE_CREAM);
        simpleItem(ModItems.BUCKET_OF_LIQUID_STRAWBERRIES_ICE_CREAM);
        simpleItem(ModItems.BUCKET_OF_LIQUID_SWEET_BERRIES_ICE_CREAM);
        simpleItem(ModItems.SCOOP_OF_STRAWBERRIES_SORBET);
        simpleItem(ModItems.SCOOP_OF_CHOCOLATE_SORBET);
        simpleItem(ModItems.SCOOP_OF_VANILLA_SORBET);
        simpleItem(ModItems.SCOOP_OF_SWEET_BERRIES_SORBET);
        simpleItem(ModItems.VANILLA_SORBET);
        simpleItem(ModItems.SWEET_BERRIES_SORBET);
        simpleItem(ModItems.CHOCOLATE_SORBET);
        simpleItem(ModItems.STRAWBERRIES_SORBET);
        simpleItem(ModItems.FIRE_SEEDS);
        simpleItem(ModItems.RUBIS);
        simpleItem(ModItems.RUBINIUM);
        simpleItem(ModItems.SUPER_CHARGED_INGOT);
        simpleItem(ModItems.SUPER_CHARGED_BALL);
        simpleItem(ModItems.COPPER_BALL);
        simpleItem(ModItems.IRON_BALL);
        simpleItem(ModItems.GOLD_BALL);
        simpleItem(ModItems.DIAMOND_BALL);
        simpleItem(ModItems.SAPPHIRE_BALL);
        simpleItem(ModItems.PRINTED_QUANTUM_CIRCUIT);
        simpleItem(ModItems.INSCRIBER_QUANTUM_PRESS);
        simpleItem(ModItems.QUANTUM_PROCESSOR);
        simpleItem(ModItems.QUANTUMITE_INGOT);
        simpleItem(ModItems.QUANTUMITE_SHEET);
        simpleItem(ModItems.QUANTUMITE_CHUNK);
        simpleItem(ModItems.RED_CLAY_BALL);
        simpleItem(ModItems.EMPTY_BIG_FLASK);
        simpleItem(ModItems.EMPTY_LITTLE_FLASK);
        simpleItem(ModItems.DIRTY_HUMID_POTION);
        simpleItem(ModItems.HUMID_POTION);
        simpleItem(ModItems.LITTLE_HUMID_POTION);
        simpleItem(ModItems.EXPLODED_POTATO);
        simpleItem(ModItems.CONTROL_PANEL);
        simpleItem(ModItems.HALF_STICK);
        simpleItem(ModItems.QUARTZ_SHARD);
        simpleItem(ModItems.CONE);
        simpleItem(ModItems.CHOCOLATE_ICE_CREAM);
        simpleItem(ModItems.VANILLA_ICE_CREAM);
        simpleItem(ModItems.QUANTUM_DUST);
        simpleItem(ModItems.VANILLA_EXTRACT);
        simpleItem(ModItems.VANILLA_POD);
        simpleItem(ModItems.DRIED_VANILLA_POD);
        simpleItem(ModItems.STRAWBERRY_ICE_CREAM);
        simpleItem(ModItems.SWEET_BERRIES_ICE_CREAM);
        simpleItem(ModItems.QUARTER_STICK);
        simpleItem(ModItems.YEAST);
        simpleItem(ModItems.DARK_SIGN);
        simpleItem(ModItems.DARK_HANGING_SIGN);
        simpleItem(ModItems.DARK_BOAT);
        simpleItem(ModItems.DARK_CHEST_BOAT);
        simpleItem(ModItems.SLIMY_INGOT);
        simpleItem(ModItems.SLIMY_STICK);
        simpleItem(ModItems.BUCKET_OF_LIQUID_ICE_CREAM);
        simpleItem(ModItems.BASE_EGG);
        simpleItem(ModItems.UNFINISHED_EMERALD);
        simpleItem(ModItems.UNFINISHED_BLAZE_POWDER);
        simpleItem(ModItems.UNFINISHED_STORM_FRAGMENT);
        simpleItem(ModItems.UNPROCESSED_SEA_PICKLE);
        simpleItem(ModItems.MAGIC_SHARD);
        simpleItem(ModItems.ROTTEN_PORKCHOP);
        simpleItem(ModItems.ROTTEN_BEEF);
        simpleItem(ModItems.ROTTEN_MUTTON);
        simpleItem(ModItems.ROTTEN_CHICKEN);
        simpleItem(ModItems.ROTTEN_COD);
        simpleItem(ModItems.ROTTEN_SALMON);
        simpleItem(ModItems.ROTTEN_RABBIT);

        // base
        discItem(ModItems.BASE_OF_DISC);
        // discs → simpleItem si personalisé / discItem sinon
        discItem(ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC);
        discItem(ModItems.BLOCK_BY_BLOCK_MUSIC_DISC);
        discItem(ModItems.CUBIC_GROOVE_MUSIC_DISC);
        discItem(ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC);
        discItem(ModItems.IN_THE_BLOCK_MUSIC_DISC);
        discItem(ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC);
        discItem(ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC);
        discItem(ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC);
        discItem(ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC);
        discItem(ModItems.NETHER_NIGHTS_MUSIC_DISC);
        discItem(ModItems.REDSTONE_PULSE_MUSIC_DISC);
        discItem(ModItems.SERENE_ECHO_MUSIC_DISC);
        simpleItem(ModItems.BAR_BRAWL_MUSIC_DISC);
        simpleItem(ModItems.DARK_SOUL_MUSIC_DISC);
        simpleItem(ModItems.END_OF_THE_START_MUSIC_DISC);

        quantumCoreItem(ModItems.QUANTUM_CORE);
        quantumCoreItem(ModItems.UNPROCESSED_QUANTUM_CORE);
        simpleItem(ModItems.STABILIZED_QUANTUM_CORE);

        simpleItem(ModItems.IRON_CATALYZER);
        simpleItem(ModItems.WIND_CHARGED_CATALYZER);
        simpleItem(ModItems.GOLD_CATALYZER);
        simpleItem(ModItems.DIAMOND_CATALYZER);
        simpleItem(ModItems.NETHERITE_CATALYZER);
        simpleItem(ModItems.SUPER_CHARGED_CATALYZER);

        simpleItem(ModItems.BURGER);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.HOLY_BREAD);
        simpleItem(ModItems.DEVIL_BREAD);

        simpleItem(ModItems.STRAWBERRY_SEEDS);
        simpleItem(ModItems.CORN_SEEDS);

        simpleItem(ModItems.METAL_DETECTOR);

        // saplings
        saplingItem(ModBlocks.DARK_SAPLING);

        // runes
        runeItem(ModItems.XP_RUNE);
        runeItem(ModItems.MONEY_RUNE);
        runeItem(ModItems.EMPTY_RUNE);
        runeItem(ModItems.OAK_RUNE);
        runeItem(ModItems.SPRUCE_RUNE);

        simpleItem(ModItems.PINE_CONE);

        // pour une porte call simpleBlockItem sans
        simpleBlockItem(ModBlocks.SAPPHIRE_DOOR);

        // fence : call fence, add baseBlock texture at second parameter ==> same pour button & wall but change method
        fenceItem(ModBlocks.SAPPHIRE_FENCE, ModBlocks.SAPPHIRE_BLOCK);
        buttonItem(ModBlocks.SAPPHIRE_BUTTON, ModBlocks.SAPPHIRE_BLOCK);
        wallItem(ModBlocks.SAPPHIRE_WALL, ModBlocks.SAPPHIRE_BLOCK);

        // stairs, slab, pressure_plate and gate
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
        handheldItem(ModItems.CONTROLLED_STICK);
        handheldItem(ModItems.SKULLKERY_TOOL);
        handheldItem(ModItems.SLIME_SWORD);

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

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder runeItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/rune"));
    }

    private ItemModelBuilder discItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID, "item/base_of_disc"));
    }

    public void eventSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(MhmBitsnbobs.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public ItemModelBuilder quantumCoreItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MhmBitsnbobs.MOD_ID,"item/quantum_core"));

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
