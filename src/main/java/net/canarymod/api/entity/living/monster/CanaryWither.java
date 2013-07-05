package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityWither;

/**
 * Wither wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWither extends CanaryEntityMob implements Wither {

    /**
     * Constructs a new wrapper for EntityWither
     * 
     * @param entity
     *            the EntityWither to wrap
     */
    public CanaryWither(EntityWither entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.WITHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityWither getHandle() {
        return (EntityWither) entity;
    }

}
