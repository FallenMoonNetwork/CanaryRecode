package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
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
    public void clearContents() {
        getHandle().clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        ItemStack item = getHandle().decreaseItemStackSize(itemId, amount);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        ItemStack[] oStacks = getHandle().getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];

        for (int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }

        return items;
    }

    @Override
    public String getInventoryName() {
        return getHandle().getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return getHandle().getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return getHandle().getInventoryStackLimit();
    }

    @Override
    public Item getItem(int itemId) {
        return getHandle().getItem(itemId);
    }

    @Override
    public Item getItem(int itemId, int amount) {
        return getHandle().getItem(itemId, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        ItemStack item = getHandle().getSlot(index);

        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return getHandle().hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
        return getHandle().hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return getHandle().removeItem(item);
    }

    @Override
    public Item removeItem(int itemId) {
        return getHandle().removeItem(itemId);
    }

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

    @Override
    public void setInventoryName(String name) {
        getHandle().setInventoryName(name);
    }

    @Override
    public void setSlot(int index, Item item) {
        getHandle().setSlot(index, ((CanaryItem) item).getHandle());
    }

    @Override
    public void addItem(int itemId, int amount) {
        getHandle().addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        getHandle().addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return getHandle().getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return getHandle().hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return getHandle().hasItemStack(itemId, minAmount, maxAmount);
    }

    @Override
    public TileEntityChest getHandle() {
        return (TileEntityChest) tileentity;
    }
}
