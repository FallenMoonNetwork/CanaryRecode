package net.canarymod.api.entity.living.animal;

import net.canarymod.api.DyeColor;
import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityWolf;

/**
 * Wolf wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryWolf extends CanaryTameable implements Wolf {

    /**
     * Constructs a new wrapper for EntityWolf
     * 
     * @param entity
     *            the EntityWolf to wrap
     */
    public CanaryWolf(EntityWolf entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.WOLF;
    }

    @Override
    public String getFqName() {
        return "Wolf";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGrowingAge() {
        return getHandle().b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGrowingAge(int age) {
        getHandle().a(age);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollarColor(DyeColor color) {
        getHandle().p(color.getColorCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DyeColor getCollarColor() {
        return DyeColor.values()[getHandle().cd()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAngry() {
        return ((EntityWolf) entity).bY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAngry(boolean angry) {
        ((EntityWolf) entity).m(angry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityWolf getHandle() {
        return (EntityWolf) entity;
    }
}
