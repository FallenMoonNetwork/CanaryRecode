package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityFurnace;


public class CanaryFurnace extends CanaryComplexBlock implements Furnace {

    public CanaryFurnace(TileEntityFurnace tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((TileEntityFurnace) tileentity);
    }

    @Override
    public void clearContents() {
        ((TileEntityFurnace) tileentity).clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        ItemStack item = ((TileEntityFurnace) tileentity).decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        ItemStack[] oStacks = ((TileEntityFurnace) tileentity).getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public String getInventoryName() {
        return ((TileEntityFurnace) tileentity).getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return ((TileEntityFurnace) tileentity).getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((TileEntityFurnace) tileentity).getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return ((TileEntityFurnace) tileentity).getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((TileEntityFurnace) tileentity).getItem(id, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        ItemStack item = ((TileEntityFurnace) tileentity).getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((TileEntityFurnace) tileentity).hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((TileEntityFurnace) tileentity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return ((TileEntityFurnace) tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((TileEntityFurnace) tileentity).removeItem(id);
    }

    @Override
    public void setContents(Item[] items) {
        ItemStack[] oStacks = new ItemStack[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                oStacks[i] = ((CanaryItem) items[i]).getHandle();
            } else {
                oStacks[i] = null;
            }
        }
        ((TileEntityFurnace) tileentity).setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        ((TileEntityFurnace) tileentity).setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item value) {
        ((TileEntityFurnace) tileentity).setSlot(index, ((CanaryItem) value).getHandle());
    }

    @Override
    public TileEntityFurnace getHandle() {
        return (TileEntityFurnace) tileentity;
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((TileEntityFurnace) tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((TileEntityFurnace) tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((TileEntityFurnace) tileentity).getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((TileEntityFurnace) tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((TileEntityFurnace) tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }

}
