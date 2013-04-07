package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.throwable.CanaryChickenEgg;
import net.canarymod.hook.entity.ProjectileHitHook;


public class EntityEgg extends EntityThrowable {

    public EntityEgg(World world) {
        super(world);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    public EntityEgg(World world, EntityLiving entityliving) {
        super(world, entityliving);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    public EntityEgg(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        // CanaryMod: ProjectileHit
        ProjectileHitHook hook = new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition == null || movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity());

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) { //
            if (movingobjectposition.g != null) {
                movingobjectposition.g.a(DamageSource.a((Entity) this, this.h()), 0);
            }

            if (!this.q.I && this.ab.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.ab.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int i0 = 0; i0 < b0; ++i0) {
                    EntityChicken entitychicken = new EntityChicken(this.q);

                    entitychicken.a(-24000);
                    entitychicken.b(this.u, this.v, this.w, this.A, 0.0F);
                    this.q.d((Entity) entitychicken);
                }
            }

            for (int i1 = 0; i1 < 8; ++i1) {
                this.q.a("snowballpoof", this.u, this.v, this.w, 0.0D, 0.0D, 0.0D);
            }

            if (!this.q.I) {
                this.w();
            }
        }
    }
}
