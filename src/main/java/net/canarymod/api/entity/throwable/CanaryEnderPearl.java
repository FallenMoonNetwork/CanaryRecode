package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntityEnderPearl;


/**
 * EnderPearl wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderPearl extends CanaryEntityThrowable implements EnderPearl {

    /**
     * Constructs a new wrapper for EntityEnderPearl
     * 
     * @param entity
     *            the EntityEnderPearl to be wrapped
     */
    public CanaryEnderPearl(EntityEnderPearl entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderPearl getHandle() {
        return (EntityEnderPearl) entity;
    }
}
