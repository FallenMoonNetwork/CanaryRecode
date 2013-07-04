package net.canarymod.api.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityFireball;

/**
 * Fireball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryFireball extends CanaryEntity implements Fireball {

    /**
     * Constructs a new wrapper for EntityFireball
     * 
     * @param entity
     *            the EntityFireball to be wrapped
     */
    public CanaryFireball(EntityFireball entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTicksAlive() {
        return getHandle().j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTicksAlive(int ticks) {
        getHandle().j = ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTicksInAir() {
        return getHandle().au;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTicksInAir(int ticks) {
        getHandle().au = ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAccelerationX() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccelerationX(double accelX) {
        getHandle().b = accelX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAccelerationY() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccelerationY(double accelY) {
        getHandle().c = accelY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAccelerationZ() {
        return getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccelerationZ(double accelZ) {
        getHandle().d = accelZ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMotionFactor() {
        return getHandle().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMotionFactor(float factor) {
        getHandle().setMotionFactor(factor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLiving getOwner() {
        return (EntityLiving) ((EntityFireball) entity).a.getCanaryEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFireball getHandle() {
        return (EntityFireball) entity;
    }
}
