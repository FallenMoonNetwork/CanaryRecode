package net.canarymod.api.world.blocks;

import net.minecraft.server.TileEntitySign;

/**
 * Sign wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySign extends CanaryComplexBlock implements Sign {

    /**
     * Constructs a new wrapper for TileEntitySign
     * 
     * @param tileentity
     *            the TileEntitySign to be wrapped
     */
    public CanarySign(TileEntitySign tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getText() {
        return getTileEntity().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextOnLine(int line) {
        if (line >= 0 && line <= 3) {
            return getTileEntity().a[line];
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(String[] text) {
        getTileEntity().a = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextOnLine(String text, int line) {
        if (line >= 0 && line <= 3) {
            getTileEntity().a[line] = text;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWallSign() {
        return getBlock().getType() == BlockType.WallSign;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSignPost() {
        return getBlock().getType() == BlockType.SignPost;
    }

    /**
     * Returns a String value representing this Block
     * 
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Sign [x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntitySign getTileEntity() {
        return (TileEntitySign) tileentity;
    }
}
