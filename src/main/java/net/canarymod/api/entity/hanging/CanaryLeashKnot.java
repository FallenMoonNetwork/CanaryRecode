package net.canarymod.api.entity.hanging;

import net.minecraft.server.EntityLeashKnot;

/**
 * LeashKnot wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryLeashKnot extends CanaryHangingEntity implements LeashKnot {

    public CanaryLeashKnot(EntityLeashKnot entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLeashKnot getHandle() {
        return (EntityLeashKnot) entity;
    }

}
