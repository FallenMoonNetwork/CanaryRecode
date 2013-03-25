package net.canarymod.api.entity;


import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityFireball;


/**
 * Fireball wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryFireball extends CanaryEntity implements Fireball {

    /**
     * Constructs a new wrapper for EntityFireball
     * 
     * @param entity
     *            the EntityEnderCrystal to be wrapped
     */
    public CanaryFireball(EntityFireball entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLiving getOwner() {
        return (EntityLiving) ((EntityFireball) entity).a.getCanaryEntity();
    }

}
