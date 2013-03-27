package net.canarymod.api.nbt;

import net.minecraft.server.NBTTagDouble;

public class CanaryDoubleTag extends CanaryBaseTag implements DoubleTag {

    protected CanaryDoubleTag(NBTTagDouble tag) {
        super(tag);
    }

    @Override
    public double getValue() {
        return getHandle().a;
    }

    @Override
    public void setValue(double value) {
        getHandle().a = value;
    }

    @Override
    protected NBTTagDouble getHandle() {
        return (NBTTagDouble) tag;
    }

}
