package net.canarymod.api.world.blocks;

import net.minecraft.server.TileEntityDaylightDetector;

/**
 * DaylightDetector wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryDaylightDetector extends CanaryComplexBlock implements DaylightDetector {

    /**
     * Constructs a new wrapper for TileEntityDaylightDetector
     * 
     * @param tileentity
     *            the TileEntityDaylightDetector
     */
    public CanaryDaylightDetector(TileEntityDaylightDetector tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityDaylightDetector getTileEntity() {
        return (TileEntityDaylightDetector) tileentity;
    }

}
