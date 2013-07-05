package net.canarymod.api.entity;

import net.minecraft.server.EntityArrow;

/**
 * Arrow Wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryArrow extends CanaryProjectile implements Arrow {

    public CanaryArrow(EntityArrow entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.ARROW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPickUp() {
        return getHandle().a == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanPickUp(boolean canPickUp) {
        getHandle().a = canPickUp ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getOwner() {
        return getHandle().c != null ? getHandle().c.getCanaryEntity() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDamage() {
        return getHandle().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(double damage) {
        getHandle().b(damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCritical() {
        return getHandle().d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsCritical(boolean critical) {
        getHandle().a(critical);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInGround() {
        return getHandle().i;
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
    public int getTicksInGround() {
        return getHandle().j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKnockbackStrength() {
        return getHandle().cmeakb();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKnockbackStrenth(int knockback) {
        getHandle().a(knockback);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityArrow getHandle() {
        return (EntityArrow) entity;
    }
}
