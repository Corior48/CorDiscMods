package net.corior48.bigburraddons.client;

import net.corior48.bigburraddons.BigBurrAddons;
import net.corior48.bigburraddons.client.screen.MusicBlockScreen;
import net.corior48.bigburraddons.client.util.DiscLyrics;
import net.corior48.bigburraddons.menu.ModMenuTypes;
import net.corior48.bigburraddons.menu.MusicBlockMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = BigBurrAddons.MODID,
        value = net.neoforged.api.distmarker.Dist.CLIENT
)
public class BigBurrAddonsClient {
    private BigBurrAddonsClient() {}

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.<MusicBlockMenu, MusicBlockScreen>register(
                ModMenuTypes.MUSIC_BLOCK_MENU.get(),
                MusicBlockScreen::new
        );
    }

    @SubscribeEvent
    public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        DiscLyrics.clearCache();
    }
}
