package net.canarymod.api.world.blocks;

import java.util.Arrays;

import net.canarymod.api.inventory.CanaryBlockInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityFurnace;

/**
 * Furnace wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFurnace extends CanaryBlockInventory implements Furnace {

    /**
     * Constructs a new wrapper for TileEntityFurnace
     * 
     * @param tileentity
     *            the TileEntityFurnace to be wrapped
     */
    public CanaryFurnace(TileEntityFurnace tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.FURNACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getBurnTime() {
        return (short) getTileEntity().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBurnTime(short time) {
        getTileEntity().a = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getCookTime() {
        return (short) getTileEntity().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCookTime(short time) {
        getTileEntity().c = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().g, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().g, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().g);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().g, 0, getSize());
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
    public TileEntityFurnace getTileEntity() {
        return (TileEntityFurnace) tileentity;
    }
}
