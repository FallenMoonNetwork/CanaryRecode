package net.canarymod.api.world.blocks;

import java.util.Random;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;

public class CanaryBlock implements Block {
    private final static Random rndm = new Random(); // Passed to the idDropped method
    protected short type, data;
    protected byte status;
    protected int x, y, z;
    protected World dimension;
    protected BlockFace faceClicked;

    public CanaryBlock(short type, short data, int x, int y, int z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.data = data;
        status = 0;
        this.dimension = world;
    }

    @Override
    public short getTypeId() {
        return type;
    }

    @Override
    public void setTypeId(short type) {
        this.type = type;

    }

    @Override
    public BlockType getType() {
        return BlockType.fromIdAndData(type, data);
    }

    @Override
    public void setType(BlockType type) {
        this.type = type.getId();
        this.data = type.getData();

    }

    @Override
    public short getData() {
        return data;
    }

    @Override
    public void setData(short data) {
        this.data = data;
    }

    @Override
    public World getWorld() {
        return this.dimension;
    }

    @Override
    public void setWorld(World dimension) {
        this.dimension = dimension;
    }

    @Override
    public BlockFace getFaceClicked() {
        return faceClicked;
    }

    @Override
    public void setFaceClicked(BlockFace face) {
        faceClicked = face;
    }

    @Override
    public void update() {
        dimension.setBlock(this);
        dimension.markBlockNeedsUpdate(x, y, z);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public byte getStatus() {
        return status;
    }

    @Override
    public boolean isAir() {
        return type == 0;
    }

    @Override
    public BlockMaterial getBlockMaterial() {
        return net.minecraft.server.Block.s[type].cU.getCanaryBlockMaterial();
    }

    @Override
    public Location getLocation() {
        return new Location(dimension, x, y, z, 0, 0);
    }

    @Override
    public Position getPosition() {
        return new Position(x, y, z);
    }

    @Override
    public Block getFacingBlock(BlockFace face) {
        switch (face) {
            case BOTTOM:
                return getRelative(0, -1, 0);

            case EAST:
                return getRelative(0, 0, -1);

            case NORTH:
                return getRelative(1, 0, 0);

            case SOUTH:
                return getRelative(-1, 0, 0);

            case TOP:
                return getRelative(0, 1, 0);

            case UNKNOWN:
                break;

            case WEST:
                return getRelative(0, 0, 1);

            default:
                break;
        }
        return null;
    }

    @Override
    public Block getRelative(int x, int y, int z) {
        return this.dimension.getBlockAt(getX() + x, getY() + y, getZ() + z);
    }

    @Override
    public int getIdDropped() {
        return net.minecraft.server.Block.s[getTypeId()].a(0, rndm, 0);
    }

    @Override
    public int getDamageDropped() {
        return net.minecraft.server.Block.s[getTypeId()].a(getData());
    }

    @Override
    public int getQuantityDropped() {
        return net.minecraft.server.Block.s[getTypeId()].a(rndm);
    }

    @Override
    public void dropBlockAsItem(boolean remove) {
        net.minecraft.server.Block.s[getTypeId()].c(((CanaryWorld) getWorld()).getHandle(), getX(), getY(), getZ(), getData(), 1);
        if (remove) {
            this.setTypeId((short) 0);
            this.update();
        }
    }

    @Override
    public TileEntity getTileEntity() {
        return getWorld().getTileEntity(this);
    }

    @Override
    public boolean rightClick(Player player) {
        return net.minecraft.server.Block.s[getTypeId()].a(((CanaryWorld) getWorld()).getHandle(), getX(), getY(), getZ(), player != null ? ((CanaryPlayer) player).getHandle() : null, 0, 0, 0, 0); // last four parameters aren't even used by lever or button
    }

    @Override
    public String toString() {
        return String.format("Block[Type=%s TypeId=%d, data=%d, x=%d, y=%d, z=%d, world=%s, dim=%d]", BlockType.fromIdAndData(type, data).getMachineName(), this.type, this.data, this.x, this.y, this.z, this.dimension.getName(), this.dimension.getType().getId());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CanaryBlock other = (CanaryBlock) obj;

        if (this.x != other.getX()) {
            return false;
        }
        if (this.y != other.getY()) {
            return false;
        }
        if (this.z != other.getZ()) {
            return false;
        }
        if (!this.getWorld().equals(other.getWorld())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this block
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + this.data;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.z;
        hash = 97 * hash + this.type;
        return hash;
    }
}
