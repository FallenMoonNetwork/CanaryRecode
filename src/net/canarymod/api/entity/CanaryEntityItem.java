package net.canarymod.api.entity;

import net.canarymod.api.inventory.CanaryItem;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;

public class CanaryEntityItem extends CanaryEntity implements EntityItem {

    public CanaryEntityItem(OEntity entity) {
        super(entity);
    }

    @Override
    public void setAge(int age) {
        ((OEntityItem)entity).c = age;
    }

    @Override
    public int getAge() {
        return ((OEntityItem)entity).c;
    }

    @Override
    public CanaryItem getItem() {
        return ((OEntityItem)entity).getItemStack().getItem();
    }

}
