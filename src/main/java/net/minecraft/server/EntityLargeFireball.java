package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryLargeFireball;
import net.canarymod.hook.entity.ProjectileHitHook;


public class EntityLargeFireball extends EntityFireball {

    public int e = 1;

    public EntityLargeFireball(World world) {
        super(world);
        this.entity = new CanaryLargeFireball(this); // CanaryMod: Wrap entity
    }

    public EntityLargeFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world, entityliving, d0, d1, d2);
        this.entity = new CanaryLargeFireball(this); // CanaryMod: Wrap entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            // CanaryMod: ProjectileHitHook
            ProjectileHitHook hook = new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition == null || movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity());

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) { //
                if (movingobjectposition.g != null) {
                    movingobjectposition.g.a(DamageSource.a((EntityFireball) this, this.a), 6);
                }

                this.q.a((Entity) this, this.u, this.v, this.w, (float) this.e, true, this.q.M().b("mobGriefing")); // CanaryMod: pass entity instead of null...
                this.w();
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("ExplosionPower", this.e);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("ExplosionPower")) {
            this.e = nbttagcompound.e("ExplosionPower");
        }
    }
}
