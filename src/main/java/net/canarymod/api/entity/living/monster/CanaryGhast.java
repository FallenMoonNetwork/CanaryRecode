package net.canarymod.api.entity.living.monster;

import net.minecraft.server.EntityGhast;

public class CanaryGhast extends CanaryEntityMob implements Ghast {

    private boolean damageWorld = true;
    private boolean damageEntity = true;

    public CanaryGhast(EntityGhast entity) {
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
