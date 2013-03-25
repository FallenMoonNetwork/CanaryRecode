package net.minecraft.server;

import net.canarymod.api.entity.vehicle.CanaryEmptyMinecart;

public class EntityMinecartEmpty extends EntityMinecart {

    public EntityMinecartEmpty(World world) {
        super(world);
        this.entity = new CanaryEmptyMinecart(this); // CanaryMod: Wrap Entity
    }

    public EntityMinecartEmpty(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryEmptyMinecart(this); // CanaryMod: Wrap Entity
    }

    public boolean a_(EntityPlayer entityplayer) {
        if (this.n != null && this.n instanceof EntityPlayer && this.n != entityplayer) {
            return true;
        } else if (this.n != null && this.n != entityplayer) {
            return false;
        } else {
            if (!this.q.I) {
                entityplayer.a((Entity) this);
            }

            return true;
        }
    }

    public int l() {
        return 0;
    }
}
