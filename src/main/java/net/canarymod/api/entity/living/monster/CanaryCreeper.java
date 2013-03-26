package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityCreeper;


/**
 * Creeper wrapper implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryCreeper extends CanaryEntityMob implements Creeper {

    private boolean damageEntity, damageWorld = true;

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
        return getHandle().m();
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
        return getHandle().g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        getHandle().g = (int) power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        getHandle().g = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuse() {
        return getHandle().f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuse(int fuse) {
        getHandle().f = fuse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAgro() {
        return getHandle().o() == 1;
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
