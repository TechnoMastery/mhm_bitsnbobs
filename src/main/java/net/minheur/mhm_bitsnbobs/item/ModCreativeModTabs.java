package net.minheur.mhm_bitsnbobs.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MhmBitsnbobs.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // down = mod item / block add

                        // item
                        output.accept(ModItems.RAW_SAPPHIRE.get());
                        output.accept(ModItems.SAPPHIRE.get());
                        // plus item

                        // block
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
                        // plus block

                        // end

                        // vanilla item / block add

                        // item
                        output.accept(Items.DIAMOND);
                        // plus item

                        // block
                        output.accept(Blocks.DIAMOND_BLOCK);
                        // plus block

                        // end
                    })
                    .build());

            public static void register(IEventBus eventBus) {
                CREATIVE_MODE_TABS.register(eventBus);
            }
}
