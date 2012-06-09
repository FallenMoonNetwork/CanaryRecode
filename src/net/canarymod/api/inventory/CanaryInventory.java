package net.canarymod.api.inventory;

import net.minecraft.server.OItemStack;

public class CanaryInventory implements Inventory{
    
    private Container<OItemStack> container;
    
    public CanaryInventory(Container<OItemStack> container){
        this.container = container;
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
            container.setSlot(index, null);
        }
        
        return items;
    }

    @Override
    public Item[] getContents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(ItemType arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasItem(ItemType arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(ItemType arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItem(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertItem(Item arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeItem(Item arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item removeItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSlot(Item item) {
        container.setSlot(item.getSlot(), ((CanaryItem)item).getItemStack());        
    }

    @Override
    public void setSlot(int arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setSlot(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateInventory() {
        // TODO Auto-generated method stub
        
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

}
