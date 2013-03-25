package net.canarymod.api.entity;


import net.minecraft.server.EntityEnderCrystal;


/**
 * EnderCrystal wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderCrystal extends CanaryEntity implements EnderCrystal {

    /**
     * Constructs a new wrapper for EntityEnderCrystal
     * 
     * @param entity
     *            the EntityEnderCrystal to be wrapped
     */
    public CanaryEnderCrystal(EntityEnderCrystal entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageWorld(boolean canDamage) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDamageWorld() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageEntities(boolean canDamage) {
    }

    @Override
    public boolean canDamageEntities() {
        return false;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int health) {
    }


    @Override
    public float getPower() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPower(float power) {
        // TODO Auto-generated method stub

    }

    @Override
    public void detonate() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isOneHitDetonate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setOneHitDetonate(boolean oneHit) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityEnderCrystal getHandle() {
        return (EntityEnderCrystal) entity;
    }
}
