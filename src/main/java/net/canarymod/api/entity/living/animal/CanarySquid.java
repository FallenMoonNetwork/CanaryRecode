package net.canarymod.api.entity.living.animal;


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
    public EntitySquid getHandle() {
        return (EntitySquid) entity;
    }
}
