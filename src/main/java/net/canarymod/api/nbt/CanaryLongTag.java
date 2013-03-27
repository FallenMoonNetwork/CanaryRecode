package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagLong;

public class CanaryLongTag extends CanaryBaseTag implements LongTag {

    protected CanaryLongTag(NBTTagLong tag) {
        super(tag);
    }

    @Override
    public long getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(long value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagLong getHandle() {
        return (NBTTagLong) tag;
    }
}
