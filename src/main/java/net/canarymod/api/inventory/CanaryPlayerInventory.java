package net.canarymod.api.inventory;

import java.util.Arrays;
import net.minecraft.server.InventoryPlayer;
import net.minecraft.server.ItemStack;

/**
 * PlayerInventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPlayerInventory extends CanaryEntityInventory implements PlayerInventory {

    public CanaryPlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getHelmetSlot() {
        return getSlot(39);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHelmetSlot(Item item) {
        setSlot(39, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getChestplateSlot() {
        return getSlot(38);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChestPlateSlot(Item item) {
        setSlot(38, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getLeggingsSlot() {
        return getSlot(37);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeggingsSlot(Item item) {
        setSlot(37, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getBootsSlot() {
        return getSlot(36);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBootsSlot(Item item) {
        setSlot(36, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedHotbarSlotId() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItemInHand() {
        ItemStack is = getHandle().h();
        return is == null ? null : is.getCanaryItem();
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
        getHandle().a = Arrays.copyOf(CanaryItem.itemArrayToStackArray(items), getHandle().a.length);
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
    public InventoryPlayer getHandle() {
        return (InventoryPlayer) inventory;
    }
}
