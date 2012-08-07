package net.minecraft.server;


public class OVillageDoorInfo {

    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public int f;
    public boolean g = false;
    private int h = 0;

    public OVillageDoorInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
        this.f = var6;
    }

    public int a(int var1, int var2, int var3) {
        int var4 = var1 - this.a;
        int var5 = var2 - this.b;
        int var6 = var3 - this.c;

        return var4 * var4 + var5 * var5 + var6 * var6;
    }

    public int b(int var1, int var2, int var3) {
        int var4 = var1 - this.a - this.d;
        int var5 = var2 - this.b;
        int var6 = var3 - this.c - this.e;

        return var4 * var4 + var5 * var5 + var6 * var6;
    }

    public int a() {
        return this.a + this.d;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c + this.e;
    }

    public boolean a(int var1, int var2) {
        int var3 = var1 - this.a;
        int var4 = var2 - this.c;

        return var3 * this.d + var4 * this.e >= 0;
    }

    public void d() {
        this.h = 0;
    }

    public void e() {
        ++this.h;
    }

    public int f() {
        return this.h;
    }
}
