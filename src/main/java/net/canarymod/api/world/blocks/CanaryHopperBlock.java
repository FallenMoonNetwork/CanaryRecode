package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.minecraft.server.IInventory;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityHopper;


/**
 *
 * @author Somners
 */
public class CanaryHopperBlock extends CanaryContainerBlock implements HopperBlock {

    public CanaryHopperBlock(TileEntityHopper hopper) {
        super(hopper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInputInventory() {
        return this.getBaseContainerBlock(this.getTileEntity().getInputInventory());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getOutputInventory() {
        return this.getBaseContainerBlock(this.getTileEntity().getOutputInventory());
    }

    /**
     * Gets the Inventory from the inventory instance.
     * @param oiinventory OIInventory instance to get Inventory wrapper for.
     * @return The inventory or null if none.
     */
    private Inventory getBaseContainerBlock(IInventory iinventory) {
        if (iinventory instanceof TileEntity) {
            return (Inventory) ((TileEntity) iinventory).complexBlock;
        } else if (iinventory instanceof InventoryLargeChest) {
            return new CanaryDoubleChest((InventoryLargeChest) iinventory);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        return (this.isInputConnected() && this.isOutputConnected());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInputConnected() {
        return this.getInputInventory() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOutputConnected() {
        return this.getOutputInventory() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.getTileEntity().az().getCanaryWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPosX() {
        return this.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPosY() {
        return this.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPosZ() {
        return this.getZ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTranferCooldown() {
        return this.getTileEntity().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransferCooldown(int cooldown) {
        this.getTileEntity().c(cooldown);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityHopper getTileEntity() {
        return (TileEntityHopper) this.tileentity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().a, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(this.getTileEntity().a, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(this.getTileEntity().a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().a, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        this.getTileEntity().a(value);
    }
}
