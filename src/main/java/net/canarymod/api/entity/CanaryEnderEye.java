package net.canarymod.api.entity;


import net.minecraft.server.EntityEnderEye;


/**
 * EnderEye wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEnderEye extends CanaryEntity implements EnderEye {

    /**
     * Constructs a new wrapper for EntityEnderEye
     * 
     * @param entity
     *            the EntityEnderEye to be wrapped
     */
    public CanaryEnderEye(EntityEnderEye entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityEnderEye getHandle() {
        return (EntityEnderEye) entity;
    }

}
