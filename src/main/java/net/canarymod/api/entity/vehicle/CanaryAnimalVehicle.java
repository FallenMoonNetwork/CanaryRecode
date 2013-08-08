package net.canarymod.api.entity.vehicle;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.CanaryEntityAnimal;
import net.minecraft.server.EntityHorse;
import net.minecraft.server.EntityPig;

/**
 * Animal Vehicle implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryAnimalVehicle extends CanaryEntityAnimal implements Vehicle {

    public CanaryAnimalVehicle(EntityPig entity) {
        super(entity);
    }

    public CanaryAnimalVehicle(EntityHorse entity) {
        super(entity);
    }

    @Override
    public Entity getPassenger() {
        if (!isEmpty()) {
            return entity.n.getCanaryEntity();
        }
        return null;
    }

    @Override
    public boolean isBoat() {
        return false;
    }

    @Override
    public boolean isMinecart() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return entity.n == null;
    }
}