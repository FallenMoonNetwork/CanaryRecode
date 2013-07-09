package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagLong;

/**
 * LongTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryLongTag extends CanaryBaseTag implements LongTag {

    /**
     * Constructs a new wrapper for NBTTagLong
     * 
     * @param tag
     *            the NBTTagLong to wrap
     */
    public CanaryLongTag(NBTTagLong tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryLongTag and associated NBTTagLong
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the long to supply the tag
     */
    public CanaryLongTag(String name, long value) {
        super(new NBTTagLong(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(long value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongTag copy() {
        return new CanaryLongTag((NBTTagLong) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagLong getHandle() {
        return (NBTTagLong) tag;
    }
}
