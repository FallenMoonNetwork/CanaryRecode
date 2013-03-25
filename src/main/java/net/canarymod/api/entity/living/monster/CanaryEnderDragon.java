package net.canarymod.api.entity.living.monster;


import net.canarymod.api.entity.EnderCrystal;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.minecraft.server.EntityDragon;


/**
 * Dragon wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderDragon extends CanaryEntityLiving implements EnderDragon {

    public CanaryEnderDragon(EntityDragon entity) {
        super(entity);
    }

    @Override
    public boolean isSlowed() {
        return ((EntityDragon) entity).bQ;
    }

    @Override
    public EnderCrystal getHealingCrystal() {
        if (((EntityDragon) entity).bS == null) {
            return null;
        }
        return (EnderCrystal) ((EntityDragon) entity).bS.getCanaryEntity();
    }

    @Override
    public EntityDragon getHandle() {
        return (EntityDragon) entity;
    }
}
