package net.canarymod.api.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityFireball;

public abstract class CanaryFireball extends CanaryEntity implements Fireball{

    public CanaryFireball(EntityFireball entity){
        super(entity);
    }

    @Override
    public EntityLiving getOwner(){
        return ((EntityFireball) entity).a.getCanaryEntityLiving();
    }

}
