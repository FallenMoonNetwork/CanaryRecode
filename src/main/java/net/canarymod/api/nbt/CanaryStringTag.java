package net.canarymod.api.nbt;


import net.minecraft.server.NBTTagString;


/**
 * StringTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryStringTag extends CanaryBaseTag implements StringTag {

    /**
     * Constructs a new wrapper for NBTTagString
     * 
     * @param tag
     *            the NBTTagString to wrap
     */
    public CanaryStringTag(NBTTagString tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryStringTag and associated NBTTagString
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the string to supply the tag
     */
    public CanaryStringTag(String name, String value) {
        super(new NBTTagString(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringTag copy() {
        return new CanaryStringTag((NBTTagString) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagString getHandle() {
        return (NBTTagString) tag;
    }
}
