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
        return ((TileEntitySign) tileentity).a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextOnLine(int line) {
        if (line > 0 && line < 3) {
            return ((TileEntitySign) tileentity).a[line];
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(String[] text) {
        ((TileEntitySign) tileentity).a = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextOnLine(String text, int line) {
        if (line > 0 && line < 3) {
            ((TileEntitySign) tileentity).a[line] = text;
        }
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
    public TileEntitySign getHandle() {
        return (TileEntitySign) tileentity;
    }
}
