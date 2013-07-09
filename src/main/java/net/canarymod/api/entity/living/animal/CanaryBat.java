package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityBat;

/**
 * Bat wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBat extends CanaryEntityAnimal implements Bat {

    /**
     * Constructs a new wrapper for EntityBat
     * 
     * @param entity
     *            the EntityBat to be wrapped
     */
    public CanaryBat(EntityBat entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.BAT;
    }

    @Override
    public String getFqName() {
        return "Bat";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHanging() {
        return getHandle().bG();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHanging(boolean hanging) {
        getHandle().a(hanging);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityBat getHandle() {
        return (EntityBat) entity;
    }
}
