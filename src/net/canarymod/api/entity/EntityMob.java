package net.canarymod.api.entity;

import net.canarymod.api.IDamageSource;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMob;

/**
 * basic mob entity wrapper class
 * 
 * @author Jason
 */
public class EntityMob extends EntityLiving implements IEntityMob {

    public EntityMob(OEntityMob entity) {
        super(entity);
    }

    @Override
    public void attackEntity(IEntityLiving arg0, IDamageSource arg1) {
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
    public IEntityLiving getTarget() {
        return new EntityLiving(((OEntityLiving) entity).at());
    }

    @Override
    public void setTarget(IEntityLiving entityliving) {
        ((OEntityLiving) entity).b(((Entity) entityliving).entity);
    }
}
