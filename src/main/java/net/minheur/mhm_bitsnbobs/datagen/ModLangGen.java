package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;
import net.minheur.mhm_bitsnbobs.item.ModCreativeModTabs;
import net.minheur.mhm_bitsnbobs.lang.ModLanguageProvider;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;

public class ModLangGen extends ModLanguageProvider {
    protected ModLangGen(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void addTranslation() {
        addTooltipCredit("zombie_arm")
                .en_us("Made by Jema")
                .fr_fr("Créer par Jema");

        addTooltip("catalyzer-wind_charged")
                .en_us("This catalyzer can handle incubations that need the power of the nature.")
                .fr_fr("Ce catalyseur peut gérer des incubations qui demandent la puissance de la nature.");
        addTooltip("catalyzer-super_charged")
                .en_us("This catalyzer can handle the most power-needed incubations.")
                .fr_fr("Ce catalyseur peut gérer les incubations qui demandent le plus de puissance.");
        addTooltip("catalyzer-netherite")
                .en_us("This catalyzer can handle ultimate incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations ultimes.");
        addTooltip("catalyzer-gold")
                .en_us("This catalyzer can handle basic incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations basiques.");
        addTooltip("catalyzer-iron")
                .en_us("This catalyzer can handle simple incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations simple.");
        addTooltip("catalyzer-diamond")
                .en_us("This catalyzer can handle advanced incubations.")
                .fr_fr("Ce catalyseur peut gérer des incubations avancées.");

        addTooltip("rotten_item")
                .en_us("This item is disabled : insall \"createdieselgenerators\" to enable it.")
                .fr_fr("Cet objet est désactivé : installez \"createdieselgenerators\" pour l'activer.");
        addTooltip("runes")
                .en_us("Right click to use")
                .fr_fr("Clique droit pour utiliser");
        addTooltip("balls")
                .en_us("Get from crushing resource dirt")
                .fr_fr("Récupérez en concassant de la terre de resources");
        addTooltip("stabilized_quantum_core")
                .en_us("An item now stable. Walk slow, or you'll awake it !")
                .fr_fr("Un objet désormait stable. Avancez lentement afin de ne pas le réveiller.");
        addTooltip("sound_block")
                .en_us("Make noise")
                .fr_fr("Fait du bruit");
        addTooltip("skullkery_tool")
                .en_us("A powerful tool used in skullkery")
                .fr_fr("Un outil puissant utilisé dans l'art des têtes");
        addTooltip("metal_detector")
                .en_us("Finds minerals underground")
                .fr_fr("Trouve des minerais sous terre");
        addTooltip("magicfuel")
                .en_us("This item have a magic power of")
                .fr_fr("Cet objet a un pouvoir magique de");
        addTooltip("empty_rune")
                .en_us("An empty and useless item. Use in crafts to fill it.")
                .fr_fr("Un objet vide et inutile. Utiliser les crafts pour le remplir");
        addTooltip("alloyed_sword")
                .en_us("Useless like this, but needed to craft the best sword ever!")
                .fr_fr("Inutile comme sa, mais nécessaire pour fabriquer la mailleur épée !");

        addSound("the_world_of_cubes")
                .en_us("Playing the world of cubes")
                .fr_fr("En train de jouer : The world of cubes");
        addSound("serene_echo")
                .en_us("Playing Serene echo")
                .fr_fr("En train de jouer : Serene echo");
        addSound("redstone_pulse")
                .en_us("Playing Redstone pulse")
                .fr_fr("En train de jouer : Redstone pulse");
        addSound("nether_nights")
                .en_us("Playing Nether nights")
                .fr_fr("En train de jouer : Nether nights");
        addSound("my_minecraft_world")
                .en_us("Playing My minecraft world")
                .fr_fr( "En train de jouer : My minecraft world");
        addSound("metal_detector_found_ore")
                .en_us("Metal detector ding")
                .fr_fr("Ding de détecteur de métal");
        addSound("legends_awaken_v1")
                .en_us("Playing Legends awaken V1")
                .fr_fr("En train de jouer : Legends awaken V1");
        addSound("legends_awaken_v2")
                .en_us("Playing Legends awaken V2")
                .fr_fr("En train de jouer : Legends awaken V2");
        addSound("in_the_world_of_minecraft")
                .en_us("Playing In the world of minecraft")
                .fr_fr("En train de jouer : In the world of minecraft");
        addSound("in_the_block")
                .en_us("Playing In the block")
                .fr_fr("En train de jouer : In the block");
        addSound("end_of_the_start")
                .en_us("Playing End of the start")
                .fr_fr("En train de jouer : End of the start");
        addSound("dark_soul")
                .en_us("Playing Dark soul")
                .fr_fr("En train de jouer : Dark soul");
        addSound("cubic_groove")
                .en_us("Playing Cubic groove")
                .fr_fr("En train de jouer : Cubic groove");
        addSound("block_by_block")
                .en_us("Playing Block by block")
                .fr_fr("En train de jouer : Block by block");
        addSound("bar_brawl")
                .en_us("Playing Bar brawl")
                .fr_fr("En train de jouer : Bar brawl");
        addSound("ballad_of_the_blocks")
                .en_us("Playing Ballad of the blocks")
                .fr_fr("En train de jouer : Ballad of the blocks");

        addRecipeName("mysterious_magic")
                .en_us("Mysterious magic")
                .fr_fr("Magie mystérieuse");
        addRecipeProperty("mysterious_magic", "power")
                .en_us("Power needed")
                .fr_fr("Energies demandé");
        addRecipeName("incubator")
                .en_us("Incubation")
                .fr_fr("Incubation");
        addRecipeName("gem_polishing_station")
                .en_us("Gem polishing")
                .fr_fr("Magie mystérious");
        addRecipeName("freezing")
                .en_us("Freezing")
                .fr_fr("Congélation");
        addRecipeName("atomical_stabilizator")
                .en_us("Atomical stabilization")
                .fr_fr("Stabilisation atomique");

        addMenuName("discord")
                .en_us("Our discord")
                .fr_fr("Notre discord");
        addMenuName("github")
                .en_us("Our github")
                .fr_fr("Notre discord");
        addMenuName("bugs_feedbacks")
                .en_us("Report bugs or give feedback")
                .fr_fr("Signaler un bug ou donner un retour");

        addGuiName("mysterious_magic")
                .en_us("Mysterious magic")
                .fr_fr("Magie mystérieuse");

        addVillagerType("soundmaster")
                .en_us("Sound master")
                .fr_fr("Ingénieur son");

        addEffect(ModEffects.ICED)
                .en_us("Iced")
                .fr_fr("Glacé");
        addEffect(ModEffects.QUANTUM_CHOCKED)
                .en_us("Quantum chocked")
                .fr_fr("Choc quantique");

        addAttackDeath(ModDamageTypes.BRAIN_FREEZE_ID.getPath())
                .en_us("%1$s got brain-freezed")
                .fr_fr("%1$s a eu un mal de tête givré");
        addAttackDeathItem(ModDamageTypes.BRAIN_FREEZE_ID.getPath())
                .en_us("%1$s got brain-freezed by %2$s using %3$s")
                .fr_fr("%1$s a eu un mal de tête givré a cause de %2$s utilisant %3$s");
        addAttackDeathPlayer(ModDamageTypes.BRAIN_FREEZE_ID.getPath())
                .en_us("%1$s got brain-freezed whilst trying to escape %2$s")
                .fr_fr("%1$s a eu un mal de tête givré en essayant de fuir %2$s");
        addAttackDeath(ModDamageTypes.ELECTROCUTED_ID.getPath())
                .en_us("%1$s got electrocuted")
                .fr_fr("%1$s a été éléctrocuté");
        addAttackDeathItem(ModDamageTypes.ELECTROCUTED_ID.getPath())
                .en_us("%1$s got electrocuted by %2$s using %3$s")
                .fr_fr("%1$s a été éléctrocuté par %2$s utilisant %3$s");
        addAttackDeathPlayer(ModDamageTypes.ELECTROCUTED_ID.getPath())
                .en_us("%1$s got electrocuted whilst trying to escape %2$s")
                .fr_fr("%1$s a été éléctrocuté en essayant de fuir %2$s");

        addCreativeTab(ModCreativeModTabs.BALLS.get())
                .en_us("Balls")
                .fr_fr("Balles");
        addCreativeTab(ModCreativeModTabs.DARK_STORM.get())
                .en_us("Storm items")
                .fr_fr("Objets de la tempête");
        addCreativeTab(ModCreativeModTabs.LIGHTNING.get())
                .en_us("Lightning items")
                .fr_fr("Les objets foudre");
        addCreativeTab(ModCreativeModTabs.RUNES.get())
                .en_us("Runes")
                .fr_fr("Les runes");
        addCreativeTab(ModCreativeModTabs.DIRT_THINGS.get())
                .en_us("Dirt things")
                .fr_fr("De la terre");
        addCreativeTab(ModCreativeModTabs.TUTORIAL_TAB.get())
                .en_us("Sapphire items")
                .fr_fr("Objets sapphire");
        addCreativeTab(ModCreativeModTabs.MUSIC_DISCS.get())
                .en_us("Music discs")
                .fr_fr("Disques de musiques");


    }

    public TranslationBuilder addMenuName(String id) {
        return add("menu." + id);
    }
}
