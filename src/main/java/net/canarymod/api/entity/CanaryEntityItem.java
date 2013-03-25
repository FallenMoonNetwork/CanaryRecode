package net.canarymod.api.entity;


import net.canarymod.api.inventory.CanaryItem;


/**
 * EntityItem wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEntityItem extends CanaryEntity implements EntityItem {

    /**
     * Constructs a new wrapper for EntityItem
     * 
     * @param entity
     *            the EntityItem to be wrapped
     */
    public CanaryEntityItem(net.minecraft.server.EntityItem entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(int age) {
        ((net.minecraft.server.EntityItem) entity).a = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAge() {
        return ((net.minecraft.server.EntityItem) entity).a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanaryItem getItem() {
        return ((net.minecraft.server.EntityItem) entity).d().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public net.minecraft.server.EntityItem getHandle() {
        return (net.minecraft.server.EntityItem) entity;
    }

}
