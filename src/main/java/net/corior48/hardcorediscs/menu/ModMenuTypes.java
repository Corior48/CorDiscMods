package net.corior48.hardcorediscs.menu;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, HardcoreDiscs.MODID);

    public static final Supplier<MenuType<MusicBlockMenu>> MUSIC_BLOCK_MENU =
            MENU_TYPES.register("music_block_menu",
                    () -> IMenuTypeExtension.create(MusicBlockMenu::new));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}