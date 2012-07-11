package net.canarymod.api.inventory;

import java.util.ArrayList;

import net.canarymod.Logman;
import net.minecraft.server.OItemStack;

public class CanaryInventory implements Inventory{
    protected Container<OItemStack> container;
    
    public CanaryInventory(Container<OItemStack> container){
        this.container = container;
    }

    @Override
    public void addItem(int id) {
        addItem(id, 0);
    }
    
    @Override
    public void addItem(int id, int amount) {
        container.addItem(id, amount);
    }

    @Override
    public void addItem(Item item) {
        container.addItem(item);
    }

    @Override
    public Item[] clearInventory() {
        Item[] items = getContents();
        
        for(int index = 0; index < getSize(); index++){
            container.setSlot(index, null);
        }
        
        return items;
    }
    
    @Override
    public Item[] getContents() {
        OItemStack[] oStacks = container.getContents();
        ArrayList<Item> items = new ArrayList<Item>(oStacks.length);
        for(int i = 0; i < oStacks.length; i++) {
            if(oStacks[i] != null) {
                items.add(new CanaryItem(oStacks[i]));
            }
        }
        Item[] itemStacks = new Item[items.size()];
        for(int i = 0; i < items.size(); i++) {
            Logman.println("Item ID: "+items.get(i).getId());
            Logman.println("Item Amount: "+items.get(i).getAmount());
            Logman.println("Item Data: "+items.get(i).getDamage());
            itemStacks[i] = items.get(i);
        }
        return itemStacks;
    }

    @Override
    public int getEmptySlot() {
        return container.getEmptySlot();
    }

    @Override
    public Item getItem(int itemId) {
        return container.getItem(itemId);
    }

    @Override
    public Item getItem(ItemType type) {
        return container.getItem(type.getId());
    }

    @Override
    public Item getItem(int itemId, int amount) {
        return container.getItem(itemId, amount);
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        return container.getItem(type.getId(), amount);
    }

    @Override
    public int getSize() {
        return container.getInventorySize();
    }

    @Override
    public boolean hasItem(ItemType type) {
        return container.hasItem(type.getId());
    }

    @Override
    public boolean hasItem(int itemId) {
        return container.hasItem(itemId);
    }

    @Override
    public boolean hasItem(ItemType type, int amount) {
        return container.hasItemStack(new OItemStack(type.getId(), amount, 0));
    }

    @Override
    public boolean hasItem(int itemId, int amount) {
        return container.hasItemStack(new OItemStack(itemId, amount, 0));
    }

    @Override
    public boolean insertItem(Item item) {
        container.addItem(item);
        return true;
    }

    @Override
    public Item removeItem(Item item) {
        return container.removeItem(item);
    }

    @Override
    public Item removeItem(int itemId) {
        return container.removeItem(itemId);
    }

    @Override
    public void setSlot(Item item) {
        container.setSlot(item.getSlot(), ((CanaryItem) item).getHandle());
    }

    @Override
    public void setSlot(int itemId, int slot) {
        CanaryItem item = new CanaryItem(new OItemStack(itemId, 1, 0));
        item.setSlot(slot);
        setSlot(item);
    }

    @Override
    public void setSlot(int slot, int itemId, int amount) {
        CanaryItem item = new CanaryItem(new OItemStack(itemId, amount, 0));
        item.setSlot(slot);
        setSlot(item);
    }
    
    @Override
    public Item getSlot(int slot) {
        OItemStack tmp = container.getSlot(slot);
        if(tmp != null) {
            return tmp.getCanaryItem();
        }
        return null;
    }

    @Override
    public void clearContents() {
        container.clearContents();
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];
        for(int i = 0; i < items.length; i++) {
            oStacks[i] = ((CanaryItem)items[i]).getHandle();
        }
        container.setContents(oStacks);
        
    }

    @Override
    public void setSlot(int index, Item value) {
        container.setSlot(index, ((CanaryItem)value).getHandle());
    }

    @Override
    public int getInventorySize() {
        return container.getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return container.getInventoryName();
    }

    @Override
    public void setInventoryName(String value) {
        container.setInventoryName(value);
    }

    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        OItemStack oStack = container.decreaseItemStackSize(itemId, amount);
        return new CanaryItem(oStack);
    }

    @Override
    public int getInventoryStackLimit() {
        return container.getInventoryStackLimit();
    }

    @Override
    public boolean hasItemStack(Item item) {
        return hasItemStack(item.getId(), item.getAmount());
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return container.hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return container.hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public void update() {
        container.update();
    }

}
