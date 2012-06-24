package net.canarymod.api.entity;

import net.minecraft.server.OEntityFireball;

public class CanaryFireball extends CanaryEntity implements Fireball{

    public CanaryFireball(OEntityFireball entity) {
        super(entity);
    }

    @Override
    public EntityLiving getShootingEntity() {
        return ((OEntityFireball)entity).a.getCanaryEntityLiving();
    }

}
