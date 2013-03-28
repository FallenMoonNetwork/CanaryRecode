package net.canarymod.api.inventory;


import java.util.ArrayList;
import net.canarymod.Logman;
import net.minecraft.server.ItemStack;

/**
 * Inventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryInventory implements Inventory {
    protected Container<ItemStack> container;

    /**
     * Constructs a new Inventory
     * 
     * @param container
     *            the container to wrap
     */
    public CanaryInventory(Container<ItemStack> container) {
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int id) {
        addItem(id, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int id, int amount) {
        container.addItem(id, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        container.addItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        Item[] items = getContents();

        for (int index = 0; index < getSize(); index++) {
            container.setSlot(index, null);
        }

        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        ItemStack[] oStacks = container.getContents();
        ArrayList<Item> items = new ArrayList<Item>(oStacks.length);

        for (int i = 0; i < oStacks.length; i++) {
            if (oStacks[i] != null) {
                items.add(new CanaryItem(oStacks[i]));
            }
        }
        Item[] itemStacks = new Item[items.size()];

        for (int i = 0; i < items.size(); i++) {
            Logman.println("Item ID: " + items.get(i).getId());
            Logman.println("Item Amount: " + items.get(i).getAmount());
            Logman.println("Item Data: " + items.get(i).getDamage());
            itemStacks[i] = items.get(i);
        }
        return itemStacks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmptySlot() {
        return container.getEmptySlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int itemId) {
        return container.getItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(ItemType type) {
        return container.getItem(type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int itemId, int amount) {
        return container.getItem(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(ItemType type, int amount) {
        return container.getItem(type.getId(), amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return container.getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(ItemType type) {
        return container.hasItem(type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        return container.hasItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(ItemType type, int amount) {
        return container.hasItemStack(new ItemStack(type.getId(), amount, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId, int amount) {
        return container.hasItemStack(new ItemStack(itemId, amount, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertItem(Item item) {
        container.addItem(item);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return container.removeItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int itemId) {
        return container.removeItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(Item item) {
        container.setSlot(item.getSlot(), ((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int itemId, int slot) {
        CanaryItem item = new CanaryItem(new ItemStack(itemId, 1, 0));

        item.setSlot(slot);
        setSlot(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int slot, int itemId, int amount) {
        CanaryItem item = new CanaryItem(new ItemStack(itemId, amount, 0));

        item.setSlot(slot);
        setSlot(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getSlot(int slot) {
        ItemStack tmp = container.getSlot(slot);

        if (tmp != null) {
            return tmp.getCanaryItem();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        container.clearContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        ItemStack[] oStacks = new ItemStack[items.length];

        for (int i = 0; i < items.length; i++) {
            oStacks[i] = ((CanaryItem) items[i]).getHandle();
        }
        container.setContents(oStacks);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item value) {
        container.setSlot(index, ((CanaryItem) value).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return container.getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return container.getInventoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        container.setInventoryName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        ItemStack oStack = container.decreaseItemStackSize(itemId, amount);

        return new CanaryItem(oStack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return container.getInventoryStackLimit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(Item item) {
        return hasItemStack(item.getId(), item.getAmount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return container.hasItemStack(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return container.hasItemStack(itemId, minAmount, maxAmount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        container.update();
    }

}
