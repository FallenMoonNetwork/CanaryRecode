package net.canarymod.api.entity;

import net.canarymod.api.DamageSource;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMob;

/**
 * basic mob entity wrapper class
 * 
 * @author Jason
 */
public class CanaryEntityMob extends CanaryEntityLiving implements EntityMob {

    public CanaryEntityMob(OEntityMob entity) {
        super(entity);
    }

    @Override
    public void attackEntity(EntityLiving arg0, DamageSource arg1) {
        // TODO Auto-generated method stub
        // pending damage source implementations
    }

    @Override
    public int getAttackStrength() {
        // TODO Auto-generated method stub
        // not sure where to get this from at this time
        return 0;
    }

    @Override
    public EntityLiving getTarget() {
        return new CanaryEntityLiving(((OEntityLiving) entity).at());
    }

    @Override
    public void setTarget(EntityLiving entityliving) {
        ((OEntityLiving) entity).b(((CanaryEntity) entityliving).entity);
    }
}
