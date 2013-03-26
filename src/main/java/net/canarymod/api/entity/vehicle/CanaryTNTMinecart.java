package net.canarymod.api.entity.vehicle;

import net.canarymod.api.world.CanaryWorld;
import net.minecraft.server.EntityMinecartTNT;

/**
 * TNTMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryTNTMinecart extends CanaryMinecart implements TNTMinecart {
    private boolean damageWorld, damageEntity;
    private float power = 4.0F;

    /**
     * Constructs a new wrapper for EntityMinecartTNT
     * 
     * @param entity
     *            the EntityMinecartTNT to be wrapped
     */
    public CanaryTNTMinecart(EntityMinecartTNT entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageWorld(boolean canDamage) {
        damageWorld = canDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDamageWorld() {
        return damageWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanDamageEntities(boolean canDamage) {
        damageEntity = canDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDamageEntities() {
        return damageEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detonate() {
        ((CanaryWorld) getWorld()).getHandle().a(getHandle(), getX(), getY(), getZ(), power, true);
        this.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuse() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuse(int fuse) {
        getHandle().a = fuse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseFuse(int increase) {
        getHandle().a += increase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseFuse(int decrease) {
        getHandle().a -= decrease;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPower() {
        return power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(float power) {
        this.power = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartTNT getHandle() {
        return (EntityMinecartTNT) entity;
    }
}
