package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntitySquid;


public class CanarySquid extends CanaryEntityAnimal implements Squid {

    public CanarySquid(EntitySquid entity) {
        super(entity);
    }

    @Override
    public EntitySquid getHandle() {
        return (EntitySquid) entity;
    }
}
