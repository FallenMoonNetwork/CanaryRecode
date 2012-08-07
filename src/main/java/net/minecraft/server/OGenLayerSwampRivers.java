package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;

public class OGenLayerSwampRivers extends OGenLayer {

    public OGenLayerSwampRivers(long var1, OGenLayer var3) {
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
                if (var9 == OBiomeGenBase.h.M && this.a(6) == 0) {
                    var6[var8 + var7 * var3] = OBiomeGenBase.i.M;
                } else if ((var9 == OBiomeGenBase.w.M || var9 == OBiomeGenBase.x.M) && this.a(8) == 0) {
                    var6[var8 + var7 * var3] = OBiomeGenBase.i.M;
                } else {
                    var6[var8 + var7 * var3] = var9;
                }
            }
        }

        return var6;
    }
}
