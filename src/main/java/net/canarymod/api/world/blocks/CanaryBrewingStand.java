package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.Container;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityBrewingStand;


/**
 * BrewingStand wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBrewingStand extends CanaryContainerBlock implements BrewingStand {

    /**
     * Constructs a new wrapper for TileEntityBrewingStand
     * 
     * @param tileentity
     *            the TileEntityBrewingStand to be wrapped
     */
    public CanaryBrewingStand(TileEntityBrewingStand tileentity) {
        super(tileentity);
    }

    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().c, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().c, getTileEntity().c.length);
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().c);
    }

    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().c, 0, getSize());
    }

    @Override
    public void setInventoryName(String value) {
        getTileEntity().a(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityBrewingStand getTileEntity() {
        return (TileEntityBrewingStand) tileentity;
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
