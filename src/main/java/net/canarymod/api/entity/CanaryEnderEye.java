package net.canarymod.api.entity;


import net.canarymod.api.entity.EnderEye;
import net.minecraft.server.EntityEnderEye;


public class CanaryEnderEye extends CanaryEntity implements EnderEye {

    public CanaryEnderEye(EntityEnderEye entity) {
        super(entity);
    }

    @Override
    public EntityEnderEye getHandle() {
        return (EntityEnderEye) entity;
    }

}
