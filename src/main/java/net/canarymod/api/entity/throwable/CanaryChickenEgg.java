package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntityEgg;


/**
 * ChickenEgg wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryChickenEgg extends CanaryEntityThrowable implements ChickenEgg {

    public CanaryChickenEgg(EntityEgg entity) {
        super(entity);
    }

    @Override
    public EntityEgg getHandle() {
        return (EntityEgg) entity;
    }
}
