package net.canarymod.api.entity.vehicle;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityBoat;

/**
 * Boat wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBoat extends CanaryVehicle implements Boat {

    /**
     * Constructs a new wrapper for EntityBoat
     * 
     * @param entity
     *            the EntityBoat to be wrapped
     */
    public CanaryBoat(EntityBoat entity) {
        super(entity);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.BOAT;
    }

    @Override
    public String getFqName() {
        return "Boat";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getForwardDirection() {
        return getHandle().h();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForwardDirection(int direction) {
        getHandle().c(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityBoat getHandle() {
        return (EntityBoat) this.entity;
    }

}
