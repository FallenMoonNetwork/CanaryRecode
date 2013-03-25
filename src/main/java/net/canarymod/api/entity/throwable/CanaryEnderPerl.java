package net.canarymod.api.entity.throwable;


import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.minecraft.server.EntityEnderPearl;


/**
 * EnderPerl wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderPerl extends CanaryEntity implements EnderPerl {

    public CanaryEnderPerl(EntityEnderPearl entity) {
        super(entity);
    }

    @Override
    public Entity getThrower() {
        return ((EntityEnderPearl) entity).h().getCanaryEntity();
    }

    @Override
    public EntityEnderPearl getHandle() {
        return (EntityEnderPearl) entity;
    }
}
