package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagString;

public class CanaryStringTag extends CanaryBaseTag implements StringTag {

    protected CanaryStringTag(NBTTagString tag) {
        super(tag);
    }

    @Override
    public String getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(String value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagString getHandle() {
        return (NBTTagString) tag;
    }
}
