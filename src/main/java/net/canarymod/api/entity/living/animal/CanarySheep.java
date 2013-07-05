package net.canarymod.api.entity.living.animal;

import net.canarymod.api.DyeColor;
import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntitySheep;

/**
 * Sheep wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanarySheep extends CanaryEntityAnimal implements Sheep {

    /**
     * Constructs a new wrapper for EntitySheep
     * 
     * @param entity
     *            the EntitySheep to wrap
     */
    public CanarySheep(EntitySheep entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.SHEEP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eatGrass() {
        getHandle().n();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DyeColor getColor() {
        return DyeColor.values()[getHandle().bP()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(DyeColor color) {
        getHandle().p(color.getDyeColorCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSheared() {
        return getHandle().bQ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSheared(boolean sheared) {
        getHandle().j(sheared);
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
    public EntitySheep getHandle() {
        return (EntitySheep) entity;
    }

}
