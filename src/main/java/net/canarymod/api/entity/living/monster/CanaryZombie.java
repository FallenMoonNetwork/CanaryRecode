package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityZombie;

/**
 * Zombie wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryZombie extends CanaryEntityMob implements Zombie {

    /**
     * Constructs a new wrapper for EntityZombie
     * 
     * @param entity
     *            the EntityZombie to wrap
     */
    public CanaryZombie(EntityZombie entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public String getFqName() {
        return "Zombie";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVillager() {
        return ((EntityZombie) entity).bP();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVillager(boolean villager) {
        ((EntityZombie) entity).j(villager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChild() {
        return ((EntityZombie) entity).g_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChild(boolean child) {
        ((EntityZombie) entity).a(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getConversionTime() {
        return ((EntityZombie) entity).getConvertTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConversionTime(int ticks) {
        ((EntityZombie) entity).a(ticks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConverting() {
        return ((EntityZombie) entity).bR();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopConverting() {
        ((EntityZombie) entity).stopConversion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void convertToVillager() {
        ((EntityZombie) entity).bS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityZombie getHandle() {
        return (EntityZombie) entity;
    }
}
