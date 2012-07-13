package net.minecraft.server;

import java.util.List;

import net.canarymod.Logman;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityArrow extends OEntity {

    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    public boolean a = false;
    public int b = 0;
    public OEntity c;
    private int k;
    private int l = 0;
    private double m = 2.0D;
    private int n;
    public boolean d = false;

    public OEntityArrow(OWorld var1) {
        super(var1);
        this.b(0.5F, 0.5F);
    }

    public OEntityArrow(OWorld var1, double var2, double var4, double var6) {
        super(var1);
        this.b(0.5F, 0.5F);
        this.c(var2, var4, var6);
        this.bF = 0.0F;
    }

    public OEntityArrow(OWorld var1, OEntityLiving var2, OEntityLiving var3, float var4, float var5) {
        super(var1);
        this.c = var2;
        this.a = var2 instanceof OEntityPlayer;
        this.bn = var2.bn + var2.B() - 0.10000000149011612D;
        double var6 = var3.bm - var2.bm;
        double var8 = var3.bn + var3.B() - 0.699999988079071D - this.bn;
        double var10 = var3.bo - var2.bo;
        double var12 = OMathHelper.a(var6 * var6 + var10 * var10);
        if (var12 >= 1.0E-7D) {
            float var14 = (float) (Math.atan2(var10, var6) * 180.0D / 3.1415927410125732D) - 90.0F;
            float var15 = (float) (-(Math.atan2(var8, var12) * 180.0D / 3.1415927410125732D));
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.c(var2.bm + var16, this.bn, var2.bo + var18, var14, var15);
            this.bF = 0.0F;
            float var20 = (float) var12 * 0.2F;
            this.a(var6, var8 + var20, var10, var4, var5);
        }
    }

    public OEntityArrow(OWorld var1, OEntityLiving var2, float var3) {
        super(var1);
        this.c = var2;
        this.a = var2 instanceof OEntityPlayer;
        this.b(0.5F, 0.5F);
        this.c(var2.bm, var2.bn + var2.B(), var2.bo, var2.bs, var2.bt);
        this.bm -= (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.bn -= 0.10000000149011612D;
        this.bo -= (OMathHelper.a(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.c(this.bm, this.bn, this.bo);
        this.bF = 0.0F;
        this.bp = (-OMathHelper.a(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F));
        this.br = (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F));
        this.bq = (-OMathHelper.a(this.bt / 180.0F * 3.1415927F));
        this.a(this.bp, this.bq, this.br, var3 * 1.5F, 1.0F);
        Logman.println("EntityArrow created");
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
        this.k = 0;
    }

    @Override
    public void F_() {
        super.F_();
        if (this.bv == 0.0F && this.bu == 0.0F) {
            float var1 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
            this.bu = this.bs = (float) (Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);
            this.bv = this.bt = (float) (Math.atan2(this.bq, var1) * 180.0D / 3.1415927410125732D);
        }

        int var15 = this.bi.a(this.e, this.f, this.g);
        if (var15 > 0) {
            OBlock.m[var15].a((OIBlockAccess) this.bi, this.e, this.f, this.g);
            OAxisAlignedBB var2 = OBlock.m[var15].e(this.bi, this.e, this.f, this.g);
            if (var2 != null && var2.a(OVec3D.b(this.bm, this.bn, this.bo))) {
                this.j = true;
            }
        }

        if (this.b > 0) {
            --this.b;
        }

        if (this.j) {
            var15 = this.bi.a(this.e, this.f, this.g);
            int var18 = this.bi.c(this.e, this.f, this.g);
            if (var15 == this.h && var18 == this.i) {
                ++this.k;
                if (this.k == 1200) {
                    this.X();
                }

            } else {
                this.j = false;
                this.bp *= (this.bS.nextFloat() * 0.2F);
                this.bq *= (this.bS.nextFloat() * 0.2F);
                this.br *= (this.bS.nextFloat() * 0.2F);
                this.k = 0;
                this.l = 0;
            }
        } else {
            ++this.l;
            OVec3D var16 = OVec3D.b(this.bm, this.bn, this.bo);
            OVec3D var17 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
            OMovingObjectPosition var3 = this.bi.a(var16, var17, false, true);
            var16 = OVec3D.b(this.bm, this.bn, this.bo);
            var17 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
            if (var3 != null) {
                var17 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
            }

            OEntity var4 = null;
            List var5 = this.bi.b(this, this.bw.a(this.bp, this.bq, this.br).b(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;

            int var8;
            float var10;
            for (var8 = 0; var8 < var5.size(); ++var8) {
                OEntity var9 = (OEntity) var5.get(var8);
                if (var9.o_() && (var9 != this.c || this.l >= 5)) {
                    var10 = 0.3F;
                    OAxisAlignedBB var11 = var9.bw.b(var10, var10, var10);
                    OMovingObjectPosition var12 = var11.a(var16, var17);
                    if (var12 != null) {
                        double var13 = var16.b(var12.f);
                        if (var13 < var6 || var6 == 0.0D) {
                            var4 = var9;
                            var6 = var13;
                        }
                    }
                }
            }

            if (var4 != null) {
                var3 = new OMovingObjectPosition(var4);
            }

            float var19;
            if (var3 != null) {
                if (var3.g != null) {
                    var19 = OMathHelper.a(this.bp * this.bp + this.bq * this.bq + this.br * this.br);
                    int var20 = (int) Math.ceil(var19 * this.m);
                    if (this.d) {
                        var20 += this.bS.nextInt(var20 / 2 + 2);
                    }

                    ODamageSource var22 = null;
                    if (this.c == null) {
                        var22 = ODamageSource.a(this, this);
                    } else {
                        var22 = ODamageSource.a(this, this.c);
                    }

                    if (this.B_()) {
                        var3.g.i(5);
                    }

                    if (var3.g.a(var22, var20)) {
                        if (var3.g instanceof OEntityLiving) {
                            ++((OEntityLiving) var3.g).aI;
                            if (this.n > 0) {
                                float var21 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
                                if (var21 > 0.0F) {
                                    var3.g.b_(this.bp * this.n * 0.6000000238418579D / var21, 0.1D, this.br * this.n * 0.6000000238418579D / var21);
                                }
                            }
                        }

                        this.bi.a(this, "random.bowhit", 1.0F, 1.2F / (this.bS.nextFloat() * 0.2F + 0.9F));
                        this.X();
                    } else {
                        this.bp *= -0.10000000149011612D;
                        this.bq *= -0.10000000149011612D;
                        this.br *= -0.10000000149011612D;
                        this.bs += 180.0F;
                        this.bu += 180.0F;
                        this.l = 0;
                    }
                } else {
                    this.e = var3.b;
                    this.f = var3.c;
                    this.g = var3.d;
                    this.h = this.bi.a(this.e, this.f, this.g);
                    this.i = this.bi.c(this.e, this.f, this.g);
                    this.bp = ((float) (var3.f.a - this.bm));
                    this.bq = ((float) (var3.f.b - this.bn));
                    this.br = ((float) (var3.f.c - this.bo));
                    var19 = OMathHelper.a(this.bp * this.bp + this.bq * this.bq + this.br * this.br);
                    this.bm -= this.bp / var19 * 0.05000000074505806D;
                    this.bn -= this.bq / var19 * 0.05000000074505806D;
                    this.bo -= this.br / var19 * 0.05000000074505806D;
                    this.bi.a(this, "random.bowhit", 1.0F, 1.2F / (this.bS.nextFloat() * 0.2F + 0.9F));
                    this.j = true;
                    this.b = 7;
                    this.d = false;
                }
            }

            if (this.d) {
                for (var8 = 0; var8 < 4; ++var8) {
                    this.bi.a("crit", this.bm + this.bp * var8 / 4.0D, this.bn + this.bq * var8 / 4.0D, this.bo + this.br * var8 / 4.0D, -this.bp, -this.bq + 0.2D, -this.br);
                }
            }

            this.bm += this.bp;
            this.bn += this.bq;
            this.bo += this.br;
            var19 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
            this.bs = (float) (Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);

            for (this.bt = (float) (Math.atan2(this.bq, var19) * 180.0D / 3.1415927410125732D); this.bt - this.bv < -180.0F; this.bv -= 360.0F) {
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
            float var23 = 0.99F;
            var10 = 0.05F;
            if (this.aU()) {
                for (int var25 = 0; var25 < 4; ++var25) {
                    float var24 = 0.25F;
                    this.bi.a("bubble", this.bm - this.bp * var24, this.bn - this.bq * var24, this.bo - this.br * var24, this.bp, this.bq, this.br);
                }

                var23 = 0.8F;
            }

            this.bp *= var23;
            this.bq *= var23;
            this.br *= var23;
            this.bq -= var10;
            this.c(this.bm, this.bn, this.bo);
        }
    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("xTile", (short) this.e);
        var1.a("yTile", (short) this.f);
        var1.a("zTile", (short) this.g);
        var1.a("inTile", (byte) this.h);
        var1.a("inData", (byte) this.i);
        var1.a("shake", (byte) this.b);
        var1.a("inGround", (byte) (this.j ? 1 : 0));
        var1.a("player", this.a);
        var1.a("damage", this.m);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.e = var1.e("xTile");
        this.f = var1.e("yTile");
        this.g = var1.e("zTile");
        this.h = var1.d("inTile") & 255;
        this.i = var1.d("inData") & 255;
        this.b = var1.d("shake") & 255;
        this.j = var1.d("inGround") == 1;
        this.a = var1.o("player");
        if (var1.c("damage")) {
            this.m = var1.i("damage");
        }

    }

    @Override
    public void a_(OEntityPlayer var1) {
        if (!this.bi.F) {
            if (this.j && this.a && this.b <= 0 && var1.k.a(new OItemStack(OItem.k, 1))) {
                this.bi.a(this, "random.pop", 0.2F, ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                var1.a(this, 1);
                this.X();
            }

        }
    }

    public void a(double var1) {
        this.m = var1;
    }

    public double k() {
        return this.m;
    }

    public void b(int var1) {
        this.n = var1;
    }

    @Override
    public boolean k_() {
        return false;
    }
}
