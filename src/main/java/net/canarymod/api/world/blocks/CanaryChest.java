package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityChest;


/**
 * Chest wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChest extends CanaryComplexBlock implements Chest {

    /**
     * Constructs a new wrapper for TileEntityChest
     * 
     * @param tileentity
     *            the TileEntityChest to be wrapped
     */
    public CanaryChest(TileEntityChest tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return new CanaryInventory(((TileEntityChest) tileentity));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        getHandle().clearContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        ItemStack item = getHandle().decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem[] getContents() {
        ItemStack[] oStacks = getHandle().getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return getHandle().getInventoryName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return getHandle().getInventorySize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return getHandle().getInventoryStackLimit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int itemId) {
        return getHandle().getItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int itemId, int amount) {
        return getHandle().getItem(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem getSlot(int index) {
        ItemStack item = getHandle().getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        return getHandle().hasItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(Item item) {
        return getHandle().hasItemStack(((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return getHandle().removeItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int itemId) {
        return getHandle().removeItem(itemId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        ItemStack[] oStacks = new ItemStack[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                oStacks[i] = ((CanaryItem) items[i]).getHandle();
            } else {
                oStacks[i] = null;
            }
        }
        getHandle().setContents(oStacks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String name) {
        getHandle().setInventoryName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item item) {
        getHandle().setSlot(index, ((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, int amount) {
        getHandle().addItem(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        getHandle().addItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmptySlot() {
        return getHandle().getEmptySlot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return getHandle().hasItemStack(itemId, amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return getHandle().hasItemStack(itemId, minAmount, maxAmount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityChest getHandle() {
        return (TileEntityChest) tileentity;
    }
}
