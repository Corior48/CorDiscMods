package net.corior48.hardcorediscs.block.entity;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.corior48.hardcorediscs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HardcoreDiscs.MODID);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MusicBlockEntity>> MUSIC_BE =
            BLOCK_ENTITIES.register("music_be",
                    () -> BlockEntityType.Builder.of(
                            MusicBlockEntity::new,
                            ModBlocks.MUSIC_BLOCK.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
