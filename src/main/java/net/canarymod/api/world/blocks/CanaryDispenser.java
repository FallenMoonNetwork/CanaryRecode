package net.canarymod.api.world.blocks;

import java.util.Arrays;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.minecraft.server.BlockDispenser;
import net.minecraft.server.BlockSourceImpl;
import net.minecraft.server.IBehaviorDispenseItem;
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
    public InventoryType getInventoryType() {
        return InventoryType.DISPENSER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity activate() {
        return dispense(null, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity dispenseFromSlot(int slot) {
        Item stack = getSlot(slot);

        if (stack != null) {
            return dispense(((CanaryItem) stack).getHandle(), slot);
        } else {
            ((CanaryWorld) getWorld()).getHandle().e(1001, this.getX(), this.getY(), this.getZ(), 0);
        }
        return null;
    }

    private Entity dispense(ItemStack item, int slot) {
        if (item != null) {
            BlockSourceImpl blocksourceimpl = new BlockSourceImpl(((CanaryWorld) getWorld()).getHandle(), getX(), getY(), getZ());
            IBehaviorDispenseItem ibehaviordispenseitem = (IBehaviorDispenseItem) BlockDispenser.a.a(item.b());

            if (ibehaviordispenseitem != IBehaviorDispenseItem.a) {
                ItemStack itemstack1 = ibehaviordispenseitem.a(blocksourceimpl, item);

                if (slot != -1) {
                    getTileEntity().a(slot, itemstack1.b == 0 ? null : itemstack1);
                }
            }
        }
        return null; // No way to get the entity with the behavior classes as is without major refactoring of native code...
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().b, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().b, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().b, 0, getSize());
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
    public TileEntityDispenser getTileEntity() {
        return (TileEntityDispenser) tileentity;
    }
}
