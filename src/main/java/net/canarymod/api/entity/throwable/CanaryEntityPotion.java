package net.canarymod.api.entity.throwable;


import net.canarymod.api.potion.PotionType;


/**
 * EntityPotion wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEntityPotion extends CanaryEntityThrowable implements EntityPotion {

    /**
     * Constructs a new wrapper for EntityXPOrb
     * 
     * @param entity
     *            the EntityXPOrb to be wrapped
     */
    public CanaryEntityPotion(net.minecraft.server.EntityPotion entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getPotionTypeId() {
        return (short) getHandle().i();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PotionType getPotionType() {
        return PotionType.fromTypeID(getPotionTypeId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPotionTypeId(short typeId) {
        getHandle().a(typeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPotionType(PotionType type) {
        getHandle().a(type.getTypeId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public net.minecraft.server.EntityPotion getHandle() {
        return (net.minecraft.server.EntityPotion) entity;
    }

}
