package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;
import net.minheur.mhm_bitsnbobs.item.ModCreativeModTabs;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;
import net.minheur.techno_lib.advancement.AdvancementBuilder;
import net.minheur.techno_lib.lang.LanguageGenProvider;

/**
 * Lang generator. Specify with the function what type of translation you want then set the parameters to define the key.
 * Once done, add the translations. You need at least the {@code en_us} one.
 */
public class ModLangGen extends LanguageGenProvider {
    protected ModLangGen(PackOutput output) {
        super(output, MhmBitsnbobs.MOD_ID);
    }

    @Override
    protected void addTranslation() {
        // credits
        addTooltipCredit("zombie_arm")
                .en_us("Made by Jema")
                .fr_fr("Créer par Jema");

        // tooltips catalyzers
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

        // tooltip
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

        // sounds
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

        // recipes
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

        // menu (modpack)
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

        // villager
        addVillagerType("soundmaster")
                .en_us("Sound master")
                .fr_fr("Ingénieur son");

        // effects
        addEffect(ModEffects.ICED)
                .en_us("Iced")
                .fr_fr("Glacé");
        addEffect(ModEffects.QUANTUM_CHOCKED)
                .en_us("Quantum chocked")
                .fr_fr("Choc quantique");

        // death
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

        // creative mod tab
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

        // blocks
        addBlock(ModBlocks.ATOMICAL_STABILIZATOR)
                .en_us("Atomical stabilizator")
                .fr_fr("Stabilisateur atomique");
        addBlock(ModBlocks.CATMINT)
                .en_us("Catmint")
                .fr_fr("Herbe a chat");
        addBlock(ModBlocks.COMPRESSED_DIRT)
                .en_us("Compressed dirt")
                .fr_fr("Terre compressée");
        addBlock(ModBlocks.CREATIVE_BLOCK)
                .en_us("Creative block")
                .fr_fr("Bloc créatif");
        addBlock(ModBlocks.CREATIVE_RESIDUE_BLOCK)
                .en_us("Block of creative residue")
                .fr_fr("Bloc de résidu créatif");
        addBlock(ModBlocks.DARK_HANGING_SIGN)
                .en_us("Dark hanging sign")
                .fr_fr("Panneau suspendu sombre");
        addBlock(ModBlocks.DARK_LEAVES)
                .en_us("Dark leaves")
                .fr_fr("Feuillage sombre");
        addBlock(ModBlocks.DARK_LOG)
                .en_us("Dark log")
                .fr_fr("Tronc sombre");
        addBlock(ModBlocks.DARK_PLANKS)
                .en_us("Dark planks")
                .fr_fr("Planches sombre");
        addBlock(ModBlocks.DARK_SAPLING)
                .en_us("Dark sapling")
                .fr_fr("Pousse d'arbre sombre");
        addBlock(ModBlocks.DARK_SIGN)
                .en_us("Dark sign")
                .fr_fr("Panneau sombre");
        addBlock(ModBlocks.DARK_WOOD)
                .en_us("Dark wood")
                .fr_fr("Bois sombre");
        addBlock(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
                .en_us("Deepslate sapphire ore")
                .fr_fr("Minerais de saphir de pierre des abîmes");
        addBlock(ModBlocks.DICE_BLOCK)
                .en_us("Dice")
                .fr_fr("Dée");
        addBlock(ModBlocks.END_SAPPHIRE_ORE)
                .en_us("End sapphire ore")
                .fr_fr("Minerais de saphir de l'end");
        addBlock(ModBlocks.EXTREMELY_DRY_DIRT_BLOCK)
                .en_us("Extremly dry dirt block")
                .fr_fr("Bloc de terre extrêmement sèche");
        addBlock(ModBlocks.FREEZER)
                .en_us("Freezer")
                .fr_fr("Congélateur");
        addBlock(ModBlocks.GEM_POLISHING_STATION)
                .en_us("Gem polishing station")
                .fr_fr("Polisseur de gems");
        addBlock(ModBlocks.INCUBATOR)
                .en_us("Incubator")
                .fr_fr("Incubateur");
        addBlock(ModBlocks.MYSTERIOUS_ALTAR)
                .en_us("Mysterious altar")
                .fr_fr("Autel mystérieux");
        addBlock(ModBlocks.NETHER_SAPPHIRE_ORE)
                .en_us("Nether sapphire ore")
                .fr_fr("Minerais de saphir du nether");
        addBlock(ModBlocks.RAW_SAPPHIRE_BLOCK)
                .en_us("Block of raw sapphire")
                .fr_fr("Bloc de saphir brute");
        addBlock(ModBlocks.RED_CLAY)
                .en_us("Red clay")
                .fr_fr("Argile rouge");
        addBlock(ModBlocks.RESOURCE_DIRT_BLOCK)
                .en_us("Resource dirt block")
                .fr_fr("Bloc de terre de ressource");
        addBlock(ModBlocks.RUBINIUM_BLOCK)
                .en_us("Rubinium block")
                .fr_fr("Bloc de rubinium");
        addBlock(ModBlocks.RUBIS_ORE)
                .en_us("Rubis ore")
                .fr_fr("Minerais de rubis");
        addBlock(ModBlocks.SAPPHIRE_BLOCK)
                .en_us("Block of sapphire")
                .fr_fr("Bloc de saphir");
        addBlock(ModBlocks.SAPPHIRE_BUTTON)
                .en_us("Sapphire button")
                .fr_fr("Bouton de saphir");
        addBlock(ModBlocks.SAPPHIRE_DOOR)
                .en_us("Sapphire door")
                .fr_fr("Porte en saphir");
        addBlock(ModBlocks.SAPPHIRE_FENCE)
                .en_us("Sapphire fence")
                .fr_fr("Barrière en saphir");
        addBlock(ModBlocks.SAPPHIRE_FENCE_GATE)
                .en_us("Sapphire fence gate")
                .fr_fr("Portillon en saphir");
        addBlock(ModBlocks.SAPPHIRE_ORE)
                .en_us("Sapphire ore")
                .fr_fr("Minerais de saphir");
        addBlock(ModBlocks.SAPPHIRE_PRESSURE_PLATE)
                .en_us("Sapphire pressure plate")
                .fr_fr("Plaque de pression en saphir");
        addBlock(ModBlocks.SAPPHIRE_SLAB)
                .en_us("Sapphire slab")
                .fr_fr("Dalle en saphir");
        addBlock(ModBlocks.SAPPHIRE_STAIRS)
                .en_us("Sapphire stairs")
                .fr_fr("Escaliers en saphir");
        addBlock(ModBlocks.SAPPHIRE_TRAPDOOR)
                .en_us("Sapphire trapdoor")
                .fr_fr("Trappe en saphir");
        addBlock(ModBlocks.SAPPHIRE_WALL)
                .en_us("Sapphire wall")
                .fr_fr("Muret en saphir");
        addBlock(ModBlocks.SOUND_BLOCK)
                .en_us("Sound block")
                .fr_fr("Bloc de musique");
        addBlock(ModBlocks.STRIPPED_DARK_LOG)
                .en_us("Stripped dark log")
                .fr_fr("Tronc sombre écorcé");
        addBlock(ModBlocks.STRIPPED_DARK_WOOD)
                .en_us("Stripped dark wood")
                .fr_fr("Bois sombre écorcé");
        addBlock(ModBlocks.SUPER_CHARGED_BLOCK)
                .en_us("Super charged block")
                .fr_fr("Bloc super chargé");
    }

    public TranslationBuilder addMenuName(String id) {
        return add("menu." + id);
    }
    public TranslationBuilder addAdvancementTitle(AdvancementBuilder adv) {
        return addAdvancementTitle(adv.getGroup(), adv.getId());
    }
}
