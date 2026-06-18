package net.corior48.bigburraddons.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.corior48.bigburraddons.network.OpenJukeboxLyricsPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    private static final int SEARCH_RADIUS = 8;

    public static final KeyMapping OPEN_JUKEBOX_LYRICS = new KeyMapping(
            "key.hardcore_discs.open_jukebox_lyrics",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_L,
            "key.categories.hardcore_discs"
    );

    private ModKeybinds() {}

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_JUKEBOX_LYRICS);
    }

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        while (OPEN_JUKEBOX_LYRICS.consumeClick()) {
            if (minecraft.player == null || minecraft.level == null || minecraft.screen != null) {
                return;
            }

            PacketDistributor.sendToServer(new OpenJukeboxLyricsPayload());
        }
    }
}
