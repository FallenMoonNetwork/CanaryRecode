package net.canarymod.api.entity;

import net.minecraft.server.OEntityCow;

/**
 * Cow Wrap - yummie
 * @author Chris
 *
 */
public class CanaryCow extends CanaryEntityAnimal implements Cow {

    public CanaryCow(OEntityCow entity) {
        super(entity);
    }

}
