package net.canarymod.api.entity.living.monster;

import net.minecraft.server.EntityPigZombie;

public class CanaryPigZombie extends CanaryZombie implements PigZombie{

    public CanaryPigZombie(EntityPigZombie entity){
        super(entity);
    }

    @Override
    public int getAngerLevel(){
        return ((EntityPigZombie) entity).a;
    }

    @Override
    public boolean isAngry(){
        return ((EntityPigZombie) entity).a > 0;
    }

    @Override
    public void setAngerLevel(int level){
        ((EntityPigZombie) entity).a = level;
    }
}
