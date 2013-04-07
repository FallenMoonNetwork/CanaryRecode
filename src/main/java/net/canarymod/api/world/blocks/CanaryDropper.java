package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntityDropper;


/**
 * Dropper wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryDropper extends CanaryDispenser implements Dropper {

    /**
     * Constructs a new wrapper for TileEntityDropper
     * 
     * @param tileentity
     *            the TileEntityDropper to wrap
     */
    public CanaryDropper(TileEntityDropper tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityDropper getTileEntity() {
        return (TileEntityDropper) tileentity;
    }
}
