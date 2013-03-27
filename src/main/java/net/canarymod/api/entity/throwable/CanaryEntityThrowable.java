package net.canarymod.api.entity.throwable;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.EntityLiving;

public abstract class CanaryEntityThrowable extends CanaryEntity implements EntityThrowable {

    public CanaryEntityThrowable(net.minecraft.server.EntityThrowable entity) {
        super(entity);
    }

    @Override
    public EntityLiving getThrower() {
        return (EntityLiving) ((net.minecraft.server.EntityThrowable) entity).h().getCanaryEntity();
    }

    @Override
    public float getGravity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setGravity(float velocity) {
        // TODO Auto-generated method stub

    }
}
