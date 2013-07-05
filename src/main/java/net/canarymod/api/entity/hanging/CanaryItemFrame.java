package net.canarymod.api.entity.hanging;

import net.canarymod.api.entity.hanging.ItemFrame;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.EntityItemFrame;

/**
 * ItemFrame wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryItemFrame extends CanaryHangingEntity implements ItemFrame {

    /**
     * Constructs a new wrapper for EntityItemFrame
     * 
     * @param entity
     *            the EntityItemFrame to be wrapped
     */
    public CanaryItemFrame(EntityItemFrame entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItemInFrame() {
        return ((EntityItemFrame) entity).h().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItemInFrame(Item item) {
        ((EntityItemFrame) entity).a(((CanaryItem) item).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemRotation() {
        return ((EntityItemFrame) entity).i();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItemRotation(int rot) {
        ((EntityItemFrame) entity).c(rot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getItemDropChance() {
        return ((EntityItemFrame) entity).e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItemDropChance(float chance) {
        ((EntityItemFrame) entity).e = chance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityItemFrame getHandle() {
        return (EntityItemFrame) entity;
    }

}
