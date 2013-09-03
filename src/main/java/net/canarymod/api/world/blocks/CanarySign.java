package net.canarymod.api.world.blocks;

import java.util.Arrays;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.TileEntitySign;

/**
 * Sign wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySign extends CanaryTileEntity implements Sign {

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
        return getTileEntity().a.clone();
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
     * {@inheirtDoc}
     */
    @Override
    public Block getBlockAttached() {
        if (isSignPost()) {
            return getWorld().getBlockAt(getX(), getY() - 1, getZ());
        }
        switch (getBlock().getData()) {
            case 2: // Facing North / Attached is South
                return getWorld().getBlockAt(getX(), getY(), getZ() + 1);
            case 3: // Facing South / Attached is North
                return getWorld().getBlockAt(getX(), getY(), getZ() - 1);
            case 4: // Facing West / Attached is East
                return getWorld().getBlockAt(getX() + 1, getY(), getZ());
            case 5: // Facing East / Attached is West
                return getWorld().getBlockAt(getX() - 1, getY(), getZ());
            default:
                return null;
        }
    }

    @Override
    public boolean isEditable() {
        return getTileEntity().c;
    }

    @Override
    public void setEditable(boolean edit) {
        getTileEntity().c = edit;
    }

    @Override
    public String getOwnerName() {
        return getTileEntity().getOwnerName();
    }

    @Override
    public Player getOwner() {
        EntityPlayer entityplayer = getTileEntity().b();
        return (Player) (entityplayer == null ? null : entityplayer.getCanaryEntity());
    }

    @Override
    public void setOwner(Player player) {
        getTileEntity().a(player == null ? null : ((CanaryPlayer) player).getHandle());
    }

    /**
     * Returns a String value representing this Block
     * 
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Sign[X=%d Y=%d Z=%d SignType=%s Text1=%s Text2=%s Text3=%s Text4=%s]", getX(), getY(), getZ(), isWallSign() ? "WallSign" : "SignPost", getTileEntity().a[0], getTileEntity().a[1], getTileEntity().a[2], getTileEntity().a[3]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CanarySign)) {
            return false;
        }
        CanarySign other = (CanarySign) obj;
        if (!Arrays.equals(this.getText(), other.getText())) {
            return false;
        }
        return super.equals(obj);
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
