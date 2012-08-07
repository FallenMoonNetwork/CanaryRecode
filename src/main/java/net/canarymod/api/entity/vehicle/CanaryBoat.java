package net.canarymod.api.entity.vehicle;

import net.canarymod.api.entity.CanaryVehicle;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityBoat;

public class CanaryBoat extends CanaryVehicle implements Boat {

    public CanaryBoat(OEntity entity) {
        super(entity);
    }

    @Override
    public int getForwardDirection() {
        return ((OEntityBoat)entity).m();
    }

    @Override
    public void setForwardDirection(int direction) {
        ((OEntityBoat)entity).d(direction);
    }
    
    public OEntityBoat getHandle() {
        return (OEntityBoat) this.entity;
    }

}
