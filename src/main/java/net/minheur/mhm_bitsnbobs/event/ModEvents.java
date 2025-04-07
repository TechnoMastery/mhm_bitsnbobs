package net.minheur.mhm_bitsnbobs.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.villager.ModVillagers;

import java.util.List;

@Mod.EventBusSubscriber(modid = MhmBitsnbobs.MOD_ID)
public class ModEvents {

    /// ce fichier cert actuellement uniquement a créer des trades.
    /// Pour cela, choisissez votre villageois : pour le wandering trader, suivez la procédure d'ajout dans le AddCustomWanderingTrades.
    /// Pour des villageois normaux, dans AddCustomTrades créer un if : un seul par métier. event.getType() dois être egal a VillagerProfession.LA PROFESSION QUE VOUS SOUHAITEZ.
    /// ensuite, dans les if, ajoutez le Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades(); a chaque fois.
    /// procédure ajout : copiez le paté. changez les infos (tous est dans le nom des infos). pour les enchants, créer l'item dans un variable.
    /// Pour les enchants, regardez il y a des exemple dans LIBRARIAN. Les noms de variables peuvent être utilisé une fois dans un même if,
    /// mais un nom peut être le même entre 2 if différent. Il ne pourront pas accéder a la variable d'un autre if

    // villagers
    @SubscribeEvent
    public static void AddCustomTrades(VillagerTradesEvent event) {

        if(event.getType() == VillagerProfession.FARMER) {
            // penser a ajouter la ligne suivante a chaques profession (chaque if)
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

        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // lvl 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.SAPPHIRE_PICKAXE.get(), 1),
                    2, 9, 0.015f));

            // lvl 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.RAW_SAPPHIRE.get(), 3),
                    15, 4, 0.02f));
        }

        if(event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // listing de création de livres anchantées
            ItemStack enchantedBook1 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.THORNS, 2));
            ItemStack enchantedBook2 = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.BINDING_CURSE, 1));

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

        // exemple pour un pnj personalisé
        if(event.getType() == ModVillagers.SOUND_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // lvl 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModBlocks.SOUND_BLOCK.get(), 1),
                    2, 5, 0.02f));

            // lvl 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(Blocks.NOTE_BLOCK, 1),
                    15, 8, 0.01f));

            // lvl 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Blocks.JUKEBOX, 1),
                    1, 3, 0.1f));
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



