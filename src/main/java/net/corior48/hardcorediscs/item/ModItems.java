package net.corior48.hardcorediscs.item;

import net.corior48.hardcorediscs.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static net.corior48.hardcorediscs.HardcoreDiscs.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> BLANK_DISC = ITEMS.register("blank_disc",
            () -> new Item((new Item.Properties()).stacksTo(32)) {
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.hardcore_discs.blank_disc"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    //Discs
    public static final DeferredItem<Item> TEST_DISC = ITEMS.registerItem("test_disc",
            (properties ->  new Item(properties.jukeboxPlayable(ModSounds.TEST_DISC_KEY).stacksTo(1).rarity(Rarity.RARE))));

    //WXB
    public static final DeferredItem<Item> ANGEL_HARE_SIDE_A = ITEMS.registerItem("angel_hare_side_a",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ANGEL_HARE_SIDE_A_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> VILLAGER_LULLABY = ITEMS.registerItem("villager_lullaby",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.VILLAGER_LULLABY_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> JAKA_JAAN = ITEMS.registerItem("jaka_jaan",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.JAKA_JAAN_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> JAKA_JAAN_ALTERNATIVE = ITEMS.registerItem("jaka_jaan_alternative",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.JAKA_JAAN_ALTERNATIVE_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> GANGNAM_STYLE = ITEMS.registerItem("gangnam_style",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.GANGNAM_STYLE_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> A_MOTHERS_LOVE = ITEMS.registerItem("a_mothers_love",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.A_MOTHERS_LOVE_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> EGGMAN_ANNOUCNEMENT = ITEMS.registerItem("eggman_announcement",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.DISCO_EGGMANS_ANNOUNCEMENT_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> DONT_MINE_AT_NIGHT = ITEMS.registerItem("dont_mine_at_night",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.DONT_MINE_AT_NIGHT_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> KYLES_MOM = ITEMS.registerItem("kyles_mom",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.KYLES_MOM_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> NEVER_GONNA_GIVE_YOU_UP = ITEMS.registerItem("never_gonna_give_you_up",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.NEVER_GONNA_GIVE_YOU_UP_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> RESULTS_AND_CHILL = ITEMS.registerItem("results_and_chill",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.RESULTS_AND_CHILL_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> STILL_ALIVE = ITEMS.registerItem("still_alive",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.STILL_ALIVE_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> WANT_YOU_GONE = ITEMS.registerItem("want_you_gone",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.WANT_YOU_GONE_KEY).stacksTo(1).rarity(Rarity.RARE)));

     public static final DeferredItem<Item> ASGORE_RUNS_OVER_DESS = ITEMS.registerItem("asgore_runs_over_dess",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ASGORE_RUNS_OVER_DESS_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> SUMMER_TROPICALA = ITEMS.registerItem("summer_tropicala",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.SUMMER_TROPICALA_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<Item> DIGGY_DIGGY_HOLE = ITEMS.registerItem("diggy_diggy_hole",
            (properties -> new Item(properties.jukeboxPlayable(ModSounds.DIGGY_DIGGY_HOLE_KEY).stacksTo(1).rarity(Rarity.RARE))));

    public static final DeferredItem<Item> REVENGE = ITEMS.registerItem("revenge",
            (properties -> new Item(properties.jukeboxPlayable(ModSounds.REVENGE_KEY).stacksTo(1).rarity(Rarity.RARE))));


    //Podcast Disc
    public static final DeferredItem<Item> BEN_DROWNED = ITEMS.registerItem("ben_drowned",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.BEN_DROWNED_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));
    //NonMain
    public static final DeferredItem<Item> SEXY_LUIGI = ITEMS.registerItem("sexy_luigi",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.SEXY_LUIGI_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HOPES_AND_DREAMS_REMASTER = ITEMS.registerItem("hopes_and_dreams_remaster",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.HOPES_AND_DREAMS_REMASTER_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> FUHUHHUK = ITEMS.registerItem("fuhuhuhk",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.FUHUHU_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> LISTEN_MY_WAY = ITEMS.registerItem("listen_my_way",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.LISTEN_MY_WAY_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MARIOS_INVINCIBLE_SONG = ITEMS.registerItem("marios_invincible_song",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.MARIOS_INVINCIBLE_SONG_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> NEVER_GONNA_STOP = ITEMS.registerItem("never_gonna_stop",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.NEVER_GONNA_STOP_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ONLY_FORCE_FOR_ME = ITEMS.registerItem("only_force_for_me",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.ONLY_FORCE_FOR_ME_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> RAMBLEY_KINITO_RAP = ITEMS.registerItem("rambley_kinito_rap",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.RAMBLEY_KINITO_RAP_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> REVENGE_2 = ITEMS.registerItem("revenge_2",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.REVENGE_2_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SONICEXE_BENDROWNED_RAP = ITEMS.registerItem("sonicexe_bendrowned_rap",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.SONICEXE_BENDROWNED_RAP_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> STORY_OF_UNDERTALE_MOTI = ITEMS.registerItem("story_of_undertale_moti",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.STORY_OF_UNDERTALE_MOTI_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> STRONGER_DAFT_SPEED = ITEMS.registerItem("stronger_daft_speed",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.STRONGER_DAFT_SPEED_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SUSIE_YURI_RAP = ITEMS.registerItem("susie_yuri_rap",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.SUSIE_YURI_RAP_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> TRIPLE_THE_THREAT_NONGAGOS = ITEMS.registerItem("triple_the_threat_nongagos",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.TRIPLE_THE_THREAT_NONGAGOS_KEY).stacksTo(1).rarity(Rarity.RARE)));

    //Soundcloud
    public static final DeferredItem<Item> BACKBONE_REMIX = ITEMS.registerItem("backbone_remix",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.BACKBONE_REMIX_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> LIKE_FATHER_LIKE_SON = ITEMS.registerItem("like_father_like_son",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.LIKE_FATHER_LIKE_SON_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MEGALOVANIA_DEMITALE = ITEMS.registerItem("megalovania_demitale",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.MEGALOVANIA_DEMITALE_KEY).stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MEGALOVANIA_ELEVATOR_JAZZ = ITEMS.registerItem("megalovania_elevator_jazz",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.MEGALOVANIA_ELEVATOR_JAZZ_KEY).stacksTo(1).rarity(Rarity.RARE)));

    //Register
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
