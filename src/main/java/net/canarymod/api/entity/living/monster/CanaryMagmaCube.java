package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityMagmaCube;

/**
 * LavaSlime wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryMagmaCube extends CanarySlime implements MagmaCube {

    /**
     * Constructs a new wrapper for EntityMagmaCube
     * 
     * @param entity
     *            the EntityMagmaCube to wrap
     */
    public CanaryMagmaCube(EntityMagmaCube entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.MAGMACUBE;
    }

    @Override
    public String getFqName() {
        return "MagmaCube";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMagmaCube getHandle() {
        return (EntityMagmaCube) entity;
    }
}
