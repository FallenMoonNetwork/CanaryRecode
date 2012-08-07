package net.minecraft.server;


import java.util.Random;


public class OMathHelper {

    private static float[] a = new float[65536];

    public OMathHelper() {
        super();
    }

    public static final float a(float var0) {
        return a[(int) (var0 * 10430.378F) & '\uffff'];
    }

    public static final float b(float var0) {
        return a[(int) (var0 * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static final float c(float var0) {
        return (float) Math.sqrt(var0);
    }

    public static final float a(double var0) {
        return (float) Math.sqrt(var0);
    }

    public static int d(float var0) {
        int var1 = (int) var0;

        return var0 < var1 ? var1 - 1 : var1;
    }

    public static int b(double var0) {
        int var2 = (int) var0;

        return var0 < var2 ? var2 - 1 : var2;
    }

    public static long c(double var0) {
        long var2 = (long) var0;

        return var0 < var2 ? var2 - 1L : var2;
    }

    public static float e(float var0) {
        return var0 >= 0.0F ? var0 : -var0;
    }

    public static int a(int var0) {
        return var0 >= 0 ? var0 : -var0;
    }

    public static int a(int var0, int var1, int var2) {
        return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
    }

    public static double a(double var0, double var2) {
        if (var0 < 0.0D) {
            var0 = -var0;
        }

        if (var2 < 0.0D) {
            var2 = -var2;
        }

        return var0 > var2 ? var0 : var2;
    }

    public static int a(Random var0, int var1, int var2) {
        return var1 >= var2 ? var1 : var0.nextInt(var2 - var1 + 1) + var1;
    }

    static {
        for (int var0 = 0; var0 < 65536; ++var0) {
            a[var0] = (float) Math.sin(var0 * 3.141592653589793D * 2.0D / 65536.0D);
        }

    }
}
