package net.canarymod.api.world.blocks;

import net.minecraft.server.TileEntityComparator;

/**
 * Comparator wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryComparator extends CanaryTileEntity implements Comparator {

    /**
     * Constructs a new wrapper for TileEntityComparator
     * 
     * @param tileentity
     *            the TileEntityComparator wrapper
     */
    public CanaryComparator(TileEntityComparator tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOutputSignal() {
        return getTileEntity().a();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOutputSignal(int signal) {
        getTileEntity().a(signal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityComparator getTileEntity() {
        return (TileEntityComparator) tileentity;
    }
}
