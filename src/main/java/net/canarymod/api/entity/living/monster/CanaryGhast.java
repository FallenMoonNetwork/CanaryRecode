package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.CanaryLivingBase;
import net.canarymod.api.entity.living.LivingBase;
import net.minecraft.server.EntityGhast;

/**
 * Ghast wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryGhast extends CanaryEntityMob implements Ghast {

    /**
     * Constructs a new wrapper for EntityGhast
     * 
     * @param entity
     *            the EntityGhast to wrap
     */
    public CanaryGhast(EntityGhast entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.GHAST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCourseChangeCooldown() {
        return getHandle().h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCourseChangeCooldown(int cooldown) {
        getHandle().h = cooldown;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWaypointX() {
        return getHandle().i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaypointX(double waypointX) {
        getHandle().i = waypointX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWaypointY() {
        return getHandle().j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaypointY(double waypointY) {
        getHandle().j = waypointY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWaypointZ() {
        return getHandle().bn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaypointZ(double waypointZ) {
        getHandle().bn = waypointZ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArgoCooldown() {
        return getHandle().br;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArgoCooldown(int ticks) {
        getHandle().br = ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingBase getAttackTarget() {
        net.minecraft.server.Entity target = getHandle().bq;
        if (target != null) {
            return (LivingBase) target.getCanaryEntity();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttackTarget(LivingBase livingbase) {
        if (livingbase == null) {
            getHandle().bq = null;
        }
        else {
            getHandle().bq = ((CanaryLivingBase) livingbase).getHandle();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityGhast getHandle() {
        return (EntityGhast) entity;
    }
}
