package net.minecraft.server;


import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;


public class OEntityEnderEye extends OEntity {

    public int a = 0;
    private double b;
    private double c;
    private double d;
    private int e;
    private boolean f;

    public OEntityEnderEye(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
    }

    @Override
    protected void b() {}

    public OEntityEnderEye(OWorld var1, double var2, double var4, double var6) {
        super(var1);
        this.e = 0;
        this.b(0.25F, 0.25F);
        this.c(var2, var4, var6);
        this.bF = 0.0F;
    }

    public void a(double var1, int var3, double var4) {
        double var6 = var1 - this.bm;
        double var8 = var4 - this.bo;
        float var10 = OMathHelper.a(var6 * var6 + var8 * var8);

        if (var10 > 12.0F) {
            this.b = this.bm + var6 / var10 * 12.0D;
            this.d = this.bo + var8 / var10 * 12.0D;
            this.c = this.bn + 8.0D;
        } else {
            this.b = var1;
            this.c = var3;
            this.d = var4;
        }

        this.e = 0;
        this.f = this.bS.nextInt(5) > 0;
    }

    @Override
    public void F_() {
        this.bL = this.bm;
        this.bM = this.bn;
        this.bN = this.bo;
        super.F_();
        this.bm += this.bp;
        this.bn += this.bq;
        this.bo += this.br;
        float var1 = OMathHelper.a(this.bp * this.bp + this.br * this.br);

        this.bs = (float) (Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);

        for (this.bt = (float) (Math.atan2(this.bq, var1) * 180.0D / 3.1415927410125732D); this.bt - this.bv < -180.0F; this.bv -= 360.0F) {
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
        if (!this.bi.F) {
            double var2 = this.b - this.bm;
            double var4 = this.d - this.bo;
            float var6 = (float) Math.sqrt(var2 * var2 + var4 * var4);
            float var7 = (float) Math.atan2(var4, var2);
            double var8 = var1 + (var6 - var1) * 0.0025D;

            if (var6 < 1.0F) {
                var8 *= 0.8D;
                this.bq *= 0.8D;
            }

            this.bp = Math.cos(var7) * var8;
            this.br = Math.sin(var7) * var8;
            if (this.bn < this.c) {
                this.bq += (1.0D - this.bq) * 0.014999999664723873D;
            } else {
                this.bq += (-1.0D - this.bq) * 0.014999999664723873D;
            }
        }

        float var10 = 0.25F;

        if (this.aU()) {
            for (int var11 = 0; var11 < 4; ++var11) {
                this.bi.a("bubble", this.bm - this.bp * var10, this.bn - this.bq * var10, this.bo - this.br * var10, this.bp, this.bq, this.br);
            }
        } else {
            this.bi.a("portal", this.bm - this.bp * var10 + this.bS.nextDouble() * 0.6D - 0.3D, this.bn - this.bq * var10 - 0.5D, this.bo - this.br * var10 + this.bS.nextDouble() * 0.6D - 0.3D, this.bp, this.bq, this.br);
        }

        if (!this.bi.F) {
            this.c(this.bm, this.bn, this.bo);
            ++this.e;
            if (this.e > 80 && !this.bi.F) {
                this.X();
                if (this.f) {
                    this.bi.b((new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.bz))));
                } else {
                    this.bi.f(2003, (int) Math.round(this.bm), (int) Math.round(this.bn), (int) Math.round(this.bo), 0);
                }
            }
        }

    }

    @Override
    public void b(ONBTTagCompound var1) {}

    @Override
    public void a(ONBTTagCompound var1) {}

    @Override
    public void a_(OEntityPlayer var1) {}

    @Override
    public float b(float var1) {
        return 1.0F;
    }

    @Override
    public boolean k_() {
        return false;
    }
}
