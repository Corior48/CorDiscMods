package net.corior48.hardcorediscs.client;

import net.corior48.hardcorediscs.client.screen.LyricsPopupScreen;
import net.corior48.hardcorediscs.client.util.DiscLyrics;
import net.corior48.hardcorediscs.common.DiscCatalog;
import net.corior48.hardcorediscs.network.JukeboxLyricsResultPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class JukeboxLyricsClientHandler {
    private JukeboxLyricsClientHandler() {}

    public static void handle(JukeboxLyricsResultPayload payload) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getById(payload.discId()).orElse(null);

        if (entry == null) {
            minecraft.player.displayClientMessage(
                    Component.literal("No nearby jukebox is playing a known disc!"),
                    true
            );
            return;
        }

        if (!DiscLyrics.hasLyrics(entry.item())) {
            minecraft.player.displayClientMessage(
                    Component.literal("This disc has no lyrics!"),
                    true
            );
            return;
        }

        minecraft.setScreen(new LyricsPopupScreen(null, entry));
    }
}