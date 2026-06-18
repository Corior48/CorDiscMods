package net.corior48.bigburraddons.client.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.corior48.bigburraddons.BigBurrAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DiscLyrics {
    private static final Gson GSON = new Gson();
    private static final Map<Item, LyricEntry> LYRICS = new HashMap<>();
    private static boolean loaded = false;

    public static void clearCache() {
        LYRICS.clear();
        loaded = false;
    }

    private DiscLyrics() {}

    public static void loadAll() {
        LYRICS.clear();

        Minecraft minecraft = Minecraft.getInstance();

        Map<ResourceLocation, Resource> resources = minecraft.getResourceManager()
                .listResources("lyrics", id -> id.getPath().endsWith(".json"));

        for (Map.Entry<ResourceLocation, Resource> resourceEntry : resources.entrySet()) {
            ResourceLocation fileId = resourceEntry.getKey();

            try (InputStreamReader reader = new InputStreamReader(
                    resourceEntry.getValue().open(),
                    StandardCharsets.UTF_8
            )) {
                JsonObject root = GSON.fromJson(reader, JsonObject.class);

                String itemIdString = getString(root, "item", "");
                String title = getString(root, "title", "No Lyrics");
                String texture = getString(root, "texture", "");
                String font = getString(root, "font", "");
                String defaultColor = getString(root, "defaultColor", "#FFFFFF");

                List<LyricLine> lines = new ArrayList<>();

                JsonArray rawLines = root.getAsJsonArray("lines");

                if (rawLines != null) {
                    for (JsonElement rawLine : rawLines) {
                        if (rawLine.isJsonPrimitive()) {
                            String text = rawLine.getAsString();

                            lines.add(new LyricLine(
                                    text,
                                    null,
                                    null,
                                    List.of(new LyricSegment(text, null, null))
                            ));
                        } else if (rawLine.isJsonArray()) {
                            List<LyricSegment> segments = new ArrayList<>();

                            for (JsonElement rawSegment : rawLine.getAsJsonArray()) {
                                JsonObject segmentObject = rawSegment.getAsJsonObject();

                                segments.add(new LyricSegment(
                                        getString(segmentObject, "text", ""),
                                        getString(segmentObject, "font", null),
                                        getString(segmentObject, "color", null)
                                ));
                            }

                            lines.add(new LyricLine(
                                    null,
                                    null,
                                    null,
                                    List.copyOf(segments)
                            ));
                        } else if (rawLine.isJsonObject()) {
                            JsonObject lineObject = rawLine.getAsJsonObject();

                            lines.add(new LyricLine(
                                    getString(lineObject, "text", ""),
                                    getString(lineObject, "font", null),
                                    getString(lineObject, "color", null),
                                    List.of(new LyricSegment(
                                            getString(lineObject, "text", ""),
                                            getString(lineObject, "font", null),
                                            getString(lineObject, "color", null)
                                    ))
                            ));
                        }
                    }
                }

                LyricEntry entry = new LyricEntry(
                        itemIdString,
                        title,
                        texture,
                        font,
                        defaultColor,
                        List.copyOf(lines)
                );

                if (entry == null || entry.item() == null || entry.item().isBlank()) {
                    BigBurrAddons.LOGGER.warn("Lyrics file {} has no disc id", fileId);
                    continue;
                }

                ResourceLocation discId = ResourceLocation.tryParse(entry.item());

                if (discId == null) {
                    BigBurrAddons.LOGGER.warn("Lyrics file {} has invalid disc id {}", fileId, entry.item());
                    continue;
                }

                Item item = BuiltInRegistries.ITEM.get(discId);

                if (item == null || item == net.minecraft.world.item.Items.AIR) {
                    BigBurrAddons.LOGGER.warn("Lyrics file {} points to missing item {}", fileId, discId);
                    continue;
                }

                LYRICS.put(item, entry);
            } catch (Exception e) {
                BigBurrAddons.LOGGER.error("Failed to load lyrics file {}", fileId, e);
            }
        }

        loaded = true;
        BigBurrAddons.LOGGER.info("Loaded {} lyric file(s)", LYRICS.size());
    }

    public static boolean hasLyrics(Item item) {
        ensureLoaded();
        return LYRICS.containsKey(item);
    }

    public static LyricEntry getLyrics(Item item) {
        ensureLoaded();
        return LYRICS.get(item);
    }

    private static void ensureLoaded() {
        if (!loaded) {
            loadAll();
        }
    }

    public record LyricEntry(
            String item,
            String title,
            String texture,
            String font,
            String defaultColor,
            List<LyricLine> lines
    ) {}

    public record LyricLine(
            String text,
            String font,
            String color,
            List<LyricSegment> segments
    ) {}

    public record LyricSegment(
            String text,
            String font,
            String color
    ) {}

    private static String getString(JsonObject object, String key, String fallback) {
        if (object == null || !object.has(key) || object.get(key).isJsonNull()) {
            return fallback;
        }

        return object.get(key).getAsString();
    }
}