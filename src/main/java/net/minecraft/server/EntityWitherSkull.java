package net.minecraft.server;

import net.canarymod.api.entity.CanaryWitherSkull;

public class EntityWitherSkull extends EntityFireball {

    public EntityWitherSkull(World world) {
        super(world);
        this.a(0.3125F, 0.3125F);
        //CanaryMod
        this.entity = new CanaryWitherSkull(this);
    }

    public EntityWitherSkull(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world, entityliving, d0, d1, d2);
        this.a(0.3125F, 0.3125F);
        //CanaryMod
        this.entity = new CanaryWitherSkull(this);
    }

    protected float c() {
        return this.d() ? 0.73F : super.c();
    }

    public boolean ae() {
        return false;
    }

    public float a(Explosion explosion, World world, int i0, int i1, int i2, Block block) {
        float f0 = super.a(explosion, world, i0, i1, i2, block);

        if (this.d() && block != Block.D && block != Block.bL && block != Block.bM) {
            f0 = Math.min(0.8F, f0);
        }

        return f0;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            if (movingobjectposition.g != null) {
                if (this.a != null) {
                    if (movingobjectposition.g.a(DamageSource.a(this.a), 8) && !movingobjectposition.g.R()) {
                        this.a.j(5);
                    }
                } else {
                    movingobjectposition.g.a(DamageSource.k, 5);
                }

                if (movingobjectposition.g instanceof EntityLiving) {
                    byte b0 = 0;

                    if (this.q.r > 1) {
                        if (this.q.r == 2) {
                            b0 = 10;
                        } else if (this.q.r == 3) {
                            b0 = 40;
                        }
                    }

                    if (b0 > 0) {
                        ((EntityLiving) movingobjectposition.g).d(new PotionEffect(Potion.v.H, 20 * b0, 1));
                    }
                }
            }

            this.q.a(this, this.u, this.v, this.w, 1.0F, false, this.q.N().b("mobGriefing"));
            this.w();
        }
    }

    public boolean K() {
        return false;
    }

    public boolean a(DamageSource damagesource, int i0) {
        return false;
    }

    protected void a() {
        this.ah.a(10, Byte.valueOf((byte) 0));
    }

    public boolean d() {
        return this.ah.a(10) == 1;
    }

    public void a(boolean flag0) {
        this.ah.b(10, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }
}
