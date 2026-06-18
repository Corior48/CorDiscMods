package net.corior48.hardcorediscs.client;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.corior48.hardcorediscs.client.screen.MusicBlockScreen;
import net.corior48.hardcorediscs.client.util.DiscLyrics;
import net.corior48.hardcorediscs.menu.ModMenuTypes;
import net.corior48.hardcorediscs.menu.MusicBlockMenu;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = HardcoreDiscs.MODID,
        value = net.neoforged.api.distmarker.Dist.CLIENT
)
public final class HardcoreDiscsClient {
    private HardcoreDiscsClient() {}

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