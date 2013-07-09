package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.canarymod.api.entity.living.monster.CanaryEnderDragon;

public class EntityDragon extends EntityLiving implements IEntityMultiPart, IMob {

    public double h;
    public double i;
    public double j;
    public double[][] bn = new double[64][3];
    public int bo = -1;
    public EntityDragonPart[] bp;
    public EntityDragonPart bq;
    public EntityDragonPart br;
    public EntityDragonPart bs;
    public EntityDragonPart bt;
    public EntityDragonPart bu;
    public EntityDragonPart bv;
    public EntityDragonPart bw;
    public float bx;
    public float by;
    public boolean bz;
    public boolean bA;
    private Entity bD;
    public int bB;
    public EntityEnderCrystal bC;

    public EntityDragon(World world) {
        super(world);
        this.bp = new EntityDragonPart[]{ this.bq = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.br = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.bs = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.bt = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.bu = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
                this.bv = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.bw = new EntityDragonPart(this, "wing", 4.0F, 4.0F) };
        this.g(this.aS());
        this.a(16.0F, 8.0F);
        this.Z = true;
        this.ag = true;
        this.i = 100.0D;
        this.am = true;
        this.entity = new CanaryEnderDragon(this); // CanaryMod: Wrap Entity
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.a).a(200.0D);
    }

    protected void a() {
        super.a();
    }

    public double[] b(int i0, float f0) {
        if (this.aM() <= 0.0F) {
            f0 = 0.0F;
        }

        f0 = 1.0F - f0;
        int i1 = this.bo - i0 * 1 & 63;
        int i2 = this.bo - i0 * 1 - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.bn[i1][0];
        double d1 = MathHelper.g(this.bn[i2][0] - d0);

        adouble[0] = d0 + d1 * (double) f0;
        d0 = this.bn[i1][1];
        d1 = this.bn[i2][1] - d0;
        adouble[1] = d0 + d1 * (double) f0;
        adouble[2] = this.bn[i1][2] + (this.bn[i2][2] - this.bn[i1][2]) * (double) f0;
        return adouble;
    }

    public void c() {
        float f0;
        float f1;

        if (this.q.I) {
            f0 = MathHelper.b(this.by * 3.1415927F * 2.0F);
            f1 = MathHelper.b(this.bx * 3.1415927F * 2.0F);
            if (f1 <= -0.3F && f0 >= -0.3F) {
                this.q.a(this.u, this.v, this.w, "mob.enderdragon.wings", 5.0F, 0.8F + this.ab.nextFloat() * 0.3F, false);
            }
        }

        this.bx = this.by;
        float f2;

        if (this.aM() <= 0.0F) {
            f0 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.ab.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            this.q.a("largeexplode", this.u + (double) f0, this.v + 2.0D + (double) f1, this.w + (double) f2, 0.0D, 0.0D, 0.0D);
        } else {
            this.bJ();
            f0 = 0.2F / (MathHelper.a(this.x * this.x + this.z * this.z) * 10.0F + 1.0F);
            f0 *= (float) Math.pow(2.0D, this.y);
            if (this.bA) {
                this.by += f0 * 0.5F;
            } else {
                this.by += f0;
            }

            this.A = MathHelper.g(this.A);
            if (this.bo < 0) {
                for (int d05 = 0; d05 < this.bn.length; ++d05) {
                    this.bn[d05][0] = (double) this.A;
                    this.bn[d05][1] = this.v;
                }
            }

            if (++this.bo == this.bn.length) {
                this.bo = 0;
            }

            this.bn[this.bo][0] = (double) this.A;
            this.bn[this.bo][1] = this.v;
            double d0;
            double d1;
            double d2;
            double d3;
            float f3;

            if (this.q.I) {
                if (this.bh > 0) {
                    d0 = this.u + (this.bi - this.u) / (double) this.bh;
                    d1 = this.v + (this.bj - this.v) / (double) this.bh;
                    d2 = this.w + (this.bk - this.w) / (double) this.bh;
                    d3 = MathHelper.g(this.bl - (double) this.A);
                    this.A = (float) ((double) this.A + d3 / (double) this.bh);
                    this.B = (float) ((double) this.B + (this.bm - (double) this.B) / (double) this.bh);
                    --this.bh;
                    this.b(d0, d1, d2);
                    this.b(this.A, this.B);
                }
            } else {
                d0 = this.h - this.u;
                d1 = this.i - this.v;
                d2 = this.j - this.w;
                d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (this.bD != null) {
                    this.h = this.bD.u;
                    this.j = this.bD.w;
                    double d4 = this.h - this.u;
                    double d5 = this.j - this.w;
                    double d6 = Math.sqrt(d4 * d4 + d5 * d5);
                    double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;

                    if (d7 > 10.0D) {
                        d7 = 10.0D;
                    }

                    this.i = this.bD.E.b + d7;
                } else {
                    this.h += this.ab.nextGaussian() * 2.0D;
                    this.j += this.ab.nextGaussian() * 2.0D;
                }

                if (this.bz || d3 < 100.0D || d3 > 22500.0D || this.G || this.H) {
                    this.bK();
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

                Vec3 vec3 = this.q.V().a(this.h - this.u, this.i - this.v, this.j - this.w).a();
                Vec3 vec31 = this.q.V().a((double) MathHelper.a(this.A * 3.1415927F / 180.0F), this.y, (double) (-MathHelper.b(this.A * 3.1415927F / 180.0F))).a();
                float f4 = (float) (vec31.b(vec3) + 0.5D) / 1.5F;

                if (f4 < 0.0F) {
                    f4 = 0.0F;
                }

                this.bg *= 0.8F;
                float f5 = MathHelper.a(this.x * this.x + this.z * this.z) * 1.0F + 1.0F;
                double d10 = Math.sqrt(this.x * this.x + this.z * this.z) * 1.0D + 1.0D;

                if (d10 > 40.0D) {
                    d10 = 40.0D;
                }

                this.bg = (float) ((double) this.bg + d9 * (0.699999988079071D / d10 / (double) f5));
                this.A += this.bg * 0.1F;
                float f6 = (float) (2.0D / (d10 + 1.0D));
                float f7 = 0.06F;

                this.a(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));
                if (this.bA) {
                    this.d(this.x * 0.800000011920929D, this.y * 0.800000011920929D, this.z * 0.800000011920929D);
                } else {
                    this.d(this.x, this.y, this.z);
                }

                Vec3 vec32 = this.q.V().a(this.x, this.y, this.z).a();
                float f8 = (float) (vec32.b(vec31) + 1.0D) / 2.0F;

                f8 = 0.8F + 0.15F * f8;
                this.x *= (double) f8;
                this.z *= (double) f8;
                this.y *= 0.9100000262260437D;
            }

            this.aN = this.A;
            this.bq.O = this.bq.P = 3.0F;
            this.bs.O = this.bs.P = 2.0F;
            this.bt.O = this.bt.P = 2.0F;
            this.bu.O = this.bu.P = 2.0F;
            this.br.P = 3.0F;
            this.br.O = 5.0F;
            this.bv.P = 2.0F;
            this.bv.O = 4.0F;
            this.bw.P = 3.0F;
            this.bw.O = 4.0F;
            f1 = (float) (this.b(5, 1.0F)[1] - this.b(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
            f2 = MathHelper.b(f1);
            float f9 = -MathHelper.a(f1);
            float f10 = this.A * 3.1415927F / 180.0F;
            float f11 = MathHelper.a(f10);
            float f12 = MathHelper.b(f10);

            this.br.l_();
            this.br.b(this.u + (double) (f11 * 0.5F), this.v, this.w - (double) (f12 * 0.5F), 0.0F, 0.0F);
            this.bv.l_();
            this.bv.b(this.u + (double) (f12 * 4.5F), this.v + 2.0D, this.w + (double) (f11 * 4.5F), 0.0F, 0.0F);
            this.bw.l_();
            this.bw.b(this.u - (double) (f12 * 4.5F), this.v + 2.0D, this.w - (double) (f11 * 4.5F), 0.0F, 0.0F);
            if (!this.q.I && this.ay == 0) {
                this.a(this.q.b((Entity) this, this.bv.E.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.a(this.q.b((Entity) this, this.bw.E.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.b(this.q.b((Entity) this, this.bq.E.b(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble = this.b(5, 1.0F);
            double[] adouble1 = this.b(0, 1.0F);

            f3 = MathHelper.a(this.A * 3.1415927F / 180.0F - this.bg * 0.01F);
            float f13 = MathHelper.b(this.A * 3.1415927F / 180.0F - this.bg * 0.01F);

            this.bq.l_();
            this.bq.b(this.u + (double) (f3 * 5.5F * f2), this.v + (adouble1[1] - adouble[1]) * 1.0D + (double) (f9 * 5.5F), this.w - (double) (f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int i1 = 0; i1 < 3; ++i1) {
                EntityDragonPart entitydragonpart = null;

                if (i1 == 0) {
                    entitydragonpart = this.bs;
                }

                if (i1 == 1) {
                    entitydragonpart = this.bt;
                }

                if (i1 == 2) {
                    entitydragonpart = this.bu;
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
                this.bA = this.a(this.bq.E) | this.a(this.br.E);
            }
        }
    }

    private void bJ() {
        if (this.bC != null) {
            if (this.bC.M) {
                if (!this.q.I) {
                    this.a(this.bq, DamageSource.a((Explosion) null), 10.0F);
                }

                this.bC = null;
            } else if (this.ac % 10 == 0 && this.aM() < this.aS()) {
                this.g(this.aM() + 1.0F);
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

            this.bC = entityendercrystal;
        }
    }

    private void a(List list) {
        double d0 = (this.br.E.a + this.br.E.d) / 2.0D;
        double d1 = (this.br.E.c + this.br.E.f) / 2.0D;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLivingBase) {
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

            if (entity instanceof EntityLivingBase) {
                entity.a(DamageSource.a((EntityLivingBase) this), 10.0F);
            }
        }
    }

    private void bK() {
        this.bz = false;
        if (this.ab.nextInt(2) == 0 && !this.q.h.isEmpty()) {
            this.bD = (Entity) this.q.h.get(this.ab.nextInt(this.q.h.size()));
        } else {
            boolean flag0 = false;

            do {
                this.h = 0.0D;
                this.i = (double) (70.0F + this.ab.nextFloat() * 50.0F);
                this.j = 0.0D;
                this.h += (double) (this.ab.nextFloat() * 120.0F - 60.0F);
                this.j += (double) (this.ab.nextFloat() * 120.0F - 60.0F);
                double d0 = this.u - this.h;
                double d1 = this.v - this.i;
                double d2 = this.w - this.j;

                flag0 = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            }
            while (!flag0);

            this.bD = null;
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
                        if (i9 != Block.au.cF && i9 != Block.bO.cF && i9 != Block.E.cF && this.q.O().b("mobGriefing")) {
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

    public boolean a(EntityDragonPart entitydragonpart, DamageSource damagesource, float f0) {
        if (entitydragonpart != this.bq) {
            f0 = f0 / 4.0F + 1.0F;
        }

        float f1 = this.A * 3.1415927F / 180.0F;
        float f2 = MathHelper.a(f1);
        float f3 = MathHelper.b(f1);

        this.h = this.u + (double) (f2 * 5.0F) + (double) ((this.ab.nextFloat() - 0.5F) * 2.0F);
        this.i = this.v + (double) (this.ab.nextFloat() * 3.0F) + 1.0D;
        this.j = this.w - (double) (f3 * 5.0F) + (double) ((this.ab.nextFloat() - 0.5F) * 2.0F);
        this.bD = null;
        if (damagesource.i() instanceof EntityPlayer || damagesource.c()) {
            this.e(damagesource, f0);
        }

        return true;
    }

    public boolean a(DamageSource damagesource, float f0) {
        return false;
    }

    protected boolean e(DamageSource damagesource, float f0) {
        return super.a(damagesource, f0);
    }

    protected void aA() {
        ++this.bB;
        if (this.bB >= 180 && this.bB <= 200) {
            float f0 = (this.ab.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.ab.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.ab.nextFloat() - 0.5F) * 8.0F;

            this.q.a("hugeexplosion", this.u + (double) f0, this.v + 2.0D + (double) f1, this.w + (double) f2, 0.0D, 0.0D, 0.0D);
        }

        int i0;
        int i1;

        if (!this.q.I) {
            if (this.bB > 150 && this.bB % 5 == 0) {
                i0 = 1000;

                while (i0 > 0) {
                    i1 = EntityXPOrb.a(i0);
                    i0 -= i1;
                    this.q.d((Entity) (new EntityXPOrb(this.q, this.u, this.v, this.w, i1)));
                }
            }

            if (this.bB == 1) {
                this.q.d(1018, (int) this.u, (int) this.v, (int) this.w, 0);
            }
        }

        this.d(0.0D, 0.10000000149011612D, 0.0D);
        this.aN = this.A += 20.0F;
        if (this.bB == 200 && !this.q.I) {
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
                                this.q.c(i3, i2, i4, Block.E.cF);
                            }
                        } else if (i2 > b0) {
                            this.q.c(i3, i2, i4, 0);
                        } else if (d2 > ((double) (b1 - 1) - 0.5D) * ((double) (b1 - 1) - 0.5D)) {
                            this.q.c(i3, i2, i4, Block.E.cF);
                        } else {
                            this.q.c(i3, i2, i4, Block.bM.cF);
                        }
                    }
                }
            }
        }

        this.q.c(i0, b0 + 0, i1, Block.E.cF);
        this.q.c(i0, b0 + 1, i1, Block.E.cF);
        this.q.c(i0, b0 + 2, i1, Block.E.cF);
        this.q.c(i0 - 1, b0 + 2, i1, Block.av.cF);
        this.q.c(i0 + 1, b0 + 2, i1, Block.av.cF);
        this.q.c(i0, b0 + 2, i1 - 1, Block.av.cF);
        this.q.c(i0, b0 + 2, i1 + 1, Block.av.cF);
        this.q.c(i0, b0 + 3, i1, Block.E.cF);
        this.q.c(i0, b0 + 4, i1, Block.bP.cF);
        BlockEndPortal.a = false;
    }

    protected void bo() {}

    public Entity[] an() {
        return this.bp;
    }

    public boolean K() {
        return false;
    }

    public World b() {
        return this.q;
    }

    protected String r() {
        return "mob.enderdragon.growl";
    }

    protected String aN() {
        return "mob.enderdragon.hit";
    }

    protected float aZ() {
        return 5.0F;
    }
}
