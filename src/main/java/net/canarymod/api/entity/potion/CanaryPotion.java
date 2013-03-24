package net.canarymod.api.entity.potion;

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

    public net.minecraft.server.Potion getHandle() {
        return potion;
    }

}
