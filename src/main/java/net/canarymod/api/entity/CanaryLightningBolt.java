package net.canarymod.api.entity;

import net.canarymod.api.world.CanaryWorld;
import net.minecraft.server.EntityLightningBolt;

/**
 * LightningBolt wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryLightningBolt extends CanaryEntity implements LightningBolt {

    public CanaryLightningBolt(EntityLightningBolt entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLivingTime() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLivingTime(int time) {
        getHandle().c = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLightningState() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLightingState(int state) {
        getHandle().b = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean spawn() { // LightningBolts are special :3
        return ((CanaryWorld) getWorld()).getHandle().c(getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLightningBolt getHandle() {
        return (EntityLightningBolt) entity;
    }
}
