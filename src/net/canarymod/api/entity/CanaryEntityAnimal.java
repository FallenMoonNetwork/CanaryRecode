package net.canarymod.api.entity;

import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityLiving;

/**
 * basic animal entity wrapper class
 * 
 * @author Jason
 */
public class CanaryEntityAnimal extends CanaryEntityLiving implements EntityAnimal {

    public CanaryEntityAnimal(OEntityAnimal entity) {
        super(entity);
    }

    @Override
    public int getGrowingAge() {
        return ((OEntityAnimal) entity).K();
    }

    @Override
    public void setGrowingAge(int age) {
        ((OEntityAnimal) entity).c(age);
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
