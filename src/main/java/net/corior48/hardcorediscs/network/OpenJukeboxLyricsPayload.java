package net.corior48.hardcorediscs.network;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenJukeboxLyricsPayload() implements CustomPacketPayload {
    public static final Type<OpenJukeboxLyricsPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "open_jukebox_lyrics"
            ));

    public static final StreamCodec<FriendlyByteBuf, OpenJukeboxLyricsPayload> STREAM_CODEC =
            StreamCodec.unit(new OpenJukeboxLyricsPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}