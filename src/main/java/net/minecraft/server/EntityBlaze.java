package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryBlaze;


public class EntityBlaze extends EntityMob {

    private float bp = 0.5F;
    private int bq;
    private int br;

    public EntityBlaze(World world) {
        super(world);
        this.ag = true;
        this.b = 10;
        this.entity = new CanaryBlaze(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.e).a(6.0D);
    }

//MERGE: Deprecated? - Chris
    public int aW() {
        return maxHealth == 0 ? 20 : maxHealth; // CanaryMod: custom Max
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected String r() {
        return "mob.blaze.breathe";
    }

    protected String aK() {
        return "mob.blaze.hit";
    }

    protected String aL() {
        return "mob.blaze.death";
    }

    public float d(float f0) {
        return 1.0F;
    }

    public void c() {
        if (!this.q.I) {
            if (this.F()) {
                this.a(DamageSource.e, 1.0F);
            }

            --this.bq;
            if (this.bq <= 0) {
                this.bq = 100;
                this.bp = 0.5F + (float) this.ab.nextGaussian() * 3.0F;
            }

            if (this.bJ() != null && this.bJ().v + (double) this.bJ().f() > this.v + (double) this.f() + (double) this.bp) {
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
        if (this.aC <= 0 && f0 < 2.0F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.aC = 20;
            this.m(entity);
        } else if (f0 < 30.0F) {
            double d0 = entity.u - this.u;
            double d1 = entity.E.b + (double) (entity.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d2 = entity.w - this.w;

            if (this.aC == 0) {
                ++this.br;
                if (this.br == 1) {
                    this.aC = 60;
                    this.a(true);
                } else if (this.br <= 4) {
                    this.aC = 6;
                } else {
                    this.aC = 100;
                    this.br = 0;
                    this.a(false);
                }

                if (this.br > 1) {
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
            this.bn = true;
        }
    }

    protected void b(float f0) {}

    protected int s() {
        return Item.bq.cv;
    }

    public boolean ad() {
        return this.bP();
    }

    protected void b(boolean flag0, int i0) {
        if (flag0) {
            int i1 = this.ab.nextInt(2 + i0);

            for (int i2 = 0; i2 < i1; ++i2) {
                this.b(Item.bq.cv, 1);
            }
        }
    }

    public boolean bP() {
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
}
