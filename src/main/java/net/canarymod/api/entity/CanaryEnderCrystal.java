package net.canarymod.api.entity;

import java.util.Random;
import net.minecraft.server.EntityEnderCrystal;

/**
 * EnderCrystal wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderCrystal extends CanaryEntity implements EnderCrystal {
    private final static Random darks_humor = new Random();
    private boolean damageWorld = true, damageEntity = true, oneHit = true;
    private float power = 6.0F;

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
    public EntityType getEntityType() {
        return EntityType.ENDERCRYSTAL;
    }

    @Override
    public String getFqName() {
        return "EnderCrystal";
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
    public void detonate() {
        this.destroy();
        getHandle().q.a(getHandle(), this.getX(), this.getY(), this.getZ(), power, damageWorld);
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
     * There is no fuse
     */
    @Override
    public int getFuse() {
        return darks_humor.nextInt(Integer.MAX_VALUE);
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
    public EntityEnderCrystal getHandle() {
        return (EntityEnderCrystal) entity;
    }
}
