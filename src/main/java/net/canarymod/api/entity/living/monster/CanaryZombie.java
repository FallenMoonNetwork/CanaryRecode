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
        return getHandle().bT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVillager(boolean villager) {
        getHandle().i(villager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChild() {
        return getHandle().g_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChild(boolean child) {
        getHandle().a(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getConversionTime() {
        return getHandle().getConvertTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConversionTime(int ticks) {
        getHandle().b(ticks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConverting() {
        return getHandle().bV();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopConverting() {
        getHandle().stopConversion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void convertToVillager() {
        getHandle().bS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityZombie getHandle() {
        return (EntityZombie) entity;
    }
}
