package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockDirectional;
import net.minecraft.server.OEntityIronGolem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySnowman;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;


public class OBlockPumpkin extends OBlockDirectional {

    private boolean a;

    protected OBlockPumpkin(int var1, int var2, boolean var3) {
        super(var1, OMaterial.z);
        this.bN = var2;
        this.a(true);
        this.a = var3;
    }

    @Override
    public int a(int var1, int var2) {
        if (var1 == 1) {
            return this.bN;
        } else if (var1 == 0) {
            return this.bN;
        } else {
            int var3 = this.bN + 1 + 16;

            if (this.a) {
                ++var3;
            }

            return var2 == 2 && var1 == 2 ? var3 : (var2 == 3 && var1 == 5 ? var3 : (var2 == 0 && var1 == 3 ? var3 : (var2 == 1 && var1 == 4 ? var3 : this.bN + 16)));
        }
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN : (var1 == 0 ? this.bN : (var1 == 3 ? this.bN + 1 + 16 : this.bN + 16));
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        if (var1.a(var2, var3 - 1, var4) == OBlock.aU.bO && var1.a(var2, var3 - 2, var4) == OBlock.aU.bO) {
            if (!var1.F) {
                var1.b(var2, var3, var4, 0);
                var1.b(var2, var3 - 1, var4, 0);
                var1.b(var2, var3 - 2, var4, 0);
                OEntitySnowman var9 = new OEntitySnowman(var1);

                var9.c(var2 + 0.5D, var3 - 1.95D, var4 + 0.5D, 0.0F, 0.0F);
                var1.b(var9);
                var1.f(var2, var3, var4, 0);
                var1.f(var2, var3 - 1, var4, 0);
                var1.f(var2, var3 - 2, var4, 0);
            }

            for (int var10 = 0; var10 < 120; ++var10) {
                var1.a("snowshovel", var2 + var1.r.nextDouble(), (var3 - 2) + var1.r.nextDouble() * 2.5D, var4 + var1.r.nextDouble(), 0.0D, 0.0D, 0.0D);
            }
        } else if (var1.a(var2, var3 - 1, var4) == OBlock.ai.bO && var1.a(var2, var3 - 2, var4) == OBlock.ai.bO) {
            boolean var5 = var1.a(var2 - 1, var3 - 1, var4) == OBlock.ai.bO && var1.a(var2 + 1, var3 - 1, var4) == OBlock.ai.bO;
            boolean var6 = var1.a(var2, var3 - 1, var4 - 1) == OBlock.ai.bO && var1.a(var2, var3 - 1, var4 + 1) == OBlock.ai.bO;

            if (var5 || var6) {
                var1.b(var2, var3, var4, 0);
                var1.b(var2, var3 - 1, var4, 0);
                var1.b(var2, var3 - 2, var4, 0);
                if (var5) {
                    var1.b(var2 - 1, var3 - 1, var4, 0);
                    var1.b(var2 + 1, var3 - 1, var4, 0);
                } else {
                    var1.b(var2, var3 - 1, var4 - 1, 0);
                    var1.b(var2, var3 - 1, var4 + 1, 0);
                }

                OEntityIronGolem var7 = new OEntityIronGolem(var1);

                var7.b(true);
                var7.c(var2 + 0.5D, var3 - 1.95D, var4 + 0.5D, 0.0F, 0.0F);
                var1.b(var7);

                for (int var8 = 0; var8 < 120; ++var8) {
                    var1.a("snowballpoof", var2 + var1.r.nextDouble(), (var3 - 2) + var1.r.nextDouble() * 3.9D, var4 + var1.r.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                var1.f(var2, var3, var4, 0);
                var1.f(var2, var3 - 1, var4, 0);
                var1.f(var2, var3 - 2, var4, 0);
                if (var5) {
                    var1.f(var2 - 1, var3 - 1, var4, 0);
                    var1.f(var2 + 1, var3 - 1, var4, 0);
                } else {
                    var1.f(var2, var3 - 1, var4 - 1, 0);
                    var1.f(var2, var3 - 1, var4 + 1, 0);
                }
            }
        }

    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.a(var2, var3, var4);

        return (var5 == 0 || OBlock.m[var5].cd.i()) && var1.e(var2, var3 - 1, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = OMathHelper.b((var5.bs * 4.0F / 360.0F) + 2.5D) & 3;

        var1.c(var2, var3, var4, var6);
    }
}
