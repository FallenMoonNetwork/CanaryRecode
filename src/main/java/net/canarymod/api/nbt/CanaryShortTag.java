package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagShort;

public class CanaryShortTag extends CanaryBaseTag implements ShortTag {

    protected CanaryShortTag(NBTTagShort tag) {
        super(tag);
    }

    @Override
    public short getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(short value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagShort getHandle() {
        return (NBTTagShort) tag;
    }
}
