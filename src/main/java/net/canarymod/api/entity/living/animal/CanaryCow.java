package net.canarymod.api.entity.living.animal;

import net.minecraft.server.EntityCow;

/**
 * Cow Wrap - yummie
 * 
 * @author Chris
 */
public class CanaryCow extends CanaryEntityAnimal implements Cow {

    public CanaryCow(EntityCow entity) {
        super(entity);
    }

}
