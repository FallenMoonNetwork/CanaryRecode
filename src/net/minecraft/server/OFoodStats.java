package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemFood;
import net.minecraft.server.ONBTTagCompound;

public class OFoodStats {

    private int a = 20;
    private float b = 5.0F;
    private float c;
    private int d = 0;
    private int e = 20;

    public OFoodStats() {
        super();
    }

    public void a(int var1, float var2) {
        this.a = Math.min(var1 + this.a, 20);
        this.b = Math.min(this.b + var1 * var2 * 2.0F, this.a);
    }

    public void a(OItemFood var1) {
        this.a(var1.o(), var1.p());
    }

    public void a(OEntityPlayer var1) {
        int var2 = var1.bi.q;
        this.e = this.a;
        if (this.c > 4.0F) {
            this.c -= 4.0F;
            if (this.b > 0.0F) {
                this.b = Math.max(this.b - 1.0F, 0.0F);
            } else if (var2 > 0) {
                this.a = Math.max(this.a - 1, 0);
            }
        }

        if (this.a >= 18 && var1.ag()) {
            ++this.d;
            if (this.d >= 80) {
                var1.d(1);
                this.d = 0;
            }
        } else if (this.a <= 0) {
            ++this.d;
            if (this.d >= 80) {
                if (var1.aD() > 10 || var2 >= 3 || var1.aD() > 1 && var2 >= 2) {
                    var1.a(ODamageSource.g, 1);
                }

                this.d = 0;
            }
        } else {
            this.d = 0;
        }

    }

    public void a(ONBTTagCompound var1) {
        if (var1.c("foodLevel")) {
            this.a = var1.f("foodLevel");
            this.d = var1.f("foodTickTimer");
            this.b = var1.h("foodSaturationLevel");
            this.c = var1.h("foodExhaustionLevel");
        }

    }

    public void b(ONBTTagCompound var1) {
        var1.a("foodLevel", this.a);
        var1.a("foodTickTimer", this.d);
        var1.a("foodSaturationLevel", this.b);
        var1.a("foodExhaustionLevel", this.c);
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.a < 20;
    }

    public void a(float var1) {
        this.c = Math.min(this.c + var1, 40.0F);
    }

    public float c() {
        return this.b;
    }
}
