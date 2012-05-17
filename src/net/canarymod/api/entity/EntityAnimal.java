package net.canarymod.api.entity;

import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityLiving;

/**
 * basic animal entity wrapper class
 * 
 * @author Jason
 */
public class EntityAnimal extends EntityLiving implements IEntityAnimal{

	public EntityAnimal(OEntityAnimal entity) {
		super(entity);
	}

	@Override
	public int getGrowingAge() {
		return ((OEntityAnimal)entity).K();
	}

	@Override
	public void setGrowingAge(int age) {
		((OEntityAnimal)entity).c(age);
	}

	@Override
	public void attackEntity(IEntityLiving arg0) {
		// TODO Auto-generated method stub
		//Also looking at IEntityMob  this is missing IDamageSource param  intentional or just oversight?
		
	}

	@Override
	public int getAttackStrenght() {
		// TODO Auto-generated method stub
		// not sure where to get this from at this time
		return 0;
	}

	@Override
	public IEntityLiving getTarget() {
		return new EntityLiving(((OEntityLiving)entity).at());
	}

	@Override
	public void setTarget(IEntityLiving entityliving) {
		((OEntityLiving)entity).b((OEntityLiving)((Entity)entityliving).entity);
	}

}
