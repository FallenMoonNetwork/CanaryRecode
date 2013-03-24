package net.canarymod.api.entity.living.animal;

import net.minecraft.server.EntitySheep;

public class CanarySheep extends CanaryEntityAnimal implements Sheep {

    public CanarySheep(EntitySheep entity) {
        super(entity);
    }

    @Override
    public void eatGrass() {
        ((EntitySheep) entity).z();
    }

    @Override
    public int getColor() {
        return ((EntitySheep) entity).x();
    }

    @Override
    public void setColor(int color) {
        ((EntitySheep) entity).d_(color);
    }

    @Override
    public boolean isSheared() {
        return ((EntitySheep) entity).A_();
    }

    @Override
    public void setSheared(boolean sheared) {
        ((EntitySheep) entity).a(sheared);
    }

}
