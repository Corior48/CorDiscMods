package net.corior48.bigburraddons.network;

import net.corior48.bigburraddons.BigBurrAddons;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = BigBurrAddons.MODID)
public class ModNetworking {
    private static final int SEARCH_RADIUS = 8;
    private static final ResourceLocation AIR_ID =
            ResourceLocation.fromNamespaceAndPath("minecraft", "air");

    private ModNetworking() {}

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(BigBurrAddons.MODID);

        registrar.playToServer(
                OpenJukeboxLyricsPayload.TYPE,
                OpenJukeboxLyricsPayload.STREAM_CODEC,
                ModNetworking::handleOpenJukeboxLyrics
        );

        registrar.playToClient(
                JukeboxLyricsResultPayload.TYPE,
                JukeboxLyricsResultPayload.STREAM_CODEC,
                ModNetworking::handleJukeboxLyricsResult
        );
    }

    private static void handleOpenJukeboxLyrics(OpenJukeboxLyricsPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();

            if (player.level().isClientSide) {
                return;
            }

            ResourceLocation discId = findNearbyJukeboxDisc(player);
            boolean foundJukebox = !discId.equals(AIR_ID);

            PacketDistributor.sendToPlayer(
                    (net.minecraft.server.level.ServerPlayer) player,
                    new JukeboxLyricsResultPayload(discId, foundJukebox)
            );
        });
    }

    private static ResourceLocation findNearbyJukeboxDisc(net.minecraft.world.entity.player.Player player) {
        BlockPos playerPos = player.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-SEARCH_RADIUS, -SEARCH_RADIUS, -SEARCH_RADIUS),
                playerPos.offset(SEARCH_RADIUS, SEARCH_RADIUS, SEARCH_RADIUS)
        )) {
            BlockEntity blockEntity = player.level().getBlockEntity(pos);

            if (blockEntity instanceof JukeboxBlockEntity jukebox) {
                ItemStack stack = jukebox.getTheItem();

                if (!stack.isEmpty()) {
                    return BuiltInRegistries.ITEM.getKey(stack.getItem());
                }
            }
        }

        return AIR_ID;
    }

    private static void handleJukeboxLyricsResult(
            JukeboxLyricsResultPayload payload,
            IPayloadContext context
    ) {
        context.enqueueWork(() -> {
            if (net.neoforged.fml.loading.FMLEnvironment.dist
                    == net.neoforged.api.distmarker.Dist.CLIENT) {
                handleClientPayloadReflectively(payload);
            }
        });
    }

    private static void handleClientPayloadReflectively(JukeboxLyricsResultPayload payload) {
        try {
            Class<?> handlerClass = Class.forName(
                    "net.corior48.BigBurrAddons.client.JukeboxLyricsClientHandler"
            );

            handlerClass
                    .getMethod("handle", JukeboxLyricsResultPayload.class)
                    .invoke(null, payload);
        } catch (Exception e) {
            BigBurrAddons.LOGGER.error("Failed to handle jukebox lyrics client payload", e);
        }
    }
}
