package net.canarymod.api.nbt;

import java.util.Collection;

import net.minecraft.server.NBTTagCompound;

public class CanaryCompoundTag extends CanaryBaseTag implements CompoundTag {

    protected CanaryCompoundTag(NBTTagCompound tag) {
        super(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BaseTag> values() {
        return getHandle().c();
    }

    @Override
    public void put(String key, BaseTag value) {
        getHandle().a(key, ((CanaryBaseTag) value).getHandle());
    }

    @Override
    public void put(String key, byte value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, short value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, int value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, long value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, float value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, double value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, String value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, byte[] value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, int[] value) {
        getHandle().a(key, value);
    }

    @Override
    public void put(String key, CompoundTag value) {
        getHandle().a(key, ((CanaryCompoundTag) value).getHandle());
    }

    @Override
    public void put(String key, boolean value) {
        getHandle().a(key, value);
    }

    @Override
    public BaseTag get(String key) {
        return CanaryBaseTag.wrap(getHandle().a(key));
    }

    @Override
    public boolean containsKey(String key) {
        return getHandle().b(key);
    }

    @Override
    public byte getByte(String key) {
        return getHandle().c(key);
    }

    @Override
    public short getShort(String key) {
        return getHandle().d(key);
    }

    @Override
    public int getInt(String key) {
        return getHandle().e(key);
    }

    @Override
    public long getLong(String key) {
        return getHandle().f(key);
    }

    @Override
    public float getFloat(String key) {
        return getHandle().g(key);
    }

    @Override
    public double getDouble(String key) {
        return getHandle().h(key);
    }

    @Override
    public String getString(String key) {
        return getHandle().i(key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return getHandle().j(key);
    }

    @Override
    public int[] getIntArray(String key) {
        return getHandle().k(key);
    }

    @Override
    public CompoundTag getCompoundTag(String key) {
        return new CanaryCompoundTag(getHandle().l(key));
    }

    @Override
    public ListTag<? extends CanaryBaseTag> getListTag(String key) {
        return new CanaryListTag<CanaryBaseTag>(getHandle().m(key));
    }

    @Override
    public boolean getBoolean(String key) {
        return getHandle().n(key);
    }

    @Override
    public void remove(String key) {
        getHandle().o(key);
    }

    @Override
    public boolean isEmpty() {
        return getHandle().d();
    }

    @Override
    protected NBTTagCompound getHandle() {
        return (NBTTagCompound) tag;
    }
}
