package net.canarymod.api.inventory;

import net.canarymod.api.entity.Player;

public class CanaryEnderChestInventory implements EnderChestInventory {

//    public CanaryEnderChestInventory(OInventoryEnderChest) {
//
//    }
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Item[] getContents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int type, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasItem(ItemType type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(int type) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(ItemType type, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(int type, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Item[] clearInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(int itemId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addItem(int itemId, int amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(int itemId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItem(Item item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(Item item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(int itemId, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public Item getSlot(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSlot(int itemId, int amount, int slot) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean insertItem(Item item) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getEmptySlot() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void clearContents() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setContents(Item[] items) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSlot(int index, Item value) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getInventorySize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setInventoryName(String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasItemStack(Item oItemStack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public Player getInventoryOwner() {
        // TODO Auto-generated method stub
        return null;
    }

}
