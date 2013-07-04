package net.canarymod.api.potion;

/**
 * Potion wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPotion implements Potion {

    net.minecraft.server.Potion potion;

    /**
     * Constructs a new wrapper for Potion
     * 
     * @param potion
     *            the Potion to wrap
     */
    public CanaryPotion(net.minecraft.server.Potion potion) {
        this.potion = potion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getID() {
        return potion.c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return potion.a();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PotionEffectType getEffectType() {
        return PotionEffectType.fromName(getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBad() {
        return potion.J;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getEffectiveness() {
        return potion.g();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsable() {
        return potion.i();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInstant() {
        return potion.b();
    }

    /**
     * Gets the Potion being wrapped
     * 
     * @return potion
     */
    public net.minecraft.server.Potion getHandle() {
        return potion;
    }

}
