package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.EntityPigZombie;

/**
 * PigZombie wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPigZombie extends CanaryZombie implements PigZombie {

    /**
     * Constructs a new wrapper for EntityPigZombie
     * 
     * @param entity
     *            the EntityPigZombie to wrap
     */
    public CanaryPigZombie(EntityPigZombie entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.PIGZOMBIE;
    }

    @Override
    public String getFqName() {
        return "PigZombie";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAngerLevel() {
        return getHandle().bs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAngry() {
        return getHandle().bs > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAngerLevel(int level) {
        getHandle().bs = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void becomeAngryAt(Player player) {
        if (player == null) {
            return;
        }
        getHandle().c(((CanaryPlayer) player).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dontMakeLemonade(Player player) {
        this.becomeAngryAt(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityPigZombie getHandle() {
        return (EntityPigZombie) entity;
    }
}
