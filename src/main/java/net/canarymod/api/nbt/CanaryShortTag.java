package net.canarymod.api.nbt;


import net.minecraft.server.NBTTagShort;


/**
 * ShortTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryShortTag extends CanaryBaseTag implements ShortTag {

    /**
     * Constructs a new wrapper for NBTTagShort
     * 
     * @param tag
     *            the NBTTagShort to wrap
     */
    public CanaryShortTag(NBTTagShort tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryShortTag and associated NBTTagShort
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the short to supply the tag
     */
    public CanaryShortTag(String name, short value) {
        super(new NBTTagShort(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(short value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagShort getHandle() {
        return (NBTTagShort) tag;
    }
}
