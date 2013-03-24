package net.canarymod.api.world.blocks;

import net.canarymod.api.world.World;
import net.minecraft.server.OTileEntity;

public class CanaryComplexBlock implements ComplexBlock {

    protected OTileEntity tileentity;

    public CanaryComplexBlock(OTileEntity tileentity) {
        this.tileentity = tileentity;
    }

    public OTileEntity getHandle() {
        return tileentity;
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public int getX() {
        return tileentity.l;
    }

    @Override
    public int getY() {
        return tileentity.m;
    }

    @Override
    public int getZ() {
        return tileentity.n;
    }

    @Override
    public World getWorld() {
        return tileentity.k.getCanaryWorld();
    }

    @Override
    public void update() {
        tileentity.k.j(getX(), getY(), getZ());
    }

    /**
     * Returns a semi-unique hashcode for this block
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + getX();
        hash = 97 * hash + getY();
        hash = 97 * hash + getZ();
        return hash;
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
        if (!(obj instanceof ComplexBlock)) {
            return false;
        }
        final ComplexBlock other = (ComplexBlock) obj;

        if (getX() != other.getX()) {
            return false;
        }
        if (getY() != other.getY()) {
            return false;
        }
        if (getZ() != other.getZ()) {
            return false;
        }
        return true;
    }
}
