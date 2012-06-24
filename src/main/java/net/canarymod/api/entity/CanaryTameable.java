package net.canarymod.api.entity;

import net.minecraft.server.OEntityTameable;

public class CanaryTameable extends CanaryEntityAnimal implements Tameable {

    public CanaryTameable(OEntityTameable entity) {
        super(entity);
    }

    @Override
    public EntityLiving getOwner() {
        return ((OEntityTameable)entity).w_().getCanaryEntityLiving();
    }

    @Override
    public void setOwner(EntityLiving entity) {
        ((OEntityTameable)entity).a(entity.getName());

    }

    @Override
    public boolean isTamed() {
        return ((OEntityTameable)entity).u_();
    }

    @Override
    public void setTamed(boolean tamed) {
        ((OEntityTameable)entity).b(tamed);
    }

    @Override
    public boolean isSitting() {
        return ((OEntityTameable)entity).v_();
    }

    @Override
    public void setSitting(boolean sitting) {
        ((OEntityTameable)entity).c(sitting);
    }

}
