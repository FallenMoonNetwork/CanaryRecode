package net.canarymod.api.world.blocks;


import java.util.Arrays;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityChest;


/**
 * Chest wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryChest extends CanaryContainerBlock implements Chest {

    /**
     * Constructs a new wrapper for TileEntityChest
     *
     * @param tileentity
     *            the TileEntityChest to be wrapped
     */
    public CanaryChest(TileEntityChest tileentity) {
        super(tileentity);
    }

    @Override
    public boolean hasAttachedChest() {
         Block block = getBlock();
         DoubleChest result;

         result = tryAttachedChest(block, BlockFace.NORTH);
         if (result != null) {
         return true;
         }

         result = tryAttachedChest(block, BlockFace.SOUTH);
         if (result != null) {
         return true;
         }

         result = tryAttachedChest(block, BlockFace.EAST);
         if (result != null) {
         return true;
         }

         result = tryAttachedChest(block, BlockFace.WEST);
         if (result != null) {
         return true;
         }
         return false;
    }

    private DoubleChest tryAttachedChest(Block origin, BlockFace face) {
        Block block = origin.getFacingBlock(face);
        if (block == null) {
            return null;
        }
        if (block.getType() == BlockType.Chest) {
            ComplexBlock cblock = getWorld().getOnlyComplexBlock(block);

            if ((cblock != null) && (cblock instanceof Chest)) {
                Chest chest = (Chest) cblock;

                return new CanaryDoubleChest(new InventoryLargeChest(getName(), this.inventory, ((CanaryChest)chest).getInventoryHandle()));
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().i, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().i, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().i, 0, getSize());
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
    public TileEntityChest getTileEntity() {
        return (TileEntityChest) tileentity;
    }

    @Override
    public DoubleChest getDoubleChest() {
        DoubleChest chest;
        chest = tryAttachedChest(getBlock(), BlockFace.NORTH);
        if(chest != null) {
            return chest;
        }

        chest = tryAttachedChest(getBlock(), BlockFace.SOUTH);
        if(chest != null) {
            return chest;
        }

        chest = tryAttachedChest(getBlock(), BlockFace.EAST);
        if(chest != null) {
            return chest;
        }

        chest = tryAttachedChest(getBlock(), BlockFace.WEST);
        if(chest != null) {
            return chest;
        }
        return null;
    }
}
