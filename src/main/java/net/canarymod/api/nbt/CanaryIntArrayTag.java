package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagIntArray;

public class CanaryIntArrayTag extends CanaryBaseTag implements IntArrayTag {

    protected CanaryIntArrayTag(NBTTagIntArray tag) {
        super(tag);
    }

    @Override
    public int[] getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(int[] value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagIntArray getHandle() {
        return (NBTTagIntArray) tag;
    }

}
