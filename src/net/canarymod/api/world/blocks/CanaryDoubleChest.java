package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
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
        return new CanaryInventory(this);
    }
    
    @Override
    public void clearContents() {
        largechest.clearContents();
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        return largechest.decreaseItemStackSize(arg0, arg1);
    }

    @Override
    public OItemStack[] getContents() {
        return largechest.getContents();
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
    public OItemStack getSlot(int index) {
        return largechest.getSlot(index);
    }

    @Override
    public boolean hasItem(int itemId) {
        return largechest.hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
       return largechest.hasItemStack(oItemStack);
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
    public void setContents(OItemStack[] values) {
        largechest.setContents(values);
    }

    @Override
    public void setInventoryName(String value) {
        largechest.setInventoryName(value);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        largechest.setSlot(index, value);
    }
    
    public OInventoryLargeChest getHandle(){
        return largechest;
    }
    
    public OTileEntityChest getHandleA(){
        return (OTileEntityChest)largechest.b;
    }
    
    public OTileEntityChest getHandleB(){
        return (OTileEntityChest)largechest.c;
    }

}
