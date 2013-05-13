package net.canarymod.api.entity;


import net.minecraft.server.EntityFallingSand;


/**
 * FallingBlock wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFallingBlock extends CanaryEntity implements FallingBlock {

    /**
     * Constructs a new wrapper for EntityFallingSand
     * 
     * @param entity
     *            the EntityFallingSand to be wrapped
     */
    public CanaryFallingBlock(EntityFallingSand entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getBlockID() {
        return (short) getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getBlockMetaData() {
        return (short) getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxDamage() {
        return getHandle().h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxDamage(int max) {
        getHandle().h = max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDamageAmount() {
        return getHandle().i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamageAmount(float damage) {
        getHandle().i = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFallingSand getHandle() {
        return (EntityFallingSand) entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockID(int id) {
        getHandle().a = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockMetaData(int data) {
        getHandle().b = data;
    }

}
