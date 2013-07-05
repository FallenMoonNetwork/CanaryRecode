package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityCreeper;

/**
 * Creeper wrapper implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryCreeper extends CanaryEntityMob implements Creeper {

    private boolean damageEntity = true, damageWorld = true;

    /**
     * Constructs a new wrapper for EntityCreeper
     * 
     * @param entity
     *            the EntityCreeper to wrap
     */
    public CanaryCreeper(EntityCreeper entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.CREEPER;
    }

    @Override
    public String getFqName() {
        return "Creeper";
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
    public boolean isCharged() {
        return getHandle().bP();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCharged(boolean charged) {
        getHandle().setCharged(charged);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPower() {
        return getHandle().bs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        getHandle().bs = (int) power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        getHandle().br = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuse() {
        return getHandle().br;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuse(int fuse) {
        getHandle().br = fuse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseFuse(int increase) {
        getHandle().br += increase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseFuse(int decrease) {
        getHandle().br -= decrease;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAgro() {
        return getHandle().bR() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAgro(boolean agro) {
        getHandle().a(agro ? 1 : -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityCreeper getHandle() {
        return (EntityCreeper) entity;
    }
}
