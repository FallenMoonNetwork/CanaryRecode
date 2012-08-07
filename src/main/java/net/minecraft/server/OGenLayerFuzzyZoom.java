package net.minecraft.server;


import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;


public class OGenLayerFuzzyZoom extends OGenLayer {

    public OGenLayerFuzzyZoom(long var1, OGenLayer var3) {
        super(var1);
        super.a = var3;
    }

    @Override
    public int[] a(int var1, int var2, int var3, int var4) {
        int var5 = var1 >> 1;
        int var6 = var2 >> 1;
        int var7 = (var3 >> 1) + 3;
        int var8 = (var4 >> 1) + 3;
        int[] var9 = this.a.a(var5, var6, var7, var8);
        int[] var10 = OIntCache.a(var7 * 2 * var8 * 2);
        int var11 = var7 << 1;

        int var13;

        for (int var12 = 0; var12 < var8 - 1; ++var12) {
            var13 = var12 << 1;
            int var14 = var13 * var11;
            int var15 = var9[0 + (var12 + 0) * var7];
            int var16 = var9[0 + (var12 + 1) * var7];

            for (int var17 = 0; var17 < var7 - 1; ++var17) {
                this.a((long) (var17 + var5 << 1), (long) (var12 + var6 << 1));
                int var18 = var9[var17 + 1 + (var12 + 0) * var7];
                int var19 = var9[var17 + 1 + (var12 + 1) * var7];

                var10[var14] = var15;
                var10[var14++ + var11] = this.a(var15, var16);
                var10[var14] = this.a(var15, var18);
                var10[var14++ + var11] = this.b(var15, var18, var16, var19);
                var15 = var18;
                var16 = var19;
            }
        }

        int[] var20 = OIntCache.a(var3 * var4);

        for (var13 = 0; var13 < var4; ++var13) {
            System.arraycopy(var10, (var13 + (var2 & 1)) * (var7 << 1) + (var1 & 1), var20, var13 * var3, var3);
        }

        return var20;
    }

    protected int a(int var1, int var2) {
        return this.a(2) == 0 ? var1 : var2;
    }

    protected int b(int var1, int var2, int var3, int var4) {
        int var5 = this.a(4);

        return var5 == 0 ? var1 : (var5 == 1 ? var2 : (var5 == 2 ? var3 : var4));
    }
}
