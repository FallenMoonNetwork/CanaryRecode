package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.config.Configuration;

public class WorldChunkManager {

    private GenLayer d;
    private GenLayer e;
    private BiomeCache f;
    private List g;
    private String world_name; // CanaryMod: Need a name to access the configuration

    protected WorldChunkManager() {
        this.f = new BiomeCache(this);
        this.g = new ArrayList();
        this.g.add(BiomeGenBase.f);
        this.g.add(BiomeGenBase.c);
        this.g.add(BiomeGenBase.g);
        this.g.add(BiomeGenBase.u);
        this.g.add(BiomeGenBase.t);
        this.g.add(BiomeGenBase.w);
        this.g.add(BiomeGenBase.x);
    }

    public WorldChunkManager(long i0, WorldType worldtype) {
        this();
        GenLayer[] agenlayer = GenLayer.a(i0, worldtype);

        this.d = agenlayer[0];
        this.e = agenlayer[1];
    }

    public WorldChunkManager(World world) {
        this(world.H(), world.N().u());
        this.world_name = world.N().k() + "_" + world.t.canaryDimensionType.getName().toUpperCase();
    }

    public List a() {
        return this.g;
    }

    public BiomeGenBase a(int i0, int i1) {
        return this.f.b(i0, i1);
    }

    public float[] a(float[] afloat, int i0, int i1, int i2, int i3) {
        IntCache.a();
        if (afloat == null || afloat.length < i2 * i3) {
            afloat = new float[i2 * i3];
        }

        int[] aint = this.e.a(i0, i1, i2, i3);

        for (int i4 = 0; i4 < i2 * i3; ++i4) {
            float f0 = (float) BiomeGenBase.a[aint[i4]].g() / 65536.0F;

            if (f0 > 1.0F) {
                f0 = 1.0F;
            }

            afloat[i4] = f0;
        }

        return afloat;
    }

    public float[] b(float[] afloat, int i0, int i1, int i2, int i3) {
        IntCache.a();
        if (afloat == null || afloat.length < i2 * i3) {
            afloat = new float[i2 * i3];
        }

        int[] aint = this.e.a(i0, i1, i2, i3);

        for (int i4 = 0; i4 < i2 * i3; ++i4) {
            float f0 = (float) BiomeGenBase.a[aint[i4]].h() / 65536.0F;

            if (f0 > 1.0F) {
                f0 = 1.0F;
            }

            afloat[i4] = f0;
        }

        return afloat;
    }

    public BiomeGenBase[] a(BiomeGenBase[] abiomegenbase, int i0, int i1, int i2, int i3) {
        IntCache.a();
        if (abiomegenbase == null || abiomegenbase.length < i2 * i3) {
            abiomegenbase = new BiomeGenBase[i2 * i3];
        }

        int[] aint = this.d.a(i0, i1, i2, i3);

        for (int i4 = 0; i4 < i2 * i3; ++i4) {
            // CanaryMod: Biome disabling and replacements
            BiomeGenBase biomegenbase = BiomeGenBase.a[aint[i4]];
            if (Configuration.getWorldConfig(world_name).isBiomeDisabled(biomegenbase.N)) {
                String old = biomegenbase.y;
                biomegenbase = BiomeGenBase.a[Configuration.getWorldConfig(world_name).getReplacementBiomeId(biomegenbase.N)];
                Canary.logDebug("Biome: " + old + " is disabled and replaced with " + biomegenbase.y);
            }
            abiomegenbase[i4] = biomegenbase;
            //
        }

        return abiomegenbase;
    }

    public BiomeGenBase[] b(BiomeGenBase[] abiomegenbase, int i0, int i1, int i2, int i3) {
        return this.a(abiomegenbase, i0, i1, i2, i3, true);
    }

    public BiomeGenBase[] a(BiomeGenBase[] abiomegenbase, int i0, int i1, int i2, int i3, boolean flag0) {
        IntCache.a();
        if (abiomegenbase == null || abiomegenbase.length < i2 * i3) {
            abiomegenbase = new BiomeGenBase[i2 * i3];
        }

        if (flag0 && i2 == 16 && i3 == 16 && (i0 & 15) == 0 && (i1 & 15) == 0) {
            BiomeGenBase[] abiomegenbase1 = this.f.e(i0, i1);

            System.arraycopy(abiomegenbase1, 0, abiomegenbase, 0, i2 * i3);
            return abiomegenbase;
        } else {
            int[] aint = this.e.a(i0, i1, i2, i3);

            for (int i4 = 0; i4 < i2 * i3; ++i4) {
                abiomegenbase[i4] = BiomeGenBase.a[aint[i4]];
            }

            return abiomegenbase;
        }
    }

    public boolean a(int i0, int i1, int i2, List list) {
        IntCache.a();
        int i3 = i0 - i2 >> 2;
        int i4 = i1 - i2 >> 2;
        int i5 = i0 + i2 >> 2;
        int i6 = i1 + i2 >> 2;
        int i7 = i5 - i3 + 1;
        int i8 = i6 - i4 + 1;
        int[] aint = this.d.a(i3, i4, i7, i8);

        for (int i9 = 0; i9 < i7 * i8; ++i9) {
            BiomeGenBase biomegenbase = BiomeGenBase.a[aint[i9]];

            if (!list.contains(biomegenbase)) {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition a(int i0, int i1, int i2, List list, Random random) {
        IntCache.a();
        int i3 = i0 - i2 >> 2;
        int i4 = i1 - i2 >> 2;
        int i5 = i0 + i2 >> 2;
        int i6 = i1 + i2 >> 2;
        int i7 = i5 - i3 + 1;
        int i8 = i6 - i4 + 1;
        int[] aint = this.d.a(i3, i4, i7, i8);
        ChunkPosition chunkposition = null;
        int i9 = 0;

        for (int i10 = 0; i10 < i7 * i8; ++i10) {
            int i11 = i3 + i10 % i7 << 2;
            int i12 = i4 + i10 / i7 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.a[aint[i10]];

            if (list.contains(biomegenbase) && (chunkposition == null || random.nextInt(i9 + 1) == 0)) {
                chunkposition = new ChunkPosition(i11, 0, i12);
                ++i9;
            }
        }

        return chunkposition;
    }

    public void b() {
        this.f.a();
    }
}
