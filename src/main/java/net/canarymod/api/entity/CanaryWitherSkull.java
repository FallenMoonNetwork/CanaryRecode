package net.canarymod.api.entity;

import net.minecraft.server.EntityWitherSkull;

/**
 * Wither Skull wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWitherSkull extends CanaryFireball implements WitherSkull {
    private boolean damageWorld = true, damageEntity = true;
    private float power = 1.0F;

    public CanaryWitherSkull(EntityWitherSkull entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public net.minecraft.server.Entity getHandle() {
        return (net.minecraft.server.Entity) this.entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDamageEntities() {
        return damageEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDamageWorld() {
        return damageWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageEntities(boolean canDamage) {
        this.damageEntity = canDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageWorld(boolean canDamage) {
        this.damageWorld = canDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPower() {
        return power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        this.power = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuse() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuse(int fuse) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseFuse(int increase) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseFuse(int decrease) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        getHandle().q.a(getHandle(), getX(), getY(), getZ(), getPower(), true, true);
    }

}
