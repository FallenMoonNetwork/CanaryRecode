package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityBat;


/**
 * Bat wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBat extends CanaryEntityAnimal implements Bat {

    public CanaryBat(EntityBat entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHanging() {
        return ((EntityBat) entity).h();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHanging(boolean hanging) {
        ((EntityBat) entity).a(hanging);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getHandle() {
        return (EntityBat) entity;
    }
}
