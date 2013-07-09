package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagByte;

/**
 * ByteTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryByteTag extends CanaryBaseTag implements ByteTag {

    /**
     * Constructs a new wrapper for NBTTagByte
     * 
     * @param tag
     *            the NBTTagByte to wrap
     */
    public CanaryByteTag(NBTTagByte tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryByteTag and associated NBTTagByte
     * 
     * @param name
     *            the name of the tag
     * @param value
     *            the byte to supply to the tag
     */
    public CanaryByteTag(String name, byte value) {
        super(new NBTTagByte(name, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getValue() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(byte value) {
        getHandle().a = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ByteTag copy() {
        return new CanaryByteTag((NBTTagByte) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagByte getHandle() {
        return (NBTTagByte) tag;
    }
}
