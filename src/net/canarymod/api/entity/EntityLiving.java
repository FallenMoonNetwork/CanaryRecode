package net.canarymod.api.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.IDamageSource;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.OIMob;

/**
 * basic living entity wrapper class
 * 
 * @author Jason
 */
public class EntityLiving extends Entity implements IEntityLiving{
	
	public EntityLiving(OEntityLiving entity){
		super(entity);
	}

	@Override
	public boolean canSee(IEntityLiving entityliving) {
		return ((OEntityLiving)entity).h(((Entity)entityliving).entity);
	}

	@Override
	public boolean canSpawn() {
		return ((OEntityLiving)entity).l();
	}

	@Override
	public void dealDamage(IDamageSource damagesource, int amount) {
		// TODO ODamageSource matching for null handle (cause someone didn't truely think out the API library)
		if(damagesource instanceof DamageSource && ((DamageSource) damagesource).getHandle() != null){
			((OEntityLiving)entity).a(((DamageSource) damagesource).getHandle(), amount);
		}
		
	}

	@Override
	public int getAge() {
		return ((OEntityLiving)entity).aq();
	}

	@Override
	public IEntityAnimal getAnimal() {
		if(isAnimal()){
			return this instanceof EntityAnimal ? (EntityAnimal) this : new EntityAnimal((OEntityAnimal)entity);
		}
		return null;
	}

	@Override
	public int getDeathTicks() {
		return ((OEntityLiving)entity).av;
	}

	@Override
	public int getHealth() {
		return ((OEntityLiving)entity).aD();
	}

	@Override
	public Vector3D getHome() {
		if(hasHome()){
			OChunkCoordinates home = ((OEntityLiving)entity).av();
			return new Vector3D(home.a, home.b, home.c);
		}
		return null;
	}

	@Override
	public int getMaxHealth() {
		return ((OEntityLiving)entity).d();
	}

	@Override
	public IEntityMob getMob() {
		if(isMob()){
			return this instanceof EntityMob ? (EntityMob) this : new EntityMob((OEntityMob)entity);
		}
		return null;
	}

	@Override
	public String getName() {
		return OEntityList.b(entity);
	}

	@Override
	public IPlayer getPlayer() {
		//if(isPlayer()){
		//	return somefancywaytogetaplayerfromanentityliving();
		//}
		return null;
	}

	@Override
	public boolean hasHome() {
		return ((OEntityLiving)entity).ay();
	}

	@Override
	public void increaseHealth(int increase) {
		((OEntityLiving)entity).d(increase);
	}

	@Override
	public boolean isAnimal() {
		return entity instanceof OIAnimals;
	}

	@Override
	public boolean isMob() {
		return entity instanceof OIMob;
	}

	@Override
	public boolean isPlayer() {
		return ((OEntityLiving)entity).ah();
	}

	@Override
	public void kill() {
		//Kill as in setHealth(0) or entity.destroy();  (currently set as destroy)
		entity.X();
	}

	@Override
	public void knockBack(double x, double z) {
		((OEntityLiving)entity).a(entity, 0, x, z); //NOTE: There may be something more needed in the arguments
	}

	@Override
	public void moveEntity(double arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void playLivingSound() {
		((OEntityLiving)entity).az();
	}

	@Override
	public void removeHome() {
		((OEntityLiving)entity).ax();
	}

	@Override
	public void setAge(int age) {
		((OEntityLiving)entity).aV = age;
	}

	@Override
	public void setDeathTicks(int ticks) {
		((OEntityLiving)entity).av = ticks;
	}

	@Override
	public void setHealth(int newHealth) {
		((OEntityLiving)entity).h(newHealth);
	}

	@Override
	public void setHome(Vector3D vector) {
		setHomeArea((int)Math.floor(vector.getX()), (int)Math.floor(vector.getY()), (int)Math.floor(vector.getZ()), 25); //TODO what should default be for maxHomeDistance?
		
	}

	@Override
	public void setHomeArea(Vector3D vector, int dist) {
		setHomeArea((int)Math.floor(vector.getX()), (int)Math.floor(vector.getY()), (int)Math.floor(vector.getZ()), dist);
	}

	@Override
	public void setHomeArea(int x, int y, int z, int dist) {
		((OEntityLiving)entity).b(x, y, z, dist);
	}

	@Override
	public void setHomeRadius(int arg0) { //What is this?
		// TODO Auto-generated method stub
	}
}
