package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityCaveSpider;

/**
 * Cave Spider wrapper
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryCaveSpider extends CanarySpider {

    /**
     * Constructs a new wrapper for EntityCaveSpider
     * 
     * @param entity
     *            the EntityCaveSpider to wrap
     */
    public CanaryCaveSpider(EntityCaveSpider entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.CAVESPIDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityCaveSpider getHandle() {
        return (EntityCaveSpider) entity;
    }

}
