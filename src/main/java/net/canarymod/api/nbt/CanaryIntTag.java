package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagInt;

public class CanaryIntTag extends CanaryBaseTag implements IntTag {

    protected CanaryIntTag(NBTTagInt tag) {
        super(tag);
    }

    @Override
    public int getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(int value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagInt getHandle() {
        return (NBTTagInt) tag;
    }
}
