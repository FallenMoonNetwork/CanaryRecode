package net.canarymod.api.entity;

import net.minecraft.server.OEntityIronGolem;

public class CanaryIronGolem extends CanaryEntityLiving implements Golem {
    public CanaryIronGolem(OEntityIronGolem entity) {
        super(entity);
    }
}
