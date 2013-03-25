package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntityChicken;


/**
 * Chicken wraps. Wraps a notchina chicken
 * 
 * @author Chris
 */
public class CanaryChicken extends CanaryEntityAnimal implements Chicken {

    public CanaryChicken(EntityChicken entity) {
        super(entity);
    }

    @Override
    public void setTimeUntilNextEgg(int timeTicks) {
        ((EntityChicken) entity).j = timeTicks;
    }

    @Override
    public EntityChicken getHandle() {
        return (EntityChicken) entity;
    }

}
