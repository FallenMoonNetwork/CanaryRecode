package net.minecraft.server;

import net.canarymod.api.entity.CanaryWitherSkull;

public class EntityWitherSkull extends EntityFireball {

    public EntityWitherSkull(World world) {
        super(world);
        this.a(0.3125F, 0.3125F);
        this.entity = new CanaryWitherSkull(this); // CanaryMod: wrap entity
    }

    public EntityWitherSkull(World world, EntityLivingBase entitylivingbase, double d0, double d1, double d2) {
        super(world, entitylivingbase, d0, d1, d2);
        this.a(0.3125F, 0.3125F);
        this.entity = new CanaryWitherSkull(this); // CanaryMod: wrap entity
    }

    public float c() {
        return /* this.d() ? 0.73F  : */super.c(); // CanaryMod: Motion Factor was made configurable
    }

    public boolean af() {
        return false;
    }

    public float a(Explosion explosion, World world, int i0, int i1, int i2, Block block) {
        float f0 = super.a(explosion, world, i0, i1, i2, block);

        if (this.d() && block != Block.E && block != Block.bM && block != Block.bN) {
            f0 = Math.min(0.8F, f0);
        }

        return f0;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            if (movingobjectposition.g != null) {
                if (this.a != null) {
                    if (movingobjectposition.g.a(DamageSource.a(this.a), 8.0F) && !movingobjectposition.g.T()) {
                        this.a.f(5.0F);
                    }
                }
                else {
                    movingobjectposition.g.a(DamageSource.k, 5.0F);
                }

                if (movingobjectposition.g instanceof EntityLivingBase) {
                    byte b0 = 0;

                    if (this.q.r > 1) {
                        if (this.q.r == 2) {
                            b0 = 10;
                        }
                        else if (this.q.r == 3) {
                            b0 = 40;
                        }
                    }

                    if (b0 > 0) {
                        ((EntityLivingBase)movingobjectposition.g).c(new PotionEffect(Potion.v.H, 20 * b0, 1));
                    }
                }
            }

            this.q.a(this, this.u, this.v, this.w, 1.0F, false, this.q.O().b("mobGriefing"));
            this.x();
        }
    }

    public boolean L() {
        return false;
    }

    public boolean a(DamageSource damagesource, float f0) {
        return false;
    }

    protected void a() {
        this.ah.a(10, Byte.valueOf((byte)0));
    }

    public boolean d() {
        return this.ah.a(10) == 1;
    }

    public void a(boolean flag0) {
        this.ah.b(10, Byte.valueOf((byte)(flag0 ? 1 : 0)));
    }
}
