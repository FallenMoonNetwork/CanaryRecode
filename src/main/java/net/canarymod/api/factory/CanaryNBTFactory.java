package net.canarymod.api.factory;

import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.ByteArrayTag;
import net.canarymod.api.nbt.ByteTag;
import net.canarymod.api.nbt.CanaryByteArrayTag;
import net.canarymod.api.nbt.CanaryByteTag;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CanaryDoubleTag;
import net.canarymod.api.nbt.CanaryFloatTag;
import net.canarymod.api.nbt.CanaryIntArrayTag;
import net.canarymod.api.nbt.CanaryIntTag;
import net.canarymod.api.nbt.CanaryListTag;
import net.canarymod.api.nbt.CanaryLongTag;
import net.canarymod.api.nbt.CanaryShortTag;
import net.canarymod.api.nbt.CanaryStringTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.DoubleTag;
import net.canarymod.api.nbt.FloatTag;
import net.canarymod.api.nbt.IntArrayTag;
import net.canarymod.api.nbt.IntTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.LongTag;
import net.canarymod.api.nbt.ShortTag;
import net.canarymod.api.nbt.StringTag;

public final class CanaryNBTFactory implements NBTFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag newCompoundTag(String name) {
        return new CanaryCompoundTag(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ByteTag newByteTag(String name, byte value) {
        return new CanaryByteTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ByteArrayTag newByteArrayTag(String name, byte[] value) {
        return new CanaryByteArrayTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoubleTag newDoubleTag(String name, double value) {
        return new CanaryDoubleTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloatTag newFloatTag(String name, float value) {
        return new CanaryFloatTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntTag newIntTag(String name, int value) {
        return new CanaryIntTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntArrayTag newIntArrayTag(String name, int[] value) {
        return new CanaryIntArrayTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends BaseTag> ListTag<E> newListTag(String name) {
        return new CanaryListTag<E>(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongTag newLongTag(String name, long value) {
        return new CanaryLongTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShortTag newShortTag(String name, short value) {
        return new CanaryShortTag(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringTag newStringTag(String name, String value) {
        return new CanaryStringTag(name, value);
    }

}
