package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.minecraft.server.Container;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityDispenser;


/**
 * Dispenser wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryDispenser extends CanaryContainerBlock implements Dispenser {
    // private Random random = new Random();

    /**
     * Constructs a new wrapper for TileEntityDispenser
     * 
     * @param tileentity
     *            the TileEntityDispenser to be wrapped
     */
    public CanaryDispenser(TileEntityDispenser tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity activate() {
        return dispense(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity dispenseFromSlot(int slot) {
        Item stack = getSlot(slot);

        if (stack != null) {
            return dispense(((CanaryItem) stack).getHandle());
        } else {
            ((CanaryWorld) getWorld()).getHandle().e(1001, this.getX(), this.getY(), this.getZ(), 0);
        }
        return null;
    }

    private Entity dispense(ItemStack item) {
        // Um... hmmmm...
        return null;
    }

    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().b, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().b, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().b);
    }

    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().b, 0, getSize());
    }

    @Override
    public void setInventoryName(String value) {
        getTileEntity().a(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityDispenser getTileEntity() {
        return (TileEntityDispenser) tileentity;
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
