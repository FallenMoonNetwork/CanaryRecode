package net.minecraft.server;

import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;

public class OGenLayerRiverInit extends OGenLayer {

    public OGenLayerRiverInit(long var1, OGenLayer var3) {
        super(var1);
        this.a = var3;
    }

    @Override
    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.a.a(var1, var2, var3, var4);
        int[] var6 = OIntCache.a(var3 * var4);

        for (int var7 = 0; var7 < var4; ++var7) {
            for (int var8 = 0; var8 < var3; ++var8) {
                this.a((var8 + var1), (var7 + var2));
                var6[var8 + var7 * var3] = var5[var8 + var7 * var3] > 0 ? this.a(2) + 2 : 0;
            }
        }

        return var6;
    }
}
