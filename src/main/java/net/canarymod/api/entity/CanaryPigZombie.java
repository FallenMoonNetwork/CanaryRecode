package net.canarymod.api.entity;

import net.minecraft.server.OEntityPigZombie;

public class CanaryPigZombie extends CanaryZombie implements PigZombie {

    public CanaryPigZombie(OEntityPigZombie entity) {
        super(entity);
    }

    @Override
    public int getAngerLevel() {
       return ((OEntityPigZombie)entity).a;
    }

    @Override
    public boolean isAngry() {
        return ((OEntityPigZombie)entity).a > 0;
    }

    @Override
    public void setAngerLevel(int level) {
        ((OEntityPigZombie)entity).a = level;
    }
}
