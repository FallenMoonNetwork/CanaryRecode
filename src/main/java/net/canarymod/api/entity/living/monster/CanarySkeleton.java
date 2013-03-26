package net.canarymod.api.entity.living.monster;


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
    public boolean isWitherSkeleton() {
        return getHandle().o() == 1;
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
