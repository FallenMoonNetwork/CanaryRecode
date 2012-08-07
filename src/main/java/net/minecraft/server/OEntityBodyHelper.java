package net.minecraft.server;


import net.minecraft.server.OEntityLiving;


public class OEntityBodyHelper {

    private OEntityLiving a;
    private int b = 0;
    private float c = 0.0F;

    public OEntityBodyHelper(OEntityLiving var1) {
        super();
        this.a = var1;
    }

    public void a() {
        double var1 = this.a.bm - this.a.bj;
        double var3 = this.a.bo - this.a.bl;

        if (var1 * var1 + var3 * var3 > 2.500000277905201E-7D) {
            this.a.V = this.a.bs;
            this.a.X = this.a(this.a.V, this.a.X, 75.0F);
            this.c = this.a.X;
            this.b = 0;
        } else {
            float var5 = 75.0F;

            if (Math.abs(this.a.X - this.c) > 15.0F) {
                this.b = 0;
                this.c = this.a.X;
            } else {
                ++this.b;
                if (this.b > 10) {
                    var5 = Math.max(1.0F - (this.b - 10) / 10.0F, 0.0F) * 75.0F;
                }
            }

            this.a.V = this.a(this.a.X, this.a.V, var5);
        }
    }

    private float a(float var1, float var2, float var3) {
        float var4;

        for (var4 = var1 - var2; var4 < -180.0F; var4 += 360.0F) {
            ;
        }

        while (var4 >= 180.0F) {
            var4 -= 360.0F;
        }

        if (var4 < -var3) {
            var4 = -var3;
        }

        if (var4 >= var3) {
            var4 = var3;
        }

        return var1 - var4;
    }
}
