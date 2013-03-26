package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.CanaryEntityLiving;


/**
 * Animal wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntityAnimal extends CanaryEntityLiving implements EntityAnimal {

    public CanaryEntityAnimal(net.minecraft.server.EntityAnimal entity) {
        super(entity);
    }

    // For them squids ...
    public CanaryEntityAnimal(net.minecraft.server.EntitySquid entity) {
        super(entity);
    }

    // For them Bats...
    public CanaryEntityAnimal(net.minecraft.server.EntityAmbientCreature entity) {
        super(entity);
    }
}
