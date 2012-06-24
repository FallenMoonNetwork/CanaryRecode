package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenBigMushroom;

public class OBlockMushroom extends OBlockFlower {

    protected OBlockMushroom(int var1, int var2) {
        super(var1, var2);
        float var3 = 0.2F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.a(true);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (var5.nextInt(25) == 0) {
            byte var6 = 4;
            int var7 = 5;

            int var8;
            int var9;
            int var10;
            for (var8 = var2 - var6; var8 <= var2 + var6; ++var8) {
                for (var9 = var4 - var6; var9 <= var4 + var6; ++var9) {
                    for (var10 = var3 - 1; var10 <= var3 + 1; ++var10) {
                        if (var1.a(var8, var10, var9) == this.bO) {
                            --var7;
                            if (var7 <= 0) {
                                return;
                            }
                        }
                    }
                }
            }

            var8 = var2 + var5.nextInt(3) - 1;
            var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
            var10 = var4 + var5.nextInt(3) - 1;

            for (int var11 = 0; var11 < 4; ++var11) {
                if (var1.g(var8, var9, var10) && this.f(var1, var8, var9, var10)) {
                    var2 = var8;
                    var3 = var9;
                    var4 = var10;
                }

                var8 = var2 + var5.nextInt(3) - 1;
                var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
                var10 = var4 + var5.nextInt(3) - 1;
            }

            if (var1.g(var8, var9, var10) && this.f(var1, var8, var9, var10)) {
                var1.e(var8, var9, var10, this.bO);
            }
        }

    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return super.c(var1, var2, var3, var4) && this.f(var1, var2, var3, var4);
    }

    @Override
    protected boolean d(int var1) {
        return OBlock.n[var1];
    }

    @Override
    public boolean f(OWorld var1, int var2, int var3, int var4) {
        if (var3 >= 0 && var3 < 256) {
            int var5 = var1.a(var2, var3 - 1, var4);
            return var5 == OBlock.by.bO || var1.m(var2, var3, var4) < 13 && this.d(var5);
        } else {
            return false;
        }
    }

    public boolean b(OWorld var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.c(var2, var3, var4);
        var1.b(var2, var3, var4, 0);
        OWorldGenBigMushroom var7 = null;
        if (this.bO == OBlock.af.bO) {
            var7 = new OWorldGenBigMushroom(0);
        } else if (this.bO == OBlock.ag.bO) {
            var7 = new OWorldGenBigMushroom(1);
        }

        if (var7 != null && var7.a(var1, var5, var2, var3, var4)) {
            return true;
        } else {
            var1.a(var2, var3, var4, this.bO, var6);
            return false;
        }
    }
}
