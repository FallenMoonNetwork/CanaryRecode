package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityTNTPrimed extends OEntity {

    public int a;

    public OEntityTNTPrimed(OWorld var1) {
        super(var1);
        this.a = 0;
        this.bf = true;
        this.b(0.98F, 0.98F);
        this.bF = this.bH / 2.0F;
    }

    public OEntityTNTPrimed(OWorld var1, double var2, double var4, double var6) {
        this(var1);
        this.c(var2, var4, var6);
        float var8 = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.bp = (-((float) Math.sin(var8)) * 0.02F);
        this.bq = 0.20000000298023224D;
        this.br = (-((float) Math.cos(var8)) * 0.02F);
        this.a = 80;
        this.bj = var2;
        this.bk = var4;
        this.bl = var6;
    }

    protected void b() {
    }

    protected boolean g_() {
        return false;
    }

    public boolean o_() {
        return !this.bE;
    }

    public void F_() {
        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bq -= 0.03999999910593033D;
        this.a(this.bp, this.bq, this.br);
        this.bp *= 0.9800000190734863D;
        this.bq *= 0.9800000190734863D;
        this.br *= 0.9800000190734863D;
        if (this.bx) {
            this.bp *= 0.699999988079071D;
            this.br *= 0.699999988079071D;
            this.bq *= -0.5D;
        }

        if (this.a-- <= 0) {
            if (!this.bi.F) {
                this.X();
                this.k();
            } else {
                this.X();
            }
        } else {
            this.bi.a("smoke", this.bm, this.bn + 0.5D, this.bo, 0.0D, 0.0D, 0.0D);
        }

    }

    private void k() {
        float var1 = 4.0F;
        this.bi.a((OEntity) null, this.bm, this.bn, this.bo, var1);
    }

    protected void b(ONBTTagCompound var1) {
        var1.a("Fuse", (byte) this.a);
    }

    protected void a(ONBTTagCompound var1) {
        this.a = var1.d("Fuse");
    }
}
