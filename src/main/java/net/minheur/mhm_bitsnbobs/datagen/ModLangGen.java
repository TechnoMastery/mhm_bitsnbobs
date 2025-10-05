package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.data.PackOutput;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import net.minheur.mhm_bitsnbobs.advancement.ModAdvancements;
import net.minheur.mhm_bitsnbobs.block.ModBlocks;
import net.minheur.mhm_bitsnbobs.effects.ModEffects;
import net.minheur.mhm_bitsnbobs.item.ModCreativeModTabs;
import net.minheur.mhm_bitsnbobs.item.ModItems;
import net.minheur.mhm_bitsnbobs.util.ModDamageTypes;
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
        addTooltip("nether_stick"); // TODO: Xaqorix: add the tooltip


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
        addBlock(ModBlocks.ELECTRONIC_CRYSTALLIZER)
                .en_us("Electronic crystallizer")
                .fr_fr("Cristalliseur électrique");


        // advancements
        addAdvancementTitle(ModAdvancements.adventure_STORM_SWORD)
                .en_us("Height voltage utilities")
                .fr_fr("Opération haut voltages");
        addAdvancementDesc(ModAdvancements.adventure_STORM_SWORD)
                .en_us("Get the sword of the storm")
                .fr_fr("L'épée de la tempète");
        addAdvancementTitle(ModAdvancements.adventure_LIGTHNING_UPGRADE)
                .en_us("The upgrade of the netherite")
                .fr_fr("L'amélioration pour la netherite");
        addAdvancementDesc(ModAdvancements.adventure_LIGTHNING_UPGRADE)
                .en_us("Pretty powerful")
                .fr_fr("Plutôt fort non ?");
        addAdvancementTitle(ModAdvancements.adventure_ITS_CHARGED)
                .en_us("The super-ingot")
                .fr_fr("Le super-lingot");
        addAdvancementDesc(ModAdvancements.adventure_ITS_CHARGED)
                .en_us("When you're too rich for netherite")
                .fr_fr("Quand tu es trop riche pour la netherite");

        addAdvancementTitle(ModAdvancements.ae2_QUANTUM_CORE)
                .en_us("A killing core")
                .fr_fr("Un noyeau tueur");
        addAdvancementDesc(ModAdvancements.ae2_QUANTUM_CORE)
                .en_us("You definitely shouldn't play with that.")
                .fr_fr("Tu dois faire attention avec cette puissance");
        addAdvancementTitle(ModAdvancements.ae2_QUANTUM_STAFF)
                .en_us("A staff")
                .fr_fr("Un septre");
        addAdvancementDesc(ModAdvancements.ae2_QUANTUM_STAFF)
                .en_us("A BIG weapon...")
                .fr_fr("Une arme trop puissante.");
        addAdvancementTitle(ModAdvancements.ae2_SINGULARITY)
                .en_us("A singularity.")
                .fr_fr("Une singularitée");
        addAdvancementDesc(ModAdvancements.ae2_SINGULARITY)
                .en_us("You'll want lots of them soon.")
                .fr_fr("Tu en voudras plus.");
        addAdvancementTitle(ModAdvancements.ae2_STABLE_POWER)
                .en_us("A core controlled")
                .fr_fr("Un noyeau controllé");
        addAdvancementDesc(ModAdvancements.ae2_STABLE_POWER)
                .en_us("This time, you'll chose who to kill.")
                .fr_fr("Cette fois, tu contrôle qui tuer.");

        addAdvancementTitle(ModAdvancements.creative_THE_ESSENCE_root)
                .en_us("Hu ho...")
                .fr_fr("Ho ho...");
        addAdvancementDesc(ModAdvancements.creative_THE_ESSENCE_root)
                .en_us("You're not suppose to have that !!")
                .fr_fr("T'es pas censé avoir sa !!");
        addAdvancementTitle(ModAdvancements.creative_PACKAGE)
                .en_us("Creative package")
                .fr_fr("Packet creatif");
        addAdvancementDesc(ModAdvancements.creative_PACKAGE)
                .en_us("Well done !")
                .fr_fr("Bravo ! Tu est déterminé toi !");
        addAdvancementTitle(ModAdvancements.creative_THE_INGOT)
                .en_us("The god's ingot")
                .fr_fr("Le lingot des dieux");
        addAdvancementDesc(ModAdvancements.creative_THE_INGOT)
                .en_us("This is  too powerful for your hands !")
                .fr_fr("C'est trop puissant pour tes mains !");
        addAdvancementTitle(ModAdvancements.creative_THE_EGG)
                .en_us("The egg")
                .fr_fr("L'œuf");
        addAdvancementDesc(ModAdvancements.creative_THE_EGG)
                .en_us("Source of life. From the god power.")
                .fr_fr("La source de vie. Prise aux dieux");

        addAdvancementTitle(ModAdvancements.dirt_DIRT_root)
                .en_us("Mine dirt !")
                .fr_fr("Minez de la terre !");
        addAdvancementDesc(ModAdvancements.dirt_DIRT_root)
                .en_us("Easy right ?")
                .fr_fr("C'était simple nan ?");
        addAdvancementTitle(ModAdvancements.dirt_VERY_COMPRESSED)
                .en_us("That's compact...")
                .fr_fr("C'est compacte...");
        addAdvancementDesc(ModAdvancements.dirt_VERY_COMPRESSED)
                .en_us("You realize compacting dirt make things compact.")
                .fr_fr("Tu réalise que compacter rend compacte.");
        addAdvancementTitle(ModAdvancements.dirt_STRONG_SWORD)
                .en_us("The powerful one")
                .fr_fr("La puissante");
        addAdvancementDesc(ModAdvancements.dirt_STRONG_SWORD)
                .en_us("You're one step from the best one !")
                .fr_fr("Tu est a 2 pas de la meilleure !");
        addAdvancementTitle(ModAdvancements.dirt_PLACED_RUBINIUM)
                .en_us("The one that cost much")
                .fr_fr("Celui qui coûte");
        addAdvancementDesc(ModAdvancements.dirt_PLACED_RUBINIUM)
                .en_us("The rubis+")
                .fr_fr("Le rubis +");
        addAdvancementTitle(ModAdvancements.dirt_PLACED_RUBIS_ORE)
                .en_us("A craftable ore")
                .fr_fr("Un minerais fabricable");
        addAdvancementDesc(ModAdvancements.dirt_PLACED_RUBIS_ORE)
                .en_us("Shiny")
                .fr_fr("Brillant");
        addAdvancementTitle(ModAdvancements.dirt_RESOURCE_PACK)
                .en_us("Valuable dirt")
                .fr_fr("Terre précieuse");
        addAdvancementDesc(ModAdvancements.dirt_RESOURCE_PACK)
                .en_us("You got all resources from it")
                .fr_fr("Tu as eu toutes les resources qu'elle offre");
        addAdvancementTitle(ModAdvancements.dirt_ATE_DIRT)
                .en_us("A wet dirt...")
                .fr_fr("Une terre mouillé...");
        addAdvancementDesc(ModAdvancements.dirt_ATE_DIRT)
                .en_us("Did you eat it ?")
                .fr_fr("L'as tu mangé ?");
        addAdvancementTitle(ModAdvancements.dirt_HOT_SWORD)
                .en_us("Hot sword")
                .fr_fr("Epée chaude");
        addAdvancementDesc(ModAdvancements.dirt_HOT_SWORD)
                .en_us("Careful to not get burned")
                .fr_fr("Attention de ne pas se brûler");

        addAdvancementTitle(ModAdvancements.story_BUTTONS)
                .en_us("Buttons !")
                .fr_fr("Boutons !");
        addAdvancementDesc(ModAdvancements.story_BUTTONS)
                .en_us("Was it a mistake ?")
                .fr_fr("Est-ce une erreur ?");
        addAdvancementTitle(ModAdvancements.story_MORE_BUTTONS)
                .en_us("Buttons ! Again !")
                .fr_fr("Plus de boutons !");
        addAdvancementDesc(ModAdvancements.story_MORE_BUTTONS)
                .en_us("Well, here you're cooked.")
                .fr_fr("Ben, là c'est fichu.");
        addAdvancementTitle(ModAdvancements.story_FIRE_POWER)
                .en_us("Fire-full")
                .fr_fr("Du feu plein la tête");
        addAdvancementDesc(ModAdvancements.story_FIRE_POWER)
                .en_us("Strong like a diamond")
                .fr_fr("Dur comme du diamant");
        addAdvancementTitle(ModAdvancements.story_GROWING_FIRE)
                .en_us("Let the fire grow...")
                .fr_fr("Laisse le feu grandir...");
        addAdvancementDesc(ModAdvancements.story_GROWING_FIRE)
                .en_us("As a plant.")
                .fr_fr("Tel une plante.");
        addAdvancementTitle(ModAdvancements.story_HARD_IRON)
                .en_us("A powerful ingot")
                .fr_fr("Un lingot puissant");
        addAdvancementDesc(ModAdvancements.story_HARD_IRON)
                .en_us("And hard too")
                .fr_fr("Solide aussi");


        // items
        addItem(ModItems.ACCUMULATION_ASSISTED_CIRCUIT_BOARD)
                .en_us("Accumulation assisted circuit board")
                .fr_fr("Circuit imprimé d'accumulation assisté");
        addItem(ModItems.ALLOYED_SWORD)
                .en_us("Alloyed sword")
                .fr_fr("Epée-alliage");
        addItem(ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC)
                .en_us("Ballad of the blocks music disc")
                .fr_fr("Disque de musique de Ballad of the blocks");
        addRecordDesc(ModItems.BALLAD_OF_THE_BLOCKS_MUSIC_DISC)
                .en_us("Ballad of blocks - Catoutou")
                .fr_fr("Ballad of blocks - Catoutou");
        addItem(ModItems.BAR_BRAWL_MUSIC_DISC)
                .en_us("Bar brawl music disc")
                .fr_fr("Disque de musique de Bar brawl");
        addRecordDesc(ModItems.BAR_BRAWL_MUSIC_DISC)
                .en_us("Bryan Tech - Bar brawl (CC0)")
                .fr_fr("Bryan Tech - Bar brawl (CC0)");
        addItem(ModItems.BASE_EGG)
                .en_us("Egg base")
                .fr_fr("Base d'oeuf");
        addItem(ModItems.BASE_OF_DISC)
                .en_us("Base of disc")
                .fr_fr("Base de disque");
        addItem(ModItems.BIOMASS)
                .en_us("Biomass")
                .fr_fr("Biomasse");
        addItem(ModItems.BLUE_BIOMASS)
                .en_us("Blue biomass")
                .fr_fr("Biomasse bleu");
        addItem(ModItems.RED_BIOMASS)
                .en_us("Red biomass")
                .fr_fr("Biomasse rouge");
        addItem(ModItems.DARKENED_BIOMASS)
                .en_us("Darkened biomass")
                .fr_fr("Biomasse assombrie");
        addItem(ModItems.BLOCK_BY_BLOCK_MUSIC_DISC)
                .en_us("Block by block music disc")
                .fr_fr("Disque de musique de Block by block");
        addRecordDesc(ModItems.BLOCK_BY_BLOCK_MUSIC_DISC)
                .en_us("Block by block - Catoutou")
                .fr_fr("Block by block - Catoutou");
        addItem(ModItems.BUCKET_OF_LIQUID_CHOCOLATE_ICE_CREAM)
                .en_us("Bucket of liquid chocolate ice cream")
                .fr_fr("Seau de glace au chocolat liquide");
        addItem(ModItems.BUCKET_OF_LIQUID_ICE_CREAM)
                .en_us("Bucket of liquid ice cream")
                .fr_fr("Seau de glace liquide");
        addItem(ModItems.BUCKET_OF_LIQUID_STRAWBERRIES_ICE_CREAM)
                .en_us("Bucket of liquid strawberry ice cream")
                .fr_fr("Seau de glace à la fraise liquide");
        addItem(ModItems.BUCKET_OF_LIQUID_SWEET_BERRIES_ICE_CREAM)
                .en_us("Bucket of liquid sweet berries ice cream")
                .fr_fr("Seau de glace aux baies sucrées liquide");
        addItem(ModItems.BUCKET_OF_LIQUID_VANILLA_ICE_CREAM)
                .en_us("Bucket of liquid vanilla ice cream")
                .fr_fr("Seau de glace à la vanille liquide");
        addItem(ModItems.BURGER)
                .en_us("Burger")
                .fr_fr("Hamburger");
        addItem(ModItems.CALCULATION_ASSISTED_CIRCUIT_BOARD)
                .en_us("Calculation assisted circuit board")
                .fr_fr("Circuit imprimé de calcul assisté");
        addItem(ModItems.CHOCOLATE_ICE_CREAM)
                .en_us("Chocolate ice cream")
                .fr_fr("Glace au chocolat");
        addItem(ModItems.CHOCOLATE_SCOOP)
                .en_us("Chocolate scoop")
                .fr_fr("Boule de glace au chocolat");
        addItem(ModItems.CHOCOLATE_SNOWBALL)
                .en_us("Chocolate snowball")
                .fr_fr("Boule de neige au chocolat");
        addItem(ModItems.CHOCOLATE_SORBET)
                .en_us("Chocolate sorbet")
                .fr_fr("Sorbet au chocolat");
        addItem(ModItems.CONE)
                .en_us("Cone")
                .fr_fr("Cône");
        addItem(ModItems.CONTROL_PANEL)
                .en_us("Control panel")
                .fr_fr("Panneau de contrôle");
        addItem(ModItems.CONTROLLED_STICK)
                .en_us("Controlled stick")
                .fr_fr("Baton de contrôle");
        addItem(ModItems.COPPER_BALL)
                .en_us("Copper ball")
                .fr_fr("Balle de cuivre");
        addItem(ModItems.DIAMOND_BALL)
                .en_us("Diamond ball")
                .fr_fr("Balle de diamant");
        addItem(ModItems.GOLD_BALL)
                .en_us("Gold ball")
                .fr_fr("Balle d'or");
        addItem(ModItems.IRON_BALL)
                .en_us("Iron ball")
                .fr_fr("Balle de fer");
        addItem(ModItems.SAPPHIRE_BALL)
                .en_us("Sapphire ball")
                .fr_fr("Balle de saphir");
        addItem(ModItems.SUPER_CHARGED_BALL)
                .en_us("Super charged ball")
                .fr_fr("Balle super chargée");
        addItem(ModItems.DIAMOND_CATALYZER)
                .en_us("Diamond catalyzer")
                .fr_fr("Catalyseur en diamant");
        addItem(ModItems.GOLD_CATALYZER)
                .en_us("Gold catalyzer")
                .fr_fr("Catalyseur d'or");
        addItem(ModItems.IRON_CATALYZER)
                .en_us("Iron catalyzer")
                .fr_fr("Catalyseur de fer");
        addItem(ModItems.NETHERITE_CATALYZER)
                .en_us("Netherite catalyzer")
                .fr_fr("Catalyseur de netherite");
        addItem(ModItems.SUPER_CHARGED_CATALYZER)
                .en_us("Super charged catalyzer")
                .fr_fr("Catalyseur super chargé");
        addItem(ModItems.WIND_CHARGED_CATALYZER)
                .en_us("Wind charged catalyzer")
                .fr_fr("Catalyseur chargé en vent");
        addItem(ModItems.UNFINISHED_BLAZE_POWDER)
                .en_us("Unfinished blaze powder")
                .fr_fr("Poudre de blaze en cours de fabrication");
        addItem(ModItems.UNFINISHED_EMERALD)
                .en_us("Unfinished emerald")
                .fr_fr("Émeraude en cours de fabrication");
        addItem(ModItems.UNFINISHED_STORM_FRAGMENT)
                .en_us("Unfinished storm fragment")
                .fr_fr("Fragment de tempête en cours de fabrication");
        addItem(ModItems.UNPROCESSED_QUANTUM_CORE)
                .en_us("Unfinished quantum core")
                .fr_fr("Noyau quantique en cours de fabrication");
        addItem(ModItems.UNPROCESSED_SEA_PICKLE)
                .en_us("Unfinished sea pickle")
                .fr_fr("Concombre de mer en cours de fabrication");
        addItem(ModItems.VANILLA_EXTRACT)
                .en_us("Vanilla extract")
                .fr_fr("Extrait de vanille");
        addItem(ModItems.VANILLA_ICE_CREAM)
                .en_us("Vanilla ice cream")
                .fr_fr("Glace à la vanille");
        addItem(ModItems.VANILLA_POD)
                .en_us("Vanilla pod")
                .fr_fr("Gousse de vanille");
        addItem(ModItems.DRIED_VANILLA_POD)
                .en_us("Dried vanilla pod")
                .fr_fr("Gousse de vanille sèche");
        addItem(ModItems.VANILLA_SCOOP)
                .en_us("Vanilla scoop")
                .fr_fr("Boule de glace à la vanille");
        addItem(ModItems.VANILLA_SNOWBALL)
                .en_us("Vanilla snowball")
                .fr_fr("Boule de neige à la vanille");
        addItem(ModItems.VANILLA_SORBET)
                .en_us("Vanilla sorbet")
                .fr_fr("Sorbet à la vanille");
        addItem(ModItems.WET_DIRT)
                .en_us("Wet dirt")
                .fr_fr("Terre mouillé");
        addItem(ModItems.WIND_CHARGED_INGOT)
                .en_us("Wind charged ingot")
                .fr_fr("Lingot chargé de vent");
        addItem(ModItems.WIND_STICK)
                .en_us("Wind stick")
                .fr_fr("Baton de vent");
        addItem(ModItems.YEAST)
                .en_us("Yeast")
                .fr_fr("Levure");
        addItem(ModItems.ZOMBIE_ARM)
                .en_us("Zombie arm")
                .fr_fr("Bras de zombie");
        addItem(ModItems.XP_RUNE)
                .en_us("Xp rune")
                .fr_fr("Rune d'xp");
        addItem(ModItems.SPRUCE_RUNE)
                .en_us("Spruce rune")
                .fr_fr("Rune de sapin");
        addItem(ModItems.OAK_RUNE)
                .en_us("Oak rune")
                .fr_fr("Rune de chêne");
        addItem(ModItems.MONEY_RUNE)
                .en_us("Money rune")
                .fr_fr("Rune d'argent");
        addItem(ModItems.EMPTY_RUNE)
                .en_us("Empty rune")
                .fr_fr("Rune vide");
        addItem(ModItems.DICE)
                .en_us("Dice")
                .fr_fr("Dée");
        addItem(ModItems.EXPLODED_POTATO)
                .en_us("Exploded potato")
                .fr_fr("Patate explosée");
        addItem(ModItems.FIRE_DIAMOND)
                .en_us("Fire diamond")
                .fr_fr("Diamant de feu");
        addItem(ModItems.FIRE_SEEDS)
                .en_us("Fire seeds")
                .fr_fr("Graines de feu");
        addItem(ModItems.FIRE_STICK)
                .en_us("Fire stick")
                .fr_fr("Baton de feu");
        addItem(ModItems.FIRE_SWORD)
                .en_us("Fire sword")
                .fr_fr("Epée de feu");
        addItem(ModItems.DIRTY_HUMID_POTION)
                .en_us("Dirty humid potion")
                .fr_fr("Potion humide salle");
        addItem(ModItems.HUMID_POTION)
                .en_us("Humid potion")
                .fr_fr("Potion humide");
        addItem(ModItems.LITTLE_HUMID_POTION)
                .en_us("Little humid potion")
                .fr_fr("Petite potion humide");
        addItem(ModItems.EMPTY_BIG_FLASK)
                .en_us("Empty big flask")
                .fr_fr("Grande fiole vide");
        addItem(ModItems.EMPTY_LITTLE_FLASK)
                .en_us("Empty little flask")
                .fr_fr("Petite fiole vide");
        addItem(ModItems.EXTREMELY_DRY_DIRT)
                .en_us("Extremely dry dirt")
                .fr_fr("Terre extrêmement sèche");
        addItem(ModItems.END_OF_THE_START_MUSIC_DISC)
                .en_us("End of the start music disc")
                .fr_fr("Disque de musique de End of the start");
        addRecordDesc(ModItems.END_OF_THE_START_MUSIC_DISC)
                .en_us("Destabilized - PunchDeck")
                .fr_fr("Destabilized - PunchDeck");
        addItem(ModItems.ENGINEERING_ASSISTED_CIRCUIT_BOARD)
                .en_us("Engineering assisted circuit board")
                .fr_fr("Circuit imprimé d'ingénierie assisté");
        addItem(ModItems.LOGIC_ASSISTED_CIRCUIT_BOARD)
                .en_us("Logic assisted circuit board")
                .fr_fr("Circuit imprimé de logique assisté");
        addItem(ModItems.QUANTUM_ASSISTED_CIRCUIT_BOARD)
                .en_us("Quantum assisted circuit board")
                .fr_fr("Circuit imprimé quantique assisté");
        addItem(ModItems.PIECE_OF_DIRT)
                .en_us("Piece of dirt")
                .fr_fr("Morceau de terre");
        addItem(ModItems.PIECE_OF_EXTREMELY_DRY_DIRT)
                .en_us("Piece of extremely dry dirt")
                .fr_fr("Morceau de terre extrêmement sèche");
        addItem(ModItems.PINE_CONE)
                .en_us("Pine cone")
                .fr_fr("Pomme de pin");
        addItem(ModItems.PRINTED_QUANTUM_CIRCUIT)
                .en_us("Printed quantum circuit")
                .fr_fr("Circuit quantique");
        addItem(ModItems.MAGIC_SHARD)
                .en_us("Magic shard")
                .fr_fr("Eclat de magie");
        addItem(ModItems.METAL_DETECTOR)
                .en_us("Metal detector")
                .fr_fr("Détecteur de métal");
        addItem(ModItems.MILK_BUCKET_WITH_EGG)
                .en_us("Milk bucket with eggs")
                .fr_fr("Seau de lait et oeufs");
        addItem(ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC)
                .en_us("Legends awaken V1 music disc")
                .fr_fr("Disque de musique de Legends awaken V1");
        addRecordDesc(ModItems.LEGENDS_AWAKEN_V1_MUSIC_DISC)
                .en_us("Legends awaken V1 - Catoutou")
                .fr_fr("Legends awaken V1 - Catoutou");
        addItem(ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC)
                .en_us("Legends awaken V2 music disc")
                .fr_fr("Disque de musique de Legends awaken V2");
        addRecordDesc(ModItems.LEGENDS_AWAKEN_V2_MUSIC_DISC)
                .en_us("Legends awaken V2 - Catoutou")
                .fr_fr("Legends awaken V2 - Catoutou");
        addItem(ModItems.LIGHTNING_SWORD)
                .en_us("Lightning upgrade")
                .fr_fr("Epée de foudre");
        addItem(ModItems.LIGHTNING_UPGRADE)
                .en_us("Lightning upgrade")
                .fr_fr("Amélioration de foudre");
        addItem(ModItems.REDSTONE_PULSE_MUSIC_DISC)
                .en_us("Redstone pulse music disc")
                .fr_fr("Disque de musique de Redstone pulse");
        addRecordDesc(ModItems.REDSTONE_PULSE_MUSIC_DISC)
                .en_us("Redstone pulse - Catoutou")
                .fr_fr("Redstone pulse - Catoutou");
        addItem(ModItems.CUBIC_GROOVE_MUSIC_DISC)
                .en_us("Cubic groove music disc")
                .fr_fr("Disque de musique de Cubic groove");
        addRecordDesc(ModItems.CUBIC_GROOVE_MUSIC_DISC)
                .en_us("Cubic groove - Catoutou")
                .fr_fr("Cubic groove - Catoutou");
        addItem(ModItems.IN_THE_BLOCK_MUSIC_DISC)
                .en_us("In the block music disc")
                .fr_fr("Disque de musique de In the block");
        addRecordDesc(ModItems.IN_THE_BLOCK_MUSIC_DISC)
                .en_us("In the block - Catoutou")
                .fr_fr("In the block - Catoutou");
        addItem(ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC)
                .en_us("In the world of minecraft music disc")
                .fr_fr("Disque de musique de In the world of minecraft");
        addRecordDesc(ModItems.IN_THE_WORLD_OF_MINECRAFT_MUSIC_DISC)
                .en_us("In the world of minecraft - Catoutou")
                .fr_fr("In the world of minecraft - Catoutou");
        addItem(ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC)
                .en_us("My minecraft world music disc")
                .fr_fr("Disque de musique de My minecraft world");
        addRecordDesc(ModItems.MY_MINECRAFT_WORLD_MUSIC_DISC)
                .en_us("My minecraft world - Catoutou")
                .fr_fr("My minecraft world - Catoutou");
        addItem(ModItems.NETHER_NIGHTS_MUSIC_DISC)
                .en_us("Nether nights music disc")
                .fr_fr("Disque de musique de Nether nights");
        addRecordDesc(ModItems.NETHER_NIGHTS_MUSIC_DISC)
                .en_us("Nether night - Catoutou")
                .fr_fr("Nether night - Catoutou");
        addItem(ModItems.SERENE_ECHO_MUSIC_DISC)
                .en_us("Serene echo music disc")
                .fr_fr("Disque de musique de Serene echo");
        addRecordDesc(ModItems.SERENE_ECHO_MUSIC_DISC)
                .en_us("Serene echo - Catoutou")
                .fr_fr("Serene echo - Catoutou");
        addItem(ModItems.DARK_SOUL_MUSIC_DISC)
                .en_us("Dark soul music disc")
                .fr_fr("Disque de musique de Dark soul");
        addRecordDesc(ModItems.DARK_SOUL_MUSIC_DISC)
                .en_us("Hold On - Myuu")
                .fr_fr("Hold On - Myuu");
        addItem(ModItems.HALF_STICK)
                .en_us("Half stick")
                .fr_fr("Moitié de baton");
        addItem(ModItems.QUARTER_STICK)
                .en_us("Quarter stick")
                .fr_fr("Quart de baton");
        addItem(ModItems.DEVIL_BREAD)
                .en_us("Devil bread")
                .fr_fr("Pains du diable");
        addItem(ModItems.HOLY_BREAD)
                .en_us("Holy bread")
                .fr_fr("Pain du paradis");
        addItem(ModItems.HARDENED_INGOT)
                .en_us("Hardened ingot")
                .fr_fr("Lingot endurcie");
        addItem(ModItems.CORN)
                .en_us("Corn")
                .fr_fr("Maïs");
        addItem(ModItems.CORN_SEEDS)
                .en_us("Corn seeds")
                .fr_fr("Graines de maïs");
        addItem(ModItems.DARK_BOAT)
                .en_us("Dark boat")
                .fr_fr("Bateau sombre");
        addItem(ModItems.DARK_CHEST_BOAT)
                .en_us("Dark chest boat")
                .fr_fr("Bateau sombre avec coffre");
        addItem(ModItems.CREATIVE_ESSENCE)
                .en_us("Creative essence")
                .fr_fr("Essence créative");
        addItem(ModItems.CREATIVE_INGOT)
                .en_us("Creative ingot")
                .fr_fr("Lingot créatif");
        addItem(ModItems.CREATIVE_NUGGET)
                .en_us("Creative nugget")
                .fr_fr("Pépite créatif");
        addItem(ModItems.CREATIVE_RESIDUE)
                .en_us("Creative residue")
                .fr_fr("Résidu créatif");
        addItem(ModItems.SMALL_CREATIVE_NUGGET)
                .en_us("Small creative nugget")
                .fr_fr("Petite pépite créative");
        addItem(ModItems.QUANTUM_CORE)
                .en_us("Quantum core")
                .fr_fr("Noyau quantique");
        addItem(ModItems.QUANTUM_DUST)
                .en_us("Quantum dust")
                .fr_fr("Poudre quantique");
        addItem(ModItems.QUANTUM_PROCESSOR)
                .en_us("Quantum processor")
                .fr_fr("Processeur quantique");
        addItem(ModItems.QUANTUM_STAFF)
                .en_us("Quantum staff")
                .fr_fr("Septre quantique");
        addItem(ModItems.QUANTUMITE_CHUNK)
                .en_us("Quantumite chunk")
                .fr_fr("Morceau de quantumite");
        addItem(ModItems.QUANTUMITE_INGOT)
                .en_us("Quantumite ingot")
                .fr_fr("Lingot de quantumite");
        addItem(ModItems.QUANTUMITE_SHEET)
                .en_us("Quantumite chunk")
                .fr_fr("Plaque de quantumite");
        addItem(ModItems.SKULLKERY_TOOL)
                .en_us("Skulkery tool")
                .fr_fr("Outil de têtologie");
        addItem(ModItems.SLIME_SWORD)
                .en_us("Slime sword")
                .fr_fr("Epée de slime");
        addItem(ModItems.SLIMY_INGOT)
                .en_us("Slimy ingot")
                .fr_fr("Lingot de slime");
        addItem(ModItems.SLIMY_STICK)
                .en_us("Slimy stick")
                .fr_fr("Baton de slime");
        addItem(ModItems.SPAWNER_PART)
                .en_us("Spawner part")
                .fr_fr("Morceau de spawner");
        addItem(ModItems.QUARTZ_SHARD)
                .en_us("Quartz shard")
                .fr_fr("Eclat de quartz");
        addItem(ModItems.RED_CLAY_BALL)
                .en_us("Red clay ball")
                .fr_fr("Balle d'argile rouge");
        addItem(ModItems.RHINO_SPAWN_EGG)
                .en_us("Rhino spawn egg")
                .fr_fr("Oeuf d'apparition de rhino");
        addItem(ModItems.ROTTEN_BEEF)
                .en_us("Rotten beef")
                .fr_fr("Boeuf putréfié");
        addItem(ModItems.ROTTEN_CHICKEN)
                .en_us("Rotten chicken")
                .fr_fr("Poulet putréfié");
        addItem(ModItems.ROTTEN_COD)
                .en_us("Rotten cod")
                .fr_fr("Morue putréfié");
        addItem(ModItems.ROTTEN_MUTTON)
                .en_us("Rotten mutton")
                .fr_fr("Mouton putréfié");
        addItem(ModItems.ROTTEN_PORKCHOP)
                .en_us("Rotten porkchop")
                .fr_fr("Porc putréfié");
        addItem(ModItems.ROTTEN_RABBIT)
                .en_us("Rotten rabbit")
                .fr_fr("Lapin putréfié");
        addItem(ModItems.ROTTEN_SALMON)
                .en_us("Rotten salmon")
                .fr_fr("Saumon putréfié");
        addItem(ModItems.ROTTEN_LEATHER)
                .en_us("Rotten leather")
                .fr_fr("Cuir putréfié");
        addItem(ModItems.RESOURCE_DIRT)
                .en_us("Resource dirt")
                .fr_fr("Terre de resource");
        addItem(ModItems.RAW_SAPPHIRE)
                .en_us("Raw sapphire")
                .fr_fr("Saphir brut");
        addItem(ModItems.SAPPHIRE)
                .en_us("Sapphire")
                .fr_fr("Saphir");
        addItem(ModItems.SAPPHIRE_AXE)
                .en_us("Sapphire axe")
                .fr_fr("Hache en saphir");
        addItem(ModItems.SAPPHIRE_BOOTS)
                .en_us("Sapphire boots")
                .fr_fr("Bottes en saphir");
        addItem(ModItems.SAPPHIRE_CHESTPLATE)
                .en_us("Sapphire chestplate")
                .fr_fr("Plastron en saphir");
        addItem(ModItems.SAPPHIRE_HELMET)
                .en_us("Sapphire helmet")
                .fr_fr("Casque en saphir");
        addItem(ModItems.SAPPHIRE_HOE)
                .en_us("Sapphire hoe")
                .fr_fr("Houe en saphir");
        addItem(ModItems.SAPPHIRE_LEGGINGS)
                .en_us("Sapphire leggings")
                .fr_fr("Pentalon en saphir");
        addItem(ModItems.SAPPHIRE_NUGGET)
                .en_us("Sapphire nugget")
                .fr_fr("Pépite de saphir");
        addItem(ModItems.SAPPHIRE_PICKAXE)
                .en_us("Sapphire pickaxe")
                .fr_fr("Pioche en saphir");
        addItem(ModItems.SAPPHIRE_SHOVEL)
                .en_us("Sapphire shovel")
                .fr_fr("Pelle en saphir");
        addItem(ModItems.SAPPHIRE_STAFF)
                .en_us("Sapphire staff")
                .fr_fr("Sceptre de saphir");
        addItem(ModItems.SAPPHIRE_SWORD)
                .en_us("Sapphire sword")
                .fr_fr("Epée en saphir");
        addItem(ModItems.RUBINIUM)
                .en_us("Rubinium")
                .fr_fr("Rubinium");
        addItem(ModItems.RUBINIUM_SWORD)
                .en_us("Rubinium sword")
                .fr_fr("Epée en rubinium");
        addItem(ModItems.RUBIS)
                .en_us("Rubis")
                .fr_fr("Rubis");
        addItem(ModItems.SCOOP_OF_CHOCOLATE_SORBET)
                .en_us("Scoop of chocolate sorbet")
                .fr_fr("Boule de sorbet au chocolat");
        addItem(ModItems.SCOOP_OF_STRAWBERRIES_SORBET)
                .en_us("Scoop of strawberries sorbet")
                .fr_fr("Boule de sorbet au fraises");
        addItem(ModItems.SCOOP_OF_SWEET_BERRIES_SORBET)
                .en_us("Scoop of sweet berries sorbet")
                .fr_fr("Boule de sorbet au baies sucrées");
        addItem(ModItems.SCOOP_OF_VANILLA_SORBET)
                .en_us("Scoop of vanilla sorbet")
                .fr_fr("Boule de sorbet à la vanille");
        addItem(ModItems.STABILIZED_QUANTUM_CORE)
                .en_us("Stabilized quantum core")
                .fr_fr("Noyau quantique stable");
        addItem(ModItems.STORM_FRAGMENT)
                .en_us("Storm fragment")
                .fr_fr("Eclat de tempête");
        addItem(ModItems.STRAWBERRIES_SNOWBALL)
                .en_us("Strawberries snowball")
                .fr_fr("Boule de neige au fraises");
        addItem(ModItems.STRAWBERRIES_SORBET)
                .en_us("Strawberries sorbet")
                .fr_fr("Sorbet au fraises");
        addItem(ModItems.STRAWBERRY)
                .en_us("Strawberry")
                .fr_fr("Fraise");
        addItem(ModItems.STRAWBERRY_ICE_CREAM)
                .en_us("Strawberry ice cream")
                .fr_fr("Glace à la fraise");
        addItem(ModItems.STRAWBERRY_SCOOP)
                .en_us("Strawberry scoop")
                .fr_fr("Boule de glace à la fraise");
        addItem(ModItems.STRAWBERRY_SEEDS)
                .en_us("Strawberry seeds")
                .fr_fr("Graines de fraise");
        addItem(ModItems.SUPER_CHARGED_INGOT)
                .en_us("Super charged ingot")
                .fr_fr("Lingot super chargé");
        addItem(ModItems.SWEET_BERRIES_ICE_CREAM)
                .en_us("Sweet berries ice cream")
                .fr_fr("Glace aux baies sucrées");
        addItem(ModItems.SWEET_BERRIES_SCOOP)
                .en_us("Sweet berries scoop")
                .fr_fr("Boule de glace aux baies sucrées");
        addItem(ModItems.SWEET_BERRIES_SNOWBALL)
                .en_us("Sweet berries snowball")
                .fr_fr("Boule de neige aux baies sucrées");
        addItem(ModItems.SWEET_BERRIES_SORBET)
                .en_us("Sweet berries sorbet")
                .fr_fr("Sorbet aux baies sucrées");
        addItem(ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC)
                .en_us("The world of cubes music disc")
                .fr_fr("Disque de musique de The world of cubes");
        addRecordDesc(ModItems.THE_WORLD_OF_CUBES_MUSIC_DISC)
                .en_us("The world of cube - Catoutou")
                .fr_fr("The world of cube - Catoutou");
        addItem(ModItems.TREE_GROWER)
                .en_us("Tree grower")
                .fr_fr("Arboriculteur");
        addItem(ModItems.OXIDIZED_ZINC)
                .en_us("Oxidized zinc")
                .fr_fr("Zinc oxydé");
        addItem(ModItems.INSCRIBER_QUANTUM_PRESS)
                .en_us("Inscriber quantum press")
                .fr_fr("Presse quantique");
        addItem(ModItems.HALF_QUANTUMITE_SHEET)
                .en_us("Half quantumite sheet")
                .fr_fr("Demi plaque de quantumite");



    }










    public TranslationBuilder addMenuName(String id) {
        return add("menu." + id);
    }
}
