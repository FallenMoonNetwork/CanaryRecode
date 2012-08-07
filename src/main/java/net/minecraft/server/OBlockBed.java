package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockDirectional;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.ODirection;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumStatus;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockBed extends OBlockDirectional {

    public static final int[][] a = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    public OBlockBed(int var1) {
        super(var1, 134, OMaterial.m);
        this.t();
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            int var6 = var1.c(var2, var3, var4);
            if (!d(var6)) {
                int var7 = b(var6);
                var2 += a[var7][0];
                var4 += a[var7][1];
                if (var1.a(var2, var3, var4) != this.bO) {
                    return true;
                }

                var6 = var1.c(var2, var3, var4);
            }

            if (!var1.t.c()) {
                double var8 = var2 + 0.5D;
                double var10 = var3 + 0.5D;
                double var12 = var4 + 0.5D;
                var1.e(var2, var3, var4, 0);
                int var14 = b(var6);
                var2 += a[var14][0];
                var4 += a[var14][1];
                if (var1.a(var2, var3, var4) == this.bO) {
                    var1.e(var2, var3, var4, 0);
                    var8 = (var8 + var2 + 0.5D) / 2.0D;
                    var10 = (var10 + var3 + 0.5D) / 2.0D;
                    var12 = (var12 + var4 + 0.5D) / 2.0D;
                }

                var1.a((OEntity) null, (var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F), 5.0F, true);
                return true;
            } else {
                if (e(var6)) {
                    OEntityPlayer var18 = null;
                    Iterator var15 = var1.d.iterator();

                    while (var15.hasNext()) {
                        OEntityPlayer var16 = (OEntityPlayer) var15.next();
                        if (var16.Z()) {
                            OChunkCoordinates var17 = var16.F;
                            if (var17.a == var2 && var17.b == var3 && var17.c == var4) {
                                var18 = var16;
                            }
                        }
                    }

                    if (var18 != null) {
                        var5.a("tile.bed.occupied");
                        return true;
                    }

                    a(var1, var2, var3, var4, false);
                }

                OEnumStatus var19 = var5.a(var2, var3, var4);
                if (var19 == OEnumStatus.a) {
                    a(var1, var2, var3, var4, true);
                    return true;
                } else {
                    if (var19 == OEnumStatus.c) {
                        var5.a("tile.bed.noSleep");
                    } else if (var19 == OEnumStatus.f) {
                        var5.a("tile.bed.notSafe");
                    }

                    return true;
                }
            }
        }
    }

    @Override
    public int a(int var1, int var2) {
        if (var1 == 0) {
            return OBlock.x.bN;
        } else {
            int var3 = b(var2);
            int var4 = ODirection.h[var3][var1];
            return d(var2) ? (var4 == 2 ? this.bN + 2 + 16 : (var4 != 5 && var4 != 4 ? this.bN + 1 : this.bN + 1 + 16)) : (var4 == 3 ? this.bN - 1 + 16 : (var4 != 5 && var4 != 4 ? this.bN : this.bN + 16));
        }
    }

    @Override
    public int c() {
        return 14;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        this.t();
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.c(var2, var3, var4);
        int var7 = b(var6);
        if (d(var6)) {
            if (var1.a(var2 - a[var7][0], var3, var4 - a[var7][1]) != this.bO) {
                var1.e(var2, var3, var4, 0);
            }
        } else if (var1.a(var2 + a[var7][0], var3, var4 + a[var7][1]) != this.bO) {
            var1.e(var2, var3, var4, 0);
            if (!var1.F) {
                this.b(var1, var2, var3, var4, var6, 0);
            }
        }

    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return d(var1) ? 0 : OItem.aZ.bP;
    }

    private void t() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }

    public static boolean d(int var0) {
        return (var0 & 8) != 0;
    }

    public static boolean e(int var0) {
        return (var0 & 4) != 0;
    }

    public static void a(OWorld var0, int var1, int var2, int var3, boolean var4) {
        int var5 = var0.c(var1, var2, var3);
        if (var4) {
            var5 |= 4;
        } else {
            var5 &= -5;
        }

        var0.c(var1, var2, var3, var5);
    }

    public static OChunkCoordinates f(OWorld var0, int var1, int var2, int var3, int var4) {
        int var5 = var0.c(var1, var2, var3);
        int var6 = OBlockDirectional.b(var5);

        for (int var7 = 0; var7 <= 1; ++var7) {
            int var8 = var1 - a[var6][0] * var7 - 1;
            int var9 = var3 - a[var6][1] * var7 - 1;
            int var10 = var8 + 2;
            int var11 = var9 + 2;

            for (int var12 = var8; var12 <= var10; ++var12) {
                for (int var13 = var9; var13 <= var11; ++var13) {
                    if (var0.e(var12, var2 - 1, var13) && var0.g(var12, var2, var13) && var0.g(var12, var2 + 1, var13)) {
                        if (var4 <= 0) {
                            return new OChunkCoordinates(var12, var2, var13);
                        }

                        --var4;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!d(var5)) {
            super.a(var1, var2, var3, var4, var5, var6, 0);
        }

    }

    @Override
    public int g() {
        return 1;
    }

}
