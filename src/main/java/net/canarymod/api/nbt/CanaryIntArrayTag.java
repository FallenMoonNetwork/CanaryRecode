package net.canarymod.api.nbt;


import net.minecraft.server.NBTTagIntArray;


/**
 * IntArrayTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryIntArrayTag extends CanaryBaseTag implements IntArrayTag {

    /**
     * Constructs a new wrapper for NBTTagIntArray
     * 
     * @param tag
     *            the NBTTagIntArray to wrap
     */
    public CanaryIntArrayTag(NBTTagIntArray tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryIntArrayTag and associated NBTTagIntArray
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the int array to supply the tag
     */
    public CanaryIntArrayTag(String name, int[] value) {
        super(new NBTTagIntArray(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(int[] value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagIntArray getHandle() {
        return (NBTTagIntArray) tag;
    }

}
