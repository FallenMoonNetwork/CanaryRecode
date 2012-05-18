package net.canarymod.api.entity;

import net.minecraft.server.OEntityChicken;

public class CanaryChicken extends CanaryEntityAnimal implements Chicken {

    public CanaryChicken(OEntityChicken entity) {
        super(entity);
    }

}
