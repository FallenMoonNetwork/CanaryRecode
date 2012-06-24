package net.canarymod.api.entity;

import net.minecraft.server.OEntitySheep;

public class CanarySheep extends CanaryEntityAnimal implements Sheep {

    public CanarySheep(OEntitySheep entity) {
        super(entity);
    }

    @Override
    public void eatGrass() {
        ((OEntitySheep)entity).z();
    }

    @Override
    public int getColor() {
        return ((OEntitySheep)entity).x();
    }

    @Override
    public void setColor(int color) {
        ((OEntitySheep)entity).d_(color);
    }

    @Override
    public boolean isSheared() {
        return ((OEntitySheep)entity).A_();
    }

    @Override
    public void setSheared(boolean sheared) {
        ((OEntitySheep)entity).a(sheared);
    }

}
