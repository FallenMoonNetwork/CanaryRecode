package net.canarymod.api.entity.living;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySnowman;

/**
 * Snowman wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySnowman extends CanaryEntityLiving implements Snowman {

    /**
     * Constructs a new wrapper for EntitySnowman
     * 
     * @param entity
     *            the EntitySnowman to be wrapped
     */
    public CanarySnowman(EntitySnowman entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SNOWMAN;
    }

    @Override
    public String getFqName() {
        return "Snowman";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySnowman getHandle() {
        return (EntitySnowman) entity;
    }

}
