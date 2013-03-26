package net.canarymod.api.entity.vehicle;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.EntityMinecartChest;
import net.minecraft.server.ItemStack;

/**
 * ChestMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChestMinecart extends CanaryMinecart implements ChestMinecart {

    /**
     * Constructs a new wrapper for EntityMinecartChest
     * 
     * @param entity
     *            the EntityMinecartChest to be wrapped
     */
    public CanaryChestMinecart(EntityMinecartChest entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        ItemStack[] contents = ((EntityMinecartChest) entity).getContents();
        Item[] canaryItems = new CanaryItem[contents.length];

        for (int i = 0; i < contents.length; i++) {
            canaryItems[i] = contents[i].getCanaryItem();
        }
        return canaryItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        ItemStack[] values = new ItemStack[getInventorySize()];

        clearContents();
        for (int i = 0; i < items.length; i++) {
            values[i] = ((CanaryItem) items[i]).getHandle();
        }
        getHandle().setContents(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getSlot(int index) {
        return getHandle().getSlot(index).getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, int amount) {
        getHandle().addItem(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        getHandle().addItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmptySlot() {
        return getHandle().getEmptySlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item value) {
        getHandle().setSlot(index, ((CanaryItem) value).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return getHandle().getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return getHandle().getInventoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getHandle().setInventoryName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        getHandle().clearContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id, int amount) {
        return getHandle().getItem(id, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id) {
        return getHandle().getItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return getHandle().removeItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int id) {
        return getHandle().removeItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        return getHandle().decreaseItemStackSize(itemId, amount).getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return getHandle().getInventoryStackLimit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(Item item) {
        return getHandle().hasItemStack(((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return getHandle().hasItemStack(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return getHandle().hasItemStack(itemId, minAmount, maxAmount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        return hasItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getHandle().update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartChest getHandle() {
        return (EntityMinecartChest) entity;
    }
}
