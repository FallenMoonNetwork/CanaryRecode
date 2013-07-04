package net.canarymod.api.entity;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;

/**
 * EntityItem wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEntityItem extends CanaryEntity implements EntityItem {

    /**
     * Constructs a new wrapper for EntityItem
     * 
     * @param entity
     *            the EntityItem to be wrapped
     */
    public CanaryEntityItem(net.minecraft.server.EntityItem entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(short age) {
        getHandle().a = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getAge() {
        return (short) getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPickUpDelay() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPickUpDelay(int delay) {
        getHandle().b = delay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getHealth() {
        return (short) getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(short health) {
        getHandle().d = Math.min(health, 255);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem getItem() {
        return getHandle().d().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItem(Item item) {
        if (item != null) {
            getHandle().a(((CanaryItem) item).getHandle());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public net.minecraft.server.EntityItem getHandle() {
        return (net.minecraft.server.EntityItem) entity;
    }
}
