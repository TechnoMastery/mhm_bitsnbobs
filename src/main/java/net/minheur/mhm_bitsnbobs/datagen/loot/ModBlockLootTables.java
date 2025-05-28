package net.minheur.mhm_bitsnbobs.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.block.custom.CornCropBlock;
import net.minheur.mhm_bitsnbobs.block.custom.StrawberryCropBlock;
import net.minheur.mhm_bitsnbobs.item.ModItems;

import java.util.Random;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {                       // WARNING !!!!! Problem here
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    /// This method is used to generate loot tables. To exclude a block from loot tables,
    /// use `.noLootTables()` in the ModBlocks.java file, in the `Blocks.property()`.
    /// For exclude a block from loot table gen but having a manual one in the files,
    /// refer to the `getKnownBlocks()` down there.
    @Override
    protected void generate() {
        // all blocks that drop self
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.dropSelf(ModBlocks.CREATIVE_BLOCK.get());
        this.dropSelf(ModBlocks.RUBINIUM_BLOCK.get());

        this.dropSelf(ModBlocks.SAPPHIRE_STAIRS.get());
        this.dropSelf(ModBlocks.SAPPHIRE_BUTTON.get());
        this.dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_TRAPDOOR.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_WALL.get());
        this.dropSelf(ModBlocks.SUPER_CHARGED_BLOCK.get());
        this.dropSelf(ModBlocks.GEM_POLISHING_STATION.get());
        this.dropSelf(ModBlocks.INCUBATOR.get());
        this.dropSelf(ModBlocks.DARK_SAPLING.get());

        this.dropSelf(ModBlocks.MOD_DEEP_PORTAL.get());

        this.dropSelf(ModBlocks.DARK_LOG.get());
        this.dropSelf(ModBlocks.DARK_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_DARK_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_DARK_WOOD.get());
        this.dropSelf(ModBlocks.DARK_PLANKS.get());

        this.add(ModBlocks.DARK_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.DARK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // signs
        this.add(ModBlocks.DARK_SIGN.get(), block ->
                createSingleItemTable(ModItems.DARK_SIGN.get()));
        this.add(ModBlocks.DARK_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.DARK_SIGN.get()));
        this.add(ModBlocks.DARK_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.DARK_HANGING_SIGN.get()));
        this.add(ModBlocks.DARK_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.DARK_HANGING_SIGN.get()));

        // utilisé quand le block drop 1 type d'item / block, dans une quantité fixe
        this.dropOneType(ModBlocks.CREATIVE_RESIDUE_BLOCK.get(), ModItems.CREATIVE_RESIDUE.get(), 2);
        this.dropOneType(ModBlocks.COMPRESSED_DIRT.get(), ModItems.PIECE_OF_DIRT.get(), 3);
        this.dropOneType(ModBlocks.EXTREMELY_DRY_DIRT_BLOCK.get(), ModItems.EXTREMELY_DRY_DIRT.get(), 4);

        this.dropOneType(ModBlocks.FIRE_PLANT.get(), ModItems.FIRE_SEEDS.get(), 1);

        // slab
        this.add(ModBlocks.SAPPHIRE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get()));
        // same door but createDoorTable
        this.add(ModBlocks.SAPPHIRE_DOOR.get(),
                block -> createDoorTable(ModBlocks.SAPPHIRE_DOOR.get()));

        // crops        penser a modif les lignes xD
        // one block tall
        LootItemCondition.Builder lootitemcomdition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5));
        this.add(ModBlocks.STRAWBERRY_CROP.get(), createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                ModItems.STRAWBERRY_SEEDS.get(), lootitemcomdition$builder));

        // two blocks tall
        LootItemCondition.Builder lootItemCondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8));
        this.add(ModBlocks.CORN_CROP.get(), createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(),
                ModItems.CORN_SEEDS.get(), lootItemCondition$builder2));

        // flowers      packet de 2 pour la fleur + potted flower
        this.dropSelf(ModBlocks.CATMINT.get());
        this.add(ModBlocks.POTTED_CATMINT.get(), createPotFlowerItemTable(ModBlocks.CATMINT.get()));

        this.add(ModBlocks.RUBIS_ORE.get(),
                block -> createCopperLickOreDrops(ModBlocks.RUBIS_ORE.get(), ModItems.RUBIS.get()));

        // packet sapphire ore
        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createCopperLickOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createCopperLickOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createCopperLickOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.END_SAPPHIRE_ORE.get(),
                block -> createCopperLickOreDrops(ModBlocks.END_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
    }

    protected LootTable.Builder createCopperLickOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createOneDropTable(ItemLike pItem, int pCount) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pItem, LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(ConstantValue.exactly(pCount))))));
    }

    protected void dropOneType(Block rootBlock, ItemLike droppedItem, int dropCount) {
        this.add(rootBlock, this.createOneDropTable(droppedItem, dropCount));
    }

    /// Here, you can add a `.filter(block -> block != ModBlocks.YOUR_BLOCK.get())`
    /// to remove `YOUR_BLOCK` from creating a data-generated file. It will still look for a
    /// file it the manuals ones.
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> block != ModBlocks.RESOURCE_DIRT_BLOCK.get())
                ::iterator;
    }
}
