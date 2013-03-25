package net.canarymod.api.entity;


import net.minecraft.server.EntityHanging;


/**
 * HangingEntity wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryHangingEntity extends CanaryEntity implements HangingEntity {

    /**
     * Constructs a new wrapper for EntityHanging
     * 
     * @param entity
     *            the EntityHanging to be wrapped
     */
    public CanaryHangingEntity(EntityHanging entity) {
        super(entity);
    }
    
}
