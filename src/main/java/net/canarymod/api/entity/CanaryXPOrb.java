package net.canarymod.api.entity;

import net.minecraft.server.EntityXPOrb;

/**
 * XPOrb wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryXPOrb extends CanaryEntity implements XPOrb {

    /**
     * Constructs a new wrapper for EntityXPOrb
     * 
     * @param entity
     *            the EntityXPOrb to be wrapped
     */
    public CanaryXPOrb(EntityXPOrb entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityXPOrb getHandle() {
        return (EntityXPOrb) entity;
    }

}
