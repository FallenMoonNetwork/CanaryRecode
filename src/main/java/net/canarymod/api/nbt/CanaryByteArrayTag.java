package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagByteArray;

public class CanaryByteArrayTag extends CanaryBaseTag implements ByteArrayTag {

    protected CanaryByteArrayTag(NBTTagByteArray tag) {
        super(tag);
    }

    @Override
    public byte[] getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(byte[] value) {
        getHandle().a = value;
    }
    
    @Override
    protected NBTTagByteArray getHandle() {
        return (NBTTagByteArray) tag;
    }
}
