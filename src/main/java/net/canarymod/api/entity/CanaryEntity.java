package net.canarymod.api.entity;


import java.util.UUID;

import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.NBTTagCompound;


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
    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        this.entity.b(x, y, z, rotation, pitch);
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        teleportTo(x, y, z, 0f, 0f);
    }

    @Override
    public void teleportTo(Position pos) {
        teleportTo(pos.getX(), pos.getY(), pos.getZ(), 0f, 0f);
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

    @Override
    public int getID() {
        return entity.k;
    }

    @Override
    public UUID getUUID() {
        return entity.getEntityUUID();
    }

    @Override
    public Vector3D getMotion() {
        return new Vector3D(getMotionX(), getMotionY(), getMotionZ());
    }

    @Override
    public Vector3D getForwardVector() {
        return Vector3D.forward;
    }

    @Override
    public void translate(Vector3D factor) {
        setX(getX() + factor.getX());
        setY(getY() + factor.getY());
        setZ(getZ() + factor.getZ());
    }

    @Override
    public void moveEntity(double motionX, double motionY, double motionZ) {
        entity.d(motionX, motionY, motionZ);
    }

    @Override
    public boolean canSpawn() {
        if (this.isLiving()) {
            return ((net.minecraft.server.EntityLiving) entity).bv();
        }
        return true;
    }

    @Override
    public boolean isRiding() {
        return entity.af();
    }

    /**
     * Destroys this entity
     */
    @Override
    public void destroy() {
        entity.w();
    }

    @Override
    public boolean spawn() {
        entity.b(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        return entity.q.d(entity);
    }

    @Override
    public boolean spawn(Entity rider) {
        boolean ret = spawn();

        if (rider != null) {
            net.minecraft.server.Entity mob2 = ((CanaryEntity) rider).getHandle();

            mob2.b(getX(), getY(), getZ(), getRotation(), 0f);
            ret &= entity.q.d(mob2);
            mob2.a(entity);
        }
        return ret;
    }

    @Override
    public void setRider(Entity rider) {
        ((CanaryEntity) rider).getHandle().a(this.entity);
    }

    @Override
    public CompoundTag getNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        this.getHandle().d(tag);
        return (new CanaryCompoundTag(tag));
    }

    @Override
    public void setNBT(BaseTag tag) {
        this.getHandle().f((NBTTagCompound) ((CanaryCompoundTag)tag).getHandle());
    }

    @Override
    public void isInvisible() {
        return entity.ai();
    }

    @Override
    public void setInvisible(boolean invisible) {
        entity.d(b);
    }

    /**
     * Gets the Minecraft entity being wrapped
     *
     * @return entity
     */
    public abstract net.minecraft.server.Entity getHandle();
}
