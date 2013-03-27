package net.canarymod.api.entity.throwable;


import net.minecraft.server.EntityEnderPearl;


/**
 * EnderPerl wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderPearl extends CanaryEntityThrowable implements EnderPearl {

    public CanaryEnderPearl(EntityEnderPearl entity) {
        super(entity);
    }

    @Override
    public EntityEnderPearl getHandle() {
        return (EntityEnderPearl) entity;
    }
}
