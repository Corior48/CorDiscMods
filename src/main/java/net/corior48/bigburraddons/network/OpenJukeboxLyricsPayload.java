package net.corior48.bigburraddons.network;

import net.corior48.bigburraddons.BigBurrAddons;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class OpenJukeboxLyricsPayload implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenJukeboxLyricsPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(
                    BigBurrAddons.MODID,
                    "open_jukebox_lyrics"
            ));

    public static final StreamCodec<FriendlyByteBuf, OpenJukeboxLyricsPayload> STREAM_CODEC =
            StreamCodec.unit(new OpenJukeboxLyricsPayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
