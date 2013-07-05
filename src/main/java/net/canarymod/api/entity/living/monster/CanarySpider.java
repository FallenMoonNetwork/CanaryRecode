package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySpider;

/**
 * Spider wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySpider extends CanaryEntityMob implements Spider {

    /**
     * Constructs a new wrapper for EntitySpider
     * 
     * @param entity
     *            the EntitySpider to wrap
     */
    public CanarySpider(EntitySpider entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SPIDER;
    }

    @Override
    public String getFqName() {
        return "Spider";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpider getHandle() {
        return (EntitySpider) entity;
    }
}
