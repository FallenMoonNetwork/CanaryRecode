package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.TileEntityBrewingStand;


public class CanaryBrewingStand extends CanaryComplexBlock implements BrewingStand {

    public CanaryBrewingStand(TileEntityBrewingStand tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((TileEntityBrewingStand) tileentity);
    }

    @Override
    public Item[] getContents() {
        OItemStack[] oStacks = ((TileEntityBrewingStand) tileentity).getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                oStacks[i] = ((CanaryItem) items[i]).getHandle();
            } else {
                oStacks[i] = null;
            }
        }
        ((TileEntityBrewingStand) tileentity).setContents(oStacks);
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((TileEntityBrewingStand) tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((TileEntityBrewingStand) tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((TileEntityBrewingStand) tileentity).getEmptySlot();
    }

    @Override
    public void setSlot(int index, Item item) {
        ((TileEntityBrewingStand) tileentity).setSlot(index, ((CanaryItem) item).getHandle());
    }

    @Override
    public int getInventorySize() {
        return ((TileEntityBrewingStand) tileentity).getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return ((TileEntityBrewingStand) tileentity).getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        ((TileEntityBrewingStand) tileentity).setInventoryName(value);
    }

    @Override
    public void clearContents() {
        ((TileEntityBrewingStand) tileentity).clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((TileEntityBrewingStand) tileentity).getItem(id, amount);
    }

    @Override
    public Item getItem(int id) {
        return ((TileEntityBrewingStand) tileentity).getItem(id);
    }

    @Override
    public Item removeItem(Item item) {
        return ((TileEntityBrewingStand) tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((TileEntityBrewingStand) tileentity).removeItem(id);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        OItemStack item = ((TileEntityBrewingStand) tileentity).decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public Item getSlot(int index) {
        OItemStack item = ((TileEntityBrewingStand) tileentity).getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public int getInventoryStackLimit() {
        return ((TileEntityBrewingStand) tileentity).getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((TileEntityBrewingStand) tileentity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((TileEntityBrewingStand) tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((TileEntityBrewingStand) tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((TileEntityBrewingStand) tileentity).hasItem(itemId);
    }

}
