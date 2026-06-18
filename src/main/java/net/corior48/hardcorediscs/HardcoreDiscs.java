package net.corior48.hardcorediscs;

import com.mojang.logging.LogUtils;
import net.corior48.hardcorediscs.block.ModBlocks;
import net.corior48.hardcorediscs.block.entity.ModBlockEntities;
import net.corior48.hardcorediscs.item.ModCreativeModeTabs;
import net.corior48.hardcorediscs.item.ModItems;
import net.corior48.hardcorediscs.menu.ModMenuTypes;
import net.corior48.hardcorediscs.sound.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(HardcoreDiscs.MODID)
public class HardcoreDiscs {
    public static final String MODID = "hardcore_discs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    // ModEventBus shit
    public HardcoreDiscs(IEventBus modEventBus, ModContainer modContainer) {
        //modloading :D
        modEventBus.addListener(this::commonSetup);

        //Classes
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
