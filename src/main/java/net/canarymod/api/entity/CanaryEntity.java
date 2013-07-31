package net.canarymod.api.entity;

import java.util.UUID;
import net.canarymod.api.entity.living.Golem;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
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
        teleportTo(x, y, z, rotation, pitch, getWorld());
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        teleportTo(x, y, z, 0.0F, 0.0F);
    }

    @Override
    public void teleportTo(double x, double y, double z, World world) {
        teleportTo(x, y, z, 0.0F, 0.0F, world);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim) {
        if (dim != this.getWorld()) {
            this.entity.a(((CanaryWorld) dim).getHandle());
        }
        this.entity.b(x, y, z, rotation, pitch);
    }

    @Override
    public void teleportTo(Location location) {
        teleportTo(location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch(), location.getWorld());
    }

    @Override
    public void teleportTo(Position pos) {
        teleportTo(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
    }

    @Override
    public void setDimension(World dim) {
        this.entity.a(((CanaryWorld) dim).getHandle());
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
    public boolean isItem() {
        return this instanceof EntityItem;
    }

    @Override
    public boolean isLiving() {
        return this instanceof LivingBase;
    }

    @Override
    public boolean isAnimal() {
        return this instanceof EntityAnimal;
    }

    @Override
    public boolean isMob() {
        return this instanceof EntityMob;
    }

    @Override
    public boolean isPlayer() {
        return this instanceof Player;
    }

    @Override
    public boolean isGolem() {
        return this instanceof Golem;
    }

    @Override
    public EntityItem dropLoot(int itemId, int amount) {
        return (EntityItem) entity.b(itemId, amount).getEntityItem();
    }

    public EntityItem dropLoot(Item item) {
        return entity.a(((CanaryItem) item).getHandle(), 0.0F).getEntityItem();
    }

    @Override
    public boolean isSprinting() { // 3
        return entity.ag();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        entity.c(sprinting);
    }

    @Override
    public boolean isSneaking() { // 1
        return entity.ag();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        entity.b(sneaking);
    }

    @Override
    public boolean isInvisible() { // 5
        return entity.ah();
    }

    @Override
    public void setInvisible(boolean invisible) {
        entity.d(invisible);
    }

    @Override
    public String getName() {
        return entity.am();
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
        return entity.av();
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
    public boolean canSpawn() {
        if (this.isLiving()) {
            return ((net.minecraft.server.EntityLiving) entity).bs();
        }
        return true;
    }

    @Override
    public boolean isRiding() {
        return entity.ae();
    }

    @Override
    public boolean isRidden() {
        return entity.n != null;
    }

    @Override
    public Entity getRiding() {
        if (entity.o != null) {
            return entity.o.getCanaryEntity();
        }
        return null;
    }

    @Override
    public Entity getRider() {
        if (entity.n != null) {
            return entity.n.getCanaryEntity();
        }
        return null;
    }

    /**
     * Destroys this entity
     */
    @Override
    public void destroy() {
        entity.w();
    }

    @Override
    public boolean isDead() {
        return entity.M;
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
        this.getHandle().f((NBTTagCompound) ((CanaryCompoundTag) tag).getHandle());
    }

    @Override
    public CompoundTag getMetaData() {
        return entity.getMetaData();
    }

    @Override
    public void moveEntity(double motionX, double motionY, double motionZ) {
        entity.d(motionX, motionY, motionZ);
    }

    /**
     * Gets the Minecraft entity being wrapped
     * 
     * @return entity
     */
    public abstract net.minecraft.server.Entity getHandle();
}
