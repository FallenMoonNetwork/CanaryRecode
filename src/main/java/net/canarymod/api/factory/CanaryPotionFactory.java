package net.canarymod.api.factory;

import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;

public class CanaryPotionFactory implements PotionFactory {

    @Override
    public PotionEffect newPotionEffect(int id, int duration, int amplifier) {
        net.minecraft.server.PotionEffect oEffect = new net.minecraft.server.PotionEffect(id, duration, amplifier);
        return new CanaryPotionEffect(oEffect);
    }

    @Override
    public PotionEffect newPotionEffect(int id, int duration, int amplifier, boolean ambient) {
        net.minecraft.server.PotionEffect oEffect = new net.minecraft.server.PotionEffect(id, duration, amplifier, ambient);
        return new CanaryPotionEffect(oEffect);
    }

    @Override
    public PotionEffect newPotionEffect(PotionEffectType type, int duration, int amplifier) {
        if (type == null) {
            return null;
        }
        return newPotionEffect(type.getID(), duration, amplifier);
    }

    @Override
    public PotionEffect newPotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient) {
        if (type == null) {
            return null;
        }
        return newPotionEffect(type.getID(), duration, amplifier, ambient);
    }
}
