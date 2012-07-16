package net.canarymod.api.entity.potion;

import net.minecraft.server.OPotion;

public class CanaryPotion implements Potion {

    OPotion potion;

    public CanaryPotion(OPotion potion) {
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
    public PotionType getType() {
        return PotionType.fromName(getName());
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
    
    public OPotion getHandle() {
        return potion;
    }

}
