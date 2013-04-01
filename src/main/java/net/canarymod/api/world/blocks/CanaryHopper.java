package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.Container;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityHopper;

public class CanaryHopper extends CanaryContainerBlock {

    public CanaryHopper(TileEntityHopper hopper) {
        super(hopper);
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
    public Item[] getContents() {
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
    public TileEntity getTileEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container getContainer() {
        // TODO Auto-generated method stub
        return null;
    }

}
