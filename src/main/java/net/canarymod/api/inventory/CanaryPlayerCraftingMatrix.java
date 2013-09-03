package net.canarymod.api.inventory;

import java.util.Arrays;
import net.minecraft.server.InventoryCrafting;
import net.minecraft.server.ItemStack;

/**
 * PlayerCraftingMatrix wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPlayerCraftingMatrix extends CanaryEntityInventory implements PlayerCraftingMatrix {

    public CanaryPlayerCraftingMatrix(InventoryCrafting inventory) {
        super(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CRAFTMATRIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getHandle().a, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getHandle().a, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getHandle().a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getHandle().a, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getHandle().setName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getHandle().k_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryCrafting getHandle() {
        return (InventoryCrafting) inventory;
    }
}
