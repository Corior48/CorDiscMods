package net.corior48.bigburraddons.common;

import net.corior48.bigburraddons.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscCatalog {
    private DiscCatalog() {}

    public enum DiscCategory {
        ALL("ALL"),
        VANILLA("Vanilla"),
        BIGBURRADDONS("BigBurrAddons"),
        GENSHIN_IMPACT("Genshin Impact"),
        THE_AETHER("The Aether"),
        TWILIGHT_FOREST("Twilight Forest"),
        CREATE("Create");

        public final String displayName;

        DiscCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
    public enum HardcoreSubCategory {
        ALL("All Hardcore"),
        NON_MAIN("Non-Main"),
        PODCASTS("Podcasts"),
        SOUNDCLOUD("Soundcloud");

        public final String displayName;
        HardcoreSubCategory(String displayName) {this.displayName = displayName;}

        public String getDisplayName() {return displayName;}
    }
    public record DiscEntry(
            ResourceLocation id,
            Item item,
            DiscCategory category,
            String displayName,
            int xpCost
    ) {}

    private static List<DiscEntry> ALL = List.of();
    private static boolean initialized = false;

    private static void ensureInitialized() {
        if (!initialized) {
            rebuildCatalog();
        }
    }

    public static void rebuildCatalog() {
        ALL = buildDiscList();
        initialized = true;
    }

    public static List<DiscEntry> getAll() {
        ensureInitialized();
        return ALL;
    }

    public static int size() {
        ensureInitialized();
        return ALL.size();
    }

    public static DiscEntry getEntry(int index) {
        ensureInitialized();
        return ALL.get(index);
    }

    public static Item getItem(int index) {
        return getEntry(index).item();
    }

    public static int getXpCost(int index) {
        return getEntry(index).xpCost();
    }

    public static Optional<DiscEntry> getById(ResourceLocation id) {
        ensureInitialized();

        for (DiscEntry entry : ALL) {
            if (entry.id().equals(id)) {
                return Optional.of(entry);
            }
        }

        return Optional.empty();
    }

    public static List<DiscCategory> getAvailableCategories() {
        ensureInitialized();
        List<DiscCategory> categories = new ArrayList<>();
        categories.add(DiscCategory.ALL);

        for (DiscCategory category : DiscCategory.values()) {
            if (category == DiscCategory.ALL) {
                continue;
            }

            boolean hasAny = false;
            for (DiscEntry entry : ALL) {
                if (entry.category() == category) {
                    hasAny = true;
                    break;
                }
            }

            if (hasAny) {
                categories.add(category);
            }
        }

        return List.copyOf(categories);
    }

    private static List<DiscEntry> buildDiscList() {
        List<DiscEntry> discs = new ArrayList<>();

        //Vanila
        add(discs, Items.MUSIC_DISC_13, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CAT, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_BLOCKS, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_CHIRP, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_FAR, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_MALL, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_MELLOHI, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_STAL, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_STRAD, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_WARD, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_11, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_WAIT, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_OTHERSIDE, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_5, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_PIGSTEP, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_RELIC, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_PRECIPICE, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_CREATOR, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, DiscCategory.VANILLA, null,1);
        // Waterfall X Bailey
        add(discs, ModItems.A_MOTHERS_LOVE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.ASGORE_RUNS_OVER_DESS.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.AMONG_US_LOFI.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.AMONG_US_REMIX.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.ANGEL_HARE_SIDE_A.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.DIGGY_DIGGY_HOLE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.DISCO_EGGMANS_ANNOUCNEMENT.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.DONT_MINE_AT_NIGHT.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.ENDLESS_ENCORE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.GANGNAM_STYLE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.JAKA_JAAN.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.JAKA_JAAN_ALTERNATIVE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.KYLES_MOM.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.MEGALOVANIA_1.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.MAJIN_FOREST_ESCAPE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.KITCHEN_GUN.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.NEVER_GONNA_GIVE_YOU_UP.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.OH_DESPAIR.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.REVENGE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.RESULTS_AND_CHILL.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.STILL_ALIVE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.SUMMER_TROPICALA.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.VILLAGER_LULLABY.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.VS_SONIC_EXE_RERUN.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.WANT_YOU_GONE.get(), DiscCategory.BIGBURRADDONS, null,5);
        add(discs, ModItems.WATER_ME_DOWN.get(), DiscCategory.BIGBURRADDONS, null,5);
        //Genshin OST
        add(discs, ModItems.BLAZING_HEART.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.EMBERFIRE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.NOD_KRAI.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.FURINA_THEME.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.GOD_DEVOURING_MANIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.INTERSTELLAR_DRIFT.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.IRRESISTIBLE_FORCE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.LA_VAGUELETTE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.PLEASABT_TIPSINESS.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.POLUMNIA_OMNIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.TAR_TAR_TAGLIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        // Optional external mod discs
        addOptional(discs, "aether:music_disc_aether_tune", DiscCategory.THE_AETHER, "Noisestorm - Aether Tune",10);
        addOptional(discs, "aether:music_disc_ascending_dawn", DiscCategory.THE_AETHER, "Emile van Krieken - Ascending Dawn",10);
        addOptional(discs, "aether:music_disc_sliders_wrath", DiscCategory.THE_AETHER, "sunsette - Slider's Wrath",10);

        addOptional(discs, "twilightforest:music_disc_thread", DiscCategory.TWILIGHT_FOREST, "MrCompost - Thread",10);
        addOptional(discs, "twilightforest:music_disc_findings", DiscCategory.TWILIGHT_FOREST, "MrCompost - Findings",10);
        addOptional(discs, "twilightforest:music_disc_radiance", DiscCategory.TWILIGHT_FOREST, "Rotch Gwylt - Radiance",10);
        addOptional(discs, "twilightforest:music_disc_steps", DiscCategory.TWILIGHT_FOREST, "Rotch Gwylt - Steps",10);
        addOptional(discs, "twilightforest:music_disc_motion", DiscCategory.TWILIGHT_FOREST, "MrCompost - Motion",10);
        addOptional(discs, "twilightforest:music_disc_wayfarer", DiscCategory.TWILIGHT_FOREST, "MrCompost - Wayfarer",10);
        addOptional(discs, "twilightforest:music_disc_home", DiscCategory.TWILIGHT_FOREST, "MrCompost - Home",10);
        addOptional(discs, "twilightforest:music_disc_maker", DiscCategory.TWILIGHT_FOREST, "MrCompost - Maker",10);
        addOptional(discs, "twilightforest:music_disc_superstitious", DiscCategory.TWILIGHT_FOREST, "Rotch Gwylt - Superstitious",10);

        addOptional(discs, "create_connected:music_disc_elevator", DiscCategory.CREATE, null, 10);
        addOptional(discs, "create_connected:music_disc_interlude", DiscCategory.CREATE, null, 10);

        return List.copyOf(discs);
    };


    private static void add(
            List<DiscEntry> discs,
            Item item,
            DiscCategory category,
            HardcoreSubCategory subCategory,
            int xpCost
    ) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);

        if (id == null || item == Items.AIR) {
            return;
        }

        discs.add(new DiscEntry(id, item, category, null, xpCost));
    }

    private static void addOptional(
            List<DiscEntry> discs,
            String itemId,
            DiscCategory category,
            String displayName,
            int xpCost
    ) {
        ResourceLocation id = ResourceLocation.tryParse(itemId);

        if (id == null) {
            return;
        }

        Item item = BuiltInRegistries.ITEM.get(id);

        if (item == Items.AIR) {
            return;
        }

        discs.add(new DiscEntry(id, item, category, displayName, xpCost));
    }
}
