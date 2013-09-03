package net.canarymod.api.world.blocks;

import java.util.Arrays;

import net.canarymod.api.inventory.CanaryBlockInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityBrewingStand;

/**
 * BrewingStand wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBrewingStand extends CanaryBlockInventory implements BrewingStand {

    /**
     * Constructs a new wrapper for TileEntityBrewingStand
     * 
     * @param tileentity
     *            the TileEntityBrewingStand to be wrapped
     */
    public CanaryBrewingStand(TileEntityBrewingStand tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.BREWING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().c, getTileEntity().c.length);

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().c, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
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
}
