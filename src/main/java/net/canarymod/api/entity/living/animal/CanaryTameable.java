package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityTameable;

public class CanaryTameable extends CanaryEntityAnimal implements Tameable {

    public CanaryTameable(EntityTameable entity) {
        super(entity);
    }

    @Override
    public EntityLiving getOwner() {
        return ((EntityTameable) entity).w_().getCanaryEntityLiving();
    }

    @Override
    public void setOwner(EntityLiving entity) {
        ((EntityTameable) entity).a(entity.getName());

    }

    @Override
    public boolean isTamed() {
        return ((EntityTameable) entity).u_();
    }

    @Override
    public void setTamed(boolean tamed) {
        ((EntityTameable) entity).b(tamed);
    }

    @Override
    public boolean isSitting() {
        return ((EntityTameable) entity).v_();
    }

    @Override
    public void setSitting(boolean sitting) {
        ((EntityTameable) entity).c(sitting);
    }

}
