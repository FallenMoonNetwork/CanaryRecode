package net.canarymod.api.entity.living;

import net.minecraft.server.EntityIronGolem;

public class CanaryIronGolem extends CanaryEntityLiving implements Golem {
    public CanaryIronGolem(EntityIronGolem entity) {
        super(entity);
    }
}
