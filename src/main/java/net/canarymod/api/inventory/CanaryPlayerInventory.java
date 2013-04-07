package net.canarymod.api.inventory;


import java.util.Arrays;
import net.minecraft.server.InventoryPlayer;
import net.minecraft.server.ItemStack;


/**
 * PlayerInventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPlayerInventory extends CanaryContainerEntity implements PlayerInventory {

    public CanaryPlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getArmorSlot(int slot) {
        return getSlot(slot + 36);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArmorSlot(Item item) {// WAT
    }

    public ItemStack getItemInHand() {
        return ((InventoryPlayer) inventory).h();
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

    public InventoryPlayer getInventoryHandle() {
        return (InventoryPlayer) inventory;
    }

}
