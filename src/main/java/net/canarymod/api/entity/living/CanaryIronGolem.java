package net.canarymod.api.entity.living;


import net.minecraft.server.EntityIronGolem;


/**
 * IronGolem wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryIronGolem extends CanaryEntityLiving implements Golem {
    public CanaryIronGolem(EntityIronGolem entity) {
        super(entity);
    }

    @Override
    public EntityIronGolem getHandle() {
        return (EntityIronGolem) entity;
    }
}
