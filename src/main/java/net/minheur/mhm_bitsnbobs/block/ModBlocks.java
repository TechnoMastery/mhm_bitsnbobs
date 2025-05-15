package net.minheur.mhm_bitsnbobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.custom.*;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.sound.ModSounds;
import net.minheur.mhm_bitsnbobs.util.ModWoodTypes;
import net.minheur.mhm_bitsnbobs.worldgen.tree.DarkTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MhmBitsnbobs.MOD_ID);

    // block create

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> CREATIVE_RESIDUE_BLOCK = registerBlock("creative_residue_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(15, 20)));
    public static final RegistryObject<Block> CREATIVE_BLOCK = registerBlock("creative_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(500, 500)));

    public static final RegistryObject<Block> RUBINIUM_BLOCK = registerBlock("rubinium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(20, 30)));
    public static final RegistryObject<Block> SUPER_CHARGED_BLOCK = registerBlock("super_charged_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1, 10)));

    public static final RegistryObject<Block> COMPRESSED_DIRT = registerBlock("compressed_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(1, 10)));
    public static final RegistryObject<Block> EXTREMELY_DRY_DIRT_BLOCK = registerBlock("extremely_dry_dirt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(1, 10)));
    public static final RegistryObject<Block> RESOURCE_DIRT_BLOCK = registerBlock("resource_dirt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(1, 10)));

    // custom block

    public static final RegistryObject<Block> DICE_BLOCK = BLOCKS.register("dice_block",
            () -> new DiceBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noLootTable()));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(ModSounds.SOUND_BLOCK_SOUNDS)));

    public static final RegistryObject<Block> GEM_POLISHING_STATION = registerBlock("gem_polishing_station",
            () -> new GemPolishingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    // end custom

    // teleporters
    public static final RegistryObject<Block> MOD_PORTAL = registerBlock("mod_portal",
            () -> new ModPortalBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().noCollission().noLootTable()));
    // TODO: add a way to enter the dim

    // saplings
    public static final RegistryObject<Block> DARK_SAPLING = registerBlock("dark_sapling",
            () -> new SaplingBlock(new DarkTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    // modèle minerais si dessous ==> UniformInt.of() = qnt min / max d'XP.
    // eft c un modèle de block a XP
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    // en dessous paté pour les variantes nether, deepslate et end du saphir
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> END_SAPPHIRE_ORE = registerBlock("end_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    // signs
    public static final RegistryObject<Block> DARK_SIGN = BLOCKS.register("dark_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.DARK));
    public static final RegistryObject<Block> DARK_WALL_SIGN = BLOCKS.register("dark_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.DARK));

    public static final RegistryObject<Block> DARK_HANGING_SIGN = BLOCKS.register("dark_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.DARK));
    public static final RegistryObject<Block> DARK_WALL_HANGING_SIGN = BLOCKS.register("dark_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.DARK));

    // non-block blocks
    public static final RegistryObject<Block> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs",
            () -> new StairBlock(() -> ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> SAPPHIRE_SLAB = registerBlock("sapphire_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_BUTTON = registerBlock("sapphire_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.AMETHYST),
                    BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST),
                    BlockSetType.IRON));

    public static final RegistryObject<Block> SAPPHIRE_FENCE = registerBlock("sapphire_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> SAPPHIRE_WALL = registerBlock("sapphire_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_DOOR = registerBlock("sapphire_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<Block> SAPPHIRE_TRAPDOOR = registerBlock("sapphire_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.IRON));

    // crops
    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    public static final RegistryObject<Block> FIRE_PLANT = BLOCKS.register("fire_crop",
            () -> new FirePlantCropBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE).noCollission().noOcclusion().instabreak().mapColor(MapColor.COLOR_RED)));

    // flowers      ils sont par 2
    public static final RegistryObject<Block> CATMINT = registerBlock("catmint",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_CATMINT = BLOCKS.register("potted_catmint",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.CATMINT,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    // custom wood
    public static final RegistryObject<Block> DARK_LOG = registerBlock("dark_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> DARK_WOOD = registerBlock("dark_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_DARK_LOG = registerBlock("stripped_dark_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_DARK_WOOD = registerBlock("stripped_dark_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> DARK_PLANKS = registerBlock("dark_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                // here is an anonymous class. When multiples planks, can create a PlanksBlock custom block class.
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> DARK_LEAVES = registerBlock("dark_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                // here is an anonymous class. When multiples leaves, can create a LeavesBlock custom block class.
                // If possible ; already is a LeavesBlock. Might have to create a class extends LeavesBlock. To expermient.
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    // end block create

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
