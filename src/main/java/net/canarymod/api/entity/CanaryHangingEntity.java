package net.canarymod.api.entity;


import net.minecraft.server.EntityHanging;


public abstract class CanaryHangingEntity extends CanaryEntity implements HangingEntity {

    public CanaryHangingEntity(EntityHanging entity) {
        super(entity);
    }
    
}
