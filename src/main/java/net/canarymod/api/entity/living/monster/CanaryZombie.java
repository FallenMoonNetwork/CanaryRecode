package net.canarymod.api.entity.living.monster;


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
    public EntityZombie getHandle() {
        return (EntityZombie) entity;
    }

}
