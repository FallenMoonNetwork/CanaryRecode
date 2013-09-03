package net.canarymod.api.inventory;

import java.util.Arrays;
import net.minecraft.server.ItemStack;

public class CanaryCustomStorageInventory extends CanaryEntityInventory implements CustomStorageInventory {

    public CanaryCustomStorageInventory(NativeCustomStorageInventory inventory) {
        super(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CUSTOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getHandle().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getHandle().c, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getHandle().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        getHandle().c = Arrays.copyOf(CanaryItem.itemArrayToStackArray(items), getHandle().c.length);
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

    public NativeCustomStorageInventory getHandle() {
        return (NativeCustomStorageInventory) inventory;
    }

}
