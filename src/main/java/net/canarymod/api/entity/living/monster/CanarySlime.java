package net.canarymod.api.entity.living.monster;


import net.minecraft.server.EntitySlime;


/**
 * Slime wrapper implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanarySlime extends CanaryEntityMob implements Slime {

    /**
     * Constructs a new wrapper for EntitySlime
     * 
     * @param entity
     *            the EntitySlime to wrap
     */
    public CanarySlime(EntitySlime entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Size getSize() {
        return Size.fromByte((byte) getHandle().p());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(Size size) {
        getHandle().c(size.getByte());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySlime getHandle() {
        return (EntitySlime) entity;
    }
}
