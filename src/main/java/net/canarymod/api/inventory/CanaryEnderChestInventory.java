package net.canarymod.api.inventory;


import java.util.Arrays;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.InventoryEnderChest;
import net.minecraft.server.ItemStack;

/**
 * EnderChest Inventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderChestInventory extends CanaryContainerEntity implements EnderChestInventory {
    private Player player;

    public CanaryEnderChestInventory(InventoryEnderChest echest, Player player) {
        super(echest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getInventoryOwner() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getInventoryHandle().c, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getInventoryHandle().c, getSize());
        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getInventoryHandle().c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getInventoryHandle().c, 0, getSize());
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

    public InventoryEnderChest getInventoryHandle() {
        return (InventoryEnderChest) inventory;
    }

}
