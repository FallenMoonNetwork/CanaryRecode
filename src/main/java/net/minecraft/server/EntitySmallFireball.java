package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanarySmallFireball;
import net.canarymod.hook.entity.ProjectileHitHook;

public class EntitySmallFireball extends EntityFireball {

    public EntitySmallFireball(World world) {
        super(world);
        this.a(0.3125F, 0.3125F);
        this.entity = new CanarySmallFireball(this); // CanaryMod: wrap entity
    }

    public EntitySmallFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world, entityliving, d0, d1, d2);
        this.a(0.3125F, 0.3125F);
        this.entity = new CanarySmallFireball(this); // CanaryMod: wrap entity
    }

    public EntitySmallFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(world, d0, d1, d2, d3, d4, d5);
        this.a(0.3125F, 0.3125F);
        this.entity = new CanarySmallFireball(this); // CanaryMod: wrap entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            // CanaryMod: ProjectileHit
            ProjectileHitHook hook = new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition == null || movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity());
            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) { //
                if (movingobjectposition.g != null) {
                    if (!movingobjectposition.g.E() && movingobjectposition.g.a(DamageSource.a((EntityFireball) this, this.a), 5)) {
                        movingobjectposition.g.d(5);
                    }
                } else {
                    int i0 = movingobjectposition.b;
                    int i1 = movingobjectposition.c;
                    int i2 = movingobjectposition.d;

                    switch (movingobjectposition.e) {
                        case 0:
                            --i1;
                            break;

                        case 1:
                            ++i1;
                            break;

                        case 2:
                            --i2;
                            break;

                        case 3:
                            ++i2;
                            break;

                        case 4:
                            --i0;
                            break;

                        case 5:
                            ++i0;
                    }

                    if (this.q.c(i0, i1, i2)) {
                        this.q.c(i0, i1, i2, Block.av.cz);
                    }
                }

                this.w();
            }
        }
    }

    public boolean K() {
        return false;
    }

    public boolean a(DamageSource damagesource, int i0) {
        return false;
    }
}
