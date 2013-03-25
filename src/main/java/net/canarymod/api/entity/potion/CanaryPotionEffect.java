package net.canarymod.api.entity.potion;


import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;


public class CanaryPotionEffect implements PotionEffect {

    net.minecraft.server.PotionEffect effect;

    public CanaryPotionEffect(net.minecraft.server.PotionEffect effect) {
        this.effect = effect;
    }

    @Override
    public int getPotionID() {
        return effect.a();
    }

    @Override
    public int getDuration() {
        return effect.b();
    }

    @Override
    public int getAmplifier() {
        return effect.c();
    }

    @Override
    public String getName() {
        return effect.d();
    }

    @Override
    public void performEffect(EntityLiving entity) {
        net.minecraft.server.EntityLiving oLiving = (net.minecraft.server.EntityLiving) ((CanaryEntityLiving) entity).getHandle();

        effect.b(oLiving);
    }

    public net.minecraft.server.PotionEffect getHandle() {
        return effect;
    }

}
