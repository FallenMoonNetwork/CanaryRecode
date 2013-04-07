package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntitySnowball;


/**
 * Snowball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySnowball extends CanaryEntityThrowable implements Snowball {

    /**
     * Constructs a new wrapper for EntitySnowball
     * 
     * @param entity
     *            the EntitySnowball to wrap
     */
    public CanarySnowball(EntitySnowball entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySnowball getHandle() {
        return (EntitySnowball) entity;
    }
}
