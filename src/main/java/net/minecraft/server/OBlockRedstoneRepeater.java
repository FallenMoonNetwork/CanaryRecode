package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockDirectional;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OBlockRedstoneRepeater extends OBlockDirectional {

    public static final double[] a = new double[] { -0.0625D, 0.0625D, 0.1875D, 0.3125D };
    private static final int[] b = new int[] { 1, 2, 3, 4 };
    private final boolean c;

    protected OBlockRedstoneRepeater(int var1, boolean var2) {
        super(var1, 6, OMaterial.p);
        this.c = var2;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return !var1.e(var2, var3 - 1, var4) ? false : super.c(var1, var2, var3, var4);
    }

    @Override
    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return !var1.e(var2, var3 - 1, var4) ? false : super.f(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.c(var2, var3, var4);
        boolean var7 = this.f(var1, var2, var3, var4, var6);
        if (this.c && !var7) {
            var1.b(var2, var3, var4, OBlock.bh.bO, var6);
        } else if (!this.c) {
            var1.b(var2, var3, var4, OBlock.bi.bO, var6);
            if (!var7) {
                int var8 = (var6 & 12) >> 2;
                var1.c(var2, var3, var4, OBlock.bi.bO, b[var8] * 2);
            }
        }

    }

    @Override
    public int a(int var1, int var2) {
        return var1 == 0 ? (this.c ? 99 : 115) : (var1 == 1 ? (this.c ? 147 : 131) : 5);
    }

    @Override
    public int c() {
        return 15;
    }

    @Override
    public int a(int var1) {
        return this.a(var1, 0);
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return this.a((OIBlockAccess) var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (!this.c) {
            return false;
        } else {
            int var6 = b(var1.c(var2, var3, var4));
            return var6 == 0 && var5 == 3 ? true : (var6 == 1 && var5 == 4 ? true : (var6 == 2 && var5 == 2 ? true : var6 == 3 && var5 == 5));
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!this.f(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2, var3 + 1, var4, this.bO);
        } else {
            int var6 = var1.c(var2, var3, var4);
            boolean var7 = this.f(var1, var2, var3, var4, var6);
            int var8 = (var6 & 12) >> 2;
            if (this.c && !var7) {
                var1.c(var2, var3, var4, this.bO, b[var8] * 2);
            } else if (!this.c && var7) {
                var1.c(var2, var3, var4, this.bO, b[var8] * 2);
            }

        }
    }

    private boolean f(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = b(var5);
        switch (var6) {
        case 0:
            return var1.j(var2, var3, var4 + 1, 3) || var1.a(var2, var3, var4 + 1) == OBlock.av.bO && var1.c(var2, var3, var4 + 1) > 0;
        case 1:
            return var1.j(var2 - 1, var3, var4, 4) || var1.a(var2 - 1, var3, var4) == OBlock.av.bO && var1.c(var2 - 1, var3, var4) > 0;
        case 2:
            return var1.j(var2, var3, var4 - 1, 2) || var1.a(var2, var3, var4 - 1) == OBlock.av.bO && var1.c(var2, var3, var4 - 1) > 0;
        case 3:
            return var1.j(var2 + 1, var3, var4, 5) || var1.a(var2 + 1, var3, var4) == OBlock.av.bO && var1.c(var2 + 1, var3, var4) > 0;
        default:
            return false;
        }
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        int var6 = var1.c(var2, var3, var4);
        int var7 = (var6 & 12) >> 2;
        var7 = var7 + 1 << 2 & 12;
        var1.c(var2, var3, var4, var7 | var6 & 3);
        return true;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = ((OMathHelper.b((var5.bs * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        var1.c(var2, var3, var4, var6);
        boolean var7 = this.f(var1, var2, var3, var4, var6);
        if (var7) {
            var1.c(var2, var3, var4, this.bO, 1);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        var1.h(var2 + 1, var3, var4, this.bO);
        var1.h(var2 - 1, var3, var4, this.bO);
        var1.h(var2, var3, var4 + 1, this.bO);
        var1.h(var2, var3, var4 - 1, this.bO);
        var1.h(var2, var3 - 1, var4, this.bO);
        var1.h(var2, var3 + 1, var4, this.bO);
    }

    @Override
    public void c(OWorld var1, int var2, int var3, int var4, int var5) {
        if (this.c) {
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2, var3 + 1, var4, this.bO);
        }

        super.c(var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.ba.bP;
    }

}
