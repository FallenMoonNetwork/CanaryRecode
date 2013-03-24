package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityFlying;
import net.minecraft.server.EntitySlime;

/**
 * basic mob entity wrapper class
 * 
 * @author Jason
 */
public class CanaryEntityMob extends CanaryEntityLiving implements EntityMob {

    public CanaryEntityMob(net.minecraft.server.EntityMob entity) {
        super(entity);
    }

    // Workaround because notchies inconsistencies
    public CanaryEntityMob(EntitySlime entity) {
        super(entity);
    }

    // Workaround because notchies inconsistencies
    public CanaryEntityMob(EntityFlying entity) {
        super(entity);
    }

    @Override
    public EntityLiving getTarget() {
        return new CanaryEntityLiving(((EntityLiving) entity).at());
    }

    @Override
    public void setTarget(EntityLiving entityliving) {
        ((net.minecraft.server.EntityLiving) entity).b(((CanaryEntity) entityliving).entity);
    }

    @Override
    public boolean canSpawn() {
        return ((net.minecraft.server.EntityLiving) entity).l();
    }
}
