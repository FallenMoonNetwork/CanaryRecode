package net.canarymod.api.entity.living.animal;


import net.minecraft.server.EntityChicken;


/**
 * Chicken wraps. Wraps a notchina chicken
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryChicken extends CanaryEntityAnimal implements Chicken {

    /**
     * Constructs a new wrapper for EntityChicken
     * 
     * @param entity
     *            the EntityChicken to be wrapped
     */
    public CanaryChicken(EntityChicken entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimeUntilNextEgg(int timeTicks) {
        getHandle().j = timeTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityChicken getHandle() {
        return (EntityChicken) entity;
    }

}
