package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.EntityType;
import net.canarymod.api.inventory.CanaryAnimalInventory;
import net.canarymod.api.inventory.Inventory;
import net.minecraft.server.EntityHorse;

/**
 * Horse wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryHorse extends CanaryEntityAnimal implements Horse {

    public CanaryHorse(EntityHorse entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        switch (getType()) {
            case DONKEY:
                return EntityType.DONKEY;
            case HORSE:
                return EntityType.HORSE;
            case MULE:
                return EntityType.MULE;
            case SKELETON:
                return EntityType.SKELETONHORSE;
            case ZOMBIE:
                return EntityType.ZOMBIEHORSE;
            default:
                return EntityType.HORSE;
        }
    }

    @Override
    public String getFqName() {
        return "Horse";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEatingHay() {
        return getHandle().cc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBred() {
        return getHandle().ce();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsBred(boolean bred) {
        getHandle().l(bred);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChested() {
        return getHandle().ca();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsChested(boolean chested) {
        getHandle().m(chested);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasReproduced() {
        return getHandle().cf();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHasReproduced(boolean reproduced) {
        getHandle().n(reproduced);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HorseType getType() {
        return HorseType.values()[getRawType()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRawType() {
        return getHandle().bP();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(HorseType type) {
        this.setType(type.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(int type) {
        getHandle().p(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVariant() {
        return getHandle().bQ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVariant(int variant) {
        getHandle().q(variant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTemper() {
        return getHandle().cg();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTemper(int temper) {
        getHandle().s(temper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTame() {
        return getHandle().bS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTame(boolean tame) {
        getHandle().j(tame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return getHandle().bG != null ? new CanaryAnimalInventory(getHandle().bG, this) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityHorse getHandle() {
        return (EntityHorse) entity;
    }

}
