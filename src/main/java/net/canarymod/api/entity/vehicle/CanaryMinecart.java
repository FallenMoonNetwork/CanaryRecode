package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityMinecart;

/**
 * Minecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryMinecart extends CanaryVehicle implements Minecart {

    /**
     * Constructs a new wrapper for EntityMinecart
     * 
     * @param entity
     *            the EntityMinecart to be wrapped
     */
    public CanaryMinecart(EntityMinecart entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInReverse() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRollingAmplitude(int amp) {
        getHandle().c(amp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRollingAmplitude() {
        return getHandle().j();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRollingDirection(int direction) {
        getHandle().h(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRollingDirection() {
        return getHandle().k();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecart getHandle() {
        return (EntityMinecart) entity;
    }
}
