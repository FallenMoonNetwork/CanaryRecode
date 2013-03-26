package net.canarymod.api.entity.living.monster;


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
    public EntitySilverfish getHandle() {
        return (EntitySilverfish) entity;
    }

}
