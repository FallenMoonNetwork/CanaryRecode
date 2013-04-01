package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityMinecartChest;

/**
 * ChestMinecart wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryChestMinecart extends CanaryContainerMinecart implements ChestMinecart {

    /**
     * Constructs a new wrapper for EntityMinecartChest
     *
     * @param entity the EntityMinecartChest to be wrapped
     */
    public CanaryChestMinecart(EntityMinecartChest entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartChest getHandle() {
        return (EntityMinecartChest) entity;
    }
}
