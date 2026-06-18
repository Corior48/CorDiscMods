package net.corior48.hardcorediscs.menu;

import net.corior48.hardcorediscs.block.entity.MusicBlockEntity;
import net.corior48.hardcorediscs.common.DiscCatalog;
import net.corior48.hardcorediscs.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.SlotItemHandler;

public class MusicBlockMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private final MusicBlockEntity blockEntity;
    private final Container fallbackContainer;

    private final DataSlot selectedRecordData;

    public MusicBlockMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(
                containerId,
                playerInventory,
                playerInventory.player.level().getBlockEntity(extraData.readBlockPos()) instanceof MusicBlockEntity be
                        ? be
                        : null
        );
    }

    public MusicBlockMenu(int containerId, Inventory playerInventory, MusicBlockEntity blockEntity) {
        super(ModMenuTypes.MUSIC_BLOCK_MENU.get(), containerId);

        this.blockEntity = blockEntity;
        this.selectedRecordData = DataSlot.standalone();

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getSelectedRecord() : selectedRecordData.get();
            }

            @Override
            public void set(int value) {
                selectedRecordData.set(value);
            }
        });
        this.fallbackContainer = new SimpleContainer(2);

        if (blockEntity != null) {
            addBlockEntitySlots(blockEntity);
        } else {
            addFallbackSlots();
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addBlockEntitySlots(MusicBlockEntity blockEntity) {
        this.addSlot(new SlotItemHandler(blockEntity.getItems(), INPUT_SLOT, 45, 49) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.BLANK_DISC.get()); // TEMP: replace with blank disc item
            }
        });
        // POS: 160, 49 (x/y)
        this.addSlot(new SlotItemHandler(blockEntity.getItems(), OUTPUT_SLOT, 160, 49) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                if (blockEntity == null) {
                    return false;
                }

                int selected = blockEntity.getSelectedRecord();

                if (selected < 0 || selected >= DiscCatalog.size()) {
                    return false;
                }

                int cost = DiscCatalog.getXpCost(selected);

                return player.isCreative() || player.experienceLevel >= cost;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);

                if (blockEntity != null) {
                    blockEntity.consumeInputAndXp(player);
                }
            }
        });
    }

    private void addFallbackSlots() {
        this.addSlot(new Slot(fallbackContainer, INPUT_SLOT, 45, 49) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.BLANK_DISC.get()); // TEMP: replace with blank disc item
            }
        });

        this.addSlot(new Slot(fallbackContainer, OUTPUT_SLOT, 160, 49) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(
                        playerInventory,
                        column + row * 9 + 9,
                        31 + column * 18,
                        117 + row * 18
                ));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(
                    playerInventory,
                    column,
                    31 + column * 18,
                    175
            ));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            originalStack = stackInSlot.copy();

            if (index == OUTPUT_SLOT) {
                if (!this.moveItemStackTo(stackInSlot, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stackInSlot, originalStack);
            } else if (index == INPUT_SLOT) {
                if (!this.moveItemStackTo(stackInSlot, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (isBlankDisc(stackInSlot)) {
                    if (!this.moveItemStackTo(stackInSlot, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 2 && index < 29) {
                    if (!this.moveItemStackTo(stackInSlot, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29 && index < 38) {
                    if (!this.moveItemStackTo(stackInSlot, 2, 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == originalStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }

        return originalStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity == null || blockEntity.stillValid(player);
    }

    public MusicBlockEntity getBlockEntity() {
        return blockEntity;
    }

    private boolean isBlankDisc(ItemStack stack) {
        return stack.is(ModItems.BLANK_DISC.get()); // TEMP: replace with your blank disc item
    }

    public int getSelectedRecord() {
        return selectedRecordData.get();
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (blockEntity == null) {
            return false;
        }

        if (id < 0 || id >= DiscCatalog.size()) {
            return false;
        }

        blockEntity.setSelectedRecord(id);
        return true;
    }
}