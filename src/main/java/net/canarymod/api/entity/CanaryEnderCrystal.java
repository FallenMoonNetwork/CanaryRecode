package net.canarymod.api.entity;


import net.minecraft.server.EntityEnderCrystal;


/**
 * EnderCrystal wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderCrystal extends CanaryEntity implements EnderCrystal {

    public CanaryEnderCrystal(EntityEnderCrystal entity) {
        super(entity);
    }

    @Override
    public void setCanDamageWorld(boolean canDamage) {// TODO Auto-generated method stub
    }

    @Override
    public boolean canDamageWorld() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCanDamageEntities(boolean canDamage) {// TODO Auto-generated method stub
    }

    @Override
    public boolean canDamageEntities() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setHealth(int health) {// TODO Auto-generated method stub
    }

    @Override
    public EntityEnderCrystal getHandle() {
        return (EntityEnderCrystal) entity;
    }
}
