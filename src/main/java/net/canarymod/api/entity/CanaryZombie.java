package net.canarymod.api.entity;

import net.minecraft.server.OEntityZombie;

public class CanaryZombie extends CanaryEntityMob implements Zombie {

    public CanaryZombie(OEntityZombie entity) {
        super(entity);
    }

}
