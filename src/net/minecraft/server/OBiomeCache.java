package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OBiomeCacheBlock;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OLongHashMap;
import net.minecraft.server.OWorldChunkManager;

public class OBiomeCache {

    private final OWorldChunkManager a;
    private long b = 0L;
    private OLongHashMap c = new OLongHashMap();
    private List<OBiomeCacheBlock> d = new ArrayList<OBiomeCacheBlock>();

    public OBiomeCache(OWorldChunkManager var1) {
        super();
        this.a = var1;
    }

    public OBiomeCacheBlock a(int var1, int var2) {
        var1 >>= 4;
        var2 >>= 4;
        long var3 = var1 & 4294967295L | (var2 & 4294967295L) << 32;
        OBiomeCacheBlock var5 = (OBiomeCacheBlock) this.c.a(var3);
        if (var5 == null) {
            var5 = new OBiomeCacheBlock(this, var1, var2);
            this.c.a(var3, var5);
            this.d.add(var5);
        }

        var5.f = System.currentTimeMillis();
        return var5;
    }

    public OBiomeGenBase b(int var1, int var2) {
        return this.a(var1, var2).a(var1, var2);
    }

    public void a() {
        long var1 = System.currentTimeMillis();
        long var3 = var1 - this.b;
        if (var3 > 7500L || var3 < 0L) {
            this.b = var1;

            for (int var5 = 0; var5 < this.d.size(); ++var5) {
                OBiomeCacheBlock var6 = (OBiomeCacheBlock) this.d.get(var5);
                long var7 = var1 - var6.f;
                if (var7 > 30000L || var7 < 0L) {
                    this.d.remove(var5--);
                    long var9 = var6.d & 4294967295L | (var6.e & 4294967295L) << 32;
                    this.c.d(var9);
                }
            }
        }

    }

    public OBiomeGenBase[] c(int var1, int var2) {
        return this.a(var1, var2).c;
    }

    // $FF: synthetic method
    static OWorldChunkManager a(OBiomeCache var0) {
        return var0.a;
    }
}
