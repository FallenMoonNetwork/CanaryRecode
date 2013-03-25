package net.canarymod.api.entity.vehicle;

import net.minecraft.server.EntityMinecartEmpty;

public class CanaryEmptyMinecart extends CanaryMinecart implements EmptyMinecart {

    public CanaryEmptyMinecart(EntityMinecartEmpty entity) {
        super(entity);
    }

}
