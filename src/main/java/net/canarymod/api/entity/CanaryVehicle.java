package net.canarymod.api.entity;

import net.canarymod.api.entity.vehicle.CanaryBoat;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayerMP;

public class CanaryVehicle extends CanaryEntity implements Vehicle {

    public CanaryVehicle(OEntity entity) {
        super(entity);
    }

 
    @Override
    public Entity getPassenger() {
        if (entity.bg != null) {
            if (entity.bg.getCanaryEntity() instanceof CanaryPlayer) {
                return ((OEntityPlayerMP) entity.bg).getPlayer();
            }
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
