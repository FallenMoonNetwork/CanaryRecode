package net.canarymod.api.entity.living.monster;

import net.minecraft.server.EntityBlaze;

/**
 * Blaze wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBlaze extends CanaryEntityMob implements Blaze {

    public CanaryBlaze(EntityBlaze entity) {
        super(entity);
    }

    @Override
    public boolean isBurning() {
        return ((EntityBlaze) entity).m();
    }

    @Override
    public void setBurning(boolean isBurning) {
        ((EntityBlaze) entity).a(isBurning);
    }
}
