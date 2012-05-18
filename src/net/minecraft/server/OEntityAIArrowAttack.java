package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OEntityAIArrowAttack extends OEntityAIBase {

    OWorld a;
    OEntityLiving b;
    OEntityLiving c;
    int d = 0;
    float e;
    int f = 0;
    int g;
    int h;

    public OEntityAIArrowAttack(OEntityLiving var1, float var2, int var3, int var4) {
        super();
        this.b = var1;
        this.a = var1.bi;
        this.e = var2;
        this.g = var3;
        this.h = var4;
        this.a(3);
    }

    public boolean a() {
        OEntityLiving var1 = this.b.at();
        if (var1 == null) {
            return false;
        } else {
            this.c = var1;
            return true;
        }
    }

    public boolean b() {
        return this.a() || !this.b.al().e();
    }

    public void d() {
        this.c = null;
    }

    public void e() {
        double var1 = 100.0D;
        double var3 = this.b.e(this.c.bm, this.c.bw.b, this.c.bo);
        boolean var5 = this.b.am().a(this.c);
        if (var5) {
            ++this.f;
        } else {
            this.f = 0;
        }

        if (var3 <= var1 && this.f >= 20) {
            this.b.al().f();
        } else {
            this.b.al().a(this.c, this.e);
        }

        this.b.ai().a(this.c, 30.0F, 30.0F);
        this.d = Math.max(this.d - 1, 0);
        if (this.d <= 0) {
            if (var3 <= var1 && var5) {
                this.f();
                this.d = this.h;
            }
        }
    }

    private void f() {
        if (this.g == 1) {
            OEntityArrow var1 = new OEntityArrow(this.a, this.b, this.c, 1.6F, 12.0F);
            this.a.a(this.b, "random.bow", 1.0F, 1.0F / (this.b.an().nextFloat() * 0.4F + 0.8F));
            this.a.b(var1);
        } else if (this.g == 2) {
            OEntitySnowball var9 = new OEntitySnowball(this.a, this.b);
            double var2 = this.c.bm - this.b.bm;
            double var4 = this.c.bn + this.c.B() - 1.100000023841858D - var9.bn;
            double var6 = this.c.bo - this.b.bo;
            float var8 = OMathHelper.a(var2 * var2 + var6 * var6) * 0.2F;
            var9.a(var2, var4 + var8, var6, 1.6F, 12.0F);
            this.a.a(this.b, "random.bow", 1.0F, 1.0F / (this.b.an().nextFloat() * 0.4F + 0.8F));
            this.a.b(var9);
        }

    }
}
