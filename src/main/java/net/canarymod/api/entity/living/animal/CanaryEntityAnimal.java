package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityAgeable;
import net.minecraft.server.EntitySquid;

/**
 * basic animal entity wrapper class
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntityAnimal extends CanaryEntityLiving implements EntityAnimal{

    public CanaryEntityAnimal(net.minecraft.server.EntityAnimal entity){
        super(entity);
    }

    // For them squids ...
    public CanaryEntityAnimal(net.minecraft.server.EntitySquid entity){
        super(entity);
    }

    // For them Bats...
    public CanaryEntityAnimal(net.minecraft.server.EntityAmbientCreature entity){
        super(entity);
    }

    /**
     * Villagers need the getters and setters from Ageable.
     * That's why villagers inherit from animals
     * 
     * @param entity
     */
    public CanaryEntityAnimal(net.minecraft.server.EntityVillager entity){
        super(entity);
    }

    @Override
    public int getGrowingAge(){ // Squid has no age
        return entity instanceof net.minecraft.server.EntitySquid ? 0 : ((net.minecraft.server.EntityAgeable) entity).K();
    }

    @Override
    public void setGrowingAge(int age){
        if (entity instanceof EntitySquid)
            return; // Squid has no age
        ((EntityAgeable) entity).c(age);
    }

    @Override
    public EntityLiving getTarget(){
        return new CanaryEntityLiving(((net.minecraft.server.EntityLiving) entity).at());
    }

    @Override
    public void setTarget(EntityLiving entityliving){
        ((EntityLiving) entity).b((EntityLiving) ((CanaryEntity) entityliving).entity);
    }

}
