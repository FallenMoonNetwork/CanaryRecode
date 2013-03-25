package net.canarymod.api.factory;


import net.canarymod.api.entity.potion.CanaryPotionEffect;
import net.canarymod.api.entity.potion.PotionEffect;
import net.canarymod.api.factory.PotionFactory;
import net.minecraft.server.OPotionEffect;


public class CanaryPotionFactory implements PotionFactory {

    @Override
    public PotionEffect newPotionEffect(int id, int duration, int amplifier) {
        OPotionEffect oEffect = new OPotionEffect(id, duration, amplifier);

        return new CanaryPotionEffect(oEffect);
    }

}
