package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySquid;

/**
 * Squid wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySquid extends CanaryEntityAnimal implements Squid {

    /**
     * Constructs a new wrapper for EntitySquid
     * 
     * @param entity
     *            the EntitySquid to wrap
     */
    public CanarySquid(EntitySquid entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SQUID;
    }

    @Override
    public String getFqName() {
        return "Squid";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySquid getHandle() {
        return (EntitySquid) entity;
    }
}
