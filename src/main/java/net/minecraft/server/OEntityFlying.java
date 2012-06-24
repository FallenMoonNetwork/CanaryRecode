package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public abstract class OEntityFlying extends OEntityLiving {

    public OEntityFlying(OWorld var1) {
        super(var1);
    }

    @Override
    protected void a(float var1) {
    }

    @Override
    public void a(float var1, float var2) {
        if (this.aU()) {
            this.a(var1, var2, 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.800000011920929D;
            this.bq *= 0.800000011920929D;
            this.br *= 0.800000011920929D;
        } else if (this.aV()) {
            this.a(var1, var2, 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.5D;
            this.bq *= 0.5D;
            this.br *= 0.5D;
        } else {
            float var3 = 0.91F;
            if (this.bx) {
                var3 = 0.54600006F;
                int var4 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
                if (var4 > 0) {
                    var3 = OBlock.m[var4].ce * 0.91F;
                }
            }

            float var11 = 0.16277136F / (var3 * var3 * var3);
            this.a(var1, var2, this.bx ? 0.1F * var11 : 0.02F);
            var3 = 0.91F;
            if (this.bx) {
                var3 = 0.54600006F;
                int var5 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
                if (var5 > 0) {
                    var3 = OBlock.m[var5].ce * 0.91F;
                }
            }

            this.a(this.bp, this.bq, this.br);
            this.bp *= var3;
            this.bq *= var3;
            this.br *= var3;
        }

        this.aD = this.aE;
        double var6 = this.bm - this.bj;
        double var8 = this.bo - this.bl;
        float var10 = OMathHelper.a(var6 * var6 + var8 * var8) * 4.0F;
        if (var10 > 1.0F) {
            var10 = 1.0F;
        }

        this.aE += (var10 - this.aE) * 0.4F;
        this.aF += this.aE;
    }

    @Override
    public boolean t() {
        return false;
    }
}
