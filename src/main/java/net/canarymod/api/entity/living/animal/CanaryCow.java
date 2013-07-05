package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.EntityType;
import net.minecraft.server.EntityCow;

/**
 * Cow wrapper implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryCow extends CanaryEntityAnimal implements Cow {

    /**
     * Constructs a new wrapper for EntityCow
     * 
     * @param entity
     *            the EntityCow to wrap
     */
    public CanaryCow(EntityCow entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.COW;
    }

    @Override
    public String getFqName() {
        return "Cow";
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
    public EntityCow getHandle() {
        return (EntityCow) entity;
    }
}
