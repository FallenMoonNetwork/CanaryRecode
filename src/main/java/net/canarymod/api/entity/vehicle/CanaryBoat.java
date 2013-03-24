package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityBoat;

public class CanaryBoat extends CanaryVehicle implements Boat{

    public CanaryBoat(EntityBoat entity){
        super(entity);
    }

    @Override
    public int getForwardDirection(){
        return ((EntityBoat) entity).h();
    }

    @Override
    public void setForwardDirection(int direction){
        ((EntityBoat) entity).h(direction);
    }

    @Override
    public EntityBoat getHandle(){
        return (EntityBoat) this.entity;
    }

}
