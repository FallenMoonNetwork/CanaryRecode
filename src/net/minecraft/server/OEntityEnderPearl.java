package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;

public class OEntityEnderPearl extends OEntityThrowable {

    public OEntityEnderPearl(OWorld var1) {
        super(var1);
    }

    public OEntityEnderPearl(OWorld var1, OEntityLiving var2) {
        super(var1, var2);
    }

    public OEntityEnderPearl(OWorld var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void a(OMovingObjectPosition var1) {
        if (var1.g != null && var1.g.a(ODamageSource.a(this, this.c), 0)) {
            ;
        }

        for (int var2 = 0; var2 < 32; ++var2) {
            this.bi.a("portal", this.bm, this.bn + this.bS.nextDouble() * 2.0D, this.bo, this.bS.nextGaussian(), 0.0D, this.bS.nextGaussian());
        }

        if (!this.bi.F) {
            if (this.c != null) {
                this.c.a_(this.bm, this.bn, this.bo);
                this.c.bK = 0.0F;
                this.c.a(ODamageSource.i, 5);
            }

            this.X();
        }

    }
}
