package net.canarymod.api.inventory;

import java.util.Arrays;
import net.minecraft.server.InventoryCrafting;
import net.minecraft.server.ItemStack;

/**
 * PlayerCraftingMatrix wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPlayerCraftingMatrix extends CanaryContainerEntity implements PlayerCraftingMatrix {

    public CanaryPlayerCraftingMatrix(InventoryCrafting inventory) {
        super(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getInventoryHandle().a, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getInventoryHandle().a, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getInventoryHandle().a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getInventoryHandle().a, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getInventoryHandle().setName(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getInventoryHandle().k_();
    }

    public InventoryCrafting getInventoryHandle() {
        return (InventoryCrafting) inventory;
    }
}
