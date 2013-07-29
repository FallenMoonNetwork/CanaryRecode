package net.canarymod.api.inventory;

import java.util.Arrays;
import net.minecraft.server.ItemStack;

public class CanaryCustomStorageInventory extends CanaryContainerEntity implements CustomStorageInventory {

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

    @Override
    public void clearContents() {
        Arrays.fill(getHandle().c, null);
    }

    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getHandle().c, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getHandle().c);
    }

    @Override
    public void setContents(Item[] items) {
        getHandle().c = Arrays.copyOf(CanaryItem.itemArrayToStackArray(items), getHandle().c.length);
    }

    @Override
    public void setInventoryName(String value) {
        getHandle().setName(value);
    }

    @Override
    public void update() {
        getHandle().k_();
    }

    public NativeCustomStorageInventory getHandle() {
        return (NativeCustomStorageInventory) inventory;
    }

}
