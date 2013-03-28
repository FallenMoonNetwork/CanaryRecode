package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.minecraft.server.ContainerWorkbench;

/**
 * Workbench wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWorkbench implements Workbench {

    private ContainerWorkbench container;

    /**
     * Constructs a new wrapper for ContainerWorkbench
     * 
     * @param container
     *            the ContainerWorkbench to be wrapped
     */
    public CanaryWorkbench(ContainerWorkbench container) {
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return container.getInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return container.h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return container.i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZ() {
        return container.j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return container.g.getCanaryWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getInventory().update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        getInventory().clearContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return getInventory().decreaseItemStackSize(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return getInventory().getContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return getInventory().getInventoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return getInventory().getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return getInventory().getInventoryStackLimit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id) {
        return getInventory().getItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id, int amount) {
        return getInventory().getItem(id, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getSlot(int index) {
        return getInventory().getSlot(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        return getInventory().hasItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(Item item) {
        return getInventory().hasItemStack(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return getInventory().removeItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int id) {
        return getInventory().removeItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        getInventory().setContents(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getInventory().setInventoryName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item item) {
        getInventory().setSlot(index, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, int amount) {
        getInventory().addItem(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        getInventory().addItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmptySlot() {
        return getInventory().getEmptySlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return getInventory().hasItemStack(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return getInventory().hasItemStack(itemId, minAmount, maxAmount);
    }

    /**
     * {@inheritDoc}
     */
    public ContainerWorkbench getHandle() {
        return container;
    }

}
