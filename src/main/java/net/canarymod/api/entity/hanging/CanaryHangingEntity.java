package net.canarymod.api.entity.hanging;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.minecraft.server.EntityHanging;

/**
 * HangingEntity wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryHangingEntity extends CanaryEntity implements HangingEntity {

    /**
     * Constructs a new wrapper for EntityHanging
     * 
     * @param entity
     *            the EntityHanging to be wrapped
     */
    public CanaryHangingEntity(EntityHanging entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHangingDirection() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHangingDirection(int direction) {
        if (direction < 0 || 3 > direction) {
            return;
        }
        getHandle().a(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOnValidSurface() {
        return getHandle().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTickCounter() {
        return getHandle().getTickCounter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTickCounter(int ticks) {
        getHandle().setTicks(ticks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityHanging getHandle() {
        return (EntityHanging) entity;
    }
}
