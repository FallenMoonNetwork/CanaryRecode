package net.canarymod.api.entity;

import net.canarymod.api.world.position.Location;
import net.minecraft.server.EntityEnderEye;

/**
 * EnderEye wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderEye extends CanaryEntity implements EnderEye {

    /**
     * Constructs a new wrapper for EntityEnderEye
     * 
     * @param entity
     *            the EntityEnderEye to be wrapped
     */
    public CanaryEnderEye(EntityEnderEye entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTargetX() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTargetY() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTargetZ() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveTowards(double targetX, int targetY, double targetZ) {
        getHandle().a(targetX, targetY, targetZ);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveTowards(Location location) {
        this.moveTowards(location.getX(), (int) location.getY(), location.getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDespawnTimer() {
        return getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDespawnTimer(int despawn) {
        getHandle().d = despawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean dropAfterDespawn() {
        return getHandle().e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDropAfterDespawn(boolean drop) {
        getHandle().e = drop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderEye getHandle() {
        return (EntityEnderEye) entity;
    }
}
