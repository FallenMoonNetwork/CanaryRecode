package net.minecraft.server;

import net.canarymod.api.entity.throwable.CanarySnowball;
import net.canarymod.hook.entity.ProjectileHitHook;

public class EntitySnowball extends EntityThrowable {

    public EntitySnowball(World world) {
        super(world);
        this.entity = new CanarySnowball(this); // CanaryMod: wrap entity
    }

    public EntitySnowball(World world, EntityLivingBase entitylivingbase) {
        super(world, entitylivingbase);
        this.entity = new CanarySnowball(this); // CanaryMod: warp entity
    }

    public EntitySnowball(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanarySnowball(this); // CanaryMod: warp entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        // CanaryMod: ProjectileHit
        ProjectileHitHook hook = (ProjectileHitHook) new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition == null || movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity()).call();
        if (!hook.isCanceled()) { //
            if (movingobjectposition.g != null) {
                byte b0 = 0;

                if (movingobjectposition.g instanceof EntityBlaze) {
                    b0 = 3;
                }

                movingobjectposition.g.a(DamageSource.a((Entity) this, this.h()), (float) b0);
            }

            for (int i0 = 0; i0 < 8; ++i0) {
                this.q.a("snowballpoof", this.u, this.v, this.w, 0.0D, 0.0D, 0.0D);
            }

            if (!this.q.I) {
                this.w();
            }
        }
    }
}
