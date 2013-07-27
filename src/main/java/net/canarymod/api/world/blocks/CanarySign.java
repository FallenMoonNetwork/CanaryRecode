package net.canarymod.api.world.blocks;

import java.util.Arrays;
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
        getTileEntity().a = insureData(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextOnLine(String text, int line) {
        if (line >= 0 && line <= 3) {
            getTileEntity().a[line] = text == null ? "" : text.length() > 15 ? text.substring(0, 15) : text;
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
        return String.format("Sign [x=%d, y=%d, z=%d, SignType=%s, Text1=%s, Text2=%s, Text3=%s, Text4=%s]", getX(), getY(), getZ(), isWallSign() ? "WallSign" : "SignPost", getTileEntity().a[0], getTileEntity().a[1], getTileEntity().a[2], getTileEntity().a[3]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntitySign getTileEntity() {
        return (TileEntitySign) tileentity;
    }

    /**
     * Corrects sign text to insure it matches perfectly to the expected length
     * 
     * @param args
     *            the String array
     * @return corrected String array
     */
    private String[] insureData(String[] args) {
        String[] toRet = args.clone();
        if (toRet.length < 4 || toRet.length > 4) {
            String[] nu = new String[4];
            Arrays.fill(nu, "");
            System.arraycopy(toRet, 0, nu, 0, toRet.length < 4 ? toRet.length : 4);
            toRet = nu;
        }
        for (int index = 0; index <= 3; index++) {
            if (toRet[index] == null) {
                toRet[index] = "";
            }
            else if (toRet[index].length() > 15) {
                toRet[index] = toRet[index].substring(0, 15);
            }
        }
        return toRet;
    }
}
