package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntityEgg;


/**
 * ChickenEgg wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChickenEgg extends CanaryEntityThrowable implements ChickenEgg {

    /**
     * Constructs a new wrapper for EntityEgg
     * 
     * @param entity
     *            the EntityEgg to be wrapped
     */
    public CanaryChickenEgg(EntityEgg entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEgg getHandle() {
        return (EntityEgg) entity;
    }
}
