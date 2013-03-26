package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.EntityLiving;
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
    public EntityLiving getOwner() {
        return (EntityLiving) ((EntityTameable) entity).p().getCanaryEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOwner(EntityLiving entity) {
        ((EntityTameable) entity).a(entity.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTamed() {
        return ((EntityTameable) entity).m();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTamed(boolean tamed) {
        ((EntityTameable) entity).j(tamed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSitting() {
        return ((EntityTameable) entity).n();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSitting(boolean sitting) {
        ((EntityTameable) entity).k(sitting);
    }
}
