package net.canarymod.api.nbt;


import net.minecraft.server.NBTTagFloat;


/**
 * FloatTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryFloatTag extends CanaryBaseTag implements FloatTag {

    /**
     * Constructs a new wrapper for NBTTagFloat
     * 
     * @param tag
     *            the NBTTagFloat to wrap
     */
    public CanaryFloatTag(NBTTagFloat tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryFloatTag and associated NBTTagFloat
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the float to supply the tag
     */
    public CanaryFloatTag(String name, float value) {
        super(new NBTTagFloat(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(float value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagFloat getHandle() {
        return (NBTTagFloat) tag;
    }

}
