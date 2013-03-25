package net.canarymod.api.entity;


import net.minecraft.server.EntityEnderCrystal;


/**
 * EnderCrystal wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderCrystal extends CanaryEntity implements EnderCrystal {
    private boolean damageWorld, damageEntity, oneHit;
    private float explode = 6.0F;

    /**
     * Constructs a new wrapper for EntityEnderCrystal
     * 
     * @param entity
     *            the EntityEnderCrystal to be wrapped
     */
    public CanaryEnderCrystal(EntityEnderCrystal entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageWorld(boolean canDamage) {
        damageWorld = canDamage;
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
        damageEntity = canDamage;
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
    public int getHealth() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(int health) {
        getHandle().b = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPower() {
        return explode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        explode = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        this.destroy();
        getHandle().q.a(getHandle(), this.getX(), this.getY(), this.getZ(), explode, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOneHitDetonate() {
        return oneHit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOneHitDetonate(boolean oneHit) {
        this.oneHit = oneHit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderCrystal getHandle() {
        return (EntityEnderCrystal) entity;
    }
}
