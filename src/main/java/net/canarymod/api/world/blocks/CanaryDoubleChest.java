package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OInventoryLargeChest;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityChest;

public class CanaryDoubleChest extends CanaryChest implements DoubleChest{
    private OInventoryLargeChest largechest;

    public CanaryDoubleChest(OInventoryLargeChest largechest) {
        super((OTileEntityChest)largechest.b);
        this.largechest = largechest;
    }
    
    @Override
    public Inventory getInventory(){
        return new CanaryInventory((OTileEntityChest)tileentity);
    }
    
    @Override
    public void clearContents() {
        largechest.clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int arg0, int arg1) {
        return largechest.decreaseItemStackSize(arg0, arg1).getCanaryItem();
    }

    @Override
    public CanaryItem[] getContents() {
        OItemStack[] oStacks = largechest.getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];
        for(int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }
        
        return items;
    }

    @Override
    public String getInventoryName() {
        return largechest.getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return largechest.getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return largechest.getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return largechest.getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
       return largechest.getItem(id, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        return largechest.getSlot(index).getCanaryItem();
    }

    @Override
    public boolean hasItem(int itemId) {
        return largechest.hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
       return largechest.hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return largechest.removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return largechest.removeItem(id);
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                oStacks[i] = ((CanaryItem)items[i]).getHandle();
            }
            else {
                oStacks[i] = null;
            }
        }
        largechest.setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        largechest.setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item value) {
        largechest.setSlot(index, ((CanaryItem)value).getHandle());
    }
    
    public OTileEntityChest getHandleA(){
        return (OTileEntityChest)largechest.b;
    }
    
    public OTileEntityChest getHandleB(){
        return (OTileEntityChest)largechest.c;
    }

}
