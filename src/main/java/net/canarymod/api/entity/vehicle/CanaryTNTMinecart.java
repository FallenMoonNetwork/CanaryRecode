package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityMinecartTNT;

public class CanaryTNTMinecart extends CanaryMinecart implements TNTMinecart {

    public CanaryTNTMinecart(EntityMinecartTNT entity) {
        super(entity);
    }

    @Override
    public void setCanDamageWorld(boolean canDamage) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canDamageWorld() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCanDamageEntities(boolean canDamage) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canDamageEntities() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void detonate() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getFuse() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFuse(int fuse) {
        // TODO Auto-generated method stub

    }

    @Override
    public void increaseFuse(int increase) {
        // TODO Auto-generated method stub

    }

    @Override
    public void decreaseFuse(int decrease) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityMinecartTNT getHandle() {
        return (EntityMinecartTNT) entity;
    }

}
