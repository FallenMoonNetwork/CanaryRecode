package net.canarymod.api.inventory;


import net.minecraft.server.InventoryPlayer;
import net.minecraft.server.ItemStack;


public class CanaryPlayerInventory extends CanaryInventory implements PlayerInventory {

    public CanaryPlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
    }

    @Override
    public Item getArmorSlot(int slot) {
        return getSlot(slot + 36);
    }

    @Override
    public void setArmorSlot(Item item) {
        // WAT
    }

    public ItemStack getItemInHand() {
        return ((InventoryPlayer) container).h();
    }

}
