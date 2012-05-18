package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;

public class OGenLayerRiverMix extends OGenLayer {

    private OGenLayer b;
    private OGenLayer c;

    public OGenLayerRiverMix(long var1, OGenLayer var3, OGenLayer var4) {
        super(var1);
        this.b = var3;
        this.c = var4;
    }

    public void a(long var1) {
        this.b.a(var1);
        this.c.a(var1);
        super.a(var1);
    }

    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.b.a(var1, var2, var3, var4);
        int[] var6 = this.c.a(var1, var2, var3, var4);
        int[] var7 = OIntCache.a(var3 * var4);

        for (int var8 = 0; var8 < var3 * var4; ++var8) {
            if (var5[var8] == OBiomeGenBase.b.M) {
                var7[var8] = var5[var8];
            } else if (var6[var8] >= 0) {
                if (var5[var8] == OBiomeGenBase.n.M) {
                    var7[var8] = OBiomeGenBase.m.M;
                } else if (var5[var8] != OBiomeGenBase.p.M && var5[var8] != OBiomeGenBase.q.M) {
                    var7[var8] = var6[var8];
                } else {
                    var7[var8] = OBiomeGenBase.q.M;
                }
            } else {
                var7[var8] = var5[var8];
            }
        }

        return var7;
    }
}
