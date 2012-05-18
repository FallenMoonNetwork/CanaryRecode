package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityXPOrb extends OEntity {

    public int a;
    public int b = 0;
    public int c;
    private int d = 5;
    private int e;

    public OEntityXPOrb(OWorld var1, double var2, double var4, double var6, int var8) {
        super(var1);
        this.b(0.5F, 0.5F);
        this.bF = this.bH / 2.0F;
        this.c(var2, var4, var6);
        this.bs = (float) (Math.random() * 360.0D);
        this.bp = ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.bq = ((float) (Math.random() * 0.2D) * 2.0F);
        this.br = ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.e = var8;
    }

    protected boolean g_() {
        return false;
    }

    public OEntityXPOrb(OWorld var1) {
        super(var1);
        this.b(0.25F, 0.25F);
        this.bF = this.bH / 2.0F;
    }

    protected void b() {
    }

    public void F_() {
        super.F_();
        if (this.c > 0) {
            --this.c;
        }

        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bq -= 0.029999999329447746D;
        if (this.bi.d(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) == OMaterial.h) {
            this.bq = 0.20000000298023224D;
            this.bp = ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.br = ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F);
            this.bi.a(this, "random.fizz", 0.4F, 2.0F + this.bS.nextFloat() * 0.4F);
        }

        this.g(this.bm, (this.bw.b + this.bw.e) / 2.0D, this.bo);
        double var1 = 8.0D;
        OEntityPlayer var3 = this.bi.a(this, var1);
        if (var3 != null) {
            double var4 = (var3.bm - this.bm) / var1;
            double var6 = (var3.bn + var3.B() - this.bn) / var1;
            double var8 = (var3.bo - this.bo) / var1;
            double var10 = Math.sqrt(var4 * var4 + var6 * var6 + var8 * var8);
            double var12 = 1.0D - var10;
            if (var12 > 0.0D) {
                var12 *= var12;
                this.bp += var4 / var10 * var12 * 0.1D;
                this.bq += var6 / var10 * var12 * 0.1D;
                this.br += var8 / var10 * var12 * 0.1D;
            }
        }

        this.a(this.bp, this.bq, this.br);
        float var14 = 0.98F;
        if (this.bx) {
            var14 = 0.58800006F;
            int var15 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
            if (var15 > 0) {
                var14 = OBlock.m[var15].ce * 0.98F;
            }
        }

        this.bp *= var14;
        this.bq *= 0.9800000190734863D;
        this.br *= var14;
        if (this.bx) {
            this.bq *= -0.8999999761581421D;
        }

        ++this.a;
        ++this.b;
        if (this.b >= 6000) {
            this.X();
        }

    }

    public boolean h_() {
        return this.bi.a(this.bw, OMaterial.g, this);
    }

    protected void a(int var1) {
        this.a(ODamageSource.b, var1);
    }

    public boolean a(ODamageSource var1, int var2) {
        this.aW();
        this.d -= var2;
        if (this.d <= 0) {
            this.X();
        }

        return false;
    }

    public void b(ONBTTagCompound var1) {
        var1.a("Health", (short) ((byte) this.d));
        var1.a("Age", (short) this.b);
        var1.a("Value", (short) this.e);
    }

    public void a(ONBTTagCompound var1) {
        this.d = var1.e("Health") & 255;
        this.b = var1.e("Age");
        this.e = var1.e("Value");
    }

    public void a_(OEntityPlayer var1) {
        if (!this.bi.F) {
            if (this.c == 0 && var1.x == 0) {
                var1.x = 2;
                this.bi.a(this, "random.orb", 0.1F, 0.5F * ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.7F + 1.8F));
                var1.a(this, 1);
                var1.g(this.e);
                this.X();
            }

        }
    }

    public int y_() {
        return this.e;
    }

    public static int b(int var0) {
        return var0 >= 2477 ? 2477 : (var0 >= 1237 ? 1237 : (var0 >= 617 ? 617 : (var0 >= 307 ? 307 : (var0 >= 149 ? 149 : (var0 >= 73 ? 73 : (var0 >= 37 ? 37 : (var0 >= 17 ? 17 : (var0 >= 7 ? 7 : (var0 >= 3 ? 3 : 1)))))))));
    }

    public boolean k_() {
        return false;
    }
}
