package net.canarymod.api.entity;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.inventory.fireworks.FireworkRocketItem;
import net.minecraft.server.EntityFireworkRocket;

/**
 * FireworkRocket wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFireworkRocket extends CanaryEntity implements FireworkRocket {

    /**
     * Constructs a new wrapper for EntityFireworkRocket
     * 
     * @param entity
     *            the EntityFireworkRocket to be wrapped
     */
    public CanaryFireworkRocket(EntityFireworkRocket entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.FIREWORKROCKET;
    }

    @Override
    public String getFqName() {
        return "FireworkRocket";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem() {
        return getHandle().getItemStack().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItem(Item item) {
        if (item.getType() == ItemType.FireworkRocket) {
            getHandle().setItemStack(((CanaryItem) item).getHandle());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FireworkRocketItem getRocketItem() {
        if (getItem() != null) {
            return new FireworkRocketItem(getItem());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRocketItem(FireworkRocketItem fireworkRocket) {
        this.setItem(fireworkRocket.getItem());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLifeTime() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLifeTime(int life) {
        getHandle().a = life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLifeTimeMax() {
        return getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLifeTimeMax(int life_time) {
        getHandle().b = life_time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFireworkRocket getHandle() {
        return (EntityFireworkRocket) entity;
    }
}
