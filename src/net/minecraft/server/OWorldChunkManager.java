package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeCache;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldType;

public class OWorldChunkManager {

    private OGenLayer a;
    private OGenLayer b;
    private OBiomeCache c;
    private List d;

    protected OWorldChunkManager() {
        super();
        this.c = new OBiomeCache(this);
        this.d = new ArrayList();
        this.d.add(OBiomeGenBase.f);
        this.d.add(OBiomeGenBase.c);
        this.d.add(OBiomeGenBase.g);
        this.d.add(OBiomeGenBase.u);
        this.d.add(OBiomeGenBase.t);
        this.d.add(OBiomeGenBase.w);
        this.d.add(OBiomeGenBase.x);
    }

    public OWorldChunkManager(long var1, OWorldType var3) {
        this();
        OGenLayer[] var4 = OGenLayer.a(var1, var3);
        this.a = var4[0];
        this.b = var4[1];
    }

    public OWorldChunkManager(OWorld var1) {
        this(var1.n(), var1.s().p());
    }

    public List a() {
        return this.d;
    }

    public OBiomeGenBase a(int var1, int var2) {
        return this.c.b(var1, var2);
    }

    public float[] b(float[] var1, int var2, int var3, int var4, int var5) {
        OIntCache.a();
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new float[var4 * var5];
        }

        int[] var6 = this.b.a(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7) {
            float var8 = OBiomeGenBase.a[var6[var7]].g() / 65536.0F;
            if (var8 > 1.0F) {
                var8 = 1.0F;
            }

            var1[var7] = var8;
        }

        return var1;
    }

    public float[] a(float[] var1, int var2, int var3, int var4, int var5) {
        OIntCache.a();
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new float[var4 * var5];
        }

        int[] var6 = this.b.a(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7) {
            float var8 = OBiomeGenBase.a[var6[var7]].h() / 65536.0F;
            if (var8 > 1.0F) {
                var8 = 1.0F;
            }

            var1[var7] = var8;
        }

        return var1;
    }

    public OBiomeGenBase[] a(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
        OIntCache.a();
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new OBiomeGenBase[var4 * var5];
        }

        int[] var6 = this.a.a(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7) {
            var1[var7] = OBiomeGenBase.a[var6[var7]];
        }

        return var1;
    }

    public OBiomeGenBase[] b(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
        return this.a(var1, var2, var3, var4, var5, true);
    }

    public OBiomeGenBase[] a(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5, boolean var6) {
        OIntCache.a();
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new OBiomeGenBase[var4 * var5];
        }

        if (var6 && var4 == 16 && var5 == 16 && (var2 & 15) == 0 && (var3 & 15) == 0) {
            OBiomeGenBase[] var9 = this.c.c(var2, var3);
            System.arraycopy(var9, 0, var1, 0, var4 * var5);
            return var1;
        } else {
            int[] var7 = this.b.a(var2, var3, var4, var5);

            for (int var8 = 0; var8 < var4 * var5; ++var8) {
                var1[var8] = OBiomeGenBase.a[var7[var8]];
            }

            return var1;
        }
    }

    public boolean a(int var1, int var2, int var3, List var4) {
        int var5 = var1 - var3 >> 2;
        int var6 = var2 - var3 >> 2;
        int var7 = var1 + var3 >> 2;
        int var8 = var2 + var3 >> 2;
        int var9 = var7 - var5 + 1;
        int var10 = var8 - var6 + 1;
        int[] var11 = this.a.a(var5, var6, var9, var10);

        for (int var12 = 0; var12 < var9 * var10; ++var12) {
            OBiomeGenBase var13 = OBiomeGenBase.a[var11[var12]];
            if (!var4.contains(var13)) {
                return false;
            }
        }

        return true;
    }

    public OChunkPosition a(int var1, int var2, int var3, List var4, Random var5) {
        int var6 = var1 - var3 >> 2;
        int var7 = var2 - var3 >> 2;
        int var8 = var1 + var3 >> 2;
        int var9 = var2 + var3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
        int[] var12 = this.a.a(var6, var7, var10, var11);
        OChunkPosition var13 = null;
        int var14 = 0;

        for (int var15 = 0; var15 < var12.length; ++var15) {
            int var16 = var6 + var15 % var10 << 2;
            int var17 = var7 + var15 / var10 << 2;
            OBiomeGenBase var18 = OBiomeGenBase.a[var12[var15]];
            if (var4.contains(var18) && (var13 == null || var5.nextInt(var14 + 1) == 0)) {
                var13 = new OChunkPosition(var16, 0, var17);
                ++var14;
            }
        }

        return var13;
    }

    public void b() {
        this.c.a();
    }
}
