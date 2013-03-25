package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityGiantZombie;


/**
 * GiantZombie wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryGiantZombie extends CanaryEntityMob implements GiantZombie {

    public CanaryGiantZombie(EntityGiantZombie entity) {
        super(entity);
    }
}
