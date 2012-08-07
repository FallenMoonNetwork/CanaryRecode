package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockSand;
import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;


public class OEntityFallingSand extends OEntity {

    public int a;
    public int b = 0;

    public OEntityFallingSand(OWorld var1) {
        super(var1);
    }

    public OEntityFallingSand(OWorld var1, double var2, double var4, double var6, int var8) {
        super(var1);
        this.a = var8;
        this.bf = true;
        this.b(0.98F, 0.98F);
        this.bF = this.bH / 2.0F;
        this.c(var2, var4, var6);
        this.bp = 0.0D;
        this.bq = 0.0D;
        this.br = 0.0D;
        this.bj = var2;
        this.bk = var4;
        this.bl = var6;
    }

    @Override
    protected boolean g_() {
        return false;
    }

    @Override
    protected void b() {}

    @Override
    public boolean o_() {
        return !this.bE;
    }

    @Override
    public void F_() {
        if (this.a == 0) {
            this.X();
        } else {
            this.bj = this.bm;
            this.bk = this.bn;
            this.bl = this.bo;
            ++this.b;
            this.bq -= 0.03999999910593033D;
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.9800000190734863D;
            this.bq *= 0.9800000190734863D;
            this.br *= 0.9800000190734863D;
            int var1 = OMathHelper.b(this.bm);
            int var2 = OMathHelper.b(this.bn);
            int var3 = OMathHelper.b(this.bo);

            if (this.b == 1 && this.bi.a(var1, var2, var3) == this.a) {
                this.bi.e(var1, var2, var3, 0);
            } else if (!this.bi.F && this.b == 1) {
                this.X();
            }

            if (this.bx) {
                this.bp *= 0.699999988079071D;
                this.br *= 0.699999988079071D;
                this.bq *= -0.5D;
                if (this.bi.a(var1, var2, var3) != OBlock.ac.bO) {
                    this.X();
                    if ((!this.bi.a(this.a, var1, var2, var3, true, 1) || OBlockSand.g(this.bi, var1, var2 - 1, var3) || !this.bi.e(var1, var2, var3, this.a)) && !this.bi.F) {
                        this.b(this.a, 1);
                    }
                }
            } else if (this.b > 100 && !this.bi.F && (var2 < 1 || var2 > 256) || this.b > 600) {
                this.b(this.a, 1);
                this.X();
            }

        }
    }

    @Override
    protected void b(ONBTTagCompound var1) {
        var1.a("Tile", (byte) this.a);
    }

    @Override
    protected void a(ONBTTagCompound var1) {
        this.a = var1.d("Tile") & 255;
    }
}
