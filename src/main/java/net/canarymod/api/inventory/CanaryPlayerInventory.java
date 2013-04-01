package net.canarymod.api.inventory;


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
    public void setArmorSlot(Item item) {
        // WAT
    }

    public ItemStack getItemInHand() {
        return ((InventoryPlayer) inventory).h();
    }

}
