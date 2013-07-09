package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagDouble;

/**
 * DoubleTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryDoubleTag extends CanaryBaseTag implements DoubleTag {

    /**
     * Constructs a new wrapper for NBTTagDouble
     * 
     * @param tag
     *            the NBTTagDouble to wrap
     */
    public CanaryDoubleTag(NBTTagDouble tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryDoubleTag and associated NBTTagDouble
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the double to supply the tag
     */
    public CanaryDoubleTag(String name, double value) {
        super(new NBTTagDouble(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(double value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleTag copy() {
        return new CanaryDoubleTag((NBTTagDouble) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagDouble getHandle() {
        return (NBTTagDouble) tag;
    }

}
