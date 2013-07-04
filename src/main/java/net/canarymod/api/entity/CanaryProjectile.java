package net.canarymod.api.entity;

import net.minecraft.server.Entity;
import net.minecraft.server.IProjectile;

/**
 * Projectile wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryProjectile extends CanaryEntity implements Projectile {

    public CanaryProjectile(IProjectile projectile) {
        super((Entity) projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProjectileHeading(double motionX, double motionY, double motionZ, float rotationYaw, float rotationPitch) {
        ((IProjectile) getHandle()).c(motionX, motionY, motionZ, rotationYaw, rotationPitch);
    }

}
