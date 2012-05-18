package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;
import net.minecraft.server.OWorldType;

public class OGenLayerBiome extends OGenLayer {

    private OBiomeGenBase[] b;

    public OGenLayerBiome(long var1, OGenLayer var3, OWorldType var4) {
        super(var1);
        this.b = new OBiomeGenBase[] { OBiomeGenBase.d, OBiomeGenBase.f, OBiomeGenBase.e, OBiomeGenBase.h, OBiomeGenBase.c, OBiomeGenBase.g, OBiomeGenBase.w };
        this.a = var3;
        if (var4 == OWorldType.d) {
            this.b = new OBiomeGenBase[] { OBiomeGenBase.d, OBiomeGenBase.f, OBiomeGenBase.e, OBiomeGenBase.h, OBiomeGenBase.c, OBiomeGenBase.g };
        }

    }

    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.a.a(var1, var2, var3, var4);
        int[] var6 = OIntCache.a(var3 * var4);

        for (int var7 = 0; var7 < var4; ++var7) {
            for (int var8 = 0; var8 < var3; ++var8) {
                this.a((var8 + var1), (var7 + var2));
                int var9 = var5[var8 + var7 * var3];
                if (var9 == 0) {
                    var6[var8 + var7 * var3] = 0;
                } else if (var9 == OBiomeGenBase.p.M) {
                    var6[var8 + var7 * var3] = var9;
                } else if (var9 == 1) {
                    var6[var8 + var7 * var3] = this.b[this.a(this.b.length)].M;
                } else {
                    var6[var8 + var7 * var3] = OBiomeGenBase.n.M;
                }
            }
        }

        return var6;
    }
}
