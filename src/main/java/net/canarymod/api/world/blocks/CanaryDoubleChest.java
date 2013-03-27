package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityChest;


public class CanaryDoubleChest extends CanaryChest implements DoubleChest {
    private InventoryLargeChest largechest;

    public CanaryDoubleChest(InventoryLargeChest largechest) {
        super((TileEntityChest) largechest.b);
        this.largechest = largechest;
    }

    @Override
    public Inventory getInventory() {
        // HERP
        return getHandle().getInventory();
    }

    @Override
    public void clearContents() {
        largechest.clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        ItemStack item = largechest.decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        ItemStack[] oStacks = largechest.getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public String getInventoryName() {
        return largechest.getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return largechest.getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return largechest.getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return largechest.getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
        return largechest.getItem(id, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        ItemStack item = largechest.getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return largechest.hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return largechest.hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return largechest.removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return largechest.removeItem(id);
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
        largechest.setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        largechest.setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item value) {
        largechest.setSlot(index, ((CanaryItem) value).getHandle());
    }

    public TileEntityChest getHandleA() {
        return (TileEntityChest) largechest.b;
    }

    public TileEntityChest getHandleB() {
        return (TileEntityChest) largechest.c;
    }

}
