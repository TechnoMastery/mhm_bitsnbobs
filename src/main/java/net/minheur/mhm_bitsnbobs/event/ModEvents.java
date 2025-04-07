package net.minheur.mhm_bitsnbobs.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.item.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = MhmBitsnbobs.MOD_ID)
public class ModEvents {

    // villagers
    @SubscribeEvent
    public static void AddCustomTrades(VillagerTradesEvent event) {

        // 1 if par metier + ajouter des trades sur le bon modèle selon les levels ==> lvl max : 5
        if(event.getType() == VillagerProfession.FARMER) {
            // penser a ajouter la ligne suivante a chaques proffession (chaque if)
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.STRAWBERRY.get(), 12),
                    10, 8, 0.02f));

            // level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(ModItems.CORN.get(), 6),
                    5, 9, 0.035f));

            // level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_INGOT, 8),
                    new ItemStack(ModItems.CORN_SEEDS.get(), 2),
                    15, 10, 0.07f));

        }

        if(event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // listing de création de livres anchantées
            ItemStack enchantedBook1 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.THORNS, 2));
            ItemStack enchantedBook2 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.BINDING_CURSE, 1));

            // il est conseillé par Minheur de rester sur le meme schema afin de comprendre et de pouvoir les lire correctement
            // level 1
            // ceci est le schéma pour un livre enchanté : retourner la variable itemstack créer ci dessus
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    enchantedBook1,
                    2, 8, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    enchantedBook2,
                    2, 5, 0.02f));
        }

    }

    // wandering trader
    @SubscribeEvent
    public static void AddCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        // pas de lvl au wandering trader, only rare & generic

        // generic
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModItems.SAPPHIRE_BOOTS.get(), 1),
                3, 2, 0.2f));


        // rare
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 24),
                new ItemStack(ModItems.METAL_DETECTOR.get(), 1),
                1, 2, 0.15f));
    }

}



