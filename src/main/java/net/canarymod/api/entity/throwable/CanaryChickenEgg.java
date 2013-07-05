package net.canarymod.api.entity.throwable;

import net.canarymod.api.entity.EntityType;
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

    @Override
    public EntityType getEntityType() {
        return EntityType.CHICKENEGG;
    }

    @Override
    public String getFqName() {
        return "ChickenEgg";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEgg getHandle() {
        return (EntityEgg) entity;
    }
}
