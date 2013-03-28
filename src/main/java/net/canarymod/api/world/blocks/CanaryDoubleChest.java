package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityChest;


/**
 * DoubleChest implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryDoubleChest extends CanaryChest implements DoubleChest {
    private InventoryLargeChest largechest;

    /**
     * Constructs a new wrapper for InventoryLargeChest
     * 
     * @param largechest
     *            the InventoryLargeChest to be wrapped
     */
    public CanaryDoubleChest(InventoryLargeChest largechest) {
        super((TileEntityChest) largechest.b);
        this.largechest = largechest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return new CanaryInventory(largechest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        largechest.clearContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        ItemStack item = largechest.decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem[] getContents() {
        ItemStack[] oStacks = largechest.getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return largechest.getInventoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return largechest.getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return largechest.getInventoryStackLimit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id) {
        return largechest.getItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id, int amount) {
        return largechest.getItem(id, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem getSlot(int index) {
        ItemStack item = largechest.getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        return largechest.hasItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(Item item) {
        return largechest.hasItemStack(((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return largechest.removeItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int id) {
        return largechest.removeItem(id);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        largechest.setInventoryName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item value) {
        largechest.setSlot(index, ((CanaryItem) value).getHandle());
    }

    /**
     * Gets the TileEntityChest part A
     * 
     * @return part A
     */
    public TileEntityChest getHandleA() {
        return (TileEntityChest) largechest.b;
    }

    /**
     * Gets the TileEntityChest part B
     * 
     * @return part B
     */
    public TileEntityChest getHandleB() {
        return (TileEntityChest) largechest.c;
    }

}
