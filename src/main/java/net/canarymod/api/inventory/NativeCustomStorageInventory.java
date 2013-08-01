package net.canarymod.api.inventory;

import net.minecraft.server.InventoryBasic;

/**
 * Native Custom Storage Inventory instance
 * 
 * @author Jason (darkdiplomat)
 */
public class NativeCustomStorageInventory extends InventoryBasic {
    private final CanaryCustomStorageInventory ccsi;

    public NativeCustomStorageInventory(int num_rows) {
        super("custom", false, num_rows > 6 ? 54 : num_rows * 9);
        this.ccsi = new CanaryCustomStorageInventory(this);
    }

    public NativeCustomStorageInventory(int num_rows, String name) {
        super(name, false, num_rows > 6 ? 54 : num_rows * 9);
        this.ccsi = new CanaryCustomStorageInventory(this);
    }

    public CanaryCustomStorageInventory getCanaryCustomInventory() {
        return this.ccsi;
    }
}
