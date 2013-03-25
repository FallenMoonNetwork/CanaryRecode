package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityFurnace;


public class CanaryFurnace extends CanaryComplexBlock implements Furnace {

    public CanaryFurnace(OTileEntityFurnace tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((OTileEntityFurnace) tileentity);
    }

    @Override
    public void clearContents() {
        ((OTileEntityFurnace) tileentity).clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        OItemStack item = ((OTileEntityFurnace) tileentity).decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        OItemStack[] oStacks = ((OTileEntityFurnace) tileentity).getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public String getInventoryName() {
        return ((OTileEntityFurnace) tileentity).getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return ((OTileEntityFurnace) tileentity).getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OTileEntityFurnace) tileentity).getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return ((OTileEntityFurnace) tileentity).getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((OTileEntityFurnace) tileentity).getItem(id, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        OItemStack item = ((OTileEntityFurnace) tileentity).getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((OTileEntityFurnace) tileentity).hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((OTileEntityFurnace) tileentity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return ((OTileEntityFurnace) tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((OTileEntityFurnace) tileentity).removeItem(id);
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
        ((OTileEntityFurnace) tileentity).setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        ((OTileEntityFurnace) tileentity).setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item value) {
        ((OTileEntityFurnace) tileentity).setSlot(index, ((CanaryItem) value).getHandle());
    }

    public OTileEntityFurnace getHandle() {
        return (OTileEntityFurnace) tileentity;
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((OTileEntityFurnace) tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((OTileEntityFurnace) tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((OTileEntityFurnace) tileentity).getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((OTileEntityFurnace) tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((OTileEntityFurnace) tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }

}
