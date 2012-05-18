package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;

public class OEntityEgg extends OEntityThrowable {

    public OEntityEgg(OWorld var1) {
        super(var1);
    }

    public OEntityEgg(OWorld var1, OEntityLiving var2) {
        super(var1, var2);
    }

    public OEntityEgg(OWorld var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void a(OMovingObjectPosition var1) {
        if (var1.g != null && var1.g.a(ODamageSource.a(this, this.c), 0)) {
            ;
        }

        if (!this.bi.F && this.bS.nextInt(8) == 0) {
            byte var2 = 1;
            if (this.bS.nextInt(32) == 0) {
                var2 = 4;
            }

            for (int var3 = 0; var3 < var2; ++var3) {
                OEntityChicken var4 = new OEntityChicken(this.bi);
                var4.c(-24000);
                var4.c(this.bm, this.bn, this.bo, this.bs, 0.0F);
                this.bi.b(var4);
            }
        }

        for (int var5 = 0; var5 < 8; ++var5) {
            this.bi.a("snowballpoof", this.bm, this.bn, this.bo, 0.0D, 0.0D, 0.0D);
        }

        if (!this.bi.F) {
            this.X();
        }

    }
}
