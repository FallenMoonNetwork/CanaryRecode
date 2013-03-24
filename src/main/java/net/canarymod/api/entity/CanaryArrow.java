package net.canarymod.api.entity;

import net.minecraft.server.EntityArrow;

/**
 * Arrow Wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryArrow extends CanaryEntity implements Arrow {

    public CanaryArrow(EntityArrow entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPickUp() {
        return ((EntityArrow) entity).a == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanPickUp(boolean canPickUp) {
        ((EntityArrow) entity).a = canPickUp ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getOwner() {
        return ((EntityArrow) entity).c != null ? ((EntityArrow) entity).c.getCanaryEntity() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDamage() {
        return ((EntityArrow) entity).c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(double damage) {
        ((EntityArrow) entity).b(damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCritical() {
        return ((EntityArrow) entity).d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsCritical(boolean critical) {
        ((EntityArrow) entity).a(critical);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityArrow getHandle() {
        return (EntityArrow) entity;
    }
}
