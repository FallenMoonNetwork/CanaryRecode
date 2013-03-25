package net.canarymod.api.entity.vehicle;


import net.minecraft.server.EntityMinecart;


public abstract class CanaryMinecart extends CanaryVehicle implements Minecart {

    public CanaryMinecart(EntityMinecart entity) {
        super(entity);
    }

    @Override
    public boolean isInReverse() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRollingAmplitude(int amp) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRollingAmplitude() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRollingDirection(int direction) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRollingDirection() {
        // TODO Auto-generated method stub
        return 0;
    }
}
