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
    public EntityType getEntityType() {
        return EntityType.WITHERSKULL;
    }

    @Override
    public String getFqName() {
        return "WitherSkull";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInvulnerable() {
        return getHandle().d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInvulnerable(boolean invulnerable) {
        getHandle().a(invulnerable);
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
     * There is no fuse
     */
    @Override
    public int getFuse() {
        return 0;
    }

    /**
     * There is no fuse
     */
    @Override
    public void setFuse(int fuse) {}

    /**
     * There is no fuse
     */
    @Override
    public void increaseFuse(int increase) {}

    /**
     * There is no fuse
     */
    @Override
    public void decreaseFuse(int decrease) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        getHandle().q.a(getHandle(), getX(), getY(), getZ(), getPower(), false, damageWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityWitherSkull getHandle() {
        return (EntityWitherSkull) this.entity;
    }
}
