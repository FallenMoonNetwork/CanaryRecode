package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityBrewingStand;


public class CanaryBrewingStand extends CanaryComplexBlock implements BrewingStand {

    public CanaryBrewingStand(OTileEntityBrewingStand tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((OTileEntityBrewingStand) tileentity);
    }

    @Override
    public Item[] getContents() {
        OItemStack[] oStacks = ((OTileEntityBrewingStand) tileentity).getContents();
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
        ((OTileEntityBrewingStand) tileentity).setContents(oStacks);
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((OTileEntityBrewingStand) tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((OTileEntityBrewingStand) tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((OTileEntityBrewingStand) tileentity).getEmptySlot();
    }

    @Override
    public void setSlot(int index, Item item) {
        ((OTileEntityBrewingStand) tileentity).setSlot(index, ((CanaryItem) item).getHandle());
    }

    @Override
    public int getInventorySize() {
        return ((OTileEntityBrewingStand) tileentity).getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return ((OTileEntityBrewingStand) tileentity).getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        ((OTileEntityBrewingStand) tileentity).setInventoryName(value);
    }

    @Override
    public void clearContents() {
        ((OTileEntityBrewingStand) tileentity).clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        return ((OTileEntityBrewingStand) tileentity).getItem(id, amount);
    }

    @Override
    public Item getItem(int id) {
        return ((OTileEntityBrewingStand) tileentity).getItem(id);
    }

    @Override
    public Item removeItem(Item item) {
        return ((OTileEntityBrewingStand) tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((OTileEntityBrewingStand) tileentity).removeItem(id);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        OItemStack item = ((OTileEntityBrewingStand) tileentity).decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public Item getSlot(int index) {
        OItemStack item = ((OTileEntityBrewingStand) tileentity).getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OTileEntityBrewingStand) tileentity).getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((OTileEntityBrewingStand) tileentity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((OTileEntityBrewingStand) tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((OTileEntityBrewingStand) tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((OTileEntityBrewingStand) tileentity).hasItem(itemId);
    }

}
