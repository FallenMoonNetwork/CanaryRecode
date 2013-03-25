package net.canarymod.api.entity;


import net.minecraft.server.EntityFireworkRocket;


/**
 * FireworkRocket wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFireworkRocket extends CanaryEntity implements FireworkRocket {

    public CanaryFireworkRocket(EntityFireworkRocket entity) {
        super(entity);
    }

    @Override
    public EntityFireworkRocket getHandle() {
        return (EntityFireworkRocket) entity;
    }

}
