package net.minecraft.server;

import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;

public class OEntityExpBottle extends OEntityThrowable {

    public OEntityExpBottle(OWorld var1) {
        super(var1);
    }

    public OEntityExpBottle(OWorld var1, OEntityLiving var2) {
        super(var1, var2);
    }

    public OEntityExpBottle(OWorld var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected float e() {
        return 0.07F;
    }

    protected float c() {
        return 0.7F;
    }

    protected float d() {
        return -20.0F;
    }

    protected void a(OMovingObjectPosition var1) {
        if (!this.bi.F) {
            this.bi.f(2002, (int) Math.round(this.bm), (int) Math.round(this.bn), (int) Math.round(this.bo), 0);
            int var2 = 3 + this.bi.r.nextInt(5) + this.bi.r.nextInt(5);

            while (var2 > 0) {
                int var3 = OEntityXPOrb.b(var2);
                var2 -= var3;
                this.bi.b((new OEntityXPOrb(this.bi, this.bm, this.bn, this.bo, var3)));
            }

            this.X();
        }

    }
}
