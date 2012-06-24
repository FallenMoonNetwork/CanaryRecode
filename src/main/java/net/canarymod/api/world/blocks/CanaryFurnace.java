package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityFurnace;

public class CanaryFurnace extends CanaryComplexBlock implements Furnace, Container<OItemStack>{

    public CanaryFurnace(OTileEntityFurnace tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory(this);
    }

    @Override
    public void clearContents() {
        ((OTileEntityFurnace)tileentity).clearContents();
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        return ((OTileEntityFurnace)tileentity).decreaseItemStackSize(arg0, arg1);
    }

    @Override
    public OItemStack[] getContents() {
        return ((OTileEntityFurnace)tileentity).getContents();
    }

    @Override
    public String getInventoryName() {
        return ((OTileEntityFurnace)tileentity).getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return ((OTileEntityFurnace)tileentity).getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OTileEntityFurnace)tileentity).getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return ((OTileEntityFurnace)tileentity).getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
       return ((OTileEntityFurnace)tileentity).getItem(id, amount);
    }

    @Override
    public OItemStack getSlot(int index) {
        return ((OTileEntityFurnace)tileentity).getSlot(index);
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((OTileEntityFurnace)tileentity).hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
       return ((OTileEntityFurnace)tileentity).hasItemStack(oItemStack);
    }

    @Override
    public Item removeItem(Item item) {
        return ((OTileEntityFurnace)tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((OTileEntityFurnace)tileentity).removeItem(id);
    }

    @Override
    public void setContents(OItemStack[] values) {
        ((OTileEntityFurnace)tileentity).setContents(values);
    }

    @Override
    public void setInventoryName(String value) {
        ((OTileEntityFurnace)tileentity).setInventoryName(value);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        ((OTileEntityFurnace)tileentity).setSlot(index, value);
    }
    
    public OTileEntityFurnace getHandle(){
        return (OTileEntityFurnace)tileentity;
    }

}
