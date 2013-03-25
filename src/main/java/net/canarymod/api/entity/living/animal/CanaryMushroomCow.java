package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntityMooshroom;


/**
 * MushroomCow wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryMushroomCow extends CanaryCow implements Mooshroom {

    /**
     * Constructs a new wrapper for EntityMooshroom
     * 
     * @param entity
     *            the EntityMooshroom to wrap
     */
    public CanaryMushroomCow(EntityMooshroom entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMooshroom getHandle() {
        return (EntityMooshroom) entity;
    }
}
