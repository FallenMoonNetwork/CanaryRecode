package net.canarymod.api.entity.living.animal;


import net.canarymod.api.DyeColor;
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
    public void eatGrass() {
        getHandle().aK();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DyeColor getColor() {
        return DyeColor.values()[getHandle().m()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(DyeColor color) {
        getHandle().s(color.getDyeColorCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSheared() {
        return getHandle().n();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSheared(boolean sheared) {
        getHandle().i(sheared);
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
