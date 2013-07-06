package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySkeleton;

/**
 * Skeleton wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySkeleton extends CanaryEntityMob implements Skeleton {

    /**
     * Constructs a new wrapper for EntitySkeleton
     * 
     * @param entity
     *            the EntitySkeleton to wrap
     */
    public CanarySkeleton(EntitySkeleton entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return isWitherSkeleton() ? EntityType.WITHERSKELETON : EntityType.SKELETON;
    }

    @Override
    public String getFqName() {
        return "Skeleton";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWitherSkeleton() {
        return getHandle().bR() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsWitherSkeleton(boolean wither) {
        getHandle().a(wither ? 1 : 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySkeleton getHandle() {
        return (EntitySkeleton) entity;
    }

}
