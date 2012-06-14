package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.minecraft.server.OContainerWorkbench;
import net.minecraft.server.OItemStack;

public class CanaryWorkbench implements Workbench, Container<OItemStack>{
    
    private OContainerWorkbench container;

    public CanaryWorkbench(OContainerWorkbench container) {
        this.container = container;
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory(this);
    }
    
    @Override
    public Block getBlock() {
        return getDimension().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public int getX(){
        return container.h;
    }
    
    @Override
    public int getY(){
        return container.i;
    }
    
    @Override
    public int getZ(){
        return container.j;
    }
    
    @Override
    public Dimension getDimension(){
        return container.c.getCanaryDimension();
    }
    
    @Override
    public void update(){
        container.b.update();
    }

    @Override
    public void clearContents() {
        container.a.clearContents();
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        return container.a.decreaseItemStackSize(arg0, arg1);
    }

    @Override
    public OItemStack[] getContents() {
        return container.a.getContents();
    }

    @Override
    public String getInventoryName() {
        return container.a.getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return container.a.getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return container.a.getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return container.a.getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
       return container.a.getItem(id, amount);
    }

    @Override
    public OItemStack getSlot(int index) {
        return container.a.getSlot(index);
    }

    @Override
    public boolean hasItem(int itemId) {
        return container.a.hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
       return container.a.hasItemStack(oItemStack);
    }

    @Override
    public Item removeItem(Item item) {
        return container.a.removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return container.a.removeItem(id);
    }

    @Override
    public void setContents(OItemStack[] values) {
        container.a.setContents(values);
    }

    @Override
    public void setInventoryName(String value) {
        container.a.setInventoryName(value);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        container.a.setSlot(index, value);
    }
    
    public OContainerWorkbench getHandle(){
        return container;
    }
}
