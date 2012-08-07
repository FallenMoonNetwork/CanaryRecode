package net.canarymod.api.entity;

import net.minecraft.server.OEntityAgeable;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySquid;
import net.minecraft.server.OEntityVillager;

/**
 * basic animal entity wrapper class
 * 
 * @author Jason
 */
public class CanaryEntityAnimal extends CanaryEntityLiving implements EntityAnimal {

    public CanaryEntityAnimal(OEntityAnimal entity) {
        super(entity);
    }
    
    //For them squids ...
    public CanaryEntityAnimal(OEntitySquid entity) {
        super(entity);
    }

    /**
     * Villagers need the getters and setters from Ageable.
     * That's why villagers inherit from animals 
     * @param entity
     */
    public CanaryEntityAnimal(OEntityVillager entity) {
        super(entity);
    }

    @Override
    public int getGrowingAge() { //Squid has no age
        return entity instanceof OEntitySquid ? 0 : ((OEntityAgeable) entity).K();
    }

    @Override
    public void setGrowingAge(int age) {
        if(entity instanceof OEntitySquid) return; //Squid has no age
        ((OEntityAgeable) entity).c(age);
    }

    @Override
    public EntityLiving getTarget() {
        return new CanaryEntityLiving(((OEntityLiving) entity).at());
    }

    @Override
    public void setTarget(EntityLiving entityliving) {
        ((OEntityLiving) entity).b((OEntityLiving) ((CanaryEntity) entityliving).entity);
    }

}
