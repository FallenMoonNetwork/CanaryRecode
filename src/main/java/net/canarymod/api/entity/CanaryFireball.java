package net.canarymod.api.entity;


import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityFireball;

/**
 * Fireball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryFireball extends CanaryEntity implements Fireball {

    public CanaryFireball(EntityFireball entity) {
        super(entity);
    }

    @Override
    public EntityLiving getOwner() {
        return (EntityLiving) ((EntityFireball) entity).a.getCanaryEntity();
    }

}
