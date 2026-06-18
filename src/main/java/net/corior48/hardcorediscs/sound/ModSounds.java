package net.corior48.hardcorediscs.sound;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, HardcoreDiscs.MODID);

    //SFX Sounds
    public static final Supplier<SoundEvent> CATEGORY_OPEN = registerSoundEvent("category_open");
    public static final Supplier<SoundEvent> MUSIC_BLOCK_OPEN = registerSoundEvent("music_block_open");
    public static final Supplier<SoundEvent> BUTTON_HIGHLIGHT = registerSoundEvent("button_highlight");
    public static final Supplier<SoundEvent> BUTTON_PRESS =  registerSoundEvent("button_press");
    public static final Supplier<SoundEvent> ERROR = registerSoundEvent("error");
    public static final Supplier<SoundEvent> DISC_CREATION = registerSoundEvent("disc_creation");

    // Test Disc
    public static final Supplier<SoundEvent> TEST_DISC = registerSoundEvent("test_disc");
    public static final ResourceKey<JukeboxSong> TEST_DISC_KEY = createSong("test_disc");

    //Waterfall x Bailey Base Discs
    public static final Supplier<SoundEvent> MEGALOVANIA_1 = registerSoundEvent("megalovania_1");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_1_KEY = createSong("wxb/megalovania_1");

    public static final Supplier<SoundEvent> ANGEL_HARE_SIDE_A = registerSoundEvent("angel_hare_side_a");
    public static final ResourceKey<JukeboxSong> ANGEL_HARE_SIDE_A_KEY = createSong("wxb/angel_hare_side_a");

    public static final Supplier<SoundEvent> VILLAGER_LULLABY = registerSoundEvent("villager_lullaby");
    public static final ResourceKey<JukeboxSong> VILLAGER_LULLABY_KEY = createSong("wxb/villager_lullaby");

    public static final Supplier<SoundEvent> JAKA_JAAN = registerSoundEvent("jaka_jaan");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_KEY = createSong("wxb/jaka_jaan");

    public static final Supplier<SoundEvent> JAKA_JAAN_ALTERNATIVE = registerSoundEvent("jaka_jaan_alternative");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_ALTERNATIVE_KEY = createSong("wxb/jaka_jaan_alternative");

    public static final Supplier<SoundEvent> GANGNAM_STYLE = registerSoundEvent("gangnam_style");
    public static final ResourceKey<JukeboxSong> GANGNAM_STYLE_KEY = createSong("wxb/gangnam_style");

    public static final Supplier<SoundEvent> A_MOTHERS_LOVE = registerSoundEvent("a_mothers_love");
    public static final ResourceKey<JukeboxSong> A_MOTHERS_LOVE_KEY = createSong("wxb/a_mothers_love");

    public static final Supplier<SoundEvent> EGGMAN_ANNOUNCEMENT = registerSoundEvent("eggman_announcement");
    public static final ResourceKey<JukeboxSong> DISCO_EGGMANS_ANNOUNCEMENT_KEY = createSong("wxb/eggman_announcement");

    public static final Supplier<SoundEvent> DONT_MINE_AT_NIGHT = registerSoundEvent("dont_mine_at_night");
    public static final ResourceKey<JukeboxSong> DONT_MINE_AT_NIGHT_KEY = createSong("wxb/dont_mine_at_night");

    public static final Supplier<SoundEvent> KYLES_MOM = registerSoundEvent("kyles_mom");
    public static final ResourceKey<JukeboxSong> KYLES_MOM_KEY = createSong("wxb/kyles_mom");

    public static final Supplier<SoundEvent> NEVER_GONNA_GIVE_YOU_UP = registerSoundEvent("never_gonna_give_you_up");
    public static final ResourceKey<JukeboxSong> NEVER_GONNA_GIVE_YOU_UP_KEY = createSong("wxb/never_gonna_give_you_up");

    public static final Supplier<SoundEvent> RESULTS_AND_CHILL = registerSoundEvent("results_and_chill");
    public static final ResourceKey<JukeboxSong> RESULTS_AND_CHILL_KEY = createSong("wxb/results_and_chill");

    public static final Supplier<SoundEvent> STILL_ALIVE = registerSoundEvent("still_alive");
    public static final ResourceKey<JukeboxSong> STILL_ALIVE_KEY = createSong("wxb/still_alive");

    public static final Supplier<SoundEvent> WANT_YOU_GONE = registerSoundEvent("want_you_gone");
    public static final ResourceKey<JukeboxSong> WANT_YOU_GONE_KEY = createSong("wxb/want_you_gone");

    public static final Supplier<SoundEvent> ASGORE_RUNS_OVER_DESS = registerSoundEvent("asgore_runs_over_dess");
    public static final ResourceKey<JukeboxSong> ASGORE_RUNS_OVER_DESS_KEY = createSong("wxb/asgore_runs_over_dess");

    public static final Supplier<SoundEvent> SUMMER_TROPICALA = registerSoundEvent("summer_tropicala");
    public static final ResourceKey<JukeboxSong> SUMMER_TROPICALA_KEY = createSong("wxb/summer_tropicala");

    public static final Supplier<SoundEvent> DIGGY_DOGGY_HOLE = registerSoundEvent("diggy_diggy_hole");
    public static final ResourceKey<JukeboxSong> DIGGY_DIGGY_HOLE_KEY = createSong("wxb/diggy_diggy_hole");

    public static final Supplier<SoundEvent> REVENGE = registerSoundEvent("revenge");
    public static final ResourceKey<JukeboxSong> REVENGE_KEY = createSong("wxb/revenge");

    //Podcast Discs
    public static final Supplier<SoundEvent> BEN_DROWNED = registerSoundEvent("ben_drowned");
    public static final ResourceKey<JukeboxSong> BEN_DROWNED_KEY = createSong("podcasts/ben_drowned");

    //Non-Main Playlist Discs
    public static final Supplier<SoundEvent> SEXY_LUIGI = registerSoundEvent("sexy_luigi");
    public static final ResourceKey<JukeboxSong> SEXY_LUIGI_KEY = createSong("non-main/sexy_luigi");

    public static final Supplier<SoundEvent> HOPES_AND_DREAMS_REMASTER = registerSoundEvent("hopes_and_dreams_remaster");
    public static final ResourceKey<JukeboxSong> HOPES_AND_DREAMS_REMASTER_KEY = createSong("non-main/hopes_and_dreams_remaster");

    public static final Supplier<SoundEvent> FUHUHUHK = registerSoundEvent("fuhuhuhk");
    public static final ResourceKey<JukeboxSong> FUHUHU_KEY = createSong("non-main/fuhuhuhk");

    public static final Supplier<SoundEvent> LISTEN_MY_WAY =  registerSoundEvent("listen_my_way");
    public static final ResourceKey<JukeboxSong> LISTEN_MY_WAY_KEY = createSong("non-main/listen_my_way");

    public static final Supplier<SoundEvent> MARIOS_INVINCIBLE_SONG = registerSoundEvent("marios_invincible_song");
    public static final ResourceKey<JukeboxSong> MARIOS_INVINCIBLE_SONG_KEY = createSong("non-main/marios_invincible_song");

    public static final Supplier<SoundEvent> NEVER_GONNA_STOP = registerSoundEvent("never_gonna_stop");
    public static final ResourceKey<JukeboxSong> NEVER_GONNA_STOP_KEY = createSong("non-main/never_gonna_stop");

    public static final Supplier<SoundEvent> ONLY_FORCE_FOR_ME =  registerSoundEvent("only_force_for_me");
    public static final ResourceKey<JukeboxSong> ONLY_FORCE_FOR_ME_KEY = createSong("non-main/only_force_for_me");

    public static final Supplier<SoundEvent> RAMBLEY_KINITO_RAP = registerSoundEvent("rambley_kinito_rap");
    public static final ResourceKey<JukeboxSong> RAMBLEY_KINITO_RAP_KEY = createSong("non-main/rambley_kinito_rap");

    public static final Supplier<SoundEvent> REVENGE_2 =  registerSoundEvent("revenge_2");
    public static final ResourceKey<JukeboxSong> REVENGE_2_KEY = createSong("non-main/revenge_2");

    public static final Supplier<SoundEvent> SONICEXE_BENDROWNED_RAP = registerSoundEvent("sonicexe_bendrowned_rap");
    public static final ResourceKey<JukeboxSong> SONICEXE_BENDROWNED_RAP_KEY = createSong("non-main/sonicexe_bendrowned_rap");

    public static final Supplier<SoundEvent> STORY_OF_UNDERTALE_MOTI =  registerSoundEvent("story_of_undertale_moti");
    public static final ResourceKey<JukeboxSong> STORY_OF_UNDERTALE_MOTI_KEY = createSong("non-main/story_of_undertale_moti");

    public static final Supplier<SoundEvent> STRONGER_DAFT_SPEED =  registerSoundEvent("stronger_daft_speed");
    public static final ResourceKey<JukeboxSong> STRONGER_DAFT_SPEED_KEY = createSong("non-main/stronger_daft_speed");

    public static final Supplier<SoundEvent> SUSIE_YURI_RAP =  registerSoundEvent("susie_yuri_rap");
    public static final ResourceKey<JukeboxSong> SUSIE_YURI_RAP_KEY = createSong("non-main/susie_yuri_rap");

    public static final Supplier<SoundEvent> TRIPLE_THE_THREAT_NONGAGOS =   registerSoundEvent("triple_the_threat_nongagos");
    public static final ResourceKey<JukeboxSong> TRIPLE_THE_THREAT_NONGAGOS_KEY = createSong("non-main/triple_the_threat_nongagos");

    //SoundCloud Discs
    public static final Supplier<SoundEvent> BACKBONE_REMIX =  registerSoundEvent("backbone_remix");
    public static final ResourceKey<JukeboxSong> BACKBONE_REMIX_KEY = createSong("soundcloud/backbone_remix");

    public static final Supplier<SoundEvent> LIKE_FATHER_LIKE_SON = registerSoundEvent("like_father_like_son");
    public static final ResourceKey<JukeboxSong> LIKE_FATHER_LIKE_SON_KEY = createSong("soundcloud/like_father_like_son");

    public static final Supplier<SoundEvent> MEGALOVANIA_DEMITALE = registerSoundEvent("megalovania_demitale");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_DEMITALE_KEY = createSong("soundcloud/megalovania_demitale");

    public static final Supplier<SoundEvent> MEGALOVANIA_ELEVATOR_JAZZ = registerSoundEvent("megalovania_elevator_jazz");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_ELEVATOR_JAZZ_KEY = createSong("soundcloud/megalovania_elevator_jazz");

    //Song Creation
    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(HardcoreDiscs.MODID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(HardcoreDiscs.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
