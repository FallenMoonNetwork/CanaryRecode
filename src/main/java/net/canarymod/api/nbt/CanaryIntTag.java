package net.canarymod.api.nbt;


import net.minecraft.server.NBTTagInt;


/**
 * IntTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryIntTag extends CanaryBaseTag implements IntTag {

    /**
     * Constructs a new wrapper for NBTTagInt
     * 
     * @param tag
     *            the NBTTagInt to wrap
     */
    public CanaryIntTag(NBTTagInt tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryIntTag and associated NBTTagInt
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the int to supply the tag
     */
    public CanaryIntTag(String name, int value) {
        super(new NBTTagInt(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(int value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntTag copy() {
        return new CanaryIntTag((NBTTagInt) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagInt getHandle() {
        return (NBTTagInt) tag;
    }
}
