package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntityCreeper;


/**
 * Creeper wrapper implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryCreeper extends CanaryEntityMob implements Creeper {

    private boolean damageEntities = true;
    private boolean damageWorld = true;

    public CanaryCreeper(EntityCreeper entity) {
        super(entity);
    }

    @Override
    public void setCanDamageWorld(boolean canDamage) {
        damageWorld = canDamage;
    }

    @Override
    public boolean canDamageWorld() {
        return damageWorld;
    }

    @Override
    public void setCanDamageEntities(boolean canDamage) {
        damageEntities = canDamage;
    }

    @Override
    public boolean canDamageEntities() {
        return damageEntities;
    }

    @Override
    public boolean isCharged() {
        return ((EntityCreeper) entity).m();
    }

    @Override
    public void setCharged(boolean charged) {
        ((EntityCreeper) entity).setCharged(charged);
    }

    @Override
    public EntityCreeper getHandle() {
        return (EntityCreeper) entity;
    }

}
