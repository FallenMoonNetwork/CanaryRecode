package net.canarymod.api.world.blocks;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.TileEntityRecordPlayer;


/**
 * Jukebox wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryJukebox extends CanaryComplexBlock implements Jukebox {

    /**
     * Constructs a new wrapper for TileEntityRecordPlayer
     * 
     * @param tileentity
     *            the TileEntityRecordPlayer to be wrapped
     */
    public CanaryJukebox(TileEntityRecordPlayer tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getDisc() {
        return getTileEntity().a().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisc(Item disc) {
        if (disc.getId() < 2256 || disc.getId() > 2267) {
            return;
        }
        getTileEntity().a(((CanaryItem) disc).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityRecordPlayer getTileEntity() {
        return (TileEntityRecordPlayer) tileentity;
    }
}
