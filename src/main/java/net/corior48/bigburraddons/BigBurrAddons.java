package net.corior48.bigburraddons;

import com.mojang.logging.LogUtils;
import net.corior48.bigburraddons.block.ModBlocks;
import net.corior48.bigburraddons.block.entity.ModBlockEntities;
import net.corior48.bigburraddons.item.ModCreativeModeTabs;
import net.corior48.bigburraddons.item.ModItems;
import net.corior48.bigburraddons.menu.ModMenuTypes;
import net.corior48.bigburraddons.sound.ModSounds;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BigBurrAddons.MODID)
public class BigBurrAddons {
    public static final String MODID = "bigburraddons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BigBurrAddons(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        //Class Registries
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }
}
