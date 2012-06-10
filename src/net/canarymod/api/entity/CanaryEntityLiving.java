package net.canarymod.api.entity;

import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.DamageSource;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityFlying;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OEntitySlime;
import net.minecraft.server.OEntitySquid;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.OIMob;
import net.minecraft.server.OWorld;

/**
 * basic living entity wrapper class
 * 
 * @author Jason
 */
public class CanaryEntityLiving extends CanaryEntity implements EntityLiving {

    public CanaryEntityLiving(OEntityLiving entity) {
        super(entity);
    }

    @Override
    public boolean canSee(EntityLiving entityliving) {
        return ((OEntityLiving) entity).h(((CanaryEntity) entityliving).entity);
    }

    @Override
    public void dealDamage(DamageSource damagesource, int amount) {
        ((OEntityLiving) entity).a(((CanaryDamageSource) damagesource).getHandle(), amount);
    }

    @Override
    public int getAge() {
        return ((OEntityLiving) entity).aq();
    }

    @Override
    public EntityAnimal getAnimal() {
        if (isAnimal()) {
            return (this instanceof CanaryEntityAnimal ? (CanaryEntityAnimal) this :
                    entity instanceof OEntitySquid ? ((OEntitySquid)entity).getCanarySquid() :
                    entity instanceof OEntityVillager ? ((OEntityVillager)entity).getCanaryVillager() :
                    (CanaryEntityAnimal)((OEntityAnimal)entity).getCanaryEntityLiving());
        }
        return null; //Not a valid animal type
    }

    @Override
    public int getDeathTicks() {
        return ((OEntityLiving) entity).av;
    }

    @Override
    public int getHealth() {
        return ((OEntityLiving) entity).aD();
    }

    @Override
    public Vector3D getHome() {
        if (hasHome()) {
            OChunkCoordinates home = ((OEntityLiving) entity).av();
            return new Vector3D(home.a, home.b, home.c);
        }
        return null;
    }

    @Override
    public int getMaxHealth() {
        return ((OEntityLiving) entity).d();
    }

    @Override
    public EntityMob getMob() {
        if (isMob()) {
            return this instanceof CanaryEntityMob ? (CanaryEntityMob) this : 
                   entity instanceof OEntityFlying ? new CanaryEntityMob((OEntityFlying) entity) : //Since OEntityFlying isn't an OEntityMob instance
                   entity instanceof OEntitySlime ? new CanaryEntityMob((OEntitySlime) entity) : //Since OEntitySlime isn't an OEntityMob instance
                   new CanaryEntityMob((OEntityMob) entity);
        }
        return null; //Not a valid mob type
    }

    @Override
    public String getName() {
        return OEntityList.b(entity);
    }

    @Override
    public Player getPlayer() {
        if(entity instanceof OEntityPlayerMP) {
            return ((OEntityPlayerMP)entity).getPlayer();
        }
        return null;
    }

    @Override
    public boolean hasHome() {
        return ((OEntityLiving) entity).ay();
    }

    @Override
    public void increaseHealth(int increase) {
        ((OEntityLiving) entity).d(increase);
    }

    @Override
    public boolean isAnimal() {
        return entity instanceof OIAnimals && !(entity instanceof OIMob); //Just noticed OIMob extends OIAnimals which could cause derpiness
    }

    @Override
    public boolean isMob() {
        return entity instanceof OIMob;
    }

    @Override
    public boolean isPlayer() {
        return ((OEntityLiving) entity).ah();
    }

    @Override
    public void kill() {
        setHealth(0);
    }
    
    @Override
    public void destroy() {
        entity.X();
    }

    @Override
    public void knockBack(double x, double z) {
        ((OEntityLiving) entity).a(entity, 0, x, z); //NOTE: There may be something more needed in the arguments
    }

    @Override
    public void moveEntity(double x, double y, double z) {
        entity.a(x, y, z);
    }

    @Override
    public void playLivingSound() {
        ((OEntityLiving) entity).az();
    }

    @Override
    public void removeHome() {
        ((OEntityLiving) entity).ax();
    }

    @Override
    public void setAge(int age) {
        ((OEntityLiving) entity).aV = age;
    }

    @Override
    public void setDeathTicks(int ticks) {
        ((OEntityLiving) entity).av = ticks;
    }

    @Override
    public void setHealth(int newHealth) {
        ((OEntityLiving) entity).h(newHealth);
    }

    @Override
    public void setHome(Vector3D vector) {
        setHomeArea((int) Math.floor(vector.getX()), (int) Math.floor(vector.getY()), (int) Math.floor(vector.getZ()), 25);
    }

    @Override
    public void setHomeArea(Vector3D vector, int dist) {
        if(vector == null) {
            throw new IllegalArgumentException("Could not set EntityLivings home. Vector was null!");
        }
        setHomeArea((int) Math.floor(vector.getX()), (int) Math.floor(vector.getY()), (int) Math.floor(vector.getZ()), dist);
    }

    @Override
    public void setHomeArea(int x, int y, int z, int dist) {
        ((OEntityLiving) entity).b(x, y, z, dist);
    }

    @Override
    public void setHomeRadius(int newRadius) { 
        setHomeArea(getHome(), newRadius);
    }

    @Override
    public void spawn() {
        spawn(null);
    }

    @Override
    public void spawn(EntityLiving rider) {
        OWorld world = ((CanaryDimension) getDimension()).getHandle();

        entity.c(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        world.b(entity);

        if (rider != null) {
            OEntityLiving mob2 = (OEntityLiving) ((CanaryEntityLiving) rider).getHandle();

            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            world.b(mob2);
            mob2.b(entity);
        }
        
    }
}
