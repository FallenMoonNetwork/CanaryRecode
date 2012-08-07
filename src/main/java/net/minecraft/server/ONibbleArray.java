package net.minecraft.server;

public class ONibbleArray {

    public final byte[] a;
    private final int b;
    private final int c;

    public ONibbleArray(int var1, int var2) {
        super();
        this.a = new byte[var1 >> 1];
        this.b = var2;
        this.c = var2 + 4;
    }

    public ONibbleArray(byte[] var1, int var2) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var2 + 4;
    }

    public int a(int var1, int var2, int var3) {
        int var4 = var2 << this.c | var3 << this.b | var1;
        int var5 = var4 >> 1;
        int var6 = var4 & 1;
        return var6 == 0 ? this.a[var5] & 15 : this.a[var5] >> 4 & 15;
    }

    public void a(int var1, int var2, int var3, int var4) {
        int var5 = var2 << this.c | var3 << this.b | var1;
        int var6 = var5 >> 1;
        int var7 = var5 & 1;
        if (var7 == 0) {
            this.a[var6] = (byte) (this.a[var6] & 240 | var4 & 15);
        } else {
            this.a[var6] = (byte) (this.a[var6] & 15 | (var4 & 15) << 4);
        }

    }
}
