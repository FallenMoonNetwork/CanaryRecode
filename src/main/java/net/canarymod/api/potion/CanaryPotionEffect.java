package net.canarymod.api.potion;


import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;


/**
 * PotionEffect wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPotionEffect implements PotionEffect {

    net.minecraft.server.PotionEffect effect;

    /**
     * Constructs a new wrapper for PotionEffect
     * 
     * @param effect
     *            the PotionEffect to wrap
     */
    public CanaryPotionEffect(net.minecraft.server.PotionEffect effect) {
        this.effect = effect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPotionID() {
        return effect.a();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDuration() {
        return effect.b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAmplifier() {
        return effect.c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return effect.f();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performEffect(EntityLiving entity) {
        net.minecraft.server.EntityLiving oLiving = (net.minecraft.server.EntityLiving) ((CanaryEntityLiving) entity).getHandle();

        effect.b(oLiving);
    }

    /**
     * Gets the PotionEffect being wrapped
     * 
     * @return the PotionEffect
     */
    public net.minecraft.server.PotionEffect getHandle() {
        return effect;
    }

}
