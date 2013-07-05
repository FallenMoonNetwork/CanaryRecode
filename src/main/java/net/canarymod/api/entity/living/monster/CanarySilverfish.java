package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySilverfish;

/**
 * Silverfish wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySilverfish extends CanaryEntityMob implements Silverfish {

    /**
     * Constructs a new wrapper for EntitySilverfish
     * 
     * @param entity
     *            the EntitySilverfish to wrap
     */
    public CanarySilverfish(EntitySilverfish entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SILVERFISH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAllySummonCooldown() {
        return getHandle().bp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAllySummonCooldown(int cooldown) {
        getHandle().bp = cooldown;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySilverfish getHandle() {
        return (EntitySilverfish) entity;
    }
}
