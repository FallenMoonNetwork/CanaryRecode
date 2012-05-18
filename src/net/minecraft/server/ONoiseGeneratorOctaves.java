package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONoiseGenerator;
import net.minecraft.server.ONoiseGeneratorPerlin;

public class ONoiseGeneratorOctaves extends ONoiseGenerator {

    private ONoiseGeneratorPerlin[] a;
    private int b;

    public ONoiseGeneratorOctaves(Random var1, int var2) {
        super();
        this.b = var2;
        this.a = new ONoiseGeneratorPerlin[var2];

        for (int var3 = 0; var3 < var2; ++var3) {
            this.a[var3] = new ONoiseGeneratorPerlin(var1);
        }

    }

    public double[] a(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7, double var8, double var10, double var12) {
        if (var1 == null) {
            var1 = new double[var5 * var6 * var7];
        } else {
            for (int var14 = 0; var14 < var1.length; ++var14) {
                var1[var14] = 0.0D;
            }
        }

        double var15 = 1.0D;

        for (int var17 = 0; var17 < this.b; ++var17) {
            double var18 = var2 * var15 * var8;
            double var20 = var3 * var15 * var10;
            double var22 = var4 * var15 * var12;
            long var24 = OMathHelper.c(var18);
            long var26 = OMathHelper.c(var22);
            var18 -= var24;
            var22 -= var26;
            var24 %= 16777216L;
            var26 %= 16777216L;
            var18 += var24;
            var22 += var26;
            this.a[var17].a(var1, var18, var20, var22, var5, var6, var7, var8 * var15, var10 * var15, var12 * var15, var15);
            var15 /= 2.0D;
        }

        return var1;
    }

    public double[] a(double[] var1, int var2, int var3, int var4, int var5, double var6, double var8, double var10) {
        return this.a(var1, var2, 10, var3, var4, 1, var5, var6, 1.0D, var8);
    }
}
