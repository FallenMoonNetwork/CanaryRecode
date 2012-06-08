package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OStatList;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityFishHook extends OEntity {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    private boolean h = false;
    public int a = 0;
    public OEntityPlayer b;
    private int i;
    private int j = 0;
    private int k = 0;
    public OEntity c = null;
    private int l;
    private double m;
    private double n;
    private double o;
    private double p;
    private double q;

    public OEntityFishHook(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.cd = true;
    }

    public OEntityFishHook(OWorld var1, OEntityPlayer var2) {
        super(var1);
        this.cd = true;
        this.b = var2;
        this.b.R = this;
        this.b(0.25F, 0.25F);
        this.c(var2.bm, var2.bn + 1.62D - var2.bF, var2.bo, var2.bs, var2.bt);
        this.bm -= (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.bn -= 0.10000000149011612D;
        this.bo -= (OMathHelper.a(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.c(this.bm, this.bn, this.bo);
        this.bF = 0.0F;
        float var3 = 0.4F;
        this.bp = (-OMathHelper.a(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * var3);
        this.br = (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * var3);
        this.bq = (-OMathHelper.a(this.bt / 180.0F * 3.1415927F) * var3);
        this.a(this.bp, this.bq, this.br, 1.5F, 1.0F);
    }

    @Override
    protected void b() {
    }

    public void a(double var1, double var3, double var5, float var7, float var8) {
        float var9 = OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5);
        var1 /= var9;
        var3 /= var9;
        var5 /= var9;
        var1 += this.bS.nextGaussian() * 0.007499999832361937D * var8;
        var3 += this.bS.nextGaussian() * 0.007499999832361937D * var8;
        var5 += this.bS.nextGaussian() * 0.007499999832361937D * var8;
        var1 *= var7;
        var3 *= var7;
        var5 *= var7;
        this.bp = var1;
        this.bq = var3;
        this.br = var5;
        float var10 = OMathHelper.a(var1 * var1 + var5 * var5);
        this.bu = this.bs = (float) (Math.atan2(var1, var5) * 180.0D / 3.1415927410125732D);
        this.bv = this.bt = (float) (Math.atan2(var3, var10) * 180.0D / 3.1415927410125732D);
        this.i = 0;
    }

    @Override
    public void F_() {
        super.F_();
        if (this.l > 0) {
            double var1 = this.bm + (this.m - this.bm) / this.l;
            double var3 = this.bn + (this.n - this.bn) / this.l;
            double var5 = this.bo + (this.o - this.bo) / this.l;

            double var7;
            for (var7 = this.p - this.bs; var7 < -180.0D; var7 += 360.0D) {
                ;
            }

            while (var7 >= 180.0D) {
                var7 -= 360.0D;
            }

            this.bs = (float) (this.bs + var7 / this.l);
            this.bt = (float) (this.bt + (this.q - this.bt) / this.l);
            --this.l;
            this.c(var1, var3, var5);
            this.c(this.bs, this.bt);
        } else {
            if (!this.bi.F) {
                OItemStack var9 = this.b.U();
                if (this.b.bE || !this.b.aE() || var9 == null || var9.a() != OItem.aQ || this.j(this.b) > 1024.0D) {
                    this.X();
                    this.b.R = null;
                    return;
                }

                if (this.c != null) {
                    if (!this.c.bE) {
                        this.bm = this.c.bm;
                        this.bn = this.c.bw.b + this.c.bH * 0.8D;
                        this.bo = this.c.bo;
                        return;
                    }

                    this.c = null;
                }
            }

            if (this.a > 0) {
                --this.a;
            }

            if (this.h) {
                int var35 = this.bi.a(this.d, this.e, this.f);
                if (var35 == this.g) {
                    ++this.i;
                    if (this.i == 1200) {
                        this.X();
                    }

                    return;
                }

                this.h = false;
                this.bp *= (this.bS.nextFloat() * 0.2F);
                this.bq *= (this.bS.nextFloat() * 0.2F);
                this.br *= (this.bS.nextFloat() * 0.2F);
                this.i = 0;
                this.j = 0;
            } else {
                ++this.j;
            }

            OVec3D var36 = OVec3D.b(this.bm, this.bn, this.bo);
            OVec3D var10 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
            OMovingObjectPosition var11 = this.bi.a(var36, var10);
            var36 = OVec3D.b(this.bm, this.bn, this.bo);
            var10 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
            if (var11 != null) {
                var10 = OVec3D.b(var11.f.a, var11.f.b, var11.f.c);
            }

            OEntity var12 = null;
            List var13 = this.bi.b(this, this.bw.a(this.bp, this.bq, this.br).b(1.0D, 1.0D, 1.0D));
            double var14 = 0.0D;

            double var21;
            for (int var16 = 0; var16 < var13.size(); ++var16) {
                OEntity var17 = (OEntity) var13.get(var16);
                if (var17.o_() && (var17 != this.b || this.j >= 5)) {
                    float var18 = 0.3F;
                    OAxisAlignedBB var19 = var17.bw.b(var18, var18, var18);
                    OMovingObjectPosition var20 = var19.a(var36, var10);
                    if (var20 != null) {
                        var21 = var36.b(var20.f);
                        if (var21 < var14 || var14 == 0.0D) {
                            var12 = var17;
                            var14 = var21;
                        }
                    }
                }
            }

            if (var12 != null) {
                var11 = new OMovingObjectPosition(var12);
            }

            if (var11 != null) {
                if (var11.g != null) {
                    if (var11.g.a(ODamageSource.a(this, this.b), 0)) {
                        this.c = var11.g;
                    }
                } else {
                    this.h = true;
                }
            }

            if (!this.h) {
                this.a(this.bp, this.bq, this.br);
                float var37 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
                this.bs = (float) (Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);

                for (this.bt = (float) (Math.atan2(this.bq, var37) * 180.0D / 3.1415927410125732D); this.bt - this.bv < -180.0F; this.bv -= 360.0F) {
                    ;
                }

                while (this.bt - this.bv >= 180.0F) {
                    this.bv += 360.0F;
                }

                while (this.bs - this.bu < -180.0F) {
                    this.bu -= 360.0F;
                }

                while (this.bs - this.bu >= 180.0F) {
                    this.bu += 360.0F;
                }

                this.bt = this.bv + (this.bt - this.bv) * 0.2F;
                this.bs = this.bu + (this.bs - this.bu) * 0.2F;
                float var38 = 0.92F;
                if (this.bx || this.by) {
                    var38 = 0.5F;
                }

                byte var39 = 5;
                double var23 = 0.0D;

                for (int var25 = 0; var25 < var39; ++var25) {
                    double var26 = this.bw.b + (this.bw.e - this.bw.b) * (var25 + 0) / var39 - 0.125D + 0.125D;
                    double var28 = this.bw.b + (this.bw.e - this.bw.b) * (var25 + 1) / var39 - 0.125D + 0.125D;
                    OAxisAlignedBB var30 = OAxisAlignedBB.b(this.bw.a, var26, this.bw.c, this.bw.d, var28, this.bw.f);
                    if (this.bi.b(var30, OMaterial.g)) {
                        var23 += 1.0D / var39;
                    }
                }

                if (var23 > 0.0D) {
                    if (this.k > 0) {
                        --this.k;
                    } else {
                        short var40 = 500;
                        if (this.bi.y(OMathHelper.b(this.bm), OMathHelper.b(this.bn) + 1, OMathHelper.b(this.bo))) {
                            var40 = 300;
                        }

                        if (this.bS.nextInt(var40) == 0) {
                            this.k = this.bS.nextInt(30) + 10;
                            this.bq -= 0.20000000298023224D;
                            this.bi.a(this, "random.splash", 0.25F, 1.0F + (this.bS.nextFloat() - this.bS.nextFloat()) * 0.4F);
                            float var31 = OMathHelper.b(this.bw.b);

                            float var34;
                            int var32;
                            float var33;
                            for (var32 = 0; var32 < 1.0F + this.bG * 20.0F; ++var32) {
                                var33 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                                var34 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                                this.bi.a("bubble", this.bm + var33, (var31 + 1.0F), this.bo + var34, this.bp, this.bq - (this.bS.nextFloat() * 0.2F), this.br);
                            }

                            for (var32 = 0; var32 < 1.0F + this.bG * 20.0F; ++var32) {
                                var33 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                                var34 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                                this.bi.a("splash", this.bm + var33, (var31 + 1.0F), this.bo + var34, this.bp, this.bq, this.br);
                            }
                        }
                    }
                }

                if (this.k > 0) {
                    this.bq -= (this.bS.nextFloat() * this.bS.nextFloat() * this.bS.nextFloat()) * 0.2D;
                }

                var21 = var23 * 2.0D - 1.0D;
                this.bq += 0.03999999910593033D * var21;
                if (var23 > 0.0D) {
                    var38 = (float) (var38 * 0.9D);
                    this.bq *= 0.8D;
                }

                this.bp *= var38;
                this.bq *= var38;
                this.br *= var38;
                this.c(this.bm, this.bn, this.bo);
            }
        }
    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("xTile", (short) this.d);
        var1.a("yTile", (short) this.e);
        var1.a("zTile", (short) this.f);
        var1.a("inTile", (byte) this.g);
        var1.a("shake", (byte) this.a);
        var1.a("inGround", (byte) (this.h ? 1 : 0));
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.d = var1.e("xTile");
        this.e = var1.e("yTile");
        this.f = var1.e("zTile");
        this.g = var1.d("inTile") & 255;
        this.a = var1.d("shake") & 255;
        this.h = var1.d("inGround") == 1;
    }

    public int k() {
        byte var1 = 0;
        if (this.c != null) {
            double var2 = this.b.bm - this.bm;
            double var4 = this.b.bn - this.bn;
            double var6 = this.b.bo - this.bo;
            double var8 = OMathHelper.a(var2 * var2 + var4 * var4 + var6 * var6);
            double var10 = 0.1D;
            this.c.bp += var2 * var10;
            this.c.bq += var4 * var10 + OMathHelper.a(var8) * 0.08D;
            this.c.br += var6 * var10;
            var1 = 3;
        } else if (this.k > 0) {
            OEntityItem var12 = new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.aT));
            double var13 = this.b.bm - this.bm;
            double var15 = this.b.bn - this.bn;
            double var17 = this.b.bo - this.bo;
            double var19 = OMathHelper.a(var13 * var13 + var15 * var15 + var17 * var17);
            double var21 = 0.1D;
            var12.bp = var13 * var21;
            var12.bq = var15 * var21 + OMathHelper.a(var19) * 0.08D;
            var12.br = var17 * var21;
            this.bi.b(var12);
            this.b.a(OStatList.B, 1);
            var1 = 1;
        }

        if (this.h) {
            var1 = 2;
        }

        this.X();
        this.b.R = null;
        return var1;
    }
}
