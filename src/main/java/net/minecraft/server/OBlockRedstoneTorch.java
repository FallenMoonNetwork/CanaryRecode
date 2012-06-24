package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockTorch;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.ORedstoneUpdateInfo;
import net.minecraft.server.OWorld;

public class OBlockRedstoneTorch extends OBlockTorch {

    private boolean a = false;
    private static List b = new ArrayList();

    @Override
    public int a(int var1, int var2) {
        return var1 == 1 ? OBlock.av.a(var1, var2) : super.a(var1, var2);
    }

    private boolean a(OWorld var1, int var2, int var3, int var4, boolean var5) {
        if (var5) {
            b.add(new ORedstoneUpdateInfo(var2, var3, var4, var1.o()));
        }

        int var6 = 0;

        for (int var7 = 0; var7 < b.size(); ++var7) {
            ORedstoneUpdateInfo var8 = (ORedstoneUpdateInfo) b.get(var7);
            if (var8.a == var2 && var8.b == var3 && var8.c == var4) {
                ++var6;
                if (var6 >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected OBlockRedstoneTorch(int var1, int var2, boolean var3) {
        super(var1, var2);
        this.a = var3;
        this.a(true);
    }

    @Override
    public int d() {
        return 2;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (var1.c(var2, var3, var4) == 0) {
            super.a(var1, var2, var3, var4);
        }

        if (this.a) {
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2, var3 + 1, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
        }

    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        if (this.a) {
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2, var3 + 1, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
        }

    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (!this.a) {
            return false;
        } else {
            int var6 = var1.c(var2, var3, var4);
            return var6 == 5 && var5 == 1 ? false : (var6 == 3 && var5 == 3 ? false : (var6 == 4 && var5 == 2 ? false : (var6 == 1 && var5 == 5 ? false : var6 != 2 || var5 != 4)));
        }
    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        return var5 == 5 && var1.j(var2, var3 - 1, var4, 0) ? true : (var5 == 3 && var1.j(var2, var3, var4 - 1, 2) ? true : (var5 == 4 && var1.j(var2, var3, var4 + 1, 3) ? true : (var5 == 1 && var1.j(var2 - 1, var3, var4, 4) ? true : var5 == 2 && var1.j(var2 + 1, var3, var4, 5))));
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        boolean var6 = this.g(var1, var2, var3, var4);

        while (b.size() > 0 && var1.o() - ((ORedstoneUpdateInfo) b.get(0)).d > 60L) {
            b.remove(0);
        }

        if (this.a) {
            if (var6) {
                var1.b(var2, var3, var4, OBlock.aP.bO, var1.c(var2, var3, var4));
                if (this.a(var1, var2, var3, var4, true)) {
                    var1.a((var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.r.nextFloat() - var1.r.nextFloat()) * 0.8F);

                    for (int var7 = 0; var7 < 5; ++var7) {
                        double var8 = var2 + var5.nextDouble() * 0.6D + 0.2D;
                        double var10 = var3 + var5.nextDouble() * 0.6D + 0.2D;
                        double var12 = var4 + var5.nextDouble() * 0.6D + 0.2D;
                        var1.a("smoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else if (!var6 && !this.a(var1, var2, var3, var4, false)) {
            var1.b(var2, var3, var4, OBlock.aQ.bO, var1.c(var2, var3, var4));
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        super.a(var1, var2, var3, var4, var5);
        var1.c(var2, var3, var4, this.bO, this.d());
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return var5 == 0 ? this.a((OIBlockAccess) var1, var2, var3, var4, var5) : false;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.aQ.bO;
    }

    @Override
    public boolean e() {
        return true;
    }

}
