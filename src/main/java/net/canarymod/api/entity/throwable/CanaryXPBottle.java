package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntityExpBottle;


/**
 * XPBottle wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryXPBottle extends CanaryEntityThrowable implements XPBottle {

    /**
     * Constructs a new wrapper for EntityExpBottle
     * 
     * @param entity
     *            the EntityExpBottle to be wrapped
     */
    public CanaryXPBottle(EntityExpBottle entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityExpBottle getHandle() {
        return (EntityExpBottle) entity;
    }

}
