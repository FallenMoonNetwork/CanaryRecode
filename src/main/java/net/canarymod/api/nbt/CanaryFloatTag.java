package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagFloat;

public class CanaryFloatTag extends CanaryBaseTag implements FloatTag {

    protected CanaryFloatTag(NBTTagFloat tag) {
        super(tag);
    }

    @Override
    public float getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(float value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagFloat getHandle() {
        return (NBTTagFloat) tag;
    }

}
