package net.canarymod.api.entity;


import net.minecraft.server.EntityLargeFireball;


/**
 * LargeFireball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryLargeFireball extends CanaryFireball implements LargeFireball {
    private boolean damageWorld = true, damageEntity = true;

    /**
     * Constructs a new wrapper for EntityLargeFireball
     * 
     * @param entity
     *            the EntityLargeFireball to wrap
     */
    public CanaryLargeFireball(EntityLargeFireball entity) {
        super(entity);
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
    public boolean canDamageWorld() {
        return this.damageWorld;
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
    public boolean canDamageEntities() {
        return this.damageEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPower() {
        return getHandle().e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        getHandle().e = (int) power;
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
    public void setFuse(int fuse) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseFuse(int increase) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseFuse(int decrease) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        // Temporary
        getHandle().q.a(getHandle(), getX(), getY(), getZ(), getPower(), true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLargeFireball getHandle() {
        return (EntityLargeFireball) entity;
    }
}
