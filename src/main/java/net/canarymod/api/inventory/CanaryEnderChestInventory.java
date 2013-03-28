package net.canarymod.api.inventory;


import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.InventoryEnderChest;

/**
 * EnderChest Inventory implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderChestInventory extends CanaryInventory implements EnderChestInventory {

    public CanaryEnderChestInventory(InventoryEnderChest echest) {
        super(echest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getInventoryOwner() {
        // TODO: Make this work
        return null;
    }

}
