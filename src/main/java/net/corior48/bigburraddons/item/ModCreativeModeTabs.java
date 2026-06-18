package net.corior48.bigburraddons.item;

import net.corior48.bigburraddons.BigBurrAddons;
import net.corior48.bigburraddons.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.corior48.bigburraddons.item.ModItems.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BigBurrAddons.MODID);

    public static final Supplier<CreativeModeTab> COMMON_ITEMS = CREATIVE_MODE_TAB.register("common_items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BLANK_DISC.get()))
                    .title(Component.translatable("itemGroup.bigburraddons.common"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MUSIC_BLOCK.get());
                        output.accept(ModItems.BLANK_DISC.get());
                        output.accept(ModItems.TEST_DISC.get());
                        output.accept(ModItems.BAILEY_BUCK.get());
                    }))
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WATERBAILEY_DISCS = CREATIVE_MODE_TAB.register("waterbailey_discs", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.bigburraddons.discs"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(BigBurrAddons.MODID, "common_items"))
            .icon(() -> NEVER_GONNA_GIVE_YOU_UP.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ANGEL_HARE_SIDE_A.get());
                output.accept(OH_DESPAIR.get());
                output.accept(VILLAGER_LULLABY.get());
                output.accept(MEGALOVANIA_1.get());
                output.accept(JAKA_JAAN.get());
                output.accept(JAKA_JAAN_ALTERNATIVE.get());
                output.accept(AMONG_US_REMIX.get());
                output.accept(AMONG_US_LOFI.get());
                output.accept(GANGNAM_STYLE.get());
                output.accept(A_MOTHERS_LOVE.get());
                output.accept(BLAZING_HEART.get());
                output.accept(DISCO_EGGMANS_ANNOUCNEMENT.get());
                output.accept(DONT_MINE_AT_NIGHT.get());
                output.accept(EMBERFIRE.get());
                output.accept(ENDLESS_ENCORE.get());
                output.accept(FURINA_THEME.get());
                output.accept(KYLES_MOM.get());
                output.accept(MAJIN_FOREST_ESCAPE.get());
                output.accept(NEVER_GONNA_GIVE_YOU_UP.get());
                output.accept(RESULTS_AND_CHILL.get());
                output.accept(STILL_ALIVE.get());
                output.accept(WANT_YOU_GONE.get());
                output.accept(WATER_ME_DOWN.get());
                output.accept(VS_SONIC_EXE_RERUN.get());
                output.accept(GOD_DEVOURING_MANIA.get());
                output.accept(INTERSTELLAR_DRIFT.get());
                output.accept(IRRESISTIBLE_FORCE.get());
                output.accept(KITCHEN_GUN.get());
                output.accept(LA_VAGUELETTE.get());
                output.accept(PLEASABT_TIPSINESS.get());
                output.accept(POLUMNIA_OMNIA.get());
                output.accept(TAR_TAR_TAGLIA.get());
                output.accept(ASGORE_RUNS_OVER_DESS.get());
                output.accept(NOD_KRAI.get());
                output.accept(SUMMER_TROPICALA.get());
                output.accept(DIGGY_DIGGY_HOLE.get());
                output.accept(HOUSE_OF_MIRRORS.get());
                output.accept(REVENGE.get());
            }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
