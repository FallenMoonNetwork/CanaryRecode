package net.canarymod.api.inventory;

import net.minecraft.server.IInventory;



/**
 * Inventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryContainerEntity implements Inventory {
    protected IInventory inventory;

    public CanaryContainerEntity(IInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void addItem(Item item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(ItemType type) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(int itemId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(int itemId, short damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(int itemId, int amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(ItemType type, int amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(int itemId, int amount, short damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(ItemType type, int amount, short damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearContents() {
        // TODO Auto-generated method stub

    }

    @Override
    public Item[] clearInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item[] getContents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getEmptySlot() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Item getItem(ItemType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int id, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int id, int amount, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType type, int amount, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Item getSlot(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasItem(int itemId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(ItemType type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(ItemType type, short damage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(int itemId, short damage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(ItemType type, int amount, short damage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount, short damage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount, short damage) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertItem(Item item) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSlot(Item item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(int itemId, short damage, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(int itemId, int amount, short damage, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(ItemType type, int amount, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(ItemType type, int amount, short damage, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public Item removeItem(Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(int id, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(ItemType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(ItemType type, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setContents(Item[] items) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInventoryName(String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(int index, Item value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
