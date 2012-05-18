package net.canarymod.api.entity;

import net.minecraft.server.OEntityCreeper;

/**
 * CanaryCreeper - Wrapper for a creeper.
 * @author Chris
 *
 */
public class CanaryCreeper extends CanaryEntityMob implements Creeper {

    private boolean damageEntities = true;
    private boolean damageWorld = true;
    public CanaryCreeper(OEntityCreeper entity) {
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
        return ((OEntityCreeper)entity).x();
    }

    @Override
    public void setCharged(boolean charged) {
        ((OEntityCreeper)entity).setCharged(charged);
    }

}
