package net.canarymod.api.entity.vehicle;


import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.minecraft.server.EntityBoat;
import net.minecraft.server.EntityMinecart;


public class CanaryVehicle extends CanaryEntity implements Vehicle {

    public CanaryVehicle(EntityBoat entity) {
        super(entity);
    }

    public CanaryVehicle(EntityMinecart entity) {
        super(entity);
    }

    @Override
    public Entity getPassenger() {
        if (entity.bg != null) {
            entity.bg.getEntity();
        }
        return null;
    }

    @Override
    public boolean isBoat() {
        return (this instanceof CanaryBoat);
    }

    @Override
    public boolean isMinecart() {
        return (this instanceof CanaryMinecart);
    }

    @Override
    public boolean isEmpty() {
        if (entity.bg == null) {
            return true;
        } else {
            return false;
        }
    }

}
