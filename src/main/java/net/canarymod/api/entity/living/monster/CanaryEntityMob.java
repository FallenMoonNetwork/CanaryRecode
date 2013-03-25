package net.canarymod.api.entity.living.monster;


import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.minecraft.server.EntityFlying;
import net.minecraft.server.EntitySlime;


/**
 * EntityMob wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntityMob extends CanaryEntityLiving implements EntityMob {

    public CanaryEntityMob(net.minecraft.server.EntityMob entity) {
        super(entity);
    }

    public CanaryEntityMob(EntitySlime entity) {
        super(entity);
    }

    public CanaryEntityMob(EntityFlying entity) {
        super(entity);
    }

    @Override
    public boolean canSpawn() {
        return ((net.minecraft.server.EntityLiving) entity).l();
    }
}
