package net.canarymod.api.entity.living;

import net.canarymod.api.world.CanaryVillage;
import net.canarymod.api.world.Village;
import net.minecraft.server.EntityIronGolem;

/**
 * IronGolem wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryIronGolem extends CanaryEntityLiving implements IronGolem {

    /**
     * Constructs a new wrapper for EntityIronGolem
     * 
     * @param entity
     *            the EntityIronGolem to be wrapped
     */
    public CanaryIronGolem(EntityIronGolem entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getVillage() {
        return getHandle().bP().getCanaryVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVillage(Village village) {
        getHandle().bp = ((CanaryVillage) village).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerCreated() {
        return getHandle().bS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerCreated(boolean created) {
        getHandle().j(created);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHoldingRose() {
        return getHandle().bR() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHoldingRose(boolean holding) {
        getHandle().a(holding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHoldRoseTicks() {
        return getHandle().bR();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHoldRoseTicks(int ticks) {
        getHandle().setRoseTicks(ticks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityIronGolem getHandle() {
        return (EntityIronGolem) entity;
    }
}
