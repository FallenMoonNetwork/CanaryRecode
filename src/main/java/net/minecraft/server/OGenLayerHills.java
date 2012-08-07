package net.minecraft.server;


import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;


public class OGenLayerHills extends OGenLayer {

    public OGenLayerHills(long var1, OGenLayer var3) {
        super(var1);
        this.a = var3;
    }

    @Override
    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.a.a(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
        int[] var6 = OIntCache.a(var3 * var4);

        for (int var7 = 0; var7 < var4; ++var7) {
            for (int var8 = 0; var8 < var3; ++var8) {
                this.a((var8 + var1), (var7 + var2));
                int var9 = var5[var8 + 1 + (var7 + 1) * (var3 + 2)];

                if (this.a(3) == 0) {
                    int var10 = var9;

                    if (var9 == OBiomeGenBase.d.M) {
                        var10 = OBiomeGenBase.s.M;
                    } else if (var9 == OBiomeGenBase.f.M) {
                        var10 = OBiomeGenBase.t.M;
                    } else if (var9 == OBiomeGenBase.g.M) {
                        var10 = OBiomeGenBase.u.M;
                    } else if (var9 == OBiomeGenBase.c.M) {
                        var10 = OBiomeGenBase.f.M;
                    } else if (var9 == OBiomeGenBase.n.M) {
                        var10 = OBiomeGenBase.o.M;
                    } else if (var9 == OBiomeGenBase.w.M) {
                        var10 = OBiomeGenBase.x.M;
                    }

                    if (var10 != var9) {
                        int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
                        int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
                        int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
                        int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];

                        if (var11 == var9 && var12 == var9 && var13 == var9 && var14 == var9) {
                            var6[var8 + var7 * var3] = var10;
                        } else {
                            var6[var8 + var7 * var3] = var9;
                        }
                    } else {
                        var6[var8 + var7 * var3] = var9;
                    }
                } else {
                    var6[var8 + var7 * var3] = var9;
                }
            }
        }

        return var6;
    }
}
