package net.minecraft.server;


import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.hook.world.IgnitionHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFluid;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockStationary extends OBlockFluid {

    protected OBlockStationary(int var1, OMaterial var2) {
        super(var1, var2);
        this.a(false);
        if (var2 == OMaterial.h) {
            this.a(true);
        }

    }

    @Override
    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return this.cd != OMaterial.h;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        super.a(var1, var2, var3, var4, var5);
        if (var1.a(var2, var3, var4) == this.bO) {
            this.i(var1, var2, var3, var4);
        }

    }

    private void i(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);

        var1.o = true;
        var1.a(var2, var3, var4, this.bO - 1, var5);
        var1.b(var2, var3, var4, var2, var3, var4);
        var1.c(var2, var3, var4, this.bO - 1, this.d());
        var1.o = false;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (this.cd == OMaterial.h) {
            int var6 = var5.nextInt(3);

            // CanaryMod allow prevention of firespread
            IgnitionHook hook = new IgnitionHook(var1.getCanaryWorld().getBlockAt(var2, var3, var4), 1);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
            int var7;
            int var8;

            for (var7 = 0; var7 < var6; ++var7) {
                var2 += var5.nextInt(3) - 1;
                ++var3;
                var4 += var5.nextInt(3) - 1;
                var8 = var1.a(var2, var3, var4);
                if (var8 == 0) {
                    if (this.j(var1, var2 - 1, var3, var4) || this.j(var1, var2 + 1, var3, var4) || this.j(var1, var2, var3, var4 - 1) || this.j(var1, var2, var3, var4 + 1) || this.j(var1, var2, var3 - 1, var4) || this.j(var1, var2, var3 + 1, var4)) {
                        var1.e(var2, var3, var4, OBlock.ar.bO);
                        return;
                    }
                } else if (OBlock.m[var8].cd.c()) {
                    return;
                }
            }

            if (var6 == 0) {
                var7 = var2;
                var8 = var4;

                for (int var9 = 0; var9 < 3; ++var9) {
                    var2 = var7 + var5.nextInt(3) - 1;
                    var4 = var8 + var5.nextInt(3) - 1;
                    if (var1.g(var2, var3 + 1, var4) && this.j(var1, var2, var3, var4)) {
                        var1.e(var2, var3 + 1, var4, OBlock.ar.bO);
                    }
                }
            }
        }

    }

    private boolean j(OWorld var1, int var2, int var3, int var4) {
        return var1.d(var2, var3, var4).g();
    }
}
