package net.corior48.hardcorediscs.network;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record JukeboxLyricsResultPayload(ResourceLocation discId, boolean foundJukebox) implements CustomPacketPayload {
    public static final Type<JukeboxLyricsResultPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "jukebox_lyrics_result"
            ));

    public static final StreamCodec<FriendlyByteBuf, JukeboxLyricsResultPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ResourceLocation.STREAM_CODEC,
                    JukeboxLyricsResultPayload::discId,
                    ByteBufCodecs.BOOL,
                    JukeboxLyricsResultPayload::foundJukebox,
                    JukeboxLyricsResultPayload::new
            );
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}