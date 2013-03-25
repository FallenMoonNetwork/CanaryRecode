package net.canarymod.api.entity.throwable;


import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.minecraft.server.EntityEgg;


/**
 * ChickenEgg wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChickenEgg extends CanaryEntity implements ChickenEgg {

    public CanaryChickenEgg(EntityEgg entity) {
        super(entity);
    }

    @Override
    public Entity getThrower() {
        return ((EntityEgg) entity).h().getCanaryEntity();
    }

    @Override
    public EntityEgg getHandle() {
        return (EntityEgg) entity;
    }
}
