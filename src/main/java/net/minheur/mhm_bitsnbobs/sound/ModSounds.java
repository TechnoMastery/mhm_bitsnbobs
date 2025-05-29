package net.minheur.mhm_bitsnbobs.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MhmBitsnbobs.MOD_ID);

    /// Ici, on crée des sons et des groupes de sons.

    // son
    public static final RegistryObject<SoundEvent> METAL_DETECTOR_FOUND_ORE = registerSoundEvents("metal_detector_found_ore");

    public static final RegistryObject<SoundEvent> SOUND_BLOCK_BREAK = registerSoundEvents("sound_block_break");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_STEP = registerSoundEvents("sound_block_step");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_FALL = registerSoundEvents("sound_block_fall");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_PLACE = registerSoundEvents("sound_block_place");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_HIT = registerSoundEvents("sound_block_hit");

    // disques de musiques
    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvents("bar_brawl");
    public static final RegistryObject<SoundEvent> DARK_SOUL = registerSoundEvents("dark_soul");
    public static final RegistryObject<SoundEvent> END_OF_THE_START = registerSoundEvents("end_of_the_start");

    public static final RegistryObject<SoundEvent> BALLAD_OF_THE_BLOCKS = registerSoundEvents("ballad_of_the_blocks");
    public static final RegistryObject<SoundEvent> BLOCK_BY_BLOCK = registerSoundEvents("block_by_block");
    public static final RegistryObject<SoundEvent> CUBIC_GROOVE = registerSoundEvents("cubic_groove");
    public static final RegistryObject<SoundEvent> IN_THE_WORLD_OF_MINECRAFT = registerSoundEvents("in_the_world_of_minecraft");
    public static final RegistryObject<SoundEvent> IN_THE_BLOCK = registerSoundEvents("in_the_block");
    public static final RegistryObject<SoundEvent> THE_WORLD_OF_CUBES = registerSoundEvents("the_world_of_minecraft");
    public static final RegistryObject<SoundEvent> LEGENDS_AWAKEN_V1 = registerSoundEvents("legends_awaken_v1");
    public static final RegistryObject<SoundEvent> LEGENDS_AWAKEN_V2 = registerSoundEvents("legends_awaken_v2");
    public static final RegistryObject<SoundEvent> MY_MINECRAFT_WORLD = registerSoundEvents("my_minecraft_world");
    public static final RegistryObject<SoundEvent> NETHER_NIGHTS = registerSoundEvents("nether_nights");
    public static final RegistryObject<SoundEvent> REDSTONE_PULSE = registerSoundEvents("redstone_pulse");
    public static final RegistryObject<SoundEvent> SERENE_ECHO = registerSoundEvents("serene_echo");

    // Groupes de sons. ATTENTION l'ordre dans lequel les sons sont appelé est important
    public static final ForgeSoundType SOUND_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
            ModSounds.SOUND_BLOCK_BREAK, ModSounds.SOUND_BLOCK_STEP, ModSounds.SOUND_BLOCK_PLACE,
            ModSounds.SOUND_BLOCK_HIT, ModSounds.SOUND_BLOCK_FALL);


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MhmBitsnbobs.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
