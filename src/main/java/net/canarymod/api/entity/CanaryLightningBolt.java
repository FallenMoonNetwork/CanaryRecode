package net.canarymod.api.entity;

import net.canarymod.api.world.CanaryWorld;
import net.minecraft.server.EntityLightningBolt;

public class CanaryLightningBolt extends CanaryEntity implements LightningBolt {

    public CanaryLightningBolt(EntityLightningBolt entity) {
        super(entity);
    }

    @Override
    public boolean spawn() { // LightningBolts are special :3
        return ((CanaryWorld) getWorld()).getHandle().c(getHandle());
    }

    @Override
    public EntityLightningBolt getHandle() {
        return (EntityLightningBolt) entity;
    }

}
