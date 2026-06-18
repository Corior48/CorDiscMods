package net.corior48.bigburraddons.client.screen;

import net.corior48.bigburraddons.BigBurrAddons;
import net.corior48.bigburraddons.client.util.DiscLyrics;
import net.corior48.bigburraddons.common.DiscCatalog;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public class LyricsPopupScreen extends Screen {
    private final Screen parent;
    private final DiscCatalog.DiscEntry discEntry;

    private static final int TEXTURE_WIDTH = 512;
    private static final int TEXTURE_HEIGHT = 256;

    private static final int PANEL_WIDTH = 320;
    private static final int PANEL_HEIGHT = 180;

    private static final int TITLE_X_OFFSET = 6;
    private static final int TITLE_Y_OFFSET = 4;

    private static final int CLOSE_X_OFFSET = 310;
    private static final int CLOSE_Y_OFFSET = 3;
    private static final int CLOSE_SIZE = 8;

    private static final int CONTENT_X_OFFSET = 8;
    private static final int CONTENT_Y_OFFSET = 16;
    private static final int CONTENT_WIDTH_OFFSET = 16;
    private static final int CONTENT_HEIGHT_OFFSET = 28;

    private static final int LINE_HEIGHT = 10;

    private int scrollOffsetY = 0;

    private static final ResourceLocation DEFAULT_LYRICS_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    BigBurrAddons.MODID,
                    "textures/gui/lyrics_popup.png"
            );

    public LyricsPopupScreen(Screen parent, DiscCatalog.DiscEntry discEntry) {
        super(Component.literal("Lyrics"));
        this.parent = parent;
        this.discEntry = discEntry;
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(parent);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fill(0, 0, this.width, this.height, 0x66000000);

        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        guiGraphics.blit(
                getLyricsTexture(),
                panelX,
                panelY,
                0,
                0,
                PANEL_WIDTH,
                PANEL_HEIGHT,
                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );

        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discEntry.item());

        if (entry == null) {
            renderDebugFailure(guiGraphics, panelX, panelY);
            return;
        }

        renderLyrics(guiGraphics, entry, panelX, panelY);
    }

    private ResourceLocation getLyricsTexture() {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discEntry.item());

        if (entry != null && entry.texture() != null && !entry.texture().isBlank()) {
            return ResourceLocation.fromNamespaceAndPath(
                    BigBurrAddons.MODID,
                    "textures/gui/lyrical_bgs/" + entry.texture() + ".png"
            );
        }

        return DEFAULT_LYRICS_TEXTURE;
    }

    private void renderDebugFailure(GuiGraphics guiGraphics, int panelX, int panelY) {
        guiGraphics.drawCenteredString(
                this.font,
                "LYRIC JSON FAILED TO LOAD - CHECK CODE DUMBASS",
                panelX + PANEL_WIDTH / 2,
                panelY + 6,
                0xFFFF5555
        );
    }

    private void renderLyrics(
            GuiGraphics guiGraphics,
            DiscLyrics.LyricEntry entry,
            int panelX,
            int panelY
    ) {
        String title = entry.title() == null || entry.title().isBlank()
                ? "Lyrics"
                : entry.title();

        guiGraphics.drawString(
                this.font,
                title,
                panelX + TITLE_X_OFFSET,
                panelY + TITLE_Y_OFFSET,
                0xFFFFFFFF,
                false
        );

        guiGraphics.drawString(
                this.font,
                "X",
                panelX + CLOSE_X_OFFSET,
                panelY + CLOSE_Y_OFFSET,
                0xFFFF5555,
                false
        );

        int contentX = panelX + CONTENT_X_OFFSET;
        int contentY = panelY + CONTENT_Y_OFFSET;
        int contentWidth = PANEL_WIDTH - CONTENT_WIDTH_OFFSET;
        int contentHeight = PANEL_HEIGHT - CONTENT_HEIGHT_OFFSET;

        guiGraphics.enableScissor(
                contentX,
                contentY,
                contentX + contentWidth,
                contentY + contentHeight
        );

        int lineHeight = LINE_HEIGHT;
        int visibleLines = contentHeight / lineHeight;

        for (int i = 0; i < visibleLines; i++) {
            int lineIndex = i + this.scrollOffsetY;

            if (lineIndex >= entry.lines().size()) {
                break;
            }

            DiscLyrics.LyricLine line = entry.lines().get(lineIndex);

            drawColoredLine(
                    guiGraphics,
                    entry,
                    line,
                    contentX,
                    contentY + i * lineHeight
            );
        }

        guiGraphics.disableScissor();
    }

    private Component makeLyricComponent(
            String text,
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            DiscLyrics.LyricSegment segment
    ) {
        String fontName = segment.font();

        if (fontName == null || fontName.isBlank()) {
            fontName = line.font();
        }

        if (fontName == null || fontName.isBlank()) {
            fontName = entry.font();
        }

        MutableComponent component = Component.literal(text);

        if (fontName != null && !fontName.isBlank()) {
            ResourceLocation fontId = ResourceLocation.tryParse(fontName);

            if (fontId != null) {
                component = component.withStyle(style -> style.withFont(fontId));
            }
        }

        return component;
    }

    private int resolveColor(
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            DiscLyrics.LyricSegment segment
    ) {
        String color = segment.color();

        if (color == null || color.isBlank()) {
            color = line.color();
        }

        if (color == null || color.isBlank()) {
            color = entry.defaultColor();
        }

        return parseHexColor(color, 0xFFFFFFFF);
    }

    private int parseHexColor(String color, int fallback) {
        if (color == null || color.isBlank()) {
            return fallback;
        }

        try {
            String clean = color.startsWith("#") ? color.substring(1) : color;

            if (clean.length() == 6) {
                return (int) (0xFF000000L | Long.parseLong(clean, 16));
            }

            if (clean.length() == 8) {
                return (int) Long.parseLong(clean, 16);
            }
        } catch (Exception ignored) {
        }

        return fallback;
    }

    private void drawColoredLine(
            GuiGraphics guiGraphics,
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            int x,
            int y
    ) {
        int currentX = x;

        if (line.segments() == null) {
            return;
        }

        for (DiscLyrics.LyricSegment segment : line.segments()) {
            Component component = makeLyricComponent(segment.text(), entry, line, segment);
            int textColor = resolveColor(entry, line, segment);

            drawBorderedSegment(guiGraphics, component, currentX, y, textColor, 0xFF000000);

            currentX += this.font.width(component);
        }
    }

    private void drawBorderedSegment(
            GuiGraphics guiGraphics,
            Component text,
            int x,
            int y,
            int textColor,
            int borderColor
    ) {
        guiGraphics.drawString(this.font, text, x - 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x + 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y - 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y + 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y, textColor, false);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discEntry.item());

        if (entry == null) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        int contentX = panelX + CONTENT_X_OFFSET;
        int contentY = panelY + CONTENT_Y_OFFSET;
        int contentWidth = PANEL_WIDTH - CONTENT_WIDTH_OFFSET;
        int contentHeight = PANEL_HEIGHT - CONTENT_HEIGHT_OFFSET;

        boolean overTextBox = mouseX >= contentX && mouseX < contentX + contentWidth
                && mouseY >= contentY && mouseY < contentY + contentHeight;

        if (!overTextBox) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        int visibleLines = contentHeight / LINE_HEIGHT;
        int maxScroll = Math.max(0, entry.lines().size() - visibleLines);

        if (scrollY > 0) {
            scrollOffsetY = Math.max(0, scrollOffsetY - 1);
        } else if (scrollY < 0) {
            scrollOffsetY = Math.min(maxScroll, scrollOffsetY + 1);
        }

        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        int closeX = panelX + CLOSE_X_OFFSET;
        int closeY = panelY + CLOSE_Y_OFFSET;

        if (mouseX >= closeX && mouseX < closeX + CLOSE_SIZE
                && mouseY >= closeY && mouseY < closeY + CLOSE_SIZE) {
            onClose();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}