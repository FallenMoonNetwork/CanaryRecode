package net.canarymod.api.entity;

import net.canarymod.api.world.IDimension;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.OEntity;

public class Entity implements IEntity {

    private OEntity entity;
    private Dimension world;
    
    public Entity(OEntity entity) {
        this.entity = entity;
        world = entity.bi.getWorldHandler();
    }
    
    @Override
    public double getX() {
        return entity.bm;
    }

    @Override
    public double getY() {
        return entity.bn;
    }

    @Override
    public double getZ() {
        return entity.bo;
    }

    @Override
    public double getMotionX() {
        return entity.bp;
    }

    @Override
    public double getMotionY() {
        return entity.bq;
    }

    @Override
    public double getMotionZ() {
        return entity.br;
    }

    @Override
    public float getPitch() {
        return entity.bt;
    }

    @Override
    public float getRotation() {
        return entity.bs;
    }

    @Override
    public void setX(double x) {
        teleportTo(x, getY(), getZ(), getPitch(), getRotation());
    }

    @Override
    public void setX(int x) {
        setX((double)x);

    }

    @Override
    public void setY(double y) {
        teleportTo(getX(), y, getZ(), getPitch(), getRotation());

    }

    @Override
    public void setY(int y) {
        setY((double)y);

    }

    @Override
    public void setZ(double z) {
        teleportTo(getX(), getY(), z, getPitch(), getRotation());
    }

    @Override
    public void setZ(int z) {
        setZ((double)z);
    }

    @Override
    public void setMotionX(double motionX) {
        entity.bp = motionX;
        entity.bA = true;
    }

    @Override
    public void setMotionY(double motionY) {
        entity.bq = motionY;
        entity.bA = true;
    }

    @Override
    public void setMotionZ(double motionZ) {
        entity.br = motionZ;
        entity.bA = true;
    }

    @Override
    public void setPitch(float pitch) {
        teleportTo(getX(), getY(), getZ(), pitch, getRotation());

    }

    @Override
    public void setRotation(float rotation) {
        teleportTo(getX(), getY(), getZ(), getPitch(), rotation);

    }

    @Override
    public void setWorld(IDimension dim) {
        this.world = (Dimension) dim;
        this.entity.bi = world.getHandle();
    }

    @Override
    public IDimension getWorld() {
        return this.world;
    }

    @Override
    public boolean isSprinting() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAirTicks(int ticks) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getAirTicks() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFireTicks(int ticks) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLiving() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isItem() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void dropLoot() {
        // TODO Auto-generated method stub

    }

    @Override
    public void teleportTo(double x, double y, double z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, IDimension dim) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch,
            float rotation, IDimension dim) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch,
            float rotation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(Location location) {
        // TODO Auto-generated method stub
        
    }

}
