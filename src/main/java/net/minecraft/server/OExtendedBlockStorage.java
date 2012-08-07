package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.ONibbleArray;


public class OExtendedBlockStorage {

    private int a;
    private int b;
    private int c;
    private byte[] d;
    private ONibbleArray e;
    private ONibbleArray f;
    private ONibbleArray g;
    private ONibbleArray h;

    public OExtendedBlockStorage(int var1) {
        super();
        this.a = var1;
        this.d = new byte[4096];
        this.f = new ONibbleArray(this.d.length, 4);
        this.h = new ONibbleArray(this.d.length, 4);
        this.g = new ONibbleArray(this.d.length, 4);
    }

    public int a(int var1, int var2, int var3) {
        int var4 = this.d[var2 << 8 | var3 << 4 | var1] & 255;

        return this.e != null ? this.e.a(var1, var2, var3) << 8 | var4 : var4;
    }

    public void a(int var1, int var2, int var3, int var4) {
        int var5 = this.d[var2 << 8 | var3 << 4 | var1] & 255;

        if (this.e != null) {
            var5 |= this.e.a(var1, var2, var3) << 8;
        }

        if (var5 == 0 && var4 != 0) {
            ++this.b;
            if (OBlock.m[var4] != null && OBlock.m[var4].n()) {
                ++this.c;
            }
        } else if (var5 != 0 && var4 == 0) {
            --this.b;
            if (OBlock.m[var5] != null && OBlock.m[var5].n()) {
                --this.c;
            }
        } else if (OBlock.m[var5] != null && OBlock.m[var5].n() && (OBlock.m[var4] == null || !OBlock.m[var4].n())) {
            --this.c;
        } else if ((OBlock.m[var5] == null || !OBlock.m[var5].n()) && OBlock.m[var4] != null && OBlock.m[var4].n()) {
            ++this.c;
        }

        this.d[var2 << 8 | var3 << 4 | var1] = (byte) (var4 & 255);
        if (var4 > 255) {
            if (this.e == null) {
                this.e = new ONibbleArray(this.d.length, 4);
            }

            this.e.a(var1, var2, var3, (var4 & 3840) >> 8);
        } else if (this.e != null) {
            this.e.a(var1, var2, var3, 0);
        }

    }

    public int b(int var1, int var2, int var3) {
        return this.f.a(var1, var2, var3);
    }

    public void b(int var1, int var2, int var3, int var4) {
        this.f.a(var1, var2, var3, var4);
    }

    public boolean a() {
        return this.b == 0;
    }

    public boolean b() {
        return this.c > 0;
    }

    public int c() {
        return this.a;
    }

    public void c(int var1, int var2, int var3, int var4) {
        this.h.a(var1, var2, var3, var4);
    }

    public int c(int var1, int var2, int var3) {
        return this.h.a(var1, var2, var3);
    }

    public void d(int var1, int var2, int var3, int var4) {
        this.g.a(var1, var2, var3, var4);
    }

    public int d(int var1, int var2, int var3) {
        return this.g.a(var1, var2, var3);
    }

    public void d() {
        this.b = 0;
        this.c = 0;

        for (int var1 = 0; var1 < 16; ++var1) {
            for (int var2 = 0; var2 < 16; ++var2) {
                for (int var3 = 0; var3 < 16; ++var3) {
                    int var4 = this.a(var1, var2, var3);

                    if (var4 > 0) {
                        if (OBlock.m[var4] == null) {
                            this.d[var2 << 8 | var3 << 4 | var1] = 0;
                            if (this.e != null) {
                                this.e.a(var1, var2, var3, 0);
                            }
                        } else {
                            ++this.b;
                            if (OBlock.m[var4].n()) {
                                ++this.c;
                            }
                        }
                    }
                }
            }
        }

    }

    public void e() {}

    public int f() {
        return this.b;
    }

    public byte[] g() {
        return this.d;
    }

    public ONibbleArray h() {
        return this.e;
    }

    public ONibbleArray i() {
        return this.f;
    }

    public ONibbleArray j() {
        return this.g;
    }

    public ONibbleArray k() {
        return this.h;
    }

    public void a(byte[] var1) {
        this.d = var1;
    }

    public void a(ONibbleArray var1) {
        this.e = var1;
    }

    public void b(ONibbleArray var1) {
        this.f = var1;
    }

    public void c(ONibbleArray var1) {
        this.g = var1;
    }

    public void d(ONibbleArray var1) {
        this.h = var1;
    }
}
