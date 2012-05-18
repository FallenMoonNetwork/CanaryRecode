package net.canarymod.api.entity;

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
    public EntityLiving getTarget() {
        return new CanaryEntityLiving(((OEntityLiving) entity).at());
    }

    @Override
    public void setTarget(EntityLiving entityliving) {
        ((OEntityLiving) entity).b(((CanaryEntity) entityliving).entity);
    }

    @Override
    public boolean canSpawn() {
        return ((OEntityMob)entity).l();
    }
}
