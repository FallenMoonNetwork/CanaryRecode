package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockEndPortal;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityDragonBase;
import net.minecraft.server.OEntityDragonPart;
import net.minecraft.server.OEntityEnderCrystal;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityDragon extends OEntityDragonBase {

    public double a;
    public double b;
    public double c;
    public double[][] d = new double[64][3];
    public int e = -1;
    public OEntityDragonPart[] f;
    public OEntityDragonPart g;
    public OEntityDragonPart h;
    public OEntityDragonPart i;
    public OEntityDragonPart j;
    public OEntityDragonPart k;
    public OEntityDragonPart l;
    public OEntityDragonPart m;
    public float n = 0.0F;
    public float o = 0.0F;
    public boolean p = false;
    public boolean q = false;
    private OEntity u;
    public int r = 0;
    public OEntityEnderCrystal s = null;

    public OEntityDragon(OWorld var1) {
        super(var1);
        this.f = new OEntityDragonPart[] { this.g = new OEntityDragonPart(this, "head", 6.0F, 6.0F), this.h = new OEntityDragonPart(this, "body", 8.0F, 8.0F), this.i = new OEntityDragonPart(this, "tail", 4.0F, 4.0F), this.j = new OEntityDragonPart(this, "tail", 4.0F, 4.0F), this.k = new OEntityDragonPart(this, "tail", 4.0F, 4.0F), this.l = new OEntityDragonPart(this, "wing", 4.0F, 4.0F), this.m = new OEntityDragonPart(this, "wing", 4.0F, 4.0F) };
        this.t = 200;
        this.h(this.t);
        this.ae = "/mob/enderdragon/ender.png";
        this.b(16.0F, 8.0F);
        this.bQ = true;
        this.bX = true;
        this.b = 100.0D;
        this.cd = true;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, new Integer(this.t));
    }

    public double[] a(int var1, float var2) {
        if (this.ap <= 0) {
            var2 = 0.0F;
        }

        var2 = 1.0F - var2;
        int var3 = this.e - var1 * 1 & 63;
        int var4 = this.e - var1 * 1 - 1 & 63;
        double[] var5 = new double[3];
        double var6 = this.d[var3][0];

        double var8;
        for (var8 = this.d[var4][0] - var6; var8 < -180.0D; var8 += 360.0D) {
            ;
        }

        while (var8 >= 180.0D) {
            var8 -= 360.0D;
        }

        var5[0] = var6 + var8 * var2;
        var6 = this.d[var3][1];
        var8 = this.d[var4][1] - var6;
        var5[1] = var6 + var8 * var2;
        var5[2] = this.d[var3][2] + (this.d[var4][2] - this.d[var3][2]) * var2;
        return var5;
    }

    @Override
    public void e() {
        this.n = this.o;
        if (!this.bi.F) {
            this.bY.b(16, Integer.valueOf(this.ap));
        }

        float var1;
        float var3;
        float var45;
        if (this.ap <= 0) {
            var1 = (this.bS.nextFloat() - 0.5F) * 8.0F;
            var45 = (this.bS.nextFloat() - 0.5F) * 4.0F;
            var3 = (this.bS.nextFloat() - 0.5F) * 8.0F;
            this.bi.a("largeexplode", this.bm + var1, this.bn + 2.0D + var45, this.bo + var3, 0.0D, 0.0D, 0.0D);
        } else {
            this.A();
            var1 = 0.2F / (OMathHelper.a(this.bp * this.bp + this.br * this.br) * 10.0F + 1.0F);
            var1 *= (float) Math.pow(2.0D, this.bq);
            if (this.q) {
                this.o += var1 * 0.5F;
            } else {
                this.o += var1;
            }

            while (this.bs >= 180.0F) {
                this.bs -= 360.0F;
            }

            while (this.bs < -180.0F) {
                this.bs += 360.0F;
            }

            if (this.e < 0) {
                for (int var2 = 0; var2 < this.d.length; ++var2) {
                    this.d[var2][0] = this.bs;
                    this.d[var2][1] = this.bn;
                }
            }

            if (++this.e == this.d.length) {
                this.e = 0;
            }

            this.d[this.e][0] = this.bs;
            this.d[this.e][1] = this.bn;
            double var4;
            double var6;
            double var8;
            double var10;
            float var20;
            if (this.bi.F) {
                if (this.aN > 0) {
                    var4 = this.bm + (this.aO - this.bm) / this.aN;
                    var6 = this.bn + (this.aP - this.bn) / this.aN;
                    var8 = this.bo + (this.aQ - this.bo) / this.aN;

                    for (var10 = this.aR - this.bs; var10 < -180.0D; var10 += 360.0D) {
                        ;
                    }

                    while (var10 >= 180.0D) {
                        var10 -= 360.0D;
                    }

                    this.bs = (float) (this.bs + var10 / this.aN);
                    this.bt = (float) (this.bt + (this.aS - this.bt) / this.aN);
                    --this.aN;
                    this.c(var4, var6, var8);
                    this.c(this.bs, this.bt);
                }
            } else {
                var4 = this.a - this.bm;
                var6 = this.b - this.bn;
                var8 = this.c - this.bo;
                var10 = var4 * var4 + var6 * var6 + var8 * var8;
                if (this.u != null) {
                    this.a = this.u.bm;
                    this.c = this.u.bo;
                    double var12 = this.a - this.bm;
                    double var14 = this.c - this.bo;
                    double var16 = Math.sqrt(var12 * var12 + var14 * var14);
                    double var18 = 0.4000000059604645D + var16 / 80.0D - 1.0D;
                    if (var18 > 10.0D) {
                        var18 = 10.0D;
                    }

                    this.b = this.u.bw.b + var18;
                } else {
                    this.a += this.bS.nextGaussian() * 2.0D;
                    this.c += this.bS.nextGaussian() * 2.0D;
                }

                if (this.p || var10 < 100.0D || var10 > 22500.0D || this.by || this.bz) {
                    this.E();
                }

                var6 /= OMathHelper.a(var4 * var4 + var8 * var8);
                var20 = 0.6F;
                if (var6 < (-var20)) {
                    var6 = (-var20);
                }

                if (var6 > var20) {
                    var6 = var20;
                }

                for (this.bq += var6 * 0.10000000149011612D; this.bs < -180.0F; this.bs += 360.0F) {
                    ;
                }

                while (this.bs >= 180.0F) {
                    this.bs -= 360.0F;
                }

                double var21 = 180.0D - Math.atan2(var4, var8) * 180.0D / 3.1415927410125732D;

                double var23;
                for (var23 = var21 - this.bs; var23 < -180.0D; var23 += 360.0D) {
                    ;
                }

                while (var23 >= 180.0D) {
                    var23 -= 360.0D;
                }

                if (var23 > 50.0D) {
                    var23 = 50.0D;
                }

                if (var23 < -50.0D) {
                    var23 = -50.0D;
                }

                OVec3D var25 = OVec3D.b(this.a - this.bm, this.b - this.bn, this.c - this.bo).b();
                OVec3D var26 = OVec3D.b(OMathHelper.a(this.bs * 3.1415927F / 180.0F), this.bq, (-OMathHelper.b(this.bs * 3.1415927F / 180.0F))).b();
                float var27 = (float) (var26.a(var25) + 0.5D) / 1.5F;
                if (var27 < 0.0F) {
                    var27 = 0.0F;
                }

                this.aY *= 0.8F;
                float var28 = OMathHelper.a(this.bp * this.bp + this.br * this.br) * 1.0F + 1.0F;
                double var29 = Math.sqrt(this.bp * this.bp + this.br * this.br) * 1.0D + 1.0D;
                if (var29 > 40.0D) {
                    var29 = 40.0D;
                }

                this.aY = (float) (this.aY + var23 * (0.699999988079071D / var29 / var28));
                this.bs += this.aY * 0.1F;
                float var31 = (float) (2.0D / (var29 + 1.0D));
                float var32 = 0.06F;
                this.a(0.0F, -1.0F, var32 * (var27 * var31 + (1.0F - var31)));
                if (this.q) {
                    this.a(this.bp * 0.800000011920929D, this.bq * 0.800000011920929D, this.br * 0.800000011920929D);
                } else {
                    this.a(this.bp, this.bq, this.br);
                }

                OVec3D var33 = OVec3D.b(this.bp, this.bq, this.br).b();
                float var34 = (float) (var33.a(var26) + 1.0D) / 2.0F;
                var34 = 0.8F + 0.15F * var34;
                this.bp *= var34;
                this.br *= var34;
                this.bq *= 0.9100000262260437D;
            }

            this.V = this.bs;
            this.g.bG = this.g.bH = 3.0F;
            this.i.bG = this.i.bH = 2.0F;
            this.j.bG = this.j.bH = 2.0F;
            this.k.bG = this.k.bH = 2.0F;
            this.h.bH = 3.0F;
            this.h.bG = 5.0F;
            this.l.bH = 2.0F;
            this.l.bG = 4.0F;
            this.m.bH = 3.0F;
            this.m.bG = 4.0F;
            var45 = (float) (this.a(5, 1.0F)[1] - this.a(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
            var3 = OMathHelper.b(var45);
            float var35 = -OMathHelper.a(var45);
            float var36 = this.bs * 3.1415927F / 180.0F;
            float var37 = OMathHelper.a(var36);
            float var38 = OMathHelper.b(var36);
            this.h.F_();
            this.h.c(this.bm + (var37 * 0.5F), this.bn, this.bo - (var38 * 0.5F), 0.0F, 0.0F);
            this.l.F_();
            this.l.c(this.bm + (var38 * 4.5F), this.bn + 2.0D, this.bo + (var37 * 4.5F), 0.0F, 0.0F);
            this.m.F_();
            this.m.c(this.bm - (var38 * 4.5F), this.bn + 2.0D, this.bo - (var37 * 4.5F), 0.0F, 0.0F);
            if (!this.bi.F) {
                this.C();
            }

            if (!this.bi.F && this.at == 0) {
                this.a(this.bi.b(this, this.l.bw.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.a(this.bi.b(this, this.m.bw.b(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
                this.b(this.bi.b(this, this.g.bw.b(1.0D, 1.0D, 1.0D)));
            }

            double[] var39 = this.a(5, 1.0F);
            double[] var40 = this.a(0, 1.0F);
            var20 = OMathHelper.a(this.bs * 3.1415927F / 180.0F - this.aY * 0.01F);
            float var41 = OMathHelper.b(this.bs * 3.1415927F / 180.0F - this.aY * 0.01F);
            this.g.F_();
            this.g.c(this.bm + (var20 * 5.5F * var3), this.bn + (var40[1] - var39[1]) * 1.0D + (var35 * 5.5F), this.bo - (var41 * 5.5F * var3), 0.0F, 0.0F);

            for (int var50 = 0; var50 < 3; ++var50) {
                OEntityDragonPart var46 = null;
                if (var50 == 0) {
                    var46 = this.i;
                }

                if (var50 == 1) {
                    var46 = this.j;
                }

                if (var50 == 2) {
                    var46 = this.k;
                }

                double[] var49 = this.a(12 + var50 * 2, 1.0F);
                float var42 = this.bs * 3.1415927F / 180.0F + this.a(var49[0] - var39[0]) * 3.1415927F / 180.0F * 1.0F;
                float var43 = OMathHelper.a(var42);
                float var44 = OMathHelper.b(var42);
                float var47 = 1.5F;
                float var48 = (var50 + 1) * 2.0F;
                var46.F_();
                var46.c(this.bm - ((var37 * var47 + var43 * var48) * var3), this.bn + (var49[1] - var39[1]) * 1.0D - ((var48 + var47) * var35) + 1.5D, this.bo + ((var38 * var47 + var44 * var48) * var3), 0.0F, 0.0F);
            }

            if (!this.bi.F) {
                this.q = this.a(this.g.bw) | this.a(this.h.bw);
            }

        }
    }

    private void A() {
        if (this.s != null) {
            if (this.s.bE) {
                if (!this.bi.F) {
                    this.a(this.g, ODamageSource.l, 10);
                }

                this.s = null;
            } else if (this.bT % 10 == 0 && this.ap < this.t) {
                ++this.ap;
            }
        }

        if (this.bS.nextInt(10) == 0) {
            float var1 = 32.0F;
            List var2 = this.bi.a(OEntityEnderCrystal.class, this.bw.b(var1, var1, var1));
            OEntityEnderCrystal var3 = null;
            double var4 = Double.MAX_VALUE;
            Iterator var6 = var2.iterator();

            while (var6.hasNext()) {
                OEntity var7 = (OEntity) var6.next();
                double var8 = var7.j(this);
                if (var8 < var4) {
                    var4 = var8;
                    var3 = (OEntityEnderCrystal) var7;
                }
            }

            this.s = var3;
        }

    }

    private void C() {
    }

    private void a(List var1) {
        double var2 = (this.h.bw.a + this.h.bw.d) / 2.0D;
        double var4 = (this.h.bw.c + this.h.bw.f) / 2.0D;
        Iterator var6 = var1.iterator();

        while (var6.hasNext()) {
            OEntity var7 = (OEntity) var6.next();
            if (var7 instanceof OEntityLiving) {
                double var8 = var7.bm - var2;
                double var10 = var7.bo - var4;
                double var12 = var8 * var8 + var10 * var10;
                var7.b_(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
            }
        }

    }

    private void b(List var1) {
        for (int var2 = 0; var2 < var1.size(); ++var2) {
            OEntity var3 = (OEntity) var1.get(var2);
            if (var3 instanceof OEntityLiving) {
                var3.a(ODamageSource.a(this), 10);
            }
        }

    }

    private void E() {
        this.p = false;
        if (this.bS.nextInt(2) == 0 && this.bi.d.size() > 0) {
            this.u = (OEntity) this.bi.d.get(this.bS.nextInt(this.bi.d.size()));
        } else {
            boolean var1 = false;

            do {
                this.a = 0.0D;
                this.b = (70.0F + this.bS.nextFloat() * 50.0F);
                this.c = 0.0D;
                this.a += (this.bS.nextFloat() * 120.0F - 60.0F);
                this.c += (this.bS.nextFloat() * 120.0F - 60.0F);
                double var2 = this.bm - this.a;
                double var4 = this.bn - this.b;
                double var6 = this.bo - this.c;
                var1 = var2 * var2 + var4 * var4 + var6 * var6 > 100.0D;
            } while (!var1);

            this.u = null;
        }

    }

    private float a(double var1) {
        while (var1 >= 180.0D) {
            var1 -= 360.0D;
        }

        while (var1 < -180.0D) {
            var1 += 360.0D;
        }

        return (float) var1;
    }

    private boolean a(OAxisAlignedBB var1) {
        int var2 = OMathHelper.b(var1.a);
        int var3 = OMathHelper.b(var1.b);
        int var4 = OMathHelper.b(var1.c);
        int var5 = OMathHelper.b(var1.d);
        int var6 = OMathHelper.b(var1.e);
        int var7 = OMathHelper.b(var1.f);
        boolean var8 = false;
        boolean var9 = false;

        for (int var10 = var2; var10 <= var5; ++var10) {
            for (int var11 = var3; var11 <= var6; ++var11) {
                for (int var12 = var4; var12 <= var7; ++var12) {
                    int var13 = this.bi.a(var10, var11, var12);
                    if (var13 != 0) {
                        if (var13 != OBlock.ap.bO && var13 != OBlock.bJ.bO && var13 != OBlock.z.bO) {
                            var9 = true;
                            this.bi.e(var10, var11, var12, 0);
                        } else {
                            var8 = true;
                        }
                    }
                }
            }
        }

        if (var9) {
            double var14 = var1.a + (var1.d - var1.a) * this.bS.nextFloat();
            double var16 = var1.b + (var1.e - var1.b) * this.bS.nextFloat();
            double var18 = var1.c + (var1.f - var1.c) * this.bS.nextFloat();
            this.bi.a("largeexplode", var14, var16, var18, 0.0D, 0.0D, 0.0D);
        }

        return var8;
    }

    @Override
    public boolean a(OEntityDragonPart var1, ODamageSource var2, int var3) {
        if (var1 != this.g) {
            var3 = var3 / 4 + 1;
        }

        float var4 = this.bs * 3.1415927F / 180.0F;
        float var5 = OMathHelper.a(var4);
        float var6 = OMathHelper.b(var4);
        this.a = this.bm + (var5 * 5.0F) + ((this.bS.nextFloat() - 0.5F) * 2.0F);
        this.b = this.bn + (this.bS.nextFloat() * 3.0F) + 1.0D;
        this.c = this.bo - (var6 * 5.0F) + ((this.bS.nextFloat() - 0.5F) * 2.0F);
        this.u = null;
        if (var2.a() instanceof OEntityPlayer || var2 == ODamageSource.l) {
            this.e(var2, var3);
        }

        return true;
    }

    @Override
    protected void aB() {
        ++this.r;
        if (this.r >= 180 && this.r <= 200) {
            float var1 = (this.bS.nextFloat() - 0.5F) * 8.0F;
            float var2 = (this.bS.nextFloat() - 0.5F) * 4.0F;
            float var3 = (this.bS.nextFloat() - 0.5F) * 8.0F;
            this.bi.a("hugeexplosion", this.bm + var1, this.bn + 2.0D + var2, this.bo + var3, 0.0D, 0.0D, 0.0D);
        }

        int var4;
        int var5;
        if (!this.bi.F && this.r > 150 && this.r % 5 == 0) {
            var4 = 1000;

            while (var4 > 0) {
                var5 = OEntityXPOrb.b(var4);
                var4 -= var5;
                this.bi.b((new OEntityXPOrb(this.bi, this.bm, this.bn, this.bo, var5)));
            }
        }

        this.a(0.0D, 0.10000000149011612D, 0.0D);
        this.V = this.bs += 20.0F;
        if (this.r == 200) {
            var4 = 10000;

            while (var4 > 0) {
                var5 = OEntityXPOrb.b(var4);
                var4 -= var5;
                this.bi.b((new OEntityXPOrb(this.bi, this.bm, this.bn, this.bo, var5)));
            }

            this.a(OMathHelper.b(this.bm), OMathHelper.b(this.bo));
            this.aH();
            this.X();
        }

    }

    private void a(int var1, int var2) {
        byte var3 = 64;
        OBlockEndPortal.a = true;
        byte var4 = 4;

        for (int var5 = var3 - 1; var5 <= var3 + 32; ++var5) {
            for (int var6 = var1 - var4; var6 <= var1 + var4; ++var6) {
                for (int var7 = var2 - var4; var7 <= var2 + var4; ++var7) {
                    double var8 = (var6 - var1);
                    double var10 = (var7 - var2);
                    double var12 = OMathHelper.a(var8 * var8 + var10 * var10);
                    if (var12 <= var4 - 0.5D) {
                        if (var5 < var3) {
                            if (var12 <= (var4 - 1) - 0.5D) {
                                this.bi.e(var6, var5, var7, OBlock.z.bO);
                            }
                        } else if (var5 > var3) {
                            this.bi.e(var6, var5, var7, 0);
                        } else if (var12 > (var4 - 1) - 0.5D) {
                            this.bi.e(var6, var5, var7, OBlock.z.bO);
                        } else {
                            this.bi.e(var6, var5, var7, OBlock.bH.bO);
                        }
                    }
                }
            }
        }

        this.bi.e(var1, var3 + 0, var2, OBlock.z.bO);
        this.bi.e(var1, var3 + 1, var2, OBlock.z.bO);
        this.bi.e(var1, var3 + 2, var2, OBlock.z.bO);
        this.bi.e(var1 - 1, var3 + 2, var2, OBlock.aq.bO);
        this.bi.e(var1 + 1, var3 + 2, var2, OBlock.aq.bO);
        this.bi.e(var1, var3 + 2, var2 - 1, OBlock.aq.bO);
        this.bi.e(var1, var3 + 2, var2 + 1, OBlock.aq.bO);
        this.bi.e(var1, var3 + 3, var2, OBlock.z.bO);
        this.bi.e(var1, var3 + 4, var2, OBlock.bK.bO);
        OBlockEndPortal.a = false;
    }

    @Override
    protected void aG() {
    }

    @Override
    public OEntity[] bb() {
        return this.f;
    }

    @Override
    public boolean o_() {
        return false;
    }
}
