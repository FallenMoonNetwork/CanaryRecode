package net.canarymod.api.entity;


import net.canarymod.api.inventory.CanaryItem;


/**
 * EntityItem wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryEntityItem extends CanaryEntity implements EntityItem {

    public CanaryEntityItem(net.minecraft.server.EntityItem entity) {
        super(entity);
    }

    @Override
    public void setAge(int age) {
        ((net.minecraft.server.EntityItem) entity).a = age;
    }

    @Override
    public int getAge() {
        return ((net.minecraft.server.EntityItem) entity).a;
    }

    @Override
    public CanaryItem getItem() {
        return ((net.minecraft.server.EntityItem) entity).d().getCanaryItem();
    }

    @Override
    public net.minecraft.server.EntityItem getHandle() {
        return (net.minecraft.server.EntityItem) entity;
    }

}
