package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OPathPoint;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OPathNavigate {

    private OEntityLiving a;
    private OWorld b;
    private OPathEntity c;
    private float d;
    private float e;
    private boolean f = false;
    private int g;
    private int h;
    private OVec3D i = OVec3D.a(0.0D, 0.0D, 0.0D);
    private boolean j = true;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;

    public OPathNavigate(OEntityLiving var1, OWorld var2, float var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.e = var3;
    }

    public void a(boolean var1) {
        this.l = var1;
    }

    public boolean a() {
        return this.l;
    }

    public void b(boolean var1) {
        this.k = var1;
    }

    public void c(boolean var1) {
        this.j = var1;
    }

    public boolean b() {
        return this.k;
    }

    public void d(boolean var1) {
        this.f = var1;
    }

    public void a(float var1) {
        this.d = var1;
    }

    public void e(boolean var1) {
        this.m = var1;
    }

    public OPathEntity a(double var1, double var3, double var5) {
        return !this.j() ? null : this.b.a(this.a, OMathHelper.b(var1), (int) var3, OMathHelper.b(var5), this.e, this.j, this.k, this.l, this.m);
    }

    public boolean a(double var1, double var3, double var5, float var7) {
        OPathEntity var8 = this.a(OMathHelper.b(var1), ((int) var3), OMathHelper.b(var5));
        return this.a(var8, var7);
    }

    public OPathEntity a(OEntityLiving var1) {
        return !this.j() ? null : this.b.a(this.a, var1, this.e, this.j, this.k, this.l, this.m);
    }

    public boolean a(OEntityLiving var1, float var2) {
        OPathEntity var3 = this.a(var1);
        return var3 != null ? this.a(var3, var2) : false;
    }

    public boolean a(OPathEntity var1, float var2) {
        if (var1 == null) {
            this.c = null;
            return false;
        } else {
            if (!var1.a(this.c)) {
                this.c = var1;
            }

            if (this.f) {
                this.l();
            }

            if (this.c.d() == 0) {
                return false;
            } else {
                this.d = var2;
                OVec3D var3 = this.h();
                this.h = this.g;
                this.i.a = var3.a;
                this.i.b = var3.b;
                this.i.c = var3.c;
                return true;
            }
        }
    }

    public OPathEntity c() {
        return this.c;
    }

    public void d() {
        ++this.g;
        if (!this.e()) {
            if (this.j()) {
                this.g();
            }

            if (!this.e()) {
                OVec3D var1 = this.c.a(this.a);
                if (var1 != null) {
                    this.a.aj().a(var1.a, var1.b, var1.c, this.d);
                }
            }
        }
    }

    private void g() {
        OVec3D var1 = this.h();
        int var2 = this.c.d();

        for (int var3 = this.c.e(); var3 < this.c.d(); ++var3) {
            if (this.c.a(var3).b != (int) var1.b) {
                var2 = var3;
                break;
            }
        }

        float var8 = this.a.bG * this.a.bG;

        int var4;
        for (var4 = this.c.e(); var4 < var2; ++var4) {
            if (var1.c(this.c.a(this.a, var4)) < var8) {
                this.c.c(var4 + 1);
            }
        }

        var4 = (int) Math.ceil(this.a.bG);
        int var5 = (int) this.a.bH + 1;
        int var6 = var4;

        for (int var7 = var2 - 1; var7 >= this.c.e(); --var7) {
            if (this.a(var1, this.c.a(this.a, var7), var4, var5, var6)) {
                this.c.c(var7);
                break;
            }
        }

        if (this.g - this.h > 100) {
            if (var1.c(this.i) < 2.25D) {
                this.f();
            }

            this.h = this.g;
            this.i.a = var1.a;
            this.i.b = var1.b;
            this.i.c = var1.c;
        }

    }

    public boolean e() {
        return this.c == null || this.c.b();
    }

    public void f() {
        this.c = null;
    }

    private OVec3D h() {
        return OVec3D.b(this.a.bm, this.i(), this.a.bo);
    }

    private int i() {
        if (this.a.aU() && this.m) {
            int var1 = (int) this.a.bw.b;
            int var2 = this.b.a(OMathHelper.b(this.a.bm), var1, OMathHelper.b(this.a.bo));
            int var3 = 0;

            do {
                if (var2 != OBlock.A.bO && var2 != OBlock.B.bO) {
                    return var1;
                }

                ++var1;
                var2 = this.b.a(OMathHelper.b(this.a.bm), var1, OMathHelper.b(this.a.bo));
                ++var3;
            } while (var3 <= 16);

            return (int) this.a.bw.b;
        } else {
            return (int) (this.a.bw.b + 0.5D);
        }
    }

    private boolean j() {
        return this.a.bx || this.m && this.k();
    }

    private boolean k() {
        return this.a.aU() || this.a.aV();
    }

    private void l() {
        if (!this.b.l(OMathHelper.b(this.a.bm), (int) (this.a.bw.b + 0.5D), OMathHelper.b(this.a.bo))) {
            for (int var1 = 0; var1 < this.c.d(); ++var1) {
                OPathPoint var2 = this.c.a(var1);
                if (this.b.l(var2.a, var2.b, var2.c)) {
                    this.c.b(var1 - 1);
                    return;
                }
            }

        }
    }

    private boolean a(OVec3D var1, OVec3D var2, int var3, int var4, int var5) {
        int var6 = OMathHelper.b(var1.a);
        int var7 = OMathHelper.b(var1.c);
        double var8 = var2.a - var1.a;
        double var10 = var2.c - var1.c;
        double var12 = var8 * var8 + var10 * var10;
        if (var12 < 1.0E-8D) {
            return false;
        } else {
            double var14 = 1.0D / Math.sqrt(var12);
            var8 *= var14;
            var10 *= var14;
            var3 += 2;
            var5 += 2;
            if (!this.a(var6, (int) var1.b, var7, var3, var4, var5, var1, var8, var10)) {
                return false;
            } else {
                var3 -= 2;
                var5 -= 2;
                double var16 = 1.0D / Math.abs(var8);
                double var18 = 1.0D / Math.abs(var10);
                double var20 = (var6 * 1) - var1.a;
                double var22 = (var7 * 1) - var1.c;
                if (var8 >= 0.0D) {
                    ++var20;
                }

                if (var10 >= 0.0D) {
                    ++var22;
                }

                var20 /= var8;
                var22 /= var10;
                int var24 = var8 < 0.0D ? -1 : 1;
                int var25 = var10 < 0.0D ? -1 : 1;
                int var26 = OMathHelper.b(var2.a);
                int var27 = OMathHelper.b(var2.c);
                int var28 = var26 - var6;
                int var29 = var27 - var7;

                do {
                    if (var28 * var24 <= 0 && var29 * var25 <= 0) {
                        return true;
                    }

                    if (var20 < var22) {
                        var20 += var16;
                        var6 += var24;
                        var28 = var26 - var6;
                    } else {
                        var22 += var18;
                        var7 += var25;
                        var29 = var27 - var7;
                    }
                } while (this.a(var6, (int) var1.b, var7, var3, var4, var5, var1, var8, var10));

                return false;
            }
        }
    }

    private boolean a(int var1, int var2, int var3, int var4, int var5, int var6, OVec3D var7, double var8, double var10) {
        int var12 = var1 - var4 / 2;
        int var13 = var3 - var6 / 2;
        if (!this.b(var12, var2, var13, var4, var5, var6, var7, var8, var10)) {
            return false;
        } else {
            for (int var14 = var12; var14 < var12 + var4; ++var14) {
                for (int var15 = var13; var15 < var13 + var6; ++var15) {
                    double var16 = var14 + 0.5D - var7.a;
                    double var18 = var15 + 0.5D - var7.c;
                    if (var16 * var8 + var18 * var10 >= 0.0D) {
                        int var20 = this.b.a(var14, var2 - 1, var15);
                        if (var20 <= 0) {
                            return false;
                        }

                        OMaterial var21 = OBlock.m[var20].cd;
                        if (var21 == OMaterial.g && !this.a.aU()) {
                            return false;
                        }

                        if (var21 == OMaterial.h) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean b(int var1, int var2, int var3, int var4, int var5, int var6, OVec3D var7, double var8, double var10) {
        for (int var12 = var1; var12 < var1 + var4; ++var12) {
            for (int var13 = var2; var13 < var2 + var5; ++var13) {
                for (int var14 = var3; var14 < var3 + var6; ++var14) {
                    double var15 = var12 + 0.5D - var7.a;
                    double var17 = var14 + 0.5D - var7.c;
                    if (var15 * var8 + var17 * var10 >= 0.0D) {
                        int var19 = this.b.a(var12, var13, var14);
                        if (var19 > 0 && !OBlock.m[var19].b(this.b, var12, var13, var14)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
