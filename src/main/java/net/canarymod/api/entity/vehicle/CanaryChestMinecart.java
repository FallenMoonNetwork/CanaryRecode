package net.canarymod.api.entity.vehicle;

import net.canarymod.api.entity.EntityType;
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
     * @param entity
     *            the EntityMinecartChest to be wrapped
     */
    public CanaryChestMinecart(EntityMinecartChest entity) {
        super(entity);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.CHESTMINECART;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartChest getHandle() {
        return (EntityMinecartChest) entity;
    }
}
