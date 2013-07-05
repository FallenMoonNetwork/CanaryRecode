package net.canarymod.api.entity;

import net.minecraft.server.EntitySmallFireball;

/**
 * SmallFireball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySmallFireball extends CanaryFireball implements SmallFireball {

    /**
     * Constructs a new wrapper for EntitySmallFireball
     * 
     * @param entity
     *            the EntitySmallFireball to wrap
     */
    public CanarySmallFireball(EntitySmallFireball entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SMALLFIREBALL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySmallFireball getHandle() {
        return (EntitySmallFireball) entity;
    }
}
