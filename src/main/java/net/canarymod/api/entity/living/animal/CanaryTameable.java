package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.LivingBase;
import net.minecraft.server.EntityTameable;

/**
 * Tameable wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryTameable extends CanaryEntityAnimal implements Tameable {

    /**
     * Constructs a new wrapper for EntityTameable
     * 
     * @param entity
     *            the EntityTameable to wrap
     */
    public CanaryTameable(EntityTameable entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingBase getOwner() {
        return (LivingBase) getHandle().bV().getCanaryEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOwnerName() {
        return getHandle().h_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOwner(LivingBase entity) {
        setOwner(entity.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOwner(String name) {
        getHandle().b(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTamed() {
        return getHandle().bT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTamed(boolean tamed) {
        getHandle().j(tamed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSitting() {
        return getHandle().bU();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSitting(boolean sitting) {
        ((EntityTameable) entity).k(sitting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityTameable getHandle() {
        return (EntityTameable) entity;
    }
}
