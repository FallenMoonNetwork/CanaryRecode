package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntityPig;


/**
 * Pig wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPig extends CanaryEntityAnimal implements Pig {

    /**
     * Constructs a new wrapper for EntityPig
     * 
     * @param entity
     *            the EntityPig to wrap
     */
    public CanaryPig(EntityPig entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityPig getHandle() {
        return (EntityPig) entity;
    }

}
