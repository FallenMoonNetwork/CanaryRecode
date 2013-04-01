package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.Container;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityFurnace;


public class CanaryFurnace extends CanaryContainerBlock implements Furnace {

    /**
     * Constructs a new wrapper for TileEntityFurnace
     * 
     * @param tileentity
     *            the TileEntityFurnace to be wrapped
     */
    public CanaryFurnace(TileEntityFurnace tileentity) {
        super(tileentity);
    }

    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().g, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().g, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().g);
    }

    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().g, 0, getSize());
    }

    @Override
    public void setInventoryName(String value) {
        getTileEntity().a(value);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityFurnace getTileEntity() {
        return (TileEntityFurnace) tileentity;
    }

    /**
     * @throws UnsupportedOperationException
     *             this isn't a Minecraft Container instance
     */
    @Override
    public Container getContainer() {
        throw new UnsupportedOperationException("Not a Container");
    }
}
