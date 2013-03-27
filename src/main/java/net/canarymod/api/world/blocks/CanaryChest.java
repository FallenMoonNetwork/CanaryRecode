package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.TileEntityChest;


public class CanaryChest extends CanaryComplexBlock implements Chest {

    public CanaryChest(TileEntityChest tileentity) {
        super(tileentity);
    }

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

    @Override
    public OTileEntityChest getHandle() {
        return (OTileEntityChest) tileentity;
    }

    @Override
    public void clearContents() {
        ((OTileEntityChest) tileentity).clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        OItemStack item = ((OTileEntityChest) tileentity).decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        OItemStack[] oStacks = ((OTileEntityChest) tileentity).getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public String getInventoryName() {
        return ((OTileEntityChest) tileentity).getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return ((OTileEntityChest) tileentity).getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OTileEntityChest) tileentity).getInventoryStackLimit();
    }

    @Override
    public Item getItem(int itemId) {
        return ((OTileEntityChest) tileentity).getItem(itemId);
    }

    @Override
    public Item getItem(int itemId, int amount) {
        return ((OTileEntityChest) tileentity).getItem(itemId, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        OItemStack item = ((OTileEntityChest) tileentity).getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((OTileEntityChest) tileentity).hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return ((OTileEntityChest) tileentity).hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return ((OTileEntityChest) tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int itemId) {
        return ((OTileEntityChest) tileentity).removeItem(itemId);
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                oStacks[i] = ((CanaryItem) items[i]).getHandle();
            } else {
                oStacks[i] = null;
            }
        }
        ((OTileEntityChest) tileentity).setContents(oStacks);
    }

    @Override
    public void setInventoryName(String name) {
        ((OTileEntityChest) tileentity).setInventoryName(name);
    }

    @Override
    public void setSlot(int index, Item item) {
        ((OTileEntityChest) tileentity).setSlot(index, ((CanaryItem) item).getHandle());
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((OTileEntityChest) tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((OTileEntityChest) tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((OTileEntityChest) tileentity).getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((OTileEntityChest) tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((OTileEntityChest) tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }
}
