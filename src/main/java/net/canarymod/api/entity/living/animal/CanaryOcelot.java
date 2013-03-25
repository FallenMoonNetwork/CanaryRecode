package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntityOcelot;

/**
 * Ocelot wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryOcelot extends CanaryTameable implements Ocelot {

    /**
     * Constructs a new wrapper for EntityOcelot
     * 
     * @param entity
     *            the EntityOcelot to wrap
     */
    public CanaryOcelot(EntityOcelot entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityOcelot getHandle() {
        return (EntityOcelot) entity;
    }
}
