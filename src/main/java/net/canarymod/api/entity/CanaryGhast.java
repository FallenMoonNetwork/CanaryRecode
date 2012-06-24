package net.canarymod.api.entity;

import net.minecraft.server.OEntityGhast;

public class CanaryGhast extends CanaryEntityMob implements Ghast {

    private boolean damageWorld = true;
    private boolean damageEntity = true;
    
    public CanaryGhast(OEntityGhast entity) {
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
        damageEntity = canDamage;
    }

    @Override
    public boolean canDamageEntities() {
        return damageEntity;
    }
}
