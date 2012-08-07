package net.minecraft.server;


import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;


public abstract class OEntityThrowable extends OEntity {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    protected boolean a = false;
    public int b = 0;
    protected OEntityLiving c;
    private int h;
    private int i = 0;

    public OEntityThrowable(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
    }

    @Override
    protected void b() {}

    public OEntityThrowable(OWorld var1, OEntityLiving var2) {
        super(var1);
        this.c = var2;
        this.b(0.25F, 0.25F);
        this.c(var2.bm, var2.bn + var2.B(), var2.bo, var2.bs, var2.bt);
        this.bm -= (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.bn -= 0.10000000149011612D;
        this.bo -= (OMathHelper.a(this.bs / 180.0F * 3.1415927F) * 0.16F);
        this.c(this.bm, this.bn, this.bo);
        this.bF = 0.0F;
        float var3 = 0.4F;

        this.bp = (-OMathHelper.a(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * var3);
        this.br = (OMathHelper.b(this.bs / 180.0F * 3.1415927F) * OMathHelper.b(this.bt / 180.0F * 3.1415927F) * var3);
        this.bq = (-OMathHelper.a((this.bt + this.d()) / 180.0F * 3.1415927F) * var3);
        this.a(this.bp, this.bq, this.br, this.c(), 1.0F);
    }

    public OEntityThrowable(OWorld var1, double var2, double var4, double var6) {
        super(var1);
        this.h = 0;
        this.b(0.25F, 0.25F);
        this.c(var2, var4, var6);
        this.bF = 0.0F;
    }

    protected float c() {
        return 1.5F;
    }

    protected float d() {
        return 0.0F;
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
        this.h = 0;
    }

    @Override
    public void F_() {
        this.bL = this.bm;
        this.bM = this.bn;
        this.bN = this.bo;
        super.F_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a) {
            int var1 = this.bi.a(this.d, this.e, this.f);

            if (var1 == this.g) {
                ++this.h;
                if (this.h == 1200) {
                    this.X();
                }

                return;
            }

            this.a = false;
            this.bp *= (this.bS.nextFloat() * 0.2F);
            this.bq *= (this.bS.nextFloat() * 0.2F);
            this.br *= (this.bS.nextFloat() * 0.2F);
            this.h = 0;
            this.i = 0;
        } else {
            ++this.i;
        }

        OVec3D var17 = OVec3D.b(this.bm, this.bn, this.bo);
        OVec3D var2 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
        OMovingObjectPosition var3 = this.bi.a(var17, var2);

        var17 = OVec3D.b(this.bm, this.bn, this.bo);
        var2 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
        if (var3 != null) {
            var2 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
        }

        if (!this.bi.F) {
            OEntity var4 = null;
            List var5 = this.bi.b(this, this.bw.a(this.bp, this.bq, this.br).b(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;

            for (int var8 = 0; var8 < var5.size(); ++var8) {
                OEntity var9 = (OEntity) var5.get(var8);

                if (var9.o_() && (var9 != this.c || this.i >= 5)) {
                    float var10 = 0.3F;
                    OAxisAlignedBB var11 = var9.bw.b(var10, var10, var10);
                    OMovingObjectPosition var12 = var11.a(var17, var2);

                    if (var12 != null) {
                        double var13 = var17.b(var12.f);

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
        }

        if (var3 != null) {
            this.a(var3);
        }

        this.bm += this.bp;
        this.bn += this.bq;
        this.bo += this.br;
        float var18 = OMathHelper.a(this.bp * this.bp + this.br * this.br);

        this.bs = (float) (Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);

        for (this.bt = (float) (Math.atan2(this.bq, var18) * 180.0D / 3.1415927410125732D); this.bt - this.bv < -180.0F; this.bv -= 360.0F) {
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
        float var19 = 0.99F;
        float var15 = this.e();

        if (this.aU()) {
            for (int var16 = 0; var16 < 4; ++var16) {
                float var20 = 0.25F;

                this.bi.a("bubble", this.bm - this.bp * var20, this.bn - this.bq * var20, this.bo - this.br * var20, this.bp, this.bq, this.br);
            }

            var19 = 0.8F;
        }

        this.bp *= var19;
        this.bq *= var19;
        this.br *= var19;
        this.bq -= var15;
        this.c(this.bm, this.bn, this.bo);
    }

    protected float e() {
        return 0.03F;
    }

    protected abstract void a(OMovingObjectPosition var1);

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("xTile", (short) this.d);
        var1.a("yTile", (short) this.e);
        var1.a("zTile", (short) this.f);
        var1.a("inTile", (byte) this.g);
        var1.a("shake", (byte) this.b);
        var1.a("inGround", (byte) (this.a ? 1 : 0));
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.d = var1.e("xTile");
        this.e = var1.e("yTile");
        this.f = var1.e("zTile");
        this.g = var1.d("inTile") & 255;
        this.b = var1.d("shake") & 255;
        this.a = var1.d("inGround") == 1;
    }

    @Override
    public void a_(OEntityPlayer var1) {}
}
