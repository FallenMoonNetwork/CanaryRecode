package net.canarymod.api.entity;

import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;

/**
 * Entity Wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntity implements Entity {

    protected net.minecraft.server.Entity entity;

    public CanaryEntity(net.minecraft.server.Entity entity) {
        this.entity = entity;
    }

    @Override
    public double getX() {
        return entity.u;
    }

    @Override
    public double getY() {
        return entity.v;
    }

    @Override
    public double getZ() {
        return entity.w;
    }

    @Override
    public double getMotionX() {
        return entity.x;
    }

    @Override
    public double getMotionY() {
        return entity.y;
    }

    @Override
    public double getMotionZ() {
        return entity.z;
    }

    @Override
    public float getPitch() {
        return entity.B;
    }

    @Override
    public float getRotation() {
        return entity.A;
    }

    @Override
    public void setX(double x) {
        this.entity.u = x;
    }

    @Override
    public void setX(int x) {
        this.entity.u = x;

    }

    @Override
    public void setY(double y) {
        this.entity.v = y;

    }

    @Override
    public void setY(int y) {
        this.entity.v = y;

    }

    @Override
    public void setZ(double z) {
        this.entity.w = z;
    }

    @Override
    public void setZ(int z) {
        this.entity.w = z;
    }

    @Override
    public void setMotionX(double motionX) {
        entity.x = motionX;
        entity.I = true;
    }

    @Override
    public void setMotionY(double motionY) {
        entity.y = motionY;
        entity.I = true;
    }

    @Override
    public void setMotionZ(double motionZ) {
        entity.z = motionZ;
        entity.I = true;
    }

    @Override
    public void setPitch(float pitch) {
        entity.B = pitch;

    }

    @Override
    public void setRotation(float rotation) {
        entity.A = rotation;

    }

    @Override
    public void setDimension(World dim) {
        this.entity.setDimension((CanaryWorld) dim);
    }

    @Override
    public World getWorld() {
        return entity.getCanaryWorld();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.entity.d = ticks;

    }

    @Override
    public int getFireTicks() {
        return this.entity.d;
    }

    @Override
    public boolean isLiving() {
        return entity instanceof net.minecraft.server.EntityLiving;
    }

    @Override
    public boolean isItem() {
        return entity instanceof net.minecraft.server.EntityItem;
    }

    @Override
    public EntityItem dropLoot(int itemId, int amount) {
        return new CanaryEntityItem(entity.b(itemId, amount));
    }

    @Override
    public boolean isSprinting() {
        return entity.ah();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        entity.c(sprinting);
    }

    @Override
    public boolean isSneaking() {
        return entity.ag();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        entity.b(sneaking);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Position getPosition() {
        return new Position(getX(), getY(), getZ());
    }

    @Override
    public Location getLocation() {
        return new Location(getWorld(), getX(), getY(), getZ(), getPitch(), getRotation());
    }

    /**
     * Gets the Minecraft entity being wrapped
     * 
     * @return entity
     */
    public abstract net.minecraft.server.Entity getHandle();
}
