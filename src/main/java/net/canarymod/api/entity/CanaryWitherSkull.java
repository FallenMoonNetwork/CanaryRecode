package net.canarymod.api.entity;

import net.minecraft.server.EntityFireball;

public class CanaryWitherSkull extends CanaryFireball implements WitherSkull {

    public CanaryWitherSkull(EntityFireball entity) {
        super(entity);
    }

    @Override
    public net.minecraft.server.Entity getHandle() {
        return (net.minecraft.server.Entity) this.entity;
    }

}
