package net.corior48.hardcorediscs.client.screen;

import net.corior48.hardcorediscs.HardcoreDiscs;
import net.corior48.hardcorediscs.client.util.DiscLyrics;
import net.corior48.hardcorediscs.common.DiscCatalog;
import net.corior48.hardcorediscs.menu.MusicBlockMenu;
import net.corior48.hardcorediscs.sound.ModSounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicBlockScreen extends AbstractContainerScreen<MusicBlockMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/music_block.png"
            );

    private static final ResourceLocation LYRICS_BUTTON_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/lyrics_button.png"
            );

    private static final ResourceLocation LYRICS_BUTTON_HOVER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/lyrics_button_highlighted.png"
            );

    private static final ResourceLocation XP_COST_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/xp_cost.png"
            );

    private static final ResourceLocation CATEGORY_DRAWER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/drawer_opened.png"
            );

    private static final ResourceLocation CATEGORY_DRAWER_CLOSED_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/drawer_closed.png"
            );

    private static final ResourceLocation CATEGORY_ICON_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/name-id.png"
            );

    private static final ResourceLocation CATEGORY_ICON_SELECTED_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/name-id-selected.png"
            );

    public static final ResourceLocation PAGE_TAB_BASE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/base-tab.png"
            );

    public static final ResourceLocation PAGE_TAB_NEXT_BASE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/arrows/next_base.png"
            );

    public static final ResourceLocation PAGE_TAB_NEXT_HIGHLIGHT =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/arrows/next_highlighted.png"
            );

    public static final ResourceLocation PAGE_TAB_PREV_BASE =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/arrows/previous_base.png"
            );

    public static final ResourceLocation PAGE_TAB_PREV_HIGHLIGHT =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/arrows/previous_highlighted.png"
            );

    public static final ResourceLocation TOTAL_PAGE_COUNT =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/numbers/total.png"
            );

    public static final ResourceLocation CAT_PAGE_1 =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/numbers/page1.png"
            );

    public static final ResourceLocation CAT_PAGE_2 =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "textures/gui/cat-tabs/page-button/numbers/page2.png"
            );

    private static final String CATEGORY_BASE_PATH =
            "textures/gui/cat-tabs/base-buttons/";

    private static final String CATEGORY_HIGHLIGHT_PATH =
            "textures/gui/cat-tabs/highlighted-buttons/";

    private static final String CATEGORY_SELECTED_PATH =
            "textures/gui/cat-tabs/selected-buttons/";

    private static final ResourceLocation LYRICS_BUTTON_FONT =
            ResourceLocation.fromNamespaceAndPath(
                    HardcoreDiscs.MODID,
                    "mercy"
            );

    private static boolean skipNextOpenSound = false;
    private boolean wasCategoryHovered = false;
    private boolean wasPageArrowHovered = false;
    private boolean returnedFromLyrics = false;
    private final boolean playOpenSound;

    private static final int GUI_WIDTH = 220;
    private static final int GUI_HEIGHT = 200;

    private static final int TITLE_X = 84;
    private static final int TITLE_Y = 5;

    private static final int INV_LABEL_X = 31;
    private static final int INV_LABEL_Y = 106;

    private static final int XP_X = 97;
    private static final int XP_Y = 53;

    private static final int LEFT_ARROW_X = 31;
    private static final int LEFT_ARROW_Y = 78;

    private static final int RIGHT_ARROW_X = 175;
    private static final int RIGHT_ARROW_Y = 78;

    private static final int SEARCH_X = 37;
    private static final int SEARCH_Y = 22;
    private static final int SEARCH_WIDTH = 147;
    private static final int SEARCH_HEIGHT = 16;

    private static final int SONG_BOX_X = 66;
    private static final int SONG_BOX_Y = 77;
    private static final int SONG_BOX_WIDTH = 102;
    private static final int SONG_BOX_HEIGHT = 18;
    private static final int SONG_TEXT_Y_OFFSET = 5;

    private static final int SONG_SCROLL_PAUSE_TICKS = 35;
    private static final int SONG_SCROLL_SPEED_DIVIDER = 4;

    private static final int ARROW_SIZE = 15;
    private int songScrollTick = 0;

    private static final int LYRICS_BUTTON_X = 141;
    private static final int LYRICS_BUTTON_Y = 199;
    private static final int LYRICS_BUTTON_WIDTH = 75;
    private static final int LYRICS_BUTTON_HEIGHT = 20;

    private static final int LYRICS_TEXT_X_OFFSET = 20;
    private static final int LYRICS_TEXT_Y_OFFSET = 9;
    private static final int LYRICS_TEXT_SPACING = -2;
    private static final int LYRICS_SLIDE_DISTANCE = 22;

    private static final int LYRICS_BUTTON_HEART_SIZE = 9;
    private boolean lyricsButtonHovered = false;
    private boolean wasLyricsHovered = false;

    private static final int XP_BOX_CENTER_X = 113;
    private static final int XP_BOX_Y = 52;
    private static final int XP_BOX_FULL_WIDTH = 48;
    private static final int XP_BOX_HEIGHT = 11;

    private int categoryPage = 0;
    private static final int MAX_CATEGORY_PAGE = 1;

    private static final int PAGE_TAB_WIDTH = 97;
    private static final int PAGE_TAB_HEIGHT = 14;

    private static final int PAGE_ARROW_WIDTH = 8;
    private static final int PAGE_ARROW_HEIGHT = 8;

    private static final int PAGE_PREV_X = 4;
    private static final int PAGE_ARROW_Y = 2;
    private static final int PAGE_NEXT_X = 85;

    private static final int CATEGORIES_PER_PAGE = 10;

    private static final int PAGE_TAB_X = 4;
    private static final int PAGE_TAB_Y = 183;

    private static final int PAGE_NUMBER_Y = 1;
    private static final int PAGE_CURRENT_NUMBER_X = 40;
    private static final int PAGE_TOTAL_NUMBER_X = 47;

    private static final int PAGE_CURRENT_NUMBER_WIDTH = 6;
    private static final int PAGE_CURRENT_NUMBER_HEIGHT = 9;
    private static final int PAGE_TOTAL_NUMBER_WIDTH = 13;
    private static final int PAGE_TOTAL_NUMBER_HEIGHT = 10;

    private static final int CATEGORY_DRAWER_WIDTH = 102;
    private static final int CATEGORY_DRAWER_HEIGHT = 185;

    private static final int CATEGORY_DRAWER_TEXTURE_WIDTH = 256;
    private static final int CATEGORY_DRAWER_TEXTURE_HEIGHT = 256;

    private static final int CATEGORY_DRAWER_CLOSED_WIDTH = 8;
    private static final int CATEGORY_DRAWER_CLOSED_HEIGHT = 185;

    private static final int CATEGORY_DRAWER_Y = 8;
    private static final int CATEGORY_DRAWER_CLOSED_X = -7;
    private static final int CATEGORY_DRAWER_OPEN_X = -101;

    private static final int CATEGORY_TEXT_X_OFFSET = 16;
    private static final int CATEGORY_TEXT_Y_START = 24;
    private static final int CATEGORY_TEXT_GAP = 16;

    private static final int CATEGORY_BUTTON_WIDTH = 80;
    private static final int CATEGORY_BUTTON_HEIGHT = 10;

    private static final int CATEGORY_BUTTON_X_OFFSET = 20;
    private static final int CATEGORY_BUTTON_Y_START = 24;
    private static final int CATEGORY_BUTTON_GAP = 16;


    private static final int CATEGORY_ICON_SIZE = 8;
    private static final int CATEGORY_ICON_X_OFFSET = 7;
    private static final int CATEGORY_ICON_Y_OFFSET = -16;

    private static final int SOUL_TEXTURE_SIZE = 9;
    private boolean categoryOpen = false;

    private float xpSlide = 0.0F;
    private float lyricsSlide = 0.0F;
    private static final float SLIDE_SPEED = 0.12F;

    private int selectedRecord = 0;

    private DiscCatalog.DiscCategory activeCategory = DiscCatalog.DiscCategory.ALL;
    private EditBox searchBox;
    private List<DiscCatalog.DiscEntry> visibleDiscs = new ArrayList<>();
    private int selectedVisibleIndex = 0;

    public static void skipNextOpenSound() {
        skipNextOpenSound = true;
    }

    private int getDisplayedRecord() {
        if (DiscCatalog.size() <= 0) {
            return 0;
        }

        boolean searching = searchBox != null && !searchBox.getValue().trim().isEmpty();

        if (searching && visibleDiscs != null && !visibleDiscs.isEmpty()) {
            DiscCatalog.DiscEntry visibleEntry = visibleDiscs.get(
                    Math.max(0, Math.min(selectedVisibleIndex, visibleDiscs.size() - 1))
            );

            for (int i = 0; i < DiscCatalog.size(); i++) {
                if (DiscCatalog.getEntry(i).id().equals(visibleEntry.id())) {
                    return i;
                }
            }
        }

        int synced = this.menu.getSelectedRecord();
        return Math.max(0, Math.min(synced, DiscCatalog.size() - 1));
    }

    public MusicBlockScreen(MusicBlockMenu menu, Inventory playerInventory, Component title) {
        this(menu, playerInventory, title, true);
    }

    public MusicBlockScreen(MusicBlockMenu menu, Inventory playerInventory, Component title, boolean playOpenSound) {
        super(menu, playerInventory, title);
        this.playOpenSound = playOpenSound;

        this.imageWidth = GUI_WIDTH;
        this.imageHeight = GUI_HEIGHT;
        this.inventoryLabelX = INV_LABEL_X;
        this.inventoryLabelY = INV_LABEL_Y;
    }

    @Override
    protected void init() {
        super.init();

        if (DiscCatalog.size() > 0 && this.menu.getBlockEntity() != null) {
            selectedRecord = this.menu.getBlockEntity().getSelectedRecord();
            selectedRecord = Math.max(0, Math.min(selectedRecord, DiscCatalog.size() - 1));
        }

        this.searchBox = new EditBox(
                this.font,
                this.leftPos + SEARCH_X,
                this.topPos + SEARCH_Y,
                SEARCH_WIDTH,
                SEARCH_HEIGHT,
                Component.literal("Search")
        );

        this.searchBox.setBordered(false);
        this.searchBox.setMaxLength(64);
        this.searchBox.setTextColor(0xFFFFFF);
        this.searchBox.setResponder(text -> {
            refreshVisibleDiscs();
            selectedVisibleIndex = 0;
            syncVisibleIndexToMenuSelection();
        });

        this.addRenderableWidget(this.searchBox);

        refreshVisibleDiscs();
        selectedRecord = getDisplayedRecord();
        syncVisibleIndexToRecord(selectedRecord);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(
                TEXTURE,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight
        );
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, "Disc Creator", TITLE_X, TITLE_Y, 0x3F3F3F, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, INV_LABEL_X, INV_LABEL_Y, 0x3F3F3F, false);

        renderSelectedDiscName(guiGraphics);
    }

    private boolean hasBlankDiscInserted() {
        return this.menu.getSlot(MusicBlockMenu.INPUT_SLOT).hasItem();
    }

    private void renderSelectedDiscName(GuiGraphics guiGraphics) {
        if (visibleDiscs.isEmpty()) {
            drawCenteredSongText(guiGraphics, "No Results");
            return;
        }

        if (!hasBlankDiscInserted()) {
            drawCenteredSongText(guiGraphics, "Insert Blank Disc");
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());

        String name = getDiscDisplayText(entry);

        int textWidth = this.font.width(name);

        guiGraphics.enableScissor(
                this.leftPos + SONG_BOX_X,
                this.topPos + SONG_BOX_Y,
                this.leftPos + SONG_BOX_X + SONG_BOX_WIDTH,
                this.topPos + SONG_BOX_Y + SONG_BOX_HEIGHT
        );

        if (textWidth <= SONG_BOX_WIDTH) {
            drawCenteredSongText(guiGraphics, name);
        } else {
            String marqueeText = name + "  •  ";
            int marqueeWidth = this.font.width(marqueeText);

            int offset = (songScrollTick / SONG_SCROLL_SPEED_DIVIDER) % marqueeWidth;

            int drawX1 = SONG_BOX_X - offset;
            int drawX2 = drawX1 + marqueeWidth;

            guiGraphics.drawString(
                    this.font,
                    marqueeText,
                    drawX1,
                    SONG_BOX_Y + SONG_TEXT_Y_OFFSET,
                    0xFFFFFF,
                    false
            );

            guiGraphics.drawString(
                    this.font,
                    marqueeText,
                    drawX2,
                    SONG_BOX_Y + SONG_TEXT_Y_OFFSET,
                    0xFFFFFF,
                    false
            );
        }

        guiGraphics.disableScissor();
    }

    private void drawCenteredSongText(GuiGraphics guiGraphics, String text) {
        int centeredX = SONG_BOX_X + (SONG_BOX_WIDTH - this.font.width(text)) / 2;

        guiGraphics.drawString(
                this.font,
                text,
                centeredX,
                SONG_BOX_Y + SONG_TEXT_Y_OFFSET,
                0xFFFFFF,
                false
        );
    }

    private void renderXpCost(GuiGraphics guiGraphics) {
        if (xpSlide <= 0.0F || DiscCatalog.size() <= 0 || !hasBlankDiscInserted()) {
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());
        int cost = entry.xpCost();
        String text = cost + "L";

        boolean canAfford = this.minecraft != null
                && this.minecraft.player != null
                && (this.minecraft.player.isCreative()
                || this.minecraft.player.experienceLevel >= cost);

        int xpColor = canAfford ? 0x55FF55 : 0xFF5555;

        int currentWidth = Math.max(1, (int) (XP_BOX_FULL_WIDTH * xpSlide));
        int localBoxX = XP_BOX_CENTER_X - currentWidth / 2;

        int screenBoxX = this.leftPos + localBoxX;
        int screenBoxY = this.topPos + XP_BOX_Y;

        guiGraphics.blit(
                XP_COST_TEXTURE,
                screenBoxX,
                screenBoxY,
                (XP_BOX_FULL_WIDTH - currentWidth) / 2,
                0,
                currentWidth,
                XP_BOX_HEIGHT,
                XP_BOX_FULL_WIDTH,
                XP_BOX_HEIGHT
        );

        if (xpSlide >= 0.75F) {
            int textX = this.leftPos + XP_BOX_CENTER_X - this.font.width(text) / 2;
            int textY = this.topPos + XP_BOX_Y + 2;

            guiGraphics.drawString(
                    this.font,
                    text,
                    textX,
                    textY,
                    xpColor,
                    false
            );
        }
    }

    private void drawTightText(
            GuiGraphics guiGraphics,
            String text,
            int x,
            int y,
            int color
    ) {
        int currentX = x;

        for (char c : text.toCharArray()) {
            Component letter = Component.literal(String.valueOf(c))
                    .withStyle(style -> style.withFont(LYRICS_BUTTON_FONT));

            guiGraphics.drawString(
                    this.font,
                    letter,
                    currentX,
                    y,
                    color,
                    false
            );

            currentX += this.font.width(letter) + LYRICS_TEXT_SPACING;
        }
    }

    private void renderLyricsButton(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (lyricsSlide <= 0.0F || !hasBlankDiscInserted() || DiscCatalog.size() <= 0) {
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());
        boolean hasLyrics = DiscLyrics.hasLyrics(entry.item());

        if (!hasLyrics && lyricsSlide <= 0.0F) {
            return;
        }

        int animatedLocalY =
                LYRICS_BUTTON_Y + (int)((1.0F - lyricsSlide) * LYRICS_SLIDE_DISTANCE);

        boolean hovered = isInside(
                mouseX - this.leftPos,
                mouseY - this.topPos,
                LYRICS_BUTTON_X,
                animatedLocalY,
                LYRICS_BUTTON_WIDTH,
                LYRICS_BUTTON_HEIGHT
        );

        lyricsButtonHovered = hovered;

        if (hovered && !wasLyricsHovered) {
            playUiSound(ModSounds.BUTTON_HIGHLIGHT.get(), 1.0F, 1.0F);
        }

        wasLyricsHovered = hovered;

        ResourceLocation texture = hovered
                ? LYRICS_BUTTON_HOVER_TEXTURE
                : LYRICS_BUTTON_TEXTURE;

        int screenX = this.leftPos + LYRICS_BUTTON_X;
        int screenY = this.topPos + animatedLocalY;

        guiGraphics.blit(
                texture,
                screenX,
                screenY,
                0,
                0,
                LYRICS_BUTTON_WIDTH,
                LYRICS_BUTTON_HEIGHT,
                LYRICS_BUTTON_WIDTH,
                LYRICS_BUTTON_HEIGHT
        );

        if (hovered) {
            guiGraphics.blit(
                    CATEGORY_ICON_TEXTURE,
                    screenX + 6,
                    screenY + 6,
                    0,
                    0,
                    LYRICS_BUTTON_HEART_SIZE,
                    LYRICS_BUTTON_HEART_SIZE,
                    SOUL_TEXTURE_SIZE,
                    SOUL_TEXTURE_SIZE
            );
        }

        int textColor = hovered ? 0xFFFF00 : 0xFF8400;

        drawTightText(
                guiGraphics,
                "LYRICS",
                screenX + LYRICS_TEXT_X_OFFSET,
                screenY + LYRICS_TEXT_Y_OFFSET,
                textColor
        );
    }

    private void renderArrowHover(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int localX = mouseX - this.leftPos;
        int localY = mouseY - this.topPos;

        if (isInside(localX, localY, LEFT_ARROW_X, LEFT_ARROW_Y, ARROW_SIZE, ARROW_SIZE)) {
            guiGraphics.fill(
                    this.leftPos + LEFT_ARROW_X,
                    this.topPos + LEFT_ARROW_Y,
                    this.leftPos + LEFT_ARROW_X + ARROW_SIZE,
                    this.topPos + LEFT_ARROW_Y + ARROW_SIZE,
                    0x55FFFFFF
            );
        }

        if (isInside(localX, localY, RIGHT_ARROW_X, RIGHT_ARROW_Y, ARROW_SIZE, ARROW_SIZE)) {
            guiGraphics.fill(
                    this.leftPos + RIGHT_ARROW_X,
                    this.topPos + RIGHT_ARROW_Y,
                    this.leftPos + RIGHT_ARROW_X + ARROW_SIZE,
                    this.topPos + RIGHT_ARROW_Y + ARROW_SIZE,
                    0x55FFFFFF
            );
        }
    }

    private void renderSongNameTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (!hasBlankDiscInserted()) {
            return;
        }

        int localX = mouseX - this.leftPos;
        int localY = mouseY - this.topPos;

        if (!isInside(localX, localY, SONG_BOX_X, SONG_BOX_Y, SONG_BOX_WIDTH, SONG_BOX_HEIGHT)) {
            return;
        }

        if (DiscCatalog.size() <= 0) {
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());

        String desc = getDiscDisplayText(entry);

        guiGraphics.renderTooltip(this.font, Component.literal(desc), mouseX, mouseY);
    }

    private String getDiscDisplayText(DiscCatalog.DiscEntry entry) {
        String descKey = entry.item().getDescriptionId() + ".desc";
        String translated = Component.translatable(descKey).getString();

        if (entry.displayName() != null) {
            return entry.displayName();
        }

        if (translated.equals(descKey)) {
            return new ItemStack(entry.item()).getHoverName().getString();
        }

        return translated;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int localX = (int) mouseX - this.leftPos;
        int localY = (int) mouseY - this.topPos;

        if (isClickingOutputSlot(localX, localY) && !canAffordCurrentDisc()) {
            playUiSound(ModSounds.ERROR.get(), 1.0F, 1.0F);
            return true;
        }

        if (isClickingOutputSlot(localX, localY) && canAffordCurrentDisc()) {
            playUiSound(ModSounds.DISC_CREATION.get(), 1.0F, 1.0F);
        }

        if (handleLyricsButtonClick(localX, localY)) {
            return true;
        }

        if (handleCategoryDrawerClick(localX, localY)) {
            return true;
        }

        if (isInside(localX, localY, LEFT_ARROW_X, LEFT_ARROW_Y, ARROW_SIZE, ARROW_SIZE)) {
            previousDisc();
            return true;
        }

        if (isInside(localX, localY, RIGHT_ARROW_X, RIGHT_ARROW_Y, ARROW_SIZE, ARROW_SIZE)) {
            nextDisc();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.searchBox != null && this.searchBox.isFocused()) {
            // ESC
            if (keyCode == 256) {
                this.searchBox.setFocused(false);
                return true;
            }

            // Inventory key, usually E
            if (this.minecraft != null
                    && this.minecraft.options.keyInventory.matches(keyCode, scanCode)) {
                return true;
            }

            if (this.searchBox.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }

            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.searchBox != null && this.searchBox.isFocused()) {
            return this.searchBox.charTyped(codePoint, modifiers);
        }

        return super.charTyped(codePoint, modifiers);
    }

    private void previousDisc() {
        if (!hasBlankDiscInserted() || visibleDiscs.isEmpty()) {
            return;
        }

        playUiSound(ModSounds.CATEGORY_OPEN.get(),  1.0F, 1.0F);

        syncVisibleIndexToRecord(getDisplayedRecord());

        selectedVisibleIndex--;

        if (selectedVisibleIndex < 0) {
            selectedVisibleIndex = visibleDiscs.size() - 1;
        }

        sendSelectedRecordForVisibleIndex();
    }

    private void nextDisc() {
        if (!hasBlankDiscInserted() || visibleDiscs.isEmpty()) {
            return;
        }

        playUiSound(ModSounds.CATEGORY_OPEN.get(),  1.0F, 1.0F);

        syncVisibleIndexToRecord(getDisplayedRecord());

        selectedVisibleIndex++;

        if (selectedVisibleIndex >= visibleDiscs.size()) {
            selectedVisibleIndex = 0;
        }

        sendSelectedRecordForVisibleIndex();
    }

    private void sendSelectedRecord() {
        if (this.minecraft != null && this.minecraft.gameMode != null) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, selectedRecord);
        }
    }

    private boolean isInside(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private void renderCategoryNames(
            GuiGraphics guiGraphics,
            int screenX,
            int screenY,
            int mouseX,
            int mouseY
    ) {
        int localMouseX = mouseX - this.leftPos;
        int localMouseY = mouseY - this.topPos;

        int drawerX = screenX - this.leftPos;
        int y = CATEGORY_DRAWER_Y + CATEGORY_BUTTON_Y_START;

        boolean anyCategoryHovered = false;

        List<DiscCatalog.DiscCategory> categories = DiscCatalog.getAvailableCategories();

        int maxPage = Math.max(0, (categories.size() - 1) / CATEGORIES_PER_PAGE);
        categoryPage = Math.max(0, Math.min(categoryPage, maxPage));

        int start = categoryPage * CATEGORIES_PER_PAGE;
        int end = Math.min(start + CATEGORIES_PER_PAGE, categories.size());

        for (int i = start; i < end; i++) {
            DiscCatalog.DiscCategory category = categories.get(i);

            boolean hovered = isInside(
                    localMouseX,
                    localMouseY,
                    drawerX + CATEGORY_BUTTON_X_OFFSET,
                    y,
                    CATEGORY_BUTTON_WIDTH,
                    CATEGORY_BUTTON_HEIGHT
            );

            if (hovered) {
                anyCategoryHovered = true;
            }

            boolean selected = category == activeCategory;

            guiGraphics.blit(
                    getCategoryButtonTexture(category, hovered, selected),
                    screenX + CATEGORY_BUTTON_X_OFFSET,
                    this.topPos + y,
                    0,
                    0,
                    CATEGORY_BUTTON_WIDTH,
                    CATEGORY_BUTTON_HEIGHT,
                    CATEGORY_BUTTON_WIDTH,
                    CATEGORY_BUTTON_HEIGHT
            );

            if ((selected || hovered) && !lyricsButtonHovered) {
                ResourceLocation iconTexture = selected
                        ? CATEGORY_ICON_SELECTED_TEXTURE
                        : CATEGORY_ICON_TEXTURE;

                guiGraphics.blit(
                        iconTexture,
                        screenX + CATEGORY_ICON_X_OFFSET,
                        this.topPos + y + CATEGORY_ICON_Y_OFFSET + CATEGORY_BUTTON_GAP,
                        0,
                        0,
                        CATEGORY_ICON_SIZE,
                        CATEGORY_ICON_SIZE,
                        CATEGORY_ICON_SIZE,
                        CATEGORY_ICON_SIZE
                );
            }

            y += CATEGORY_BUTTON_GAP;
        }

        if (maxPage > 0) {
            renderCategoryPageTab(guiGraphics, screenX, mouseX, mouseY, maxPage);
        }

        if (anyCategoryHovered && !wasCategoryHovered) {
            playUiSound(ModSounds.BUTTON_HIGHLIGHT.get(), 1.0F, 1.0F);
        }

        wasCategoryHovered = anyCategoryHovered;
    }

    private boolean handleCategoryDrawerClick(int mouseX, int mouseY) {
        if (!categoryOpen) {
            // Closed: handle is next to the main GUI
            if (isInside(
                    mouseX,
                    mouseY,
                    CATEGORY_DRAWER_CLOSED_X,
                    CATEGORY_DRAWER_Y,
                    CATEGORY_DRAWER_CLOSED_WIDTH,
                    CATEGORY_DRAWER_HEIGHT
            )) {
                categoryOpen = true;
                playUiSound(ModSounds.CATEGORY_OPEN.get(), 1.0F, 1.0F);
                return true;
            }

            return false;
        }

        // Open: handle/click strip is on the LEFT side of the opened drawer
        if (isInside(
                mouseX,
                mouseY,
                CATEGORY_DRAWER_OPEN_X,
                CATEGORY_DRAWER_Y,
                CATEGORY_DRAWER_CLOSED_WIDTH,
                CATEGORY_DRAWER_HEIGHT
        )) {
            categoryOpen = false;
            playUiSound(ModSounds.CATEGORY_OPEN.get(), 1.0F, 1.0F);
            return true;
        }

        // Category text area
        if (isInside(
                mouseX,
                mouseY,
                CATEGORY_DRAWER_OPEN_X,
                CATEGORY_DRAWER_Y,
                CATEGORY_DRAWER_WIDTH,
                CATEGORY_DRAWER_HEIGHT + PAGE_TAB_HEIGHT
        )) {
            return handleCategoryNameClick(mouseX, mouseY, CATEGORY_DRAWER_OPEN_X);
        }

        return false;
    }

    private boolean handleCategoryNameClick(int mouseX, int mouseY, int drawerX) {
        List<DiscCatalog.DiscCategory> categories = DiscCatalog.getAvailableCategories();

        int maxPage = Math.max(0, (categories.size() - 1) / CATEGORIES_PER_PAGE);

        if (maxPage > 0) {
            int tabX = drawerX + PAGE_TAB_X;
            int tabY = CATEGORY_DRAWER_Y + PAGE_TAB_Y;

            if (isInside(
                    mouseX,
                    mouseY,
                    tabX + PAGE_PREV_X,
                    tabY + PAGE_ARROW_Y,
                    PAGE_ARROW_WIDTH,
                    PAGE_ARROW_HEIGHT
            )) {
                if (categoryPage > 0) {
                    categoryPage--;
                    playUiSound(ModSounds.CATEGORY_OPEN.get(), 1.0F, 1.0F);
                } else {
                    playUiSound(ModSounds.ERROR.get(), 1.0F, 1.0F);
                }
                return true;
            }

            if (isInside(
                    mouseX,
                    mouseY,
                    tabX + PAGE_NEXT_X,
                    tabY + PAGE_ARROW_Y,
                    PAGE_ARROW_WIDTH,
                    PAGE_ARROW_HEIGHT
            )) {
                if (categoryPage < maxPage) {
                    categoryPage++;
                    playUiSound(ModSounds.CATEGORY_OPEN.get(), 1.0F, 1.0F);
                } else {
                    playUiSound(ModSounds.ERROR.get(), 1.0F, 1.0F);
                }
                return true;
            }
        }

        int y = CATEGORY_DRAWER_Y + CATEGORY_BUTTON_Y_START;

        int start = categoryPage * CATEGORIES_PER_PAGE;
        int end = Math.min(start + CATEGORIES_PER_PAGE, categories.size());

        for (int i = start; i < end; i++) {
            DiscCatalog.DiscCategory category = categories.get(i);

            if (isInside(
                    mouseX,
                    mouseY,
                    drawerX + CATEGORY_BUTTON_X_OFFSET,
                    y,
                    CATEGORY_BUTTON_WIDTH,
                    CATEGORY_BUTTON_HEIGHT
            )) {
                playUiSound(ModSounds.BUTTON_PRESS.get(), 1.0F, 1.0F);

                activeCategory = category;
                refreshVisibleDiscs();

                int currentRecord = getDisplayedRecord();

                if (activeCategory == DiscCatalog.DiscCategory.ALL) {
                    syncVisibleIndexToRecord(currentRecord);
                    return true;
                }

                DiscCatalog.DiscEntry currentEntry = DiscCatalog.getEntry(currentRecord);

                boolean currentExistsInCategory = visibleDiscs.stream()
                        .anyMatch(entry -> entry.id().equals(currentEntry.id()));

                if (currentExistsInCategory) {
                    syncVisibleIndexToRecord(currentRecord);
                } else if (!visibleDiscs.isEmpty()) {
                    selectedVisibleIndex = 0;
                    sendSelectedRecordForVisibleIndex();
                }

                return true;
            }

            y += CATEGORY_BUTTON_GAP;
        }

        return true;
    }

    private boolean handleLyricsButtonClick(int mouseX, int mouseY) {
        if (!hasBlankDiscInserted() || DiscCatalog.size() <= 0) {
            return false;
        }

        if (!isInside(
                mouseX,
                mouseY,
                LYRICS_BUTTON_X,
                LYRICS_BUTTON_Y,
                LYRICS_BUTTON_WIDTH,
                LYRICS_BUTTON_HEIGHT
        )) {
            return false;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());

        if (!DiscLyrics.hasLyrics(entry.item())) {
            return false;
        }

        playUiSound(ModSounds.BUTTON_PRESS.get(),  1.0F, 1.0F);
        this.minecraft.setScreen(new LyricsPopupScreen(this, entry));
        return true;
    }

    private void renderCategoryDrawer(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (!categoryOpen) {
            wasPageArrowHovered = false;
            guiGraphics.blit(
                    CATEGORY_DRAWER_CLOSED_TEXTURE,
                    this.leftPos + CATEGORY_DRAWER_CLOSED_X,
                    this.topPos + CATEGORY_DRAWER_Y,
                    0,
                    0,
                    CATEGORY_DRAWER_CLOSED_WIDTH,
                    CATEGORY_DRAWER_HEIGHT,
                    256,
                    256
            );
            return;
        }

        int screenX = this.leftPos + CATEGORY_DRAWER_OPEN_X;
        int screenY = this.topPos + CATEGORY_DRAWER_Y;

        guiGraphics.blit(
                CATEGORY_DRAWER_TEXTURE,
                screenX,
                screenY,
                0,
                0,
                CATEGORY_DRAWER_WIDTH,
                CATEGORY_DRAWER_HEIGHT,
                256,
                256
        );

        renderCategoryNames(guiGraphics, screenX, screenY, mouseX, mouseY);
    }

    private void renderCategoryPageTab(
            GuiGraphics guiGraphics,
            int screenX,
            int mouseX,
            int mouseY,
            int maxPage
    ) {
        int localMouseX = mouseX - this.leftPos;
        int localMouseY = mouseY - this.topPos;

        int tabLocalX = CATEGORY_DRAWER_OPEN_X + PAGE_TAB_X;
        int tabLocalY = CATEGORY_DRAWER_Y + PAGE_TAB_Y;

        int tabScreenX = screenX + PAGE_TAB_X;
        int tabScreenY = this.topPos + tabLocalY;

        boolean hoveringPrev = isInside(
                localMouseX,
                localMouseY,
                tabLocalX + PAGE_PREV_X,
                tabLocalY + PAGE_ARROW_Y,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT
        );

        boolean hoveringNext = isInside(
                localMouseX,
                localMouseY,
                tabLocalX + PAGE_NEXT_X,
                tabLocalY + PAGE_ARROW_Y,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT
        );

        boolean anyPageArrowHovered = hoveringPrev || hoveringNext;

        if (anyPageArrowHovered && !wasPageArrowHovered) {
            playUiSound(ModSounds.BUTTON_HIGHLIGHT.get(), 1.0F, 1.0F);
        }

        wasPageArrowHovered = anyPageArrowHovered;

        guiGraphics.blit(
                PAGE_TAB_BASE_TEXTURE,
                tabScreenX,
                tabScreenY,
                0,
                0,
                PAGE_TAB_WIDTH,
                PAGE_TAB_HEIGHT,
                PAGE_TAB_WIDTH,
                PAGE_TAB_HEIGHT
        );

        guiGraphics.blit(
                hoveringPrev ? PAGE_TAB_PREV_HIGHLIGHT : PAGE_TAB_PREV_BASE,
                tabScreenX + PAGE_PREV_X,
                tabScreenY + PAGE_ARROW_Y,
                0,
                0,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT
        );

        guiGraphics.blit(
                hoveringNext ? PAGE_TAB_NEXT_HIGHLIGHT : PAGE_TAB_NEXT_BASE,
                tabScreenX + PAGE_NEXT_X,
                tabScreenY + PAGE_ARROW_Y,
                0,
                0,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT,
                PAGE_ARROW_WIDTH,
                PAGE_ARROW_HEIGHT
        );

        ResourceLocation currentPageTexture = categoryPage == 0
                ? CAT_PAGE_1
                : CAT_PAGE_2;

        guiGraphics.blit(
                currentPageTexture,
                tabScreenX + PAGE_CURRENT_NUMBER_X,
                tabScreenY + PAGE_NUMBER_Y,
                0,
                0,
                PAGE_CURRENT_NUMBER_WIDTH,
                PAGE_CURRENT_NUMBER_HEIGHT,
                PAGE_CURRENT_NUMBER_WIDTH,
                PAGE_CURRENT_NUMBER_HEIGHT
        );

        guiGraphics.blit(
                TOTAL_PAGE_COUNT,
                tabScreenX + PAGE_TOTAL_NUMBER_X,
                tabScreenY + PAGE_NUMBER_Y,
                0,
                0,
                PAGE_TOTAL_NUMBER_WIDTH,
                PAGE_TOTAL_NUMBER_HEIGHT,
                PAGE_TOTAL_NUMBER_WIDTH,
                PAGE_TOTAL_NUMBER_HEIGHT
        );
    }

    private ResourceLocation getCategoryButtonTexture(
            DiscCatalog.DiscCategory category,
            boolean hovered,
            boolean selected
    ) {
        String folder = selected
                ? CATEGORY_SELECTED_PATH
                : hovered
                  ? CATEGORY_HIGHLIGHT_PATH
                  : CATEGORY_BASE_PATH;

        String fileName = category.name().toLowerCase(Locale.ROOT);

        return ResourceLocation.fromNamespaceAndPath(
                HardcoreDiscs.MODID,
                folder + fileName + ".png"
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        songScrollTick++;
        updateAnimations();

        lyricsButtonHovered = false;

        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        renderXpCost(guiGraphics);
        renderLyricsButton(guiGraphics, mouseX, mouseY);
        renderCategoryDrawer(guiGraphics, mouseX, mouseY);
        renderArrowHover(guiGraphics, mouseX, mouseY);

        // Keep this for normal inventory/input/output item tooltips
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        // Custom song-name tooltip AFTER vanilla slot tooltips
        renderSongNameTooltip(guiGraphics, mouseX, mouseY);
    }


    private void updateAnimations() {
        boolean showXp = hasBlankDiscInserted() && DiscCatalog.size() > 0;
        boolean showLyrics = showXp && DiscLyrics.hasLyrics(DiscCatalog.getEntry(getDisplayedRecord()).item());

        xpSlide = approach(xpSlide, showXp ? 1.0F : 0.0F, SLIDE_SPEED);
        lyricsSlide = approach(lyricsSlide, showLyrics ? 1.0F : 0.0F, SLIDE_SPEED);
    }

    private float approach(float current, float target, float amount) {
        if (current < target) {
            return Math.min(target, current + amount);
        }

        if (current > target) {
            return Math.max(target, current - amount);
        }

        return current;
    }

    private void refreshVisibleDiscs() {
        String query = searchBox == null ? "" : searchBox.getValue().trim().toLowerCase(Locale.ROOT);

        List<DiscCatalog.DiscEntry> all = DiscCatalog.getAll();
        List<DiscCatalog.DiscEntry> filtered = new ArrayList<>();

        for (DiscCatalog.DiscEntry entry : all) {
            if (activeCategory != DiscCatalog.DiscCategory.ALL
                    && entry.category() != activeCategory) {
                continue;
            }

            ItemStack stack = new ItemStack(entry.item());

            String desc = Component.translatable(
                    entry.item().getDescriptionId() + ".desc"
            ).getString().toLowerCase(Locale.ROOT);

            String name = stack.getHoverName().getString().toLowerCase(Locale.ROOT);
            String id = entry.id().toString().toLowerCase(Locale.ROOT);

            if (query.isEmpty()
                    || desc.contains(query)
                    || name.contains(query)
                    || id.contains(query)) {
                filtered.add(entry);
            }
        }

        this.visibleDiscs = filtered;

        if (selectedVisibleIndex >= visibleDiscs.size()) {
            selectedVisibleIndex = Math.max(0, visibleDiscs.size() - 1);
        }
    }

    private void sendSelectedRecordForVisibleIndex() {
        if (visibleDiscs.isEmpty()) {
            return;
        }

        DiscCatalog.DiscEntry entry = visibleDiscs.get(selectedVisibleIndex);

        for (int i = 0; i < DiscCatalog.size(); i++) {
            if (DiscCatalog.getEntry(i).id().equals(entry.id())) {
                selectedRecord = i;
                sendSelectedRecord();
                return;
            }
        }
    }

    private void syncVisibleIndexToMenuSelection() {
        if (visibleDiscs == null || visibleDiscs.isEmpty()) {
            selectedVisibleIndex = 0;
            return;
        }

        int savedIndex = this.menu.getSelectedRecord();

        if (savedIndex < 0 || savedIndex >= DiscCatalog.size()) {
            selectedVisibleIndex = 0;
            return;
        }

        DiscCatalog.DiscEntry savedEntry = DiscCatalog.getEntry(savedIndex);

        for (int i = 0; i < visibleDiscs.size(); i++) {
            if (visibleDiscs.get(i).id().equals(savedEntry.id())) {
                selectedVisibleIndex = i;
                return;
            }
        }

        selectedVisibleIndex = 0;
    }

    private void syncVisibleIndexToRecord(int recordIndex) {
        if (visibleDiscs == null || visibleDiscs.isEmpty()) {
            selectedVisibleIndex = 0;
            return;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(recordIndex);

        for (int i = 0; i < visibleDiscs.size(); i++) {
            if (visibleDiscs.get(i).id().equals(entry.id())) {
                selectedVisibleIndex = i;
                return;
            }
        }

        selectedVisibleIndex = 0;
    }

    private boolean isClickingOutputSlot(int localX, int localY) {
        return isInside(localX, localY, 160, 49, 16, 16);
    }

    private boolean canAffordCurrentDisc() {
        if (this.minecraft == null || this.minecraft.player == null || DiscCatalog.size() <= 0) {
            return false;
        }

        DiscCatalog.DiscEntry entry = DiscCatalog.getEntry(getDisplayedRecord());
        int cost = entry.xpCost();

        return this.minecraft.player.isCreative()
                || this.minecraft.player.experienceLevel >= cost;
    }

    private void playUiSound(SoundEvent sound, float volume, float pitch) {
        if (this.minecraft != null) {
            this.minecraft.getSoundManager().play(
                    SimpleSoundInstance.forUI(sound, volume, pitch)
            );
        }
    }
}