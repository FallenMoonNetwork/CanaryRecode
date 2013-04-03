package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.vehicle.CanaryEmptyMinecart;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.VehicleEnterHook;
import net.canarymod.hook.entity.VehicleExitHook;

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
                // CanaryMod: VehicleEnter/VehicleExit
                CancelableHook hook = null;
                if (this.n == null) {
                    hook = new VehicleEnterHook((Vehicle) this.entity, (EntityLiving) entityplayer.getCanaryEntity());
                } else if (this.n == entityplayer) {
                    hook = new VehicleExitHook((Vehicle) this.entity, (EntityLiving) entityplayer.getCanaryEntity());
                }
                if (hook != null) {
                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        entityplayer.a((Entity) this);
                    }
                }
                //
            }

            return true;
        }
    }

    public int l() {
        return 0;
    }
}
