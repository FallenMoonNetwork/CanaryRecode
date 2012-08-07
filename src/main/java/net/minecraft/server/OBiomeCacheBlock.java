package net.minecraft.server;


import net.minecraft.server.OBiomeCache;
import net.minecraft.server.OBiomeGenBase;


public class OBiomeCacheBlock {

    public float[] a;
    public float[] b;
    public OBiomeGenBase[] c;
    public int d;
    public int e;
    public long f;
    // $FF: synthetic field
    final OBiomeCache g;

    public OBiomeCacheBlock(OBiomeCache var1, int var2, int var3) {
        super();
        this.g = var1;
        this.a = new float[256];
        this.b = new float[256];
        this.c = new OBiomeGenBase[256];
        this.d = var2;
        this.e = var3;
        OBiomeCache.a(var1).a(this.a, var2 << 4, var3 << 4, 16, 16);
        OBiomeCache.a(var1).b(this.b, var2 << 4, var3 << 4, 16, 16);
        OBiomeCache.a(var1).a(this.c, var2 << 4, var3 << 4, 16, 16, false);
    }

    public OBiomeGenBase a(int var1, int var2) {
        return this.c[var1 & 15 | (var2 & 15) << 4];
    }
}
