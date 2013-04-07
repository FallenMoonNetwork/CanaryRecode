package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntitySkull;


/**
 * Skull wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySkull extends CanaryComplexBlock implements Skull {

    /**
     * Constructs a new wrapper for TileEntitySkull
     * 
     * @param tileentity
     *            the TileEntitySkull to wrap
     */
    public CanarySkull(TileEntitySkull tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSkullType() {
        return getTileEntity().a();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkullType(int type) {
        this.setSkullAndExtraType(type, getExtraType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtraType() {
        return getTileEntity().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExtraType(String extra) {
        this.setSkullAndExtraType(getSkullType(), extra);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkullAndExtraType(int type, String extra) {
        getTileEntity().a(type, extra);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRotation() {
        return getTileEntity().getRotation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotation(int rot) {
        getTileEntity().a(rot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntitySkull getTileEntity() {
        return (TileEntitySkull) tileentity;
    }
}
