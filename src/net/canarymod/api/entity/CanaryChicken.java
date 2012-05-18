package net.canarymod.api.entity;

import net.minecraft.server.OEntityChicken;

/**
 * Chicken wraps. Wraps a notchina chicken
 * @author Chris
 *
 */
public class CanaryChicken extends CanaryEntityAnimal implements Chicken {

    public CanaryChicken(OEntityChicken entity) {
        super(entity);
    }

    @Override
    public void setTimeUntilNextEgg(int timeTicks) {
        ((OEntityChicken)entity).j = timeTicks;
    }

}
