package net.canarymod.api.entity.potion;

import net.canarymod.api.entity.CanaryEntityLiving;
import net.canarymod.api.entity.EntityLiving;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OPotionEffect;

public class CanaryPotionEffect implements PotionEffect {

    OPotionEffect effect;
    
    public CanaryPotionEffect(OPotionEffect effect) {
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
        OEntityLiving oLiving = (OEntityLiving) ((CanaryEntityLiving) entity).getHandle();
        effect.b(oLiving);
    }
    
    public OPotionEffect getHandle() {
        return effect;
    }

}
