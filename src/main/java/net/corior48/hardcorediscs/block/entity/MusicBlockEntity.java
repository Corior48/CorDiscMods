package net.corior48.hardcorediscs.block.entity;

import net.corior48.hardcorediscs.common.DiscCatalog;
import net.corior48.hardcorediscs.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class MusicBlockEntity extends BlockEntity {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private final ItemStackHandler items = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            if (slot == INPUT_SLOT) {
                updateOutput();
            }
        }
    };

    public boolean stillValid(Player player) {
        if (level == null) {
            return false;
        }

        if (level.getBlockEntity(worldPosition) != this) {
            return false;
        }

        return player.distanceToSqr(
                worldPosition.getX() + 0.5,
                worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5
        ) <= 64.0;
    }

    private int selectedRecord = 0;

    public MusicBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MUSIC_BE.get(), pos, state);
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public int getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(int selectedRecord) {
        this.selectedRecord = selectedRecord;
        updateOutput();
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean hasBlankDisc() {
        ItemStack stack = items.getStackInSlot(INPUT_SLOT);
        return !stack.isEmpty() && stack.is(ModItems.BLANK_DISC.get());
    }

    public void updateOutput() {
        ItemStack result = ItemStack.EMPTY;

        if (hasBlankDisc()
                && selectedRecord >= 0
                && selectedRecord < DiscCatalog.size()) {
            result = new ItemStack(DiscCatalog.getItem(selectedRecord));
        }

        ItemStack current = items.getStackInSlot(OUTPUT_SLOT);

        if (!ItemStack.matches(current, result)) {
            items.setStackInSlot(OUTPUT_SLOT, result);
        }

        setChanged();
    }

    public boolean consumeInputAndXp(Player player) {
        if (!hasBlankDisc()) {
            return false;
        }

        if (selectedRecord < 0 || selectedRecord >= DiscCatalog.size()) {
            return false;
        }

        int cost = DiscCatalog.getXpCost(selectedRecord);

        if (!player.isCreative() && player.experienceLevel < cost) {
            return false;
        }

        if (!player.isCreative()) {
            player.giveExperienceLevels(-cost);
        }

        items.getStackInSlot(INPUT_SLOT).shrink(1);
        updateOutput();
        setChanged();

        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }

        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.put("inventory", this.items.serializeNBT(registries));
        tag.putInt("selected_record", this.selectedRecord);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("inventory")) {
            this.items.deserializeNBT(registries, tag.getCompound("inventory"));
        }

        this.selectedRecord = tag.getInt("selected_record");
    }
}
