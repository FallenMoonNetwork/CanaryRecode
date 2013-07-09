package net.canarymod.api.world.blocks;

import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.World;
import net.minecraft.server.IInventory;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.TileEntity;

/**
 * ComplexBlock implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryComplexBlock implements ComplexBlock {

    protected TileEntity tileentity;
    protected IInventory inventory;

    /**
     * Constructs a new wrapper for TileEntityChest
     * 
     * @param tileentity
     *            the TileEntityChest to be wrapped
     */
    public CanaryComplexBlock(TileEntity tileentity) {
        this.tileentity = tileentity;
    }

    public CanaryComplexBlock(IInventory inventory) {
        this.inventory = inventory;
        if (inventory instanceof TileEntity) {
            this.tileentity = (TileEntity) inventory;
        }
    }

    /**
     * Gets the TileEntity being wrapped
     * 
     * @return the TileEntity
     */
    public abstract TileEntity getTileEntity();

    /**
     * {@inheritDoc}
     */
    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return tileentity.l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return tileentity.m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZ() {
        return tileentity.n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return tileentity.az().getCanaryWorld();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        tileentity.az().j(getX(), getY(), getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getDataTag() {
        if (tileentity != null) {
            NBTTagCompound tag = new NBTTagCompound("tag");
            tileentity.b(tag);
            return new CanaryCompoundTag(tag);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getMetaTag() {
        if (tileentity != null) {
            CompoundTag dataTag = getDataTag();

            if (dataTag == null) {
                dataTag = new CanaryCompoundTag("tag");
                writeToTag(dataTag);
            }
            if (!dataTag.containsKey("Canary")) {
                dataTag.put("Canary", new CanaryCompoundTag("Canary"));
            }
            return dataTag.getCompoundTag("Canary");
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag writeToTag(CompoundTag tag) {
        if (tileentity != null) {
            tileentity.b(((CanaryCompoundTag) tag).getHandle());
            return tag;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromTag(CompoundTag tag) {
        if (tileentity != null) {
            tileentity.a(((CanaryCompoundTag) tag).getHandle());
        }
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
