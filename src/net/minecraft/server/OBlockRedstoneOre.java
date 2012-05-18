package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockRedstoneOre extends OBlock {

    private boolean a;

    public OBlockRedstoneOre(int var1, int var2, boolean var3) {
        super(var1, var2, OMaterial.e);
        if (var3) {
            this.a(true);
        }

        this.a = var3;
    }

    public int d() {
        return 30;
    }

    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.g(var1, var2, var3, var4);
        super.b(var1, var2, var3, var4, var5);
    }

    public void b(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        this.g(var1, var2, var3, var4);
        super.b(var1, var2, var3, var4, var5);
    }

    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.g(var1, var2, var3, var4);
        return super.a(var1, var2, var3, var4, var5);
    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        this.h(var1, var2, var3, var4);
        if (this.bO == OBlock.aN.bO) {
            var1.e(var2, var3, var4, OBlock.aO.bO);
        }

    }

    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (this.bO == OBlock.aO.bO) {
            var1.e(var2, var3, var4, OBlock.aN.bO);
        }

    }

    public int a(int var1, Random var2, int var3) {
        return OItem.aB.bP;
    }

    public int a(int var1, Random var2) {
        return this.a(var2) + var2.nextInt(var1 + 1);
    }

    public int a(Random var1) {
        return 4 + var1.nextInt(2);
    }

    private void h(OWorld var1, int var2, int var3, int var4) {
        Random var5 = var1.r;
        double var6 = 0.0625D;

        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (var2 + var5.nextFloat());
            double var11 = (var3 + var5.nextFloat());
            double var13 = (var4 + var5.nextFloat());
            if (var8 == 0 && !var1.r(var2, var3 + 1, var4)) {
                var11 = (var3 + 1) + var6;
            }

            if (var8 == 1 && !var1.r(var2, var3 - 1, var4)) {
                var11 = (var3 + 0) - var6;
            }

            if (var8 == 2 && !var1.r(var2, var3, var4 + 1)) {
                var13 = (var4 + 1) + var6;
            }

            if (var8 == 3 && !var1.r(var2, var3, var4 - 1)) {
                var13 = (var4 + 0) - var6;
            }

            if (var8 == 4 && !var1.r(var2 + 1, var3, var4)) {
                var9 = (var2 + 1) + var6;
            }

            if (var8 == 5 && !var1.r(var2 - 1, var3, var4)) {
                var9 = (var2 + 0) - var6;
            }

            if (var9 < var2 || var9 > (var2 + 1) || var11 < 0.0D || var11 > (var3 + 1) || var13 < var4 || var13 > (var4 + 1)) {
                var1.a("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected OItemStack a_(int var1) {
        return new OItemStack(OBlock.aN);
    }
}
