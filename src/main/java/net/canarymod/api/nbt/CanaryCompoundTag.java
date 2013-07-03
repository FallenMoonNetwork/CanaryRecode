package net.canarymod.api.nbt;


import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;


/**
 * CompoundTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryCompoundTag extends CanaryBaseTag implements CompoundTag {

    /**
     * Constructs a new wrapper for NBTTagCompound
     * 
     * @param tag
     *            the NBTTagCompound to wrap
     */
    public CanaryCompoundTag(NBTTagCompound tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryCompoundTag and associated NBTTagCompound
     * 
     * @param name
     *            the name of the tag
     */
    public CanaryCompoundTag(String name) {
        super(new NBTTagCompound(name));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<BaseTag> values() {
        Collection<BaseTag> values = new ArrayList<BaseTag>();
        for (NBTBase tag : (Collection<NBTBase>) getHandle().c()) {
            values.add(CanaryBaseTag.wrap(tag));
        }
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, BaseTag value) {
        getHandle().a(key, ((CanaryBaseTag) value).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, byte value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, short value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, int value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, long value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, float value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, double value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, String value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, byte[] value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, int[] value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, CompoundTag value) {
        getHandle().a(key, ((CanaryCompoundTag) value).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, boolean value) {
        getHandle().a(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseTag get(String key) {
        return CanaryBaseTag.wrap(getHandle().a(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(String key) {
        return getHandle().b(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(String key) {
        return getHandle().c(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(String key) {
        return getHandle().d(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(String key) {
        return getHandle().e(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(String key) {
        return getHandle().f(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(String key) {
        return getHandle().g(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(String key) {
        return getHandle().h(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String key) {
        return getHandle().i(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getByteArray(String key) {
        return getHandle().j(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getIntArray(String key) {
        return getHandle().k(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getCompoundTag(String key) {
        return new CanaryCompoundTag(getHandle().l(key));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public ListTag<? extends CanaryBaseTag> getListTag(String key) {
        return new CanaryListTag<CanaryBaseTag>(getHandle().m(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(String key) {
        return getHandle().n(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String key) {
        getHandle().o(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getHandle().d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag copy() {
        return new CanaryCompoundTag((NBTTagCompound) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagCompound getHandle() {
        return (NBTTagCompound) tag;
    }
}
