package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityPigZombie;


/**
 * PigZombie wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPigZombie extends CanaryZombie implements PigZombie {

    /**
     * Constructs a new wrapper for EntityPigZombie
     * 
     * @param entity
     *            the EntityPigZombie to wrap
     */
    public CanaryPigZombie(EntityPigZombie entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAngerLevel() {
        return getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAngry() {
        return getHandle().d > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAngerLevel(int level) {
        getHandle().d = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityPigZombie getHandle() {
        return (EntityPigZombie) entity;
    }
}
