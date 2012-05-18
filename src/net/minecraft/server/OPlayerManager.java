package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OLongHashMap;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPlayerInstance;
import net.minecraft.server.OWorldProvider;
import net.minecraft.server.OWorldServer;

public class OPlayerManager {

    public List a = new ArrayList();
    private OLongHashMap b = new OLongHashMap();
    private List c = new ArrayList();
    private OMinecraftServer d;
    private int e;
    private int f;
    private final int[][] g = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public OPlayerManager(OMinecraftServer var1, int var2, int var3) {
        super();
        if (var3 > 15) {
            throw new IllegalArgumentException("Too big view radius!");
        } else if (var3 < 3) {
            throw new IllegalArgumentException("Too small view radius!");
        } else {
            this.f = var3;
            this.d = var1;
            this.e = var2;
        }
    }

    public OWorldServer a() {
        return this.d.a(this.e);
    }

    public void b() {
        for (int var1 = 0; var1 < this.c.size(); ++var1) {
            ((OPlayerInstance) this.c.get(var1)).a();
        }

        this.c.clear();
        if (this.a.isEmpty()) {
            OWorldServer var3 = this.d.a(this.e);
            OWorldProvider var2 = var3.t;
            if (!var2.c()) {
                var3.G.c();
            }
        }

    }

    private OPlayerInstance a(int var1, int var2, boolean var3) {
        long var4 = var1 + 2147483647L | var2 + 2147483647L << 32;
        OPlayerInstance var6 = (OPlayerInstance) this.b.a(var4);
        if (var6 == null && var3) {
            var6 = new OPlayerInstance(this, var1, var2);
            this.b.a(var4, var6);
        }

        return var6;
    }

    public void a(int var1, int var2, int var3) {
        int var4 = var1 >> 4;
        int var5 = var3 >> 4;
        OPlayerInstance var6 = this.a(var4, var5, false);
        if (var6 != null) {
            var6.a(var1 & 15, var2, var3 & 15);
        }

    }

    public void a(OEntityPlayerMP var1) {
        int var2 = (int) var1.bm >> 4;
        int var3 = (int) var1.bo >> 4;
        var1.d = var1.bm;
        var1.e = var1.bo;
        int var4 = 0;
        int var5 = this.f;
        int var6 = 0;
        int var7 = 0;
        this.a(var2, var3, true).a(var1);

        int var8;
        for (var8 = 1; var8 <= var5 * 2; ++var8) {
            for (int var9 = 0; var9 < 2; ++var9) {
                int[] var10 = this.g[var4++ % 4];

                for (int var11 = 0; var11 < var8; ++var11) {
                    var6 += var10[0];
                    var7 += var10[1];
                    this.a(var2 + var6, var3 + var7, true).a(var1);
                }
            }
        }

        var4 %= 4;

        for (var8 = 0; var8 < var5 * 2; ++var8) {
            var6 += this.g[var4][0];
            var7 += this.g[var4][1];
            this.a(var2 + var6, var3 + var7, true).a(var1);
        }

        this.a.add(var1);
    }

    public void b(OEntityPlayerMP var1) {
        int var2 = (int) var1.d >> 4;
        int var3 = (int) var1.e >> 4;

        for (int var4 = var2 - this.f; var4 <= var2 + this.f; ++var4) {
            for (int var5 = var3 - this.f; var5 <= var3 + this.f; ++var5) {
                OPlayerInstance var6 = this.a(var4, var5, false);
                if (var6 != null) {
                    var6.b(var1);
                }
            }
        }

        this.a.remove(var1);
    }

    private boolean a(int var1, int var2, int var3, int var4) {
        int var5 = var1 - var3;
        int var6 = var2 - var4;
        return var5 >= -this.f && var5 <= this.f ? var6 >= -this.f && var6 <= this.f : false;
    }

    public void c(OEntityPlayerMP var1) {
        int var2 = (int) var1.bm >> 4;
        int var3 = (int) var1.bo >> 4;
        double var4 = var1.d - var1.bm;
        double var6 = var1.e - var1.bo;
        double var8 = var4 * var4 + var6 * var6;
        if (var8 >= 64.0D) {
            int var10 = (int) var1.d >> 4;
            int var11 = (int) var1.e >> 4;
            int var12 = var2 - var10;
            int var13 = var3 - var11;
            if (var12 != 0 || var13 != 0) {
                for (int var14 = var2 - this.f; var14 <= var2 + this.f; ++var14) {
                    for (int var15 = var3 - this.f; var15 <= var3 + this.f; ++var15) {
                        if (!this.a(var14, var15, var10, var11)) {
                            this.a(var14, var15, true).a(var1);
                        }

                        if (!this.a(var14 - var12, var15 - var13, var2, var3)) {
                            OPlayerInstance var16 = this.a(var14 - var12, var15 - var13, false);
                            if (var16 != null) {
                                var16.b(var1);
                            }
                        }
                    }
                }

                var1.d = var1.bm;
                var1.e = var1.bo;
            }
        }
    }

    public int c() {
        return this.f * 16 - 16;
    }

    // $FF: synthetic method
    static OLongHashMap a(OPlayerManager var0) {
        return var0.b;
    }

    // $FF: synthetic method
    static List b(OPlayerManager var0) {
        return var0.c;
    }
}
