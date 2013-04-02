package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryBlaze;


public class EntityBlaze extends EntityMob {

    private float d = 0.5F;
    private int e;
    private int f;

    public EntityBlaze(World world) {
        super(world);
        this.aH = "/mob/fire.png";
        this.ag = true;
        this.be = 10;
        this.maxHealth = 20; // CanaryMod: initialize
        this.entity = new CanaryBlaze(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth; // CanaryMod: custom Max
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected String bb() {
        return "mob.blaze.breathe";
    }

    protected String bc() {
        return "mob.blaze.hit";
    }

    protected String bd() {
        return "mob.blaze.death";
    }

    public float c(float f0) {
        return 1.0F;
    }

    public void c() {
        if (!this.q.I) {
            if (this.F()) {
                this.a(DamageSource.e, 1);
            }

            --this.e;
            if (this.e <= 0) {
                this.e = 100;
                this.d = 0.5F + (float) this.ab.nextGaussian() * 3.0F;
            }

            if (this.l() != null && this.l().v + (double) this.l().e() > this.v + (double) this.e() + (double) this.d) {
                this.y += (0.30000001192092896D - this.y) * 0.30000001192092896D;
            }
        }

        if (this.ab.nextInt(24) == 0) {
            this.q.a(this.u + 0.5D, this.v + 0.5D, this.w + 0.5D, "fire.fire", 1.0F + this.ab.nextFloat(), this.ab.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.F && this.y < 0.0D) {
            this.y *= 0.6D;
        }

        for (int i0 = 0; i0 < 2; ++i0) {
            this.q.a("largesmoke", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, 0.0D, 0.0D, 0.0D);
        }

        super.c();
    }

    protected void a(Entity entity, float f0) {
        if (this.ba <= 0 && f0 < 2.0F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.ba = 20;
            this.m(entity);
        } else if (f0 < 30.0F) {
            double d0 = entity.u - this.u;
            double d1 = entity.E.b + (double) (entity.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d2 = entity.w - this.w;

            if (this.ba == 0) {
                ++this.f;
                if (this.f == 1) {
                    this.ba = 60;
                    this.a(true);
                } else if (this.f <= 4) {
                    this.ba = 6;
                } else {
                    this.ba = 100;
                    this.f = 0;
                    this.a(false);
                }

                if (this.f > 1) {
                    float f1 = MathHelper.c(f0) * 0.5F;

                    this.q.a((EntityPlayer) null, 1009, (int) this.u, (int) this.v, (int) this.w, 0);

                    for (int i0 = 0; i0 < 1; ++i0) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.q, this, d0 + this.ab.nextGaussian() * (double) f1, d1, d2 + this.ab.nextGaussian() * (double) f1);

                        entitysmallfireball.v = this.v + (double) (this.P / 2.0F) + 0.5D;
                        this.q.d((Entity) entitysmallfireball);
                    }
                }
            }

            this.A = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.b = true;
        }
    }

    protected void a(float f0) {}

    protected int be() {
        return Item.bp.cp;
    }

    public boolean ae() {
        return this.m();
    }

    protected void a(boolean flag0, int i0) {
        if (flag0) {
            int i1 = this.ab.nextInt(2 + i0);

            for (int i2 = 0; i2 < i1; ++i2) {
                this.b(Item.bp.cp, 1);
            }
        }
    }

    public boolean m() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void a(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.ah.b(16, Byte.valueOf(b0));
    }

    protected boolean i_() {
        return true;
    }

    public int c(Entity entity) {
        return 6;
    }
}
