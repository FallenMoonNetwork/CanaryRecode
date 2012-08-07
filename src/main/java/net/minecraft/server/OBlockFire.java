package net.minecraft.server;


import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.hook.world.IgnitionHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldProviderEnd;


public class OBlockFire extends OBlock {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected OBlockFire(int var1, int var2) {
        super(var1, var2, OMaterial.n);
        this.a(true);
    }

    @Override
    public void k() {
        this.a(OBlock.x.bO, 5, 20);
        this.a(OBlock.aZ.bO, 5, 20);
        this.a(OBlock.at.bO, 5, 20);
        this.a(OBlock.J.bO, 5, 5);
        this.a(OBlock.K.bO, 30, 60);
        this.a(OBlock.an.bO, 30, 20);
        this.a(OBlock.am.bO, 15, 100);
        this.a(OBlock.X.bO, 60, 100);
        this.a(OBlock.ab.bO, 30, 60);
        this.a(OBlock.bu.bO, 15, 100);
    }

    private void a(int var1, int var2, int var3) {
        this.a[var1] = var2;
        this.b[var1] = var3;
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int c() {
        return 3;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public int d() {
        return 30;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        boolean var6 = var1.a(var2, var3 - 1, var4) == OBlock.bb.bO;

        if (var1.t instanceof OWorldProviderEnd && var1.a(var2, var3 - 1, var4) == OBlock.z.bO) {
            var6 = true;
        }

        if (!this.c(var1, var2, var3, var4)) {
            var1.e(var2, var3, var4, 0);
        }

        if (!var6 && var1.x() && (var1.y(var2, var3, var4) || var1.y(var2 - 1, var3, var4) || var1.y(var2 + 1, var3, var4) || var1.y(var2, var3, var4 - 1) || var1.y(var2, var3, var4 + 1))) {
            var1.e(var2, var3, var4, 0);
        } else {
            int var7 = var1.c(var2, var3, var4);

            if (var7 < 15) {
                var1.d(var2, var3, var4, var7 + var5.nextInt(3) / 2);
            }

            var1.c(var2, var3, var4, this.bO, this.d() + var5.nextInt(10));
            if (!var6 && !this.g(var1, var2, var3, var4)) {
                if (!var1.e(var2, var3 - 1, var4) || var7 > 3) {
                    var1.e(var2, var3, var4, 0);
                }

            } else if (!var6 && !this.c((OIBlockAccess) var1, var2, var3 - 1, var4) && var7 == 15 && var5.nextInt(4) == 0) {
                var1.e(var2, var3, var4, 0);
            } else {
                boolean var8 = var1.z(var2, var3, var4);
                byte var9 = 0;

                if (var8) {
                    var9 = -50;
                }

                this.a(var1, var2 + 1, var3, var4, 300 + var9, var5, var7);
                this.a(var1, var2 - 1, var3, var4, 300 + var9, var5, var7);
                this.a(var1, var2, var3 - 1, var4, 250 + var9, var5, var7);
                this.a(var1, var2, var3 + 1, var4, 250 + var9, var5, var7);
                this.a(var1, var2, var3, var4 - 1, 300 + var9, var5, var7);
                this.a(var1, var2, var3, var4 + 1, 300 + var9, var5, var7);

                for (int var10 = var2 - 1; var10 <= var2 + 1; ++var10) {
                    for (int var11 = var4 - 1; var11 <= var4 + 1; ++var11) {
                        for (int var12 = var3 - 1; var12 <= var3 + 4; ++var12) {
                            if (var10 != var2 || var12 != var3 || var11 != var4) {
                                int var13 = 100;

                                if (var12 > var3 + 1) {
                                    var13 += (var12 - (var3 + 1)) * 100;
                                }

                                int var14 = this.h(var1, var10, var12, var11);

                                if (var14 > 0) {
                                    int var15 = (var14 + 40) / (var7 + 30);

                                    if (var8) {
                                        var15 /= 2;
                                    }

                                    if (var15 > 0 && var5.nextInt(var13) <= var15 && (!var1.x() || !var1.y(var10, var12, var11)) && !var1.y(var10 - 1, var12, var4) && !var1.y(var10 + 1, var12, var11) && !var1.y(var10, var12, var11 - 1) && !var1.y(var10, var12, var11 + 1)) {
                                        int var16 = var7 + var5.nextInt(5) / 4;

                                        if (var16 > 15) {
                                            var16 = 15;
                                        }

                                        // CanaryMod Ignite hook. Warning, that thing spams like mad! 
                                        IgnitionHook hook = new IgnitionHook(var1.getCanaryWorld().getBlockAt(var2, var3, var4), 3);

                                        Canary.hooks().callHook(hook);
                                        if (!hook.isCanceled()) {
                                            var1.b(var10, var12, var11, this.bO, var16);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private void a(OWorld var1, int var2, int var3, int var4, int var5, Random var6, int var7) {
        int var8 = this.b[var1.a(var2, var3, var4)];

        if (var6.nextInt(var5) < var8) {
            boolean var9 = var1.a(var2, var3, var4) == OBlock.am.bO;

            if (var6.nextInt(var7 + 10) < 5 && !var1.y(var2, var3, var4)) {
                int var10 = var7 + var6.nextInt(5) / 4;

                if (var10 > 15) {
                    var10 = 15;
                }

                var1.b(var2, var3, var4, this.bO, var10);
            } else {
                var1.e(var2, var3, var4, 0);
            }

            if (var9) {
                OBlock.am.c(var1, var2, var3, var4, 1);
            }
        }

    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        return this.c((OIBlockAccess) var1, var2 + 1, var3, var4) ? true : (this.c((OIBlockAccess) var1, var2 - 1, var3, var4) ? true : (this.c((OIBlockAccess) var1, var2, var3 - 1, var4) ? true : (this.c((OIBlockAccess) var1, var2, var3 + 1, var4) ? true : (this.c((OIBlockAccess) var1, var2, var3, var4 - 1) ? true : this.c((OIBlockAccess) var1, var2, var3, var4 + 1)))));
    }

    private int h(OWorld var1, int var2, int var3, int var4) {
        byte var5 = 0;

        if (!var1.g(var2, var3, var4)) {
            return 0;
        } else {
            int var6 = this.f(var1, var2 + 1, var3, var4, var5);

            var6 = this.f(var1, var2 - 1, var3, var4, var6);
            var6 = this.f(var1, var2, var3 - 1, var4, var6);
            var6 = this.f(var1, var2, var3 + 1, var4, var6);
            var6 = this.f(var1, var2, var3, var4 - 1, var6);
            var6 = this.f(var1, var2, var3, var4 + 1, var6);
            return var6;
        }
    }

    @Override
    public boolean E_() {
        return false;
    }

    public boolean c(OIBlockAccess var1, int var2, int var3, int var4) {
        return this.a[var1.a(var2, var3, var4)] > 0;
    }

    public int f(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = this.a[var1.a(var2, var3, var4)];

        return var6 > var5 ? var6 : var5;
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var1.e(var2, var3 - 1, var4) || this.g(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.e(var2, var3 - 1, var4) && !this.g(var1, var2, var3, var4)) {
            var1.e(var2, var3, var4, 0);
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (var1.t.g > 0 || var1.a(var2, var3 - 1, var4) != OBlock.ap.bO || !OBlock.be.b_(var1, var2, var3, var4)) {
            if (!var1.e(var2, var3 - 1, var4) && !this.g(var1, var2, var3, var4)) {
                var1.e(var2, var3, var4, 0);
            } else {
                var1.c(var2, var3, var4, this.bO, this.d() + var1.r.nextInt(10));
            }
        }
    }
}
