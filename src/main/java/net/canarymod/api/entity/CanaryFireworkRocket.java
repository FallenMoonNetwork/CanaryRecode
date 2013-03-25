package net.canarymod.api.entity;


import net.minecraft.server.EntityFireworkRocket;


/**
 * FireworkRocket wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFireworkRocket extends CanaryEntity implements FireworkRocket {

    /**
     * Constructs a new wrapper for EntityFireworkRocket
     * 
     * @param entity
     *            the EntityFireworkRocket to be wrapped
     */
    public CanaryFireworkRocket(EntityFireworkRocket entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFireworkRocket getHandle() {
        return (EntityFireworkRocket) entity;
    }

}
