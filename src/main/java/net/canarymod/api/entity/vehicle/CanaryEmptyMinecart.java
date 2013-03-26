package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityMinecartEmpty;

/**
 * EmptyMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEmptyMinecart extends CanaryMinecart implements EmptyMinecart {

    /**
     * Constructs a new wrapper for EntityMinecartEmpty
     * 
     * @param entity
     *            the EntityMinecartEmpty to be wrapped
     */
    public CanaryEmptyMinecart(EntityMinecartEmpty entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartEmpty getHandle() {
        return (EntityMinecartEmpty) entity;
    }

}
