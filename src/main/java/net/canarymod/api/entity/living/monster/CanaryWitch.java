package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityWitch;


/**
 * Witch wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWitch extends CanaryEntityMob implements Witch {

    /**
     * Constructs a new wrapper for EntityWitch
     * 
     * @param entity
     *            the EntityWitch to wrap
     */
    public CanaryWitch(EntityWitch entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityWitch getHandle() {
        return (EntityWitch) entity;
    }

}
