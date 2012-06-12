package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Inventory;
import net.minecraft.server.OTileEntityFurnace;

public class CanaryFurnace extends CanaryComplexBlock implements Furnace{

    public CanaryFurnace(OTileEntityFurnace tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((OTileEntityFurnace)tileentity);
    }

}
