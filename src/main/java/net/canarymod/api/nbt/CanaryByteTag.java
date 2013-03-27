package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagByte;

public class CanaryByteTag extends CanaryBaseTag implements ByteTag {

    public CanaryByteTag(NBTTagByte tag) {
        super(tag);
    }

    @Override
    public byte getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(byte value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagByte getHandle() {
        return (NBTTagByte) tag;
    }
}
