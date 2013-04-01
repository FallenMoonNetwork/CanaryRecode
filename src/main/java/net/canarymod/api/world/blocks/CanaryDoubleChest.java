package net.canarymod.api.world.blocks;


import java.util.Arrays;
import net.canarymod.ToolBox;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.TileEntityChest;


/**
 * DoubleChest implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryDoubleChest extends CanaryChest implements DoubleChest {
    private InventoryLargeChest largechest;

    /**
     * Constructs a new wrapper for InventoryLargeChest
     * 
     * @param largechest
     *            the InventoryLargeChest to be wrapped
     */
    public CanaryDoubleChest(InventoryLargeChest largechest) {
        super((TileEntityChest) largechest.b);
        this.largechest = largechest;
    }

    @Override
    public void clearContents() {
        getHandleA().getCanaryChest().clearContents();
        getHandleB().getCanaryChest().clearContents();
    }

    @Override
    public Item[] clearInventory() {
        Item[] itemsA = Arrays.copyOf(getHandleA().getCanaryChest().getContents(), getHandleA().getCanaryChest().getSize());
        Item[] itemsB = Arrays.copyOf(getHandleB().getCanaryChest().getContents(), getHandleB().getCanaryChest().getSize());
        clearContents();
        Item[] toRet = ToolBox.arrayMerge(itemsA, itemsB);
        return toRet;
    }

    @Override
    public Item[] getContents() {
        return ToolBox.arrayMerge(getHandleA().getCanaryChest().getContents(), getHandleB().getCanaryChest().getContents());
    }

    @Override
    public void setContents(Item[] items) {
        // TODO
    }

    /**
     * Gets the TileEntityChest part A
     * 
     * @return part A
     */
    public TileEntityChest getHandleA() {
        return (TileEntityChest) largechest.b;
    }

    /**
     * Gets the TileEntityChest part B
     * 
     * @return part B
     */
    public TileEntityChest getHandleB() {
        return (TileEntityChest) largechest.c;
    }
}
