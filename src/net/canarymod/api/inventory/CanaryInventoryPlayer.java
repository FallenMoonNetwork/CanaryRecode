package net.canarymod.api.inventory;

import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;

public class CanaryInventoryPlayer implements Inventory{
    
    private OInventoryPlayer inventory;
    
    public CanaryInventoryPlayer(OInventoryPlayer playerInventory){
        this.inventory = playerInventory;
    }

    @Override
    public void addItem(int id) {
        addItem(id, 0);
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            return;
        }
        int slot = item.getSlot();
        int size = getSize();

        if (slot < size && slot >= 0) {
            if (item.getAmount() <= 0) {
                removeItem(slot);
            } else {
                setSlot(item);
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();

            if (newSlot != -1) {
                item.setSlot(newSlot);
                setSlot(item);
            }
        }
    }

    @Override
    public void addItem(int id, int amount) {
        addItem(new CanaryItem(new OItemStack(id, amount, 0)));
    }

    @Override
    public Item[] clearInventory() {
        Item[] items = getContents();
        
        for(int index = 0; index < getSize(); index++){
            inventory.setSlot(index, null);
        }
        
        return items;
    }

    @Override
    public Item[] getContents() {
        OItemStack[] oStacks = inventory.getContents();
        Item[] items = new Item[oStacks.length];
        for(int i = 0; i < oStacks.length; i++) {
            items[i] = oStacks[i].getCanaryItem();
        }
        return items;
    }

    @Override
    public Item getItem(int itemId) {
        return inventory.getItem(itemId);
    }

    @Override
    public Item getItem(ItemType type) {
        return inventory.getItem(type.getId());
    }

    @Override
    public Item getItem(int itemId, int amount) {
        return inventory.getItem(itemId, amount);
    }

    @Override
    public Item getItem(ItemType type, int amount) {
        return inventory.getItem(type.getId(), amount);
    }

    @Override
    public int getSize() {
        return inventory.getInventorySize();
    }

    @Override
    public boolean hasItem(ItemType type) {
        return inventory.hasItem(type.getId());
    }

    @Override
    public boolean hasItem(int itemId) {
        return inventory.hasItem(itemId);
    }

    @Override
    public boolean hasItem(ItemType type, int amount) {
        return inventory.hasItemStack(new OItemStack(type.getId(), amount, 0));
    }

    @Override
    public boolean hasItem(int itemId, int amount) {
        return inventory.hasItemStack(new OItemStack(itemId, amount, 0));
    }

    @Override
    public boolean insertItem(Item item) {
        return inventory.addItemToBackPack(((CanaryItem) item).getHandle());
    }

    @Override
    public void removeItem(Item item) {
        if(inventory.hasItem(item.getId())) {
            inventory.removeItem(item);
        }
    }

    @Override
    public Item removeItem(int itemId) {
        if(inventory.hasItem(itemId)) {
            return inventory.removeItem(itemId);
        }
        return null;
    }

    @Override
    public void setSlot(Item item) {
        inventory.setSlot(item.getSlot(), ((CanaryItem) item).getHandle());
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
    public void updateInventory() {
         inventory.update();
    }

    @Override
    public int getEmptySlot() {
        int size = getSize();

        for (int index = 0; size > index; index++) {
            if (inventory.getSlot(index) != null){
                continue;
            }
            return index;
        }

        return -1;
    }

    @Override
    public void clearContents() {
        inventory.clearContents();
    }


}
