package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityEnderman;

/**
 * Enderman wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderman extends CanaryEntityMob implements Enderman {

    /**
     * Constructs a new wrapper for EntityEnderman
     * 
     * @param entity
     *            the EntityEnderman to wrap
     */
    public CanaryEnderman(EntityEnderman entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.ENDERMAN;
    }

    @Override
    public String getFqName() {
        return "Enderman";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getCarriedBlockID() {
        return (short) getHandle().bW();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarriedBlockID(short blockId) {
        getHandle().a(blockId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getCarriedBlockMetaData() {
        return (short) getHandle().bV();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarriedBlockMetaData(short metadata) {
        getHandle().c(metadata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean randomTeleport() {
        return getHandle().bT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isScreaming() {
        return getHandle().bX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScreaming(boolean screaming) {
        getHandle().a(screaming);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderman getHandle() {
        return (EntityEnderman) entity;
    }
}
