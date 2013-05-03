package net.minecraft.server;


import java.util.Iterator;
import java.util.List;
import net.canarymod.api.entity.living.monster.CanaryEnderDragon;


public class EntityDragon extends EntityLiving implements IEntityMultiPart {

    public double a;
    public double b;
    public double c;
    public double[][] d = new double[64][3];
    public int e = -1;
    public EntityDragonPart[] f;
    public EntityDragonPart g;
    public EntityDragonPart h;
    public EntityDragonPart i;
    public EntityDragonPart j;
    public EntityDragonPart bK;
    public EntityDragonPart bL;
    public EntityDragonPart bM;
    public float bN = 0.0F;
    public float bO = 0.0F;
    public boolean bP = false;
    public boolean bQ = false;
    private Entity bT;
    public int bR = 0;
    public EntityEnderCrystal bS = null;

    public EntityDragon(World world) {
        super(world);
        this.f = new EntityDragonPart[] { this.g = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.h = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.i = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.j = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.bK = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.bL = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.bM = new EntityDragonPart(this, "wing", 4.0F, 4.0F)};
        this.b(this.aW());
        this.aH = "/mob/enderdragon/ender.png";
        this.a(16.0F, 8.0F);
        this.Z = true;
        this.ag = true;
        this.b = 100.0D;
        this.am = true;
        this.entity = new CanaryEnderDragon(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth == 0 ? 200 : maxHealth; // CanaryMod: custom Max Health
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Integer(this.aW()));
    }

    public double[] b(int i0, float f0) {
        if (this.aS <= 0) {
            f0 = 0.0F;
        }

        f0 = 1.0F - f0;
        int i1 = this.e - i0 * 1 & 63;
        int i2 = this.e - i0 * 1 - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.d[i1][0];
        double d1 = MathHelper.g(this.d[i2][0] - d0);

        adouble[0] = d0 + d1 * (double) f0;
        d0 = this.d[i1][1];
        d1 = this.d[i2][1] - d0;
        adouble[1] = d0 + d1 * (double) f0;
        adouble[2] = this.d[i1][2] + (this.d[i2][2] - this.d[i1][2]) * (double) f0;
        return adouble;
    }

    public void c() {
        float f0;
        float f1;

        if (!this.q.I) {
            this.ah.b(16, Integer.valueOf(this.aS));
        } else {
            f0 = MathHelper.b(this.bO * 3.1415927F * 2.0F);
            f1 = MathHelper.b(this.bN * 3.1415927F * 2.0F);
            if (f1 <= -0.3F && f0 >= -0.3F) {
                this.q.a(this.u, this.v, this.w, "mob.enderdragon.wings", 5.0F, 0.8F + this.ab.nextFloat() * 0.3F, false);
            }
        }

        this.bN = this.bO;
        float f2;

        if (this.aS <= 0) {
            f0 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.ab.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            this.q.a("largeexplode", this.u + (double) f0, this.v + 2.0D + (double) f1, this.w + (double) f2, 0.0D, 0.0D, 0.0D);
        } else {
            this.h();
            f0 = 0.2F / (MathHelper.a(this.x * this.x + this.z * this.z) * 10.0F + 1.0F);
            f0 *= (float) Math.pow(2.0D, this.y);
            if (this.bQ) {
                this.bO += f0 * 0.5F;
            } else {
                this.bO += f0;
            }

            this.A = MathHelper.g(this.A);
            if (this.e < 0) {
                for (int d05 = 0; d05 < this.d.length; ++d05) {
                    this.d[d05][0] = (double) this.A;
                    this.d[d05][1] = this.v;
                }
            }

            if (++this.e == this.d.length) {
                this.e = 0;
            }

            this.d[this.e][0] = (double) this.A;
            this.d[this.e][1] = this.v;
            double d0;
            double d1;
            double d2;
            double d3;
            float f3;

            if (this.q.I) {
                if (this.bu > 0) {
                    d0 = this.u + (this.bv - this.u) / (double) this.bu;
                    d1 = this.v + (this.bw - this.v) / (double) this.bu;
                    d2 = this.w + (this.bx - this.w) / (double) this.bu;
                    d3 = MathHelper.g(this.by - (double) this.A);
                    this.A = (float) ((double) this.A + d3 / (double) this.bu);
                    this.B = (float) ((double) this.B + (this.bz - (double) this.B) / (double) this.bu);
                    --this.bu;
                    this.b(d0, d1, d2);
                    this.b(this.A, this.B);
                }
            } else {
                d0 = this.a - this.u;
                d1 = this.b - this.v;
                d2 = this.c - this.w;
                d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (this.bT != null) {
                    this.a = this.bT.u;
                    this.c = this.bT.w;
                    double d4 = this.a - this.u;
                    double d5 = this.c - this.w;
                    double d6 = Math.sqrt(d4 * d4 + d5 * d5);
                    double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;

                    if (d7 > 10.0D) {
                        d7 = 10.0D;
                    }

                    this.b = this.bT.E.b + d7;
                } else {
                    this.a += this.ab.nextGaussian() * 2.0D;
                    this.c += this.ab.nextGaussian() * 2.0D;
                }

                if (this.bP || d3 < 100.0D || d3 > 22500.0D || this.G || this.H) {
                    this.i();
                }

                d1 /= (double) MathHelper.a(d0 * d0 + d2 * d2);
                f3 = 0.6F;
                if (d1 < (double) (-f3)) {
                    d1 = (double) (-f3);
                }

                if (d1 > (double) f3) {
                    d1 = (double) f3;
                }

                this.y += d1 * 0.10000000149011612D;
                this.A = MathHelper.g(this.A);
                double d8 = 180.0D - Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D;
                double d9 = MathHelper.g(d8 - (double) this.A);

                if (d9 > 50.0D) {
                    d9 = 50.0D;
                }

                if (d9 < -50.0D) {
                    d9 = -50.0D;
                }

                Vec3 vec3 = this.q.U().a(this.a - this.u, this.b - this.v, this.c - this.w).a();
                Vec3 vec31 = this.q.U().a((double) MathHelper.a(this.A * 3.1415927F / 180.0F), this.y, (double) (-MathHelper.b(this.A * 3.1415927F / 180.0F))).a();
                float f4 = (float) (vec31.b(vec3) + 0.5D) / 1.5F;

                if (f4 < 0.0F) {
                    f4 = 0.0F;
                }

                this.bF *= 0.8F;
                float f5 = MathHelper.a(this.x * this.x + this.z * this.z) * 1.0F + 1.0F;
                double d10 = Math.sqrt(this.x * this.x + this.z * this.z) * 1.0D + 1.0D;

                if (d10 > 40.0D) {
                    d10 = 40.0D;
                }

                this.bF = (float) ((double) this.bF + d9 * (0.699999988079071D / d10 / (double) f5));
                this.A += this.bF * 0.1F;
                float f6 = (float) (2.0D / (d10 + 1.0D));
                float f7 = 0.06F;

                this.a(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));
                if (this.bQ) {
                    this.d(this.x * 0.800000011920929D, this.y * 0.800000011920929D, this.z * 0.800000011920929D);
                } else {
                    this.d(this.x, this.y, this.z);
                }

                Vec3 vec32 = this.q.U().a(this.x, this.y, this.z).a();
                float f8 = (float) (vec32.b(vec31) + 1.0D) / 2.0F;

                f8 = 0.8F + 0.15F * f8;
                this.x *= (double) f8;
                this.z *= (double) f8;
                this.y *= 0.9100000262260437D;
            }

            this.ay = this.A;
            this.g.O = this.g.P = 3.0F;
            this.i.O = this.i.P = 2.0F;
            this.j.O = this.j.P = 2.0F;
            this.bK.O = this.bK.P = 2.0F;
            this.h.P = 3.0F;
            this.h.O = 5.0F;
            this.bL.P = 2.0F;
            this.bL.O = 4.0F;
            this.bM.P = 3.0F;
            this.bM.O = 4.0F;
            f1 = (float) (this.b(5, 1.0F)[1] - this.b(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
            f2 = MathHelper.b(f1);
            float f9 = -MathHelper.a(f1);
            float f10 = this.A * 3.1415927F / 180.0F;
            float f11 = MathHelper.a(f10);
            float f12 = MathHelper.b(f10);

            this.h.l_();
            this.h.b(this.u + (double) (f11 * 0.5F), this.v, this.w - (double) (f12 * 0.5F), 0.0F, 0.0F);
            this.bL.l_();
            this.bL.b(this.u + (double) (f12 * 4.5F), this.v + 2.0D, this.w + (double) (f11 * 4.5F), 0.0F, 0.0F);
            this.bM.l_();
            this.bM.b(this.u - (double) (f12 * 4.5F), this.v + 2.0D, this.w - (double) (f11 * 4.5F), 0.0F, 0.0F);
            if (!this.q.I && this.aW == 0) {
                this.a(this.q.b((Entity) this, this.bL.E.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.a(this.q.b((Entity) this, this.bM.E.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.b(this.q.b((Entity) this, this.g.E.b(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble = this.b(5, 1.0F);
            double[] adouble1 = this.b(0, 1.0F);

            f3 = MathHelper.a(this.A * 3.1415927F / 180.0F - this.bF * 0.01F);
            float f13 = MathHelper.b(this.A * 3.1415927F / 180.0F - this.bF * 0.01F);

            this.g.l_();
            this.g.b(this.u + (double) (f3 * 5.5F * f2), this.v + (adouble1[1] - adouble[1]) * 1.0D + (double) (f9 * 5.5F), this.w - (double) (f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int i1 = 0; i1 < 3; ++i1) {
                EntityDragonPart entitydragonpart = null;

                if (i1 == 0) {
                    entitydragonpart = this.i;
                }

                if (i1 == 1) {
                    entitydragonpart = this.j;
                }

                if (i1 == 2) {
                    entitydragonpart = this.bK;
                }

                double[] adouble2 = this.b(12 + i1 * 2, 1.0F);
                float f14 = this.A * 3.1415927F / 180.0F + this.b(adouble2[0] - adouble[0]) * 3.1415927F / 180.0F * 1.0F;
                float f15 = MathHelper.a(f14);
                float f16 = MathHelper.b(f14);
                float f17 = 1.5F;
                float f18 = (float) (i1 + 1) * 2.0F;

                entitydragonpart.l_();
                entitydragonpart.b(this.u - (double) ((f11 * f17 + f15 * f18) * f2), this.v + (adouble2[1] - adouble[1]) * 1.0D - (double) ((f18 + f17) * f9) + 1.5D, this.w + (double) ((f12 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
            }

            if (!this.q.I) {
                this.bQ = this.a(this.g.E) | this.a(this.h.E);
            }
        }
    }

    private void h() {
        if (this.bS != null) {
            if (this.bS.M) {
                if (!this.q.I) {
                    this.a(this.g, DamageSource.a((Explosion) null), 10);
                }

                this.bS = null;
            } else if (this.ac % 10 == 0 && this.aX() < this.aW()) {
                this.b(this.aX() + 1);
            }
        }

        if (this.ab.nextInt(10) == 0) {
            float f0 = 32.0F;
            List list = this.q.a(EntityEnderCrystal.class, this.E.b((double) f0, (double) f0, (double) f0));
            EntityEnderCrystal entityendercrystal = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityEnderCrystal entityendercrystal1 = (EntityEnderCrystal) iterator.next();
                double d1 = entityendercrystal1.e(this);

                if (d1 < d0) {
                    d0 = d1;
                    entityendercrystal = entityendercrystal1;
                }
            }

            this.bS = entityendercrystal;
        }
    }

    private void a(List list) {
        double d0 = (this.h.E.a + this.h.E.d) / 2.0D;
        double d1 = (this.h.E.c + this.h.E.f) / 2.0D;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLiving) {
                double d2 = entity.u - d0;
                double d3 = entity.w - d1;
                double d4 = d2 * d2 + d3 * d3;

                entity.g(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
            }
        }
    }

    private void b(List list) {
        for (int i0 = 0; i0 < list.size(); ++i0) {
            Entity entity = (Entity) list.get(i0);

            if (entity instanceof EntityLiving) {
                entity.a(DamageSource.a((EntityLiving) this), 10);
            }
        }
    }

    private void i() {
        this.bP = false;
        if (this.ab.nextInt(2) == 0 && !this.q.h.isEmpty()) {
            this.bT = (Entity) this.q.h.get(this.ab.nextInt(this.q.h.size()));
        } else {
            boolean flag0 = false;

            do {
                this.a = 0.0D;
                this.b = (double) (70.0F + this.ab.nextFloat() * 50.0F);
                this.c = 0.0D;
                this.a += (double) (this.ab.nextFloat() * 120.0F - 60.0F);
                this.c += (double) (this.ab.nextFloat() * 120.0F - 60.0F);
                double d0 = this.u - this.a;
                double d1 = this.v - this.b;
                double d2 = this.w - this.c;

                flag0 = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            } while (!flag0);

            this.bT = null;
        }
    }

    private float b(double d0) {
        return (float) MathHelper.g(d0);
    }

    private boolean a(AxisAlignedBB axisalignedbb) {
        int i0 = MathHelper.c(axisalignedbb.a);
        int i1 = MathHelper.c(axisalignedbb.b);
        int i2 = MathHelper.c(axisalignedbb.c);
        int i3 = MathHelper.c(axisalignedbb.d);
        int i4 = MathHelper.c(axisalignedbb.e);
        int i5 = MathHelper.c(axisalignedbb.f);
        boolean flag0 = false;
        boolean flag1 = false;

        for (int i6 = i0; i6 <= i3; ++i6) {
            for (int i7 = i1; i7 <= i4; ++i7) {
                for (int i8 = i2; i8 <= i5; ++i8) {
                    int i9 = this.q.a(i6, i7, i8);

                    if (i9 != 0) {
                        if (i9 != Block.at.cz && i9 != Block.bN.cz && i9 != Block.D.cz && this.q.N().b("mobGriefing")) {
                            flag1 = this.q.i(i6, i7, i8) || flag1;
                        } else {
                            flag0 = true;
                        }
                    }
                }
            }
        }

        if (flag1) {
            double d0 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * (double) this.ab.nextFloat();
            double d1 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * (double) this.ab.nextFloat();
            double d2 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * (double) this.ab.nextFloat();

            this.q.a("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        return flag0;
    }

    public boolean a(EntityDragonPart entitydragonpart, DamageSource damagesource, int i0) {
        if (entitydragonpart != this.g) {
            i0 = i0 / 4 + 1;
        }

        float f0 = this.A * 3.1415927F / 180.0F;
        float f1 = MathHelper.a(f0);
        float f2 = MathHelper.b(f0);

        this.a = this.u + (double) (f1 * 5.0F) + (double) ((this.ab.nextFloat() - 0.5F) * 2.0F);
        this.b = this.v + (double) (this.ab.nextFloat() * 3.0F) + 1.0D;
        this.c = this.w - (double) (f2 * 5.0F) + (double) ((this.ab.nextFloat() - 0.5F) * 2.0F);
        this.bT = null;
        if (damagesource.i() instanceof EntityPlayer || damagesource.c()) {
            this.e(damagesource, i0);
        }

        return true;
    }

    public boolean a(DamageSource damagesource, int i0) {
        return false;
    }

    protected boolean e(DamageSource damagesource, int i0) {
        return super.a(damagesource, i0);
    }

    protected void aS() {
        ++this.bR;
        if (this.bR >= 180 && this.bR <= 200) {
            float f0 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.ab.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.ab.nextFloat() - 0.5F) * 8.0F;

            this.q.a("hugeexplosion", this.u + (double) f0, this.v + 2.0D + (double) f1, this.w + (double) f2, 0.0D, 0.0D, 0.0D);
        }

        int i0;
        int i1;

        if (!this.q.I) {
            if (this.bR > 150 && this.bR % 5 == 0) {
                i0 = 1000;

                while (i0 > 0) {
                    i1 = EntityXPOrb.a(i0);
                    i0 -= i1;
                    this.q.d((Entity) (new EntityXPOrb(this.q, this.u, this.v, this.w, i1)));
                }
            }

            if (this.bR == 1) {
                this.q.d(1018, (int) this.u, (int) this.v, (int) this.w, 0);
            }
        }

        this.d(0.0D, 0.10000000149011612D, 0.0D);
        this.ay = this.A += 20.0F;
        if (this.bR == 200 && !this.q.I) {
            i0 = 2000;

            while (i0 > 0) {
                i1 = EntityXPOrb.a(i0);
                i0 -= i1;
                this.q.d((Entity) (new EntityXPOrb(this.q, this.u, this.v, this.w, i1)));
            }

            this.c(MathHelper.c(this.u), MathHelper.c(this.w));
            this.w();
        }
    }

    private void c(int i0, int i1) {
        byte b0 = 64;

        BlockEndPortal.a = true;
        byte b1 = 4;

        for (int i2 = b0 - 1; i2 <= b0 + 32; ++i2) {
            for (int i3 = i0 - b1; i3 <= i0 + b1; ++i3) {
                for (int i4 = i1 - b1; i4 <= i1 + b1; ++i4) {
                    double d0 = (double) (i3 - i0);
                    double d1 = (double) (i4 - i1);
                    double d2 = d0 * d0 + d1 * d1;

                    if (d2 <= ((double) b1 - 0.5D) * ((double) b1 - 0.5D)) {
                        if (i2 < b0) {
                            if (d2 <= ((double) (b1 - 1) - 0.5D) * ((double) (b1 - 1) - 0.5D)) {
                                this.q.c(i3, i2, i4, Block.D.cz);
                            }
                        } else if (i2 > b0) {
                            this.q.c(i3, i2, i4, 0);
                        } else if (d2 > ((double) (b1 - 1) - 0.5D) * ((double) (b1 - 1) - 0.5D)) {
                            this.q.c(i3, i2, i4, Block.D.cz);
                        } else {
                            this.q.c(i3, i2, i4, Block.bL.cz);
                        }
                    }
                }
            }
        }

        this.q.c(i0, b0 + 0, i1, Block.D.cz);
        this.q.c(i0, b0 + 1, i1, Block.D.cz);
        this.q.c(i0, b0 + 2, i1, Block.D.cz);
        this.q.c(i0 - 1, b0 + 2, i1, Block.au.cz);
        this.q.c(i0 + 1, b0 + 2, i1, Block.au.cz);
        this.q.c(i0, b0 + 2, i1 - 1, Block.au.cz);
        this.q.c(i0, b0 + 2, i1 + 1, Block.au.cz);
        this.q.c(i0, b0 + 3, i1, Block.D.cz);
        this.q.c(i0, b0 + 4, i1, Block.bO.cz);
        BlockEndPortal.a = false;
    }

    protected void bn() {}

    public Entity[] an() {
        return this.f;
    }

    public boolean K() {
        return false;
    }

    public World d() {
        return this.q;
    }

    protected String bb() {
        return "mob.enderdragon.growl";
    }

    protected String bc() {
        return "mob.enderdragon.hit";
    }

    protected float ba() {
        return 5.0F;
    }
}
