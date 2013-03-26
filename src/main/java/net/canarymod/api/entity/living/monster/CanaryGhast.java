package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityGhast;


/**
 * Ghast wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryGhast extends CanaryEntityMob implements Ghast {

    /**
     * Constructs a new wrapper for EntityGhast
     * 
     * @param entity
     *            the EntityGhast to wrap
     */
    public CanaryGhast(EntityGhast entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityGhast getHandle() {
        return (EntityGhast) entity;
    }
}
