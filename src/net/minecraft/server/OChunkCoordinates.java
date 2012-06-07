package net.minecraft.server;

public class OChunkCoordinates implements Comparable {

    public int a;
    public int b;
    public int c;

    public OChunkCoordinates() {
        super();
    }

    public OChunkCoordinates(int var1, int var2, int var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public OChunkCoordinates(OChunkCoordinates var1) {
        super();
        this.a = var1.a;
        this.b = var1.b;
        this.c = var1.c;
    }

    @Override
    public boolean equals(Object var1) {
        if (!(var1 instanceof OChunkCoordinates)) {
            return false;
        } else {
            OChunkCoordinates var2 = (OChunkCoordinates) var1;
            return this.a == var2.a && this.b == var2.b && this.c == var2.c;
        }
    }

    @Override
    public int hashCode() {
        return this.a + this.c << 8 + this.b << 16;
    }

    public int a(OChunkCoordinates var1) {
        return this.b == var1.b ? (this.c == var1.c ? this.a - var1.a : this.c - var1.c) : this.b - var1.b;
    }

    public void a(int var1, int var2, int var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public double b(int var1, int var2, int var3) {
        int var4 = this.a - var1;
        int var5 = this.b - var2;
        int var6 = this.c - var3;
        return Math.sqrt((var4 * var4 + var5 * var5 + var6 * var6));
    }

    public float c(int var1, int var2, int var3) {
        int var4 = this.a - var1;
        int var5 = this.b - var2;
        int var6 = this.c - var3;
        return (var4 * var4 + var5 * var5 + var6 * var6);
    }

    // $FF: synthetic method
    // $FF: bridge method
    @Override
    public int compareTo(Object var1) {
        return this.a((OChunkCoordinates) var1);
    }
}
