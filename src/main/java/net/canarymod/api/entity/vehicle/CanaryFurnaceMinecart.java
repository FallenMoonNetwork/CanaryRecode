package net.canarymod.api.entity.vehicle;


import net.minecraft.server.EntityMinecartFurnace;

/**
 * FurnaceMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFurnaceMinecart extends CanaryMinecart implements FurnaceMinecart {

    /**
     * Constructs a new wrapper for EntityMinecartFurnace
     * 
     * @param entity
     *            the EntityMinecartFurnace to be wrapped
     */
    public CanaryFurnaceMinecart(EntityMinecartFurnace entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuelLevel() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuelLevel(int level) {
        getHandle().c = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseFuelLevel(int increase) {
        getHandle().c += increase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseFuelLevel(int decrease) {
        getHandle().c -= decrease;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityMinecartFurnace getHandle() {
        return (EntityMinecartFurnace) entity;
    }

}
