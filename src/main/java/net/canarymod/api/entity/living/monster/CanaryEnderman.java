package net.canarymod.api.entity.living.monster;


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
    public short getCarriedBlockID() {
        return (short) getHandle().o();
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
        return (short) getHandle().p();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCarriedBlockMetaData(short metadata) {
        getHandle().s(metadata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean randomTeleport() {
        return getHandle().m();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderman getHandle() {
        return (EntityEnderman) entity;
    }
}
