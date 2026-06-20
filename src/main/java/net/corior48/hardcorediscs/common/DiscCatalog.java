package net.corior48.hardcorediscs.common;

import net.corior48.hardcorediscs.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public final class DiscCatalog {
    private DiscCatalog() {}

    public enum DiscCategory {
        ALL("All"),
        VANILLA("Vanilla"),
        NON_MAIN("Non-Main"),
        PODCASTS("Podcasts"),
        SOUNDCLOUD("Soundcloud"),
        WATERFALL_X_BAILEY("Waterfall x Bailey"),
        ALEX_MOBS("Alex's Mobs"),
        APOTHIC_ENCHANTING("Apothic Enchanting"),
        QUARK("Quark"),
        SUPPLEMENTARIES("Supplementaries"),
        THE_AETHER("The Aether"),
        TWILIGHT_FOREST("Twilight Forest"),
        CREATE("Create");

        private final String displayName;

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

        private final String displayName;

        HardcoreSubCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
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

    public static boolean contains(ResourceLocation id) {
        return getById(id).isPresent();
    }

    public static List<DiscEntry> getByCategory(DiscCategory category) {
        ensureInitialized();

        if (category == DiscCategory.ALL) {
            return ALL;
        }

        List<DiscEntry> result = new ArrayList<>();

        for (DiscEntry entry : ALL) {
            if (entry.category() == category) {
                result.add(entry);
            }
        }

        return List.copyOf(result);
    }

    public static List<DiscCategory> getAvailableCategories() {
        ensureInitialized();

        List<DiscCategory> categories = new ArrayList<>();

        for (DiscCategory category : DiscCategory.values()) {
            if (category == DiscCategory.ALL) {
                categories.add(category);
                continue;
            }

            for (DiscEntry entry : ALL) {
                if (entry.category() == category) {
                    categories.add(category);
                    break;
                }
            }
        }

        return List.copyOf(categories);
    }

    public static boolean matchesCategory(DiscEntry entry, DiscCategory category) {
        if (category == DiscCategory.ALL) {
            return true;
        }

        return entry.category() == category;
    }


    private static List<DiscEntry> buildDiscList() {
        List<DiscEntry> discs = new ArrayList<>();

        // Vanilla
        add(discs, Items.MUSIC_DISC_13, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CAT, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_BLOCKS, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CHIRP, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_FAR, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_MALL, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_MELLOHI, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_STAL, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_STRAD, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_WARD, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_11, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_WAIT, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_OTHERSIDE, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_5, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_PIGSTEP, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_RELIC, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_PRECIPICE, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CREATOR, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, DiscCategory.VANILLA, null, 1);

        //Non-Main
        add(discs, ModItems.SEXY_LUIGI.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.HOPES_AND_DREAMS_REMASTER.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.FUHUHHUK.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.LISTEN_MY_WAY.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.MARIOS_INVINCIBLE_SONG.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.NEVER_GONNA_STOP.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.ONLY_FORCE_FOR_ME.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.RAMBLEY_KINITO_RAP.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.REVENGE_2.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.STORY_OF_UNDERTALE_MOTI.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.SONICEXE_BENDROWNED_RAP.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.STRONGER_DAFT_SPEED.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.SUSIE_YURI_RAP.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);
        add(discs, ModItems.TRIPLE_THE_THREAT_NONGAGOS.get(), DiscCategory.NON_MAIN, HardcoreSubCategory.NON_MAIN, 5);

        //Podcasts
        add(discs, ModItems.BEN_DROWNED.get(), DiscCategory.PODCASTS, HardcoreSubCategory.PODCASTS, 5);

        //Soundcloud
        add(discs, ModItems.BACKBONE_REMIX.get(), DiscCategory.SOUNDCLOUD, HardcoreSubCategory.SOUNDCLOUD, 5);
        add(discs, ModItems.LIKE_FATHER_LIKE_SON.get(), DiscCategory.SOUNDCLOUD, HardcoreSubCategory.SOUNDCLOUD, 5);
        add(discs, ModItems.MEGALOVANIA_DEMITALE.get(), DiscCategory.SOUNDCLOUD, HardcoreSubCategory.SOUNDCLOUD, 5);
        add(discs, ModItems.MEGALOVANIA_ELEVATOR_JAZZ.get(), DiscCategory.SOUNDCLOUD, HardcoreSubCategory.SOUNDCLOUD, 5);

        // Waterfall x Bailey
        add(discs, ModItems.A_MOTHERS_LOVE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.ASGORE_RUNS_OVER_DESS.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.ANGEL_HARE_SIDE_A.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.DIGGY_DIGGY_HOLE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.EGGMAN_ANNOUCNEMENT.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.DONT_MINE_AT_NIGHT.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.GANGNAM_STYLE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.JAKA_JAAN.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.JAKA_JAAN_ALTERNATIVE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.KYLES_MOM.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.NEVER_GONNA_GIVE_YOU_UP.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.REVENGE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.RESULTS_AND_CHILL.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.STILL_ALIVE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.SUMMER_TROPICALA.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.VILLAGER_LULLABY.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);
        add(discs, ModItems.WANT_YOU_GONE.get(), DiscCategory.WATERFALL_X_BAILEY, null, 5);

        // Optional external mod discs
        addOptional(discs, "aether:music_disc_aether_tune", DiscCategory.THE_AETHER, "Noisestorm - Aether Tune",10);
        addOptional(discs, "aether:music_disc_ascending_dawn", DiscCategory.THE_AETHER, "Emile van Krieken - Ascending Dawn",10);
        addOptional(discs, "aether:music_disc_sliders_wrath", DiscCategory.THE_AETHER, "sunsette - Slider's Wrath",10);

        addOptional(discs, "alexsmobs:music_disc_thime", DiscCategory.ALEX_MOBS, "Thime - Enderiophage", 10);
        addOptional(discs, "alexsmobs:music_disc_daze", DiscCategory.ALEX_MOBS, "Daze - Mimic Octopus", 10);

        addOptional(discs, "apothic_enchanting:music_disc_eterna", DiscCategory.APOTHIC_ENCHANTING,"Firel - Eterna", 10);
        addOptional(discs, "apothic_enchanting:music_disc_quanta", DiscCategory.APOTHIC_ENCHANTING,"Firel - Quanta", 10);
        addOptional(discs, "apothic_enchanting:music_disc_arcana", DiscCategory.APOTHIC_ENCHANTING,"Firel - Arcana", 10);

        addOptional(discs, "quark:music_disc_drips", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_ocean", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_rainbow", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_wind", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_fire", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_clock", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_crickets", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_chatter", DiscCategory.QUARK, null, 10);
        addOptional(discs, "quark:music_disc_endermosh", DiscCategory.QUARK, null, 10);

        addOptional(discs, "supplementaries:music_disc_heave_ho", DiscCategory.SUPPLEMENTARIES, "Hlzfss - Heave Ho!", 10);

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
    }

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