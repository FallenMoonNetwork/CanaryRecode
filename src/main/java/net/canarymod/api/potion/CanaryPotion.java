package net.canarymod.api.potion;

import net.canarymod.api.potion.Potion;
import net.canarymod.api.potion.PotionEffectType;


public class CanaryPotion implements Potion {

    net.minecraft.server.Potion potion;

    public CanaryPotion(net.minecraft.server.Potion potion) {
        this.potion = potion;
    }

    @Override
    public int getID() {
        return potion.a();
    }

    @Override
    public String getName() {
        return potion.c();
    }

    @Override
    public PotionEffectType getType() {
        return PotionEffectType.fromName(getName());
    }

    @Override
    public boolean isBad() {
        return potion.isBad();
    }

    @Override
    public double getEffectiveness() {
        return potion.d();
    }

    @Override
    public boolean isUsable() {
        return potion.f();
    }

    @Override
    public boolean isInstant() {
        return potion.b();
    }

    public net.minecraft.server.Potion getHandle() {
        return potion;
    }

}
