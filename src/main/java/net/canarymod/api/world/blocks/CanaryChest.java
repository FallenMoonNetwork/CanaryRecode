package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.Container;
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

    // TODO: Implement hasAttachedChest and the private tryAttachedChest!!
    // Requres changes to the Block interface
    @Override
    public boolean hasAttachedChest() {
        // Block block = getBlock();
        // DoubleChest result;
        //
        // result = tryAttachedChest(block, BlockFace.NORTH);
        // if (result != null) {
        // return result;
        // }
        //
        // result = tryAttachedChest(block, Block.Face.Back);
        // if (result != null) {
        // return result;
        // }
        //
        // result = tryAttachedChest(block, Block.Face.Left);
        // if (result != null) {
        // return result;
        // }
        //
        // result = tryAttachedChest(block, Block.Face.Right);
        // if (result != null) {
        // return result;
        // }
        //
        // return result != null;
        return false;
    }

    // private DoubleChest tryAttachedChest(Block origin, BlockFace face) {
    // Block block = origin.getFace(face);
    //
    // if (BlockType.fromId(block.getType()) == BlockType.Chest) {
    // ComplexBlock cblock = getWorld().getOnlyComplexBlock(block);
    //
    // if ((cblock != null) && (cblock instanceof Chest)) {
    // Chest chest = (Chest) cblock;
    //
    // return new DoubleChest(new OInventoryLargeChest(getName(), container, chest.container));
    // }
    // }
    //
    // return null;
    // }

    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().i, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().i, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().i);
    }

    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().i, 0, getSize());
    }

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

    /**
     * @throws UnsupportedOperationException
     *             this isn't a Minecraft Container instance
     */
    @Override
    public Container getContainer() {
        throw new UnsupportedOperationException("Not a Container");
    }
}
