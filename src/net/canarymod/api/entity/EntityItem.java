package net.canarymod.api.entity;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;

public class EntityItem extends Entity implements IEntityItem {

    public EntityItem(OEntity entity) {
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
    public Item getItem() {
        return ((OEntityItem)entity).getItemStack().getItem();
    }

}
