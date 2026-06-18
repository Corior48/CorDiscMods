package net.corior48.hardcorediscs.item;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.corior48.hardcorediscs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.C;

import java.util.function.Supplier;

import static net.corior48.hardcorediscs.item.ModItems.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HardcoreDiscs.MODID);

    public static final Supplier<CreativeModeTab> COMMON_ITEMS = CREATIVE_MODE_TAB.register("common_items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BLANK_DISC.get()))
                    .title(Component.translatable("creativetab.commom.title"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MUSIC_BLOCK.get());
                        output.accept(ModItems.BLANK_DISC.get());
                        output.accept(ModItems.TEST_DISC.get());
                    }))
                    .build());
    public static final Supplier<CreativeModeTab> NON_MAIN_PLAYLIST = CREATIVE_MODE_TAB.register("non_main_playlist",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.HOPES_AND_DREAMS_REMASTER.get()))
                    .title(Component.translatable("creativetab.nmp.title"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.FUHUHHUK);
                        output.accept(ModItems.HOPES_AND_DREAMS_REMASTER);
                        output.accept(ModItems.LISTEN_MY_WAY);
                        output.accept(ModItems.MARIOS_INVINCIBLE_SONG);
                        output.accept(ModItems.NEVER_GONNA_STOP);
                        output.accept(ModItems.ONLY_FORCE_FOR_ME);
                        output.accept(ModItems.RAMBLEY_KINITO_RAP);
                        output.accept(ModItems.REVENGE_2);
                        output.accept(ModItems.SEXY_LUIGI);
                        output.accept(ModItems.SONICEXE_BENDROWNED_RAP);
                        output.accept(ModItems.STORY_OF_UNDERTALE_MOTI);
                        output.accept(ModItems.STRONGER_DAFT_SPEED);
                        output.accept(ModItems.TRIPLE_THE_THREAT_NONGAGOS);
                    }))
                    .build());
    public static final Supplier<CreativeModeTab> PODCASTS = CREATIVE_MODE_TAB.register("podcasts",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BEN_DROWNED.get()))
                    .title(Component.translatable("creativetab.podcasts.title"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BEN_DROWNED.get());
                    }))
                    .build());
    public static final Supplier<CreativeModeTab> WXB_TAB = CREATIVE_MODE_TAB.register("wxb_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.NEVER_GONNA_GIVE_YOU_UP.get()))
                    .title(Component.translatable("creativetab.wxb_tab.title"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ANGEL_HARE_SIDE_A.get());
                        output.accept(VILLAGER_LULLABY.get());
                        output.accept(JAKA_JAAN.get());
                        output.accept(JAKA_JAAN_ALTERNATIVE.get());
                        output.accept(GANGNAM_STYLE.get());
                        output.accept(A_MOTHERS_LOVE.get());
                        output.accept(EGGMAN_ANNOUCNEMENT.get());
                        output.accept(DONT_MINE_AT_NIGHT.get());
                        output.accept(KYLES_MOM.get());
                        output.accept(NEVER_GONNA_GIVE_YOU_UP.get());
                        output.accept(RESULTS_AND_CHILL.get());
                        output.accept(STILL_ALIVE.get());
                        output.accept(WANT_YOU_GONE.get());
                        output.accept(ASGORE_RUNS_OVER_DESS.get());
                        output.accept(SUMMER_TROPICALA.get());
                        output.accept(DIGGY_DIGGY_HOLE.get());
                        output.accept(REVENGE.get());
                    }))
                    .build());
    public static final Supplier<CreativeModeTab> SOUNCLOUD_TAB = CREATIVE_MODE_TAB.register("soundcloud_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.MEGALOVANIA_ELEVATOR_JAZZ.get()))
                    .title(Component.translatable("creativetab.soundcloud.title"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BACKBONE_REMIX);
                        output.accept(ModItems.LIKE_FATHER_LIKE_SON);
                        output.accept(ModItems.MEGALOVANIA_DEMITALE);
                        output.accept(ModItems.MEGALOVANIA_ELEVATOR_JAZZ);
                    }))
                    .build());


    //eventbus
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
