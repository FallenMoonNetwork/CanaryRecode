package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Inventory;
import net.minecraft.server.OTileEntityBrewingStand;

public class CanaryBrewingStand extends CanaryComplexBlock implements BrewingStand{

    public CanaryBrewingStand(OTileEntityBrewingStand tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((OTileEntityBrewingStand)tileentity);
    }

}
