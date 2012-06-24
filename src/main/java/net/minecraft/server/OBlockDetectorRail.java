package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlockRail;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityMinecart;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OWorld;

public class OBlockDetectorRail extends OBlockRail {

    public OBlockDetectorRail(int var1, int var2) {
        super(var1, var2, true);
        this.a(true);
    }

    @Override
    public int d() {
        return 20;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        if (!var1.F) {
            int var6 = var1.c(var2, var3, var4);
            if ((var6 & 8) == 0) {
                this.f(var1, var2, var3, var4, var6);
            }
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (!var1.F) {
            int var6 = var1.c(var2, var3, var4);
            if ((var6 & 8) != 0) {
                this.f(var1, var2, var3, var4, var6);
            }
        }
    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        return (var1.c(var2, var3, var4) & 8) != 0;
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return (var1.c(var2, var3, var4) & 8) == 0 ? false : var5 == 1;
    }

    private void f(OWorld var1, int var2, int var3, int var4, int var5) {
        boolean var6 = (var5 & 8) != 0;
        boolean var7 = false;
        float var8 = 0.125F;
        List var9 = var1.a(OEntityMinecart.class, OAxisAlignedBB.b((var2 + var8), var3, (var4 + var8), ((var2 + 1) - var8), ((var3 + 1) - var8), ((var4 + 1) - var8)));
        if (var9.size() > 0) {
            var7 = true;
        }

        if (var7 && !var6) {
            var1.c(var2, var3, var4, var5 | 8);
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.b(var2, var3, var4, var2, var3, var4);
        }

        if (!var7 && var6) {
            var1.c(var2, var3, var4, var5 & 7);
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.b(var2, var3, var4, var2, var3, var4);
        }

        if (var7) {
            var1.c(var2, var3, var4, this.bO, this.d());
        }

    }
}
