package net.canarymod.api.inventory;

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
        addItem(new CanaryItem(new OItemStack(id, amount, 0)));
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
        Item[] items = new Item[oStacks.length];
        for(int i = 0; i < oStacks.length; i++) {
            items[i] = oStacks[i].getCanaryItem();
        }
        return items;
    }

    @Override
    public int getEmptySlot() {
        int size = getSize();

        for (int index = 0; size > index; index++) {
            if (container.getSlot(index) != null){
                continue;
            }
            return index;
        }

        return -1;
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
        
        return false;
    }

    @Override
    public void removeItem(Item item) {
        if(container.hasItem(item.getId())) {
            container.removeItem(item);
        }
    }

    @Override
    public Item removeItem(int itemId) {
        if(container.hasItem(itemId)) {
            return container.removeItem(itemId);
        }
        return null;
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
        return new CanaryItem(container.getSlot(slot));
    }

    @Override
    public void updateInventory() {
        container.update();
    }
    
    @Override
    public void clearContents() {
        container.clearContents();
    }

}
