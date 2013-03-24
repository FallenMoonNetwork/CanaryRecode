package net.canarymod.api.entity;

import net.canarymod.api.inventory.CanaryItem;

public class CanaryEntityItem extends CanaryEntity implements EntityItem {

    public CanaryEntityItem(net.minecraft.server.EntityItem entity) {
        super(entity);
    }

    @Override
    public void setAge(int age) {
        ((EntityItem) entity).c = age;
    }

    @Override
    public int getAge() {
        return ((EntityItem) entity).c;
    }

    @Override
    public CanaryItem getItem() {
        return ((EntityItem) entity).getItemStack().getCanaryItem();
    }

    @Override
    public net.minecraft.server.EntityItem getHandle() {
        return (net.minecraft.server.EntityItem) entity;
    }

}
