package net.minecraft.server;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.canarymod.Logman;
import net.canarymod.api.world.CanaryChunk;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OExtendedBlockStorage;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OProfiler;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldChunkManager;


public class OChunk {

    public static boolean a;
    private OExtendedBlockStorage[] q;
    private byte[] r;
    public int[] b;
    public boolean[] c;
    public boolean d;
    public OWorld e;
    public int[] f;
    public final int g;
    public final int h;
    private boolean s;
    public Map i;
    public List[] j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public boolean o;
    private int t;
    boolean p;
    
    // CanaryMod Chunk handler
    private CanaryChunk canaryChunk;

    public OChunk(OWorld var1, int var2, int var3) {
        super();
        this.q = new OExtendedBlockStorage[16];
        this.r = new byte[256];
        this.b = new int[256];
        this.c = new boolean[256];
        this.s = false;
        this.i = new HashMap();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = 0L;
        this.o = false;
        this.t = 4096;
        this.p = false;
        this.j = new List[16];
        this.e = var1;
        this.g = var2;
        this.h = var3;
        this.f = new int[256];

        for (int var4 = 0; var4 < this.j.length; ++var4) {
            this.j[var4] = new ArrayList();
        }

        Arrays.fill(this.b, -999);
        Arrays.fill(this.r, (byte) -1);
    }

    public OChunk(OWorld var1, byte[] var2, int var3, int var4) {
        this(var1, var3, var4);
        int var5 = var2.length / 256;

        for (int var6 = 0; var6 < 16; ++var6) {
            for (int var7 = 0; var7 < 16; ++var7) {
                for (int var8 = 0; var8 < var5; ++var8) {
                    byte var9 = var2[var6 << 11 | var7 << 7 | var8];

                    if (var9 != 0) {
                        int var10 = var8 >> 4;

                        if (this.q[var10] == null) {
                            this.q[var10] = new OExtendedBlockStorage(var10 << 4);
                        }

                        this.q[var10].a(var6, var8 & 15, var7, var9);
                    }
                }
            }
        }

    }
    
    // CanaryMod start
    public CanaryChunk getCanaryChunk() {
        return canaryChunk;
    }

    // CanaryMod end

    public boolean a(int var1, int var2) {
        return var1 == this.g && var2 == this.h;
    }

    public int b(int var1, int var2) {
        return this.f[var2 << 4 | var1];
    }

    public int g() {
        for (int var1 = this.q.length - 1; var1 >= 0; --var1) {
            if (this.q[var1] != null) {
                return this.q[var1].c();
            }
        }

        return 0;
    }

    public OExtendedBlockStorage[] h() {
        return this.q;
    }

    public void a() {
        int var1 = this.g();

        int var2;
        int var3;

        for (var2 = 0; var2 < 16; ++var2) {
            var3 = 0;

            while (var3 < 16) {
                this.b[var2 + (var3 << 4)] = -999;
                int var4 = var1 + 16 - 1;

                while (true) {
                    if (var4 > 0) {
                        if (this.b(var2, var4 - 1, var3) == 0) {
                            --var4;
                            continue;
                        }

                        this.f[var3 << 4 | var2] = var4;
                    }

                    if (!this.e.t.e) {
                        var4 = 15;
                        int var5 = var1 + 16 - 1;

                        do {
                            var4 -= this.b(var2, var5, var3);
                            if (var4 > 0) {
                                OExtendedBlockStorage var6 = this.q[var5 >> 4];

                                if (var6 != null) {
                                    var6.c(var2, var5 & 15, var3, var4);
                                    this.e.o((this.g << 4) + var2, var5, (this.h << 4) + var3);
                                }
                            }

                            --var5;
                        } while (var5 > 0 && var4 > 0);
                    }

                    ++var3;
                    break;
                }
            }
        }

        this.l = true;

        for (var2 = 0; var2 < 16; ++var2) {
            for (var3 = 0; var3 < 16; ++var3) {
                this.e(var2, var3);
            }
        }

    }

    public void b() {}

    private void e(int var1, int var2) {
        this.c[var1 + var2 * 16] = true;
        this.s = true;
    }

    private void o() {
        OProfiler.a("recheckGaps");
        if (this.e.a(this.g * 16 + 8, 0, this.h * 16 + 8, 16)) {
            for (int var1 = 0; var1 < 16; ++var1) {
                for (int var2 = 0; var2 < 16; ++var2) {
                    if (this.c[var1 + var2 * 16]) {
                        this.c[var1 + var2 * 16] = false;
                        int var3 = this.b(var1, var2);
                        int var4 = this.g * 16 + var1;
                        int var5 = this.h * 16 + var2;
                        int var6 = this.e.e(var4 - 1, var5);
                        int var7 = this.e.e(var4 + 1, var5);
                        int var8 = this.e.e(var4, var5 - 1);
                        int var9 = this.e.e(var4, var5 + 1);

                        if (var7 < var6) {
                            var6 = var7;
                        }

                        if (var8 < var6) {
                            var6 = var8;
                        }

                        if (var9 < var6) {
                            var6 = var9;
                        }

                        this.g(var4, var5, var6);
                        this.g(var4 - 1, var5, var3);
                        this.g(var4 + 1, var5, var3);
                        this.g(var4, var5 - 1, var3);
                        this.g(var4, var5 + 1, var3);
                    }
                }
            }

            this.s = false;
        }

        OProfiler.a();
    }

    private void g(int var1, int var2, int var3) {
        int var4 = this.e.e(var1, var2);

        if (var4 > var3) {
            this.d(var1, var2, var3, var4 + 1);
        } else if (var4 < var3) {
            this.d(var1, var2, var4, var3 + 1);
        }

    }

    private void d(int var1, int var2, int var3, int var4) {
        if (var4 > var3 && this.e.a(var1, 0, var2, 16)) {
            for (int var5 = var3; var5 < var4; ++var5) {
                this.e.b(OEnumSkyBlock.a, var1, var5, var2);
            }

            this.l = true;
        }

    }

    private void h(int var1, int var2, int var3) {
        int var4 = this.f[var3 << 4 | var1] & 255;
        int var5 = var4;

        if (var2 > var4) {
            var5 = var2;
        }

        while (var5 > 0 && this.b(var1, var5 - 1, var3) == 0) {
            --var5;
        }

        if (var5 != var4) {
            this.e.g(var1, var3, var5, var4);
            this.f[var3 << 4 | var1] = var5;
            int var6 = this.g * 16 + var1;
            int var7 = this.h * 16 + var3;
            int var8;
            int var12;

            if (!this.e.t.e) {
                OExtendedBlockStorage var9;

                if (var5 < var4) {
                    for (var8 = var5; var8 < var4; ++var8) {
                        var9 = this.q[var8 >> 4];
                        if (var9 != null) {
                            var9.c(var1, var8 & 15, var3, 15);
                            this.e.o((this.g << 4) + var1, var8, (this.h << 4) + var3);
                        }
                    }
                } else {
                    for (var8 = var4; var8 < var5; ++var8) {
                        // CanaryMod start: Catch corrupt index info
                        if (var8 >> 4 < 0 || var8 >> 4 >= 16) {
                            Logman.logWarning("Invalid chunk info array index: " + (var8 >> 4));
                            Logman.logWarning("var4: " + var4 + ", var5: " + var5);
                            Logman.logWarning("Chunk location: " + var6 + ", " + var7);
                            var8 = 0;
                        }
                        // CanaryMod end
                        var9 = this.q[var8 >> 4];
                        if (var9 != null) {
                            var9.c(var1, var8 & 15, var3, 0);
                            this.e.o((this.g << 4) + var1, var8, (this.h << 4) + var3);
                        }
                    }
                }

                var8 = 15;

                while (var5 > 0 && var8 > 0) {
                    --var5;
                    var12 = this.b(var1, var5, var3);
                    if (var12 == 0) {
                        var12 = 1;
                    }

                    var8 -= var12;
                    if (var8 < 0) {
                        var8 = 0;
                    }

                    OExtendedBlockStorage var10 = this.q[var5 >> 4];

                    if (var10 != null) {
                        var10.c(var1, var5 & 15, var3, var8);
                    }
                }
            }

            var8 = this.f[var3 << 4 | var1];
            var12 = var4;
            int var13 = var8;

            if (var8 < var4) {
                var12 = var8;
                var13 = var4;
            }

            if (!this.e.t.e) {
                this.d(var6 - 1, var7, var12, var13);
                this.d(var6 + 1, var7, var12, var13);
                this.d(var6, var7 - 1, var12, var13);
                this.d(var6, var7 + 1, var12, var13);
                this.d(var6, var7, var12, var13);
            }

            this.l = true;
        }
    }

    public int b(int var1, int var2, int var3) {
        return OBlock.o[this.a(var1, var2, var3)];
    }

    public int a(int var1, int var2, int var3) {
        if (var2 >> 4 >= this.q.length) {
            return 0;
        } else {
            OExtendedBlockStorage var4 = this.q[var2 >> 4];

            return var4 != null ? var4.a(var1, var2 & 15, var3) : 0;
        }
    }

    public int c(int var1, int var2, int var3) {
        if (var2 >> 4 >= this.q.length) {
            return 0;
        } else {
            OExtendedBlockStorage var4 = this.q[var2 >> 4];

            return var4 != null ? var4.b(var1, var2 & 15, var3) : 0;
        }
    }

    public boolean a(int var1, int var2, int var3, int var4) {
        return this.a(var1, var2, var3, var4, 0);
    }

    public boolean a(int var1, int var2, int var3, int var4, int var5) {
        int var6 = var3 << 4 | var1;

        if (var2 >= this.b[var6] - 1) {
            this.b[var6] = -999;
        }

        int var7 = this.f[var6];
        int var8 = this.a(var1, var2, var3);

        if (var8 == var4 && this.c(var1, var2, var3) == var5) {
            return false;
        } else {
            OExtendedBlockStorage var9 = this.q[var2 >> 4];
            boolean var10 = false;

            if (var9 == null) {
                if (var4 == 0) {
                    return false;
                }

                var9 = this.q[var2 >> 4] = new OExtendedBlockStorage(var2 >> 4 << 4);
                var10 = var2 >= var7;
            }

            var9.a(var1, var2 & 15, var3, var4);
            int var11 = this.g * 16 + var1;
            int var12 = this.h * 16 + var3;

            if (var8 != 0) {
                if (!this.e.F) {
                    OBlock.m[var8].d(this.e, var11, var2, var12);
                } else if (OBlock.m[var8] instanceof OBlockContainer && var8 != var4) {
                    this.e.q(var11, var2, var12);
                }
            }

            if (var9.a(var1, var2 & 15, var3) != var4) {
                return false;
            } else {
                var9.b(var1, var2 & 15, var3, var5);
                if (var10) {
                    this.a();
                } else {
                    if (OBlock.o[var4 & 4095] > 0) {
                        if (var2 >= var7) {
                            this.h(var1, var2 + 1, var3);
                        }
                    } else if (var2 == var7 - 1) {
                        this.h(var1, var2, var3);
                    }

                    this.e(var1, var3);
                }

                OTileEntity var13;

                if (var4 != 0) {
                    if (!this.e.F) {
                        OBlock.m[var4].a(this.e, var11, var2, var12);
                    }

                    if (OBlock.m[var4] instanceof OBlockContainer) {
                        var13 = this.e(var1, var2, var3);
                        if (var13 == null) {
                            var13 = ((OBlockContainer) OBlock.m[var4]).a_();
                            this.e.a(var11, var2, var12, var13);
                        }

                        if (var13 != null) {
                            var13.h();
                        }
                    }
                } else if (var8 > 0 && OBlock.m[var8] instanceof OBlockContainer) {
                    var13 = this.e(var1, var2, var3);
                    if (var13 != null) {
                        var13.h();
                    }
                }

                this.l = true;
                return true;
            }
        }
    }

    public boolean b(int var1, int var2, int var3, int var4) {
        OExtendedBlockStorage var5 = this.q[var2 >> 4];

        if (var5 == null) {
            return false;
        } else {
            int var6 = var5.b(var1, var2 & 15, var3);

            if (var6 == var4) {
                return false;
            } else {
                this.l = true;
                var5.b(var1, var2 & 15, var3, var4);
                int var7 = var5.a(var1, var2 & 15, var3);

                if (var7 > 0 && OBlock.m[var7] instanceof OBlockContainer) {
                    OTileEntity var8 = this.e(var1, var2, var3);

                    if (var8 != null) {
                        var8.h();
                        var8.p = var4;
                    }
                }

                return true;
            }
        }
    }

    public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
        OExtendedBlockStorage var5 = this.q[var3 >> 4];

        return var5 == null ? var1.c : (var1 == OEnumSkyBlock.a ? var5.c(var2, var3 & 15, var4) : (var1 == OEnumSkyBlock.b ? var5.d(var2, var3 & 15, var4) : var1.c));
    }

    public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
        OExtendedBlockStorage var6 = this.q[var3 >> 4];

        if (var6 == null) {
            var6 = this.q[var3 >> 4] = new OExtendedBlockStorage(var3 >> 4 << 4);
            this.a();
        }

        this.l = true;
        if (var1 == OEnumSkyBlock.a) {
            if (!this.e.t.e) {
                var6.c(var2, var3 & 15, var4, var5);
            }
        } else {
            if (var1 != OEnumSkyBlock.b) {
                return;
            }

            var6.d(var2, var3 & 15, var4, var5);
        }

    }

    public int c(int var1, int var2, int var3, int var4) {
        OExtendedBlockStorage var5 = this.q[var2 >> 4];

        if (var5 == null) {
            return !this.e.t.e && var4 < OEnumSkyBlock.a.c ? OEnumSkyBlock.a.c - var4 : 0;
        } else {
            int var6 = this.e.t.e ? 0 : var5.c(var1, var2 & 15, var3);

            if (var6 > 0) {
                a = true;
            }

            var6 -= var4;
            int var7 = var5.d(var1, var2 & 15, var3);

            if (var7 > var6) {
                var6 = var7;
            }

            return var6;
        }
    }

    public void a(OEntity var1) {
        this.m = true;
        int var2 = OMathHelper.b(var1.bm / 16.0D);
        int var3 = OMathHelper.b(var1.bo / 16.0D);

        if (var2 != this.g || var3 != this.h) {
            Logman.logWarning("Wrong location for " + var1.getCanaryEntity().getName() + " - Destroying entity.");
            // Thread.dumpStack(); //CanaryMod do not dump the stack
            // Remove the offending entity.
            var1.X();
            return;
        }

        int var4 = OMathHelper.b(var1.bn / 16.0D);

        if (var4 < 0) {
            var4 = 0;
        }

        if (var4 >= this.j.length) {
            var4 = this.j.length - 1;
        }

        var1.bZ = true;
        var1.ca = this.g;
        var1.cb = var4;
        var1.cc = this.h;
        this.j[var4].add(var1);
    }

    public void b(OEntity var1) {
        this.a(var1, var1.cb);
    }

    public void a(OEntity var1, int var2) {
        if (var2 < 0) {
            var2 = 0;
        }

        if (var2 >= this.j.length) {
            var2 = this.j.length - 1;
        }

        this.j[var2].remove(var1);
    }

    public boolean d(int var1, int var2, int var3) {
        return var2 >= this.f[var3 << 4 | var1];
    }

    public OTileEntity e(int var1, int var2, int var3) {
        OChunkPosition var4 = new OChunkPosition(var1, var2, var3);
        OTileEntity var5 = (OTileEntity) this.i.get(var4);

        if (var5 == null) {
            int var6 = this.a(var1, var2, var3);

            if (var6 <= 0 || !OBlock.m[var6].o()) {
                return null;
            }

            if (var5 == null) {
                var5 = ((OBlockContainer) OBlock.m[var6]).a_();
                this.e.a(this.g * 16 + var1, var2, this.h * 16 + var3, var5);
            }

            var5 = (OTileEntity) this.i.get(var4);
        }

        if (var5 != null && var5.l()) {
            this.i.remove(var4);
            return null;
        } else {
            return var5;
        }
    }

    public void a(OTileEntity var1) {
        int var2 = var1.l - this.g * 16;
        int var3 = var1.m;
        int var4 = var1.n - this.h * 16;

        this.a(var2, var3, var4, var1);
        if (this.d) {
            this.e.c.add(var1);
        }

    }

    public void a(int var1, int var2, int var3, OTileEntity var4) {
        OChunkPosition var5 = new OChunkPosition(var1, var2, var3);

        var4.k = this.e;
        var4.l = this.g * 16 + var1;
        var4.m = var2;
        var4.n = this.h * 16 + var3;
        if (this.a(var1, var2, var3) != 0 && OBlock.m[this.a(var1, var2, var3)] instanceof OBlockContainer) {
            var4.m();
            this.i.put(var5, var4);
        }
    }

    public void f(int var1, int var2, int var3) {
        OChunkPosition var4 = new OChunkPosition(var1, var2, var3);

        if (this.d) {
            OTileEntity var5 = (OTileEntity) this.i.remove(var4);

            if (var5 != null) {
                var5.j();
            }
        }

    }

    public void c() {
        this.d = true;
        this.e.a(this.i.values());

        for (int var1 = 0; var1 < this.j.length; ++var1) {
            this.e.a(this.j[var1]);
        }

    }

    public void d() {
        this.d = false;
        Iterator var1 = this.i.values().iterator();

        while (var1.hasNext()) {
            OTileEntity var2 = (OTileEntity) var1.next();

            this.e.a(var2);
        }

        for (int var3 = 0; var3 < this.j.length; ++var3) {
            this.e.b(this.j[var3]);
        }

    }

    public void e() {
        this.l = true;
    }

    public void a(OEntity var1, OAxisAlignedBB var2, List var3) {
        int var4 = OMathHelper.b((var2.b - 2.0D) / 16.0D);
        int var5 = OMathHelper.b((var2.e + 2.0D) / 16.0D);

        if (var4 < 0) {
            var4 = 0;
        }

        if (var5 >= this.j.length) {
            var5 = this.j.length - 1;
        }

        for (int var6 = var4; var6 <= var5; ++var6) {
            List var7 = this.j[var6];

            for (int var8 = 0; var8 < var7.size(); ++var8) {
                OEntity var9 = (OEntity) var7.get(var8);

                if (var9 != var1 && var9.bw.a(var2)) {
                    var3.add(var9);
                    OEntity[] var10 = var9.bb();

                    if (var10 != null) {
                        for (int var11 = 0; var11 < var10.length; ++var11) {
                            var9 = var10[var11];
                            if (var9 != var1 && var9.bw.a(var2)) {
                                var3.add(var9);
                            }
                        }
                    }
                }
            }
        }

    }

    public void a(Class var1, OAxisAlignedBB var2, List var3) {
        int var4 = OMathHelper.b((var2.b - 2.0D) / 16.0D);
        int var5 = OMathHelper.b((var2.e + 2.0D) / 16.0D);

        if (var4 < 0) {
            var4 = 0;
        } else if (var4 >= this.j.length) {
            var4 = this.j.length - 1;
        }

        if (var5 >= this.j.length) {
            var5 = this.j.length - 1;
        } else if (var5 < 0) {
            var5 = 0;
        }

        for (int var6 = var4; var6 <= var5; ++var6) {
            List var7 = this.j[var6];

            for (int var8 = 0; var8 < var7.size(); ++var8) {
                OEntity var9 = (OEntity) var7.get(var8);

                if (var1.isAssignableFrom(var9.getClass()) && var9.bw.a(var2)) {
                    var3.add(var9);
                }
            }
        }

    }

    public boolean a(boolean var1) {
        if (var1) {
            if (this.m && this.e.o() != this.n) {
                return true;
            }
        } else if (this.m && this.e.o() >= this.n + 600L) {
            return true;
        }

        return this.l;
    }

    public Random a(long var1) {
        return new Random(this.e.n() + (this.g * this.g * 4987142) + (this.g * 5947611) + (this.h * this.h) * 4392871L + (this.h * 389711) ^ var1);
    }

    public boolean f() {
        return false;
    }

    public void i() {
        OExtendedBlockStorage[] var1 = this.q;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            OExtendedBlockStorage var4 = var1[var3];

            if (var4 != null) {
                var4.e();
            }
        }

    }

    public void a(OIChunkProvider var1, OIChunkProvider var2, int var3, int var4) {
        if (!this.k && var1.a(var3 + 1, var4 + 1) && var1.a(var3, var4 + 1) && var1.a(var3 + 1, var4)) {
            var1.a(var2, var3, var4);
        }

        if (var1.a(var3 - 1, var4) && !var1.b(var3 - 1, var4).k && var1.a(var3 - 1, var4 + 1) && var1.a(var3, var4 + 1) && var1.a(var3 - 1, var4 + 1)) {
            var1.a(var2, var3 - 1, var4);
        }

        if (var1.a(var3, var4 - 1) && !var1.b(var3, var4 - 1).k && var1.a(var3 + 1, var4 - 1) && var1.a(var3 + 1, var4 - 1) && var1.a(var3 + 1, var4)) {
            var1.a(var2, var3, var4 - 1);
        }

        if (var1.a(var3 - 1, var4 - 1) && !var1.b(var3 - 1, var4 - 1).k && var1.a(var3, var4 - 1) && var1.a(var3 - 1, var4)) {
            var1.a(var2, var3 - 1, var4 - 1);
        }

    }

    public int d(int var1, int var2) {
        int var3 = var1 | var2 << 4;
        int var4 = this.b[var3];

        if (var4 == -999) {
            int var5 = this.g() + 15;

            var4 = -1;

            while (var5 > 0 && var4 == -1) {
                int var6 = this.a(var1, var5, var2);
                OMaterial var7 = var6 == 0 ? OMaterial.a : OBlock.m[var6].cd;

                if (!var7.c() && !var7.d()) {
                    --var5;
                } else {
                    var4 = var5 + 1;
                }
            }

            this.b[var3] = var4;
        }

        return var4;
    }

    public void j() {
        if (this.s && !this.e.t.e) {
            this.o();
        }

    }

    public OChunkCoordIntPair k() {
        return new OChunkCoordIntPair(this.g, this.h);
    }

    public boolean c(int var1, int var2) {
        if (var1 < 0) {
            var1 = 0;
        }

        if (var2 >= 256) {
            var2 = 255;
        }

        for (int var3 = var1; var3 <= var2; var3 += 16) {
            OExtendedBlockStorage var4 = this.q[var3 >> 4];

            if (var4 != null && !var4.a()) {
                return false;
            }
        }

        return true;
    }

    public void a(OExtendedBlockStorage[] var1) {
        this.q = var1;
    }

    public OBiomeGenBase a(int var1, int var2, OWorldChunkManager var3) {
        int var4 = this.r[var2 << 4 | var1] & 255;

        if (var4 == 255) {
            OBiomeGenBase var5 = var3.a((this.g << 4) + var1, (this.h << 4) + var2);

            var4 = var5.M;
            this.r[var2 << 4 | var1] = (byte) (var4 & 255);
        }

        return OBiomeGenBase.a[var4] == null ? OBiomeGenBase.c : OBiomeGenBase.a[var4];
    }

    public byte[] l() {
        return this.r;
    }

    public void a(byte[] var1) {
        this.r = var1;
    }

    public void m() {
        this.t = 0;
    }

    public void n() {
        for (int var1 = 0; var1 < 8; ++var1) {
            if (this.t >= 4096) {
                return;
            }

            int var2 = this.t % 16;
            int var3 = this.t / 16 % 16;
            int var4 = this.t / 256;

            ++this.t;
            int var5 = (this.g << 4) + var3;
            int var6 = (this.h << 4) + var4;

            for (int var7 = 0; var7 < 16; ++var7) {
                int var8 = (var2 << 4) + var7;

                if (this.q[var2] == null && (var7 == 0 || var7 == 15 || var3 == 0 || var3 == 15 || var4 == 0 || var4 == 15) || this.q[var2] != null && this.q[var2].a(var3, var7, var4) == 0) {
                    if (OBlock.q[this.e.a(var5, var8 - 1, var6)] > 0) {
                        this.e.v(var5, var8 - 1, var6);
                    }

                    if (OBlock.q[this.e.a(var5, var8 + 1, var6)] > 0) {
                        this.e.v(var5, var8 + 1, var6);
                    }

                    if (OBlock.q[this.e.a(var5 - 1, var8, var6)] > 0) {
                        this.e.v(var5 - 1, var8, var6);
                    }

                    if (OBlock.q[this.e.a(var5 + 1, var8, var6)] > 0) {
                        this.e.v(var5 + 1, var8, var6);
                    }

                    if (OBlock.q[this.e.a(var5, var8, var6 - 1)] > 0) {
                        this.e.v(var5, var8, var6 - 1);
                    }

                    if (OBlock.q[this.e.a(var5, var8, var6 + 1)] > 0) {
                        this.e.v(var5, var8, var6 + 1);
                    }

                    this.e.v(var5, var8, var6);
                }
            }
        }

    }
}
