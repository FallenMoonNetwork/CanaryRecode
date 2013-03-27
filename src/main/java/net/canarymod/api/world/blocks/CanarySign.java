package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntitySign;


public class CanarySign extends CanaryComplexBlock implements Sign {

    public CanarySign(TileEntitySign sign) {
        super(sign);
    }

    @Override
    public String[] getText() {
        return ((TileEntitySign) tileentity).a;
    }

    @Override
    public String getTextOnLine(int line) {
        if (line > 0 && line < 3) {
            return ((TileEntitySign) tileentity).a[line];
        }
        return null;
    }

    @Override
    public void setText(String[] text) {
        ((TileEntitySign) tileentity).a = text;
    }

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
}
