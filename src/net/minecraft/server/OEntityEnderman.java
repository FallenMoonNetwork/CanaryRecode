package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityDamageSourceIndirect;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityEnderman extends OEntityMob {

    private static boolean[] b = new boolean[256];
    public boolean a = false;
    private int g = 0;
    private int h = 0;

    public OEntityEnderman(OWorld var1) {
        super(var1);
        this.ae = "/mob/enderman.png";
        this.bb = 0.2F;
        this.c = 7;
        this.b(0.6F, 2.9F);
        this.bP = 1.0F;
    }

    public int d() {
        return 40;
    }

    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
        this.bY.a(17, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("carried", (short) this.A());
        var1.a("carriedData", (short) this.E());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.c(var1.e("carried"));
        this.e(var1.e("carriedData"));
    }

    protected OEntity o() {
        OEntityPlayer var1 = this.bi.b(this, 64.0D);
        if (var1 != null) {
            if (this.c(var1)) {
                if (this.h++ == 5) {
                    this.h = 0;
                    return var1;
                }
            } else {
                this.h = 0;
            }
        }

        return null;
    }

    public float b(float var1) {
        return super.b(var1);
    }

    private boolean c(OEntityPlayer var1) {
        OItemStack var2 = var1.k.b[3];
        if (var2 != null && var2.c == OBlock.ba.bO) {
            return false;
        } else {
            OVec3D var3 = var1.f(1.0F).b();
            OVec3D var4 = OVec3D.b(this.bm - var1.bm, this.bw.b + (this.bH / 2.0F) - (var1.bn + var1.B()), this.bo - var1.bo);
            double var5 = var4.c();
            var4 = var4.b();
            double var7 = var3.a(var4);
            return var7 > 1.0D - 0.025D / var5 ? var1.h(this) : false;
        }
    }

    public void e() {
        if (this.aT()) {
            this.a(ODamageSource.f, 1);
        }

        this.a = this.d != null;
        this.bb = this.d != null ? 6.5F : 0.3F;
        int var1;
        if (!this.bi.F) {
            int var2;
            int var3;
            int var4;
            if (this.A() == 0) {
                if (this.bS.nextInt(20) == 0) {
                    var1 = OMathHelper.b(this.bm - 2.0D + this.bS.nextDouble() * 4.0D);
                    var2 = OMathHelper.b(this.bn + this.bS.nextDouble() * 3.0D);
                    var3 = OMathHelper.b(this.bo - 2.0D + this.bS.nextDouble() * 4.0D);
                    var4 = this.bi.a(var1, var2, var3);
                    if (b[var4]) {
                        this.c(this.bi.a(var1, var2, var3));
                        this.e(this.bi.c(var1, var2, var3));
                        this.bi.e(var1, var2, var3, 0);
                    }
                }
            } else if (this.bS.nextInt(2000) == 0) {
                var1 = OMathHelper.b(this.bm - 1.0D + this.bS.nextDouble() * 2.0D);
                var2 = OMathHelper.b(this.bn + this.bS.nextDouble() * 2.0D);
                var3 = OMathHelper.b(this.bo - 1.0D + this.bS.nextDouble() * 2.0D);
                var4 = this.bi.a(var1, var2, var3);
                int var5 = this.bi.a(var1, var2 - 1, var3);
                if (var4 == 0 && var5 > 0 && OBlock.m[var5].b()) {
                    this.bi.b(var1, var2, var3, this.A(), this.E());
                    this.c(0);
                }
            }
        }

        for (var1 = 0; var1 < 2; ++var1) {
            this.bi.a("portal", this.bm + (this.bS.nextDouble() - 0.5D) * this.bG, this.bn + this.bS.nextDouble() * this.bH - 0.25D, this.bo + (this.bS.nextDouble() - 0.5D) * this.bG, (this.bS.nextDouble() - 0.5D) * 2.0D, -this.bS.nextDouble(), (this.bS.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.bi.e() && !this.bi.F) {
            float var6 = this.b(1.0F);
            if (var6 > 0.5F && this.bi.l(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) && this.bS.nextFloat() * 30.0F < (var6 - 0.4F) * 2.0F) {
                this.d = null;
                this.x();
            }
        }

        if (this.aT()) {
            this.d = null;
            this.x();
        }

        this.aZ = false;
        if (this.d != null) {
            this.a(this.d, 100.0F, 100.0F);
        }

        if (!this.bi.F && this.aE()) {
            if (this.d != null) {
                if (this.d instanceof OEntityPlayer && this.c((OEntityPlayer) this.d)) {
                    this.aW = this.aX = 0.0F;
                    this.bb = 0.0F;
                    if (this.d.j(this) < 16.0D) {
                        this.x();
                    }

                    this.g = 0;
                } else if (this.d.j(this) > 256.0D && this.g++ >= 30 && this.e(this.d)) {
                    this.g = 0;
                }
            } else {
                this.g = 0;
            }
        }

        super.e();
    }

    protected boolean x() {
        double var1 = this.bm + (this.bS.nextDouble() - 0.5D) * 64.0D;
        double var3 = this.bn + (this.bS.nextInt(64) - 32);
        double var5 = this.bo + (this.bS.nextDouble() - 0.5D) * 64.0D;
        return this.b(var1, var3, var5);
    }

    protected boolean e(OEntity var1) {
        OVec3D var2 = OVec3D.b(this.bm - var1.bm, this.bw.b + (this.bH / 2.0F) - var1.bn + var1.B(), this.bo - var1.bo);
        var2 = var2.b();
        double var3 = 16.0D;
        double var5 = this.bm + (this.bS.nextDouble() - 0.5D) * 8.0D - var2.a * var3;
        double var7 = this.bn + (this.bS.nextInt(16) - 8) - var2.b * var3;
        double var9 = this.bo + (this.bS.nextDouble() - 0.5D) * 8.0D - var2.c * var3;
        return this.b(var5, var7, var9);
    }

    protected boolean b(double var1, double var3, double var5) {
        double var7 = this.bm;
        double var9 = this.bn;
        double var11 = this.bo;
        this.bm = var1;
        this.bn = var3;
        this.bo = var5;
        boolean var13 = false;
        int var14 = OMathHelper.b(this.bm);
        int var15 = OMathHelper.b(this.bn);
        int var16 = OMathHelper.b(this.bo);
        int var18;
        if (this.bi.i(var14, var15, var16)) {
            boolean var17 = false;

            while (!var17 && var15 > 0) {
                var18 = this.bi.a(var14, var15 - 1, var16);
                if (var18 != 0 && OBlock.m[var18].cd.c()) {
                    var17 = true;
                } else {
                    --this.bn;
                    --var15;
                }
            }

            if (var17) {
                this.c(this.bm, this.bn, this.bo);
                if (this.bi.a(this, this.bw).size() == 0 && !this.bi.c(this.bw)) {
                    var13 = true;
                }
            }
        }

        if (!var13) {
            this.c(var7, var9, var11);
            return false;
        } else {
            short var30 = 128;

            for (var18 = 0; var18 < var30; ++var18) {
                double var19 = var18 / (var30 - 1.0D);
                float var21 = (this.bS.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.bS.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.bS.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.bm - var7) * var19 + (this.bS.nextDouble() - 0.5D) * this.bG * 2.0D;
                double var26 = var9 + (this.bn - var9) * var19 + this.bS.nextDouble() * this.bH;
                double var28 = var11 + (this.bo - var11) * var19 + (this.bS.nextDouble() - 0.5D) * this.bG * 2.0D;
                this.bi.a("portal", var24, var26, var28, var21, var22, var23);
            }

            this.bi.a(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
            this.bi.a(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String i() {
        return "mob.endermen.idle";
    }

    protected String j() {
        return "mob.endermen.hit";
    }

    protected String k() {
        return "mob.endermen.death";
    }

    protected int f() {
        return OItem.bm.bP;
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.f();
        if (var3 > 0) {
            int var4 = this.bS.nextInt(2 + var2);

            for (int var5 = 0; var5 < var4; ++var5) {
                this.b(var3, 1);
            }
        }

    }

    public void c(int var1) {
        this.bY.b(16, Byte.valueOf((byte) (var1 & 255)));
    }

    public int A() {
        return this.bY.a(16);
    }

    public void e(int var1) {
        this.bY.b(17, Byte.valueOf((byte) (var1 & 255)));
    }

    public int E() {
        return this.bY.a(17);
    }

    public boolean a(ODamageSource var1, int var2) {
        if (var1 instanceof OEntityDamageSourceIndirect) {
            for (int var3 = 0; var3 < 64; ++var3) {
                if (this.x()) {
                    return true;
                }
            }

            return false;
        } else {
            return super.a(var1, var2);
        }
    }

    static {
        b[OBlock.u.bO] = true;
        b[OBlock.v.bO] = true;
        b[OBlock.E.bO] = true;
        b[OBlock.F.bO] = true;
        b[OBlock.ad.bO] = true;
        b[OBlock.ae.bO] = true;
        b[OBlock.af.bO] = true;
        b[OBlock.ag.bO] = true;
        b[OBlock.am.bO] = true;
        b[OBlock.aV.bO] = true;
        b[OBlock.aW.bO] = true;
        b[OBlock.ba.bO] = true;
        b[OBlock.br.bO] = true;
        b[OBlock.by.bO] = true;
    }
}
