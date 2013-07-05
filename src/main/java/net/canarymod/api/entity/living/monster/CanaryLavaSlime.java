package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityMagmaCube;

/**
 * LavaSlime wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryLavaSlime extends CanarySlime implements LavaSlime {

    /**
     * Constructs a new wrapper for EntityMagmaCube
     * 
     * @param entity
     *            the EntityMagmaCube to wrap
     */
    public CanaryLavaSlime(EntityMagmaCube entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.LAVASLIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMagmaCube getHandle() {
        return (EntityMagmaCube) entity;
    }
}
