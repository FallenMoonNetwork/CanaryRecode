package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityWitch;

/**
 * Witch wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWitch extends CanaryEntityMob implements Witch {

    /**
     * Constructs a new wrapper for EntityWitch
     * 
     * @param entity
     *            the EntityWitch to wrap
     */
    public CanaryWitch(EntityWitch entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.WITCH;
    }

    @Override
    public String getFqName() {
        return "Witch";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAgressive() {
        return getHandle().bT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAggressive(boolean aggressive) {
        getHandle().a(aggressive);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityWitch getHandle() {
        return (EntityWitch) entity;
    }
}
