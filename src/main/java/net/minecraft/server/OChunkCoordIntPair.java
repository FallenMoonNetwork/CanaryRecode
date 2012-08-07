package net.minecraft.server;

import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEntity;

public class OChunkCoordIntPair {

    public final int a;
    public final int b;

    public OChunkCoordIntPair(int var1, int var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    public static long a(int var0, int var1) {
        long var2 = var0;
        long var4 = var1;
        return var2 & 4294967295L | (var4 & 4294967295L) << 32;
    }

    @Override
    public int hashCode() {
        long var1 = a(this.a, this.b);
        int var3 = (int) var1;
        int var4 = (int) (var1 >> 32);
        return var3 ^ var4;
    }

    @Override
    public boolean equals(Object var1) {
        OChunkCoordIntPair var2 = (OChunkCoordIntPair) var1;
        return var2.a == this.a && var2.b == this.b;
    }

    public double a(OEntity var1) {
        double var2 = (this.a * 16 + 8);
        double var4 = (this.b * 16 + 8);
        double var6 = var2 - var1.bm;
        double var8 = var4 - var1.bo;
        return var6 * var6 + var8 * var8;
    }

    public int a() {
        return (this.a << 4) + 8;
    }

    public int b() {
        return (this.b << 4) + 8;
    }

    public OChunkPosition a(int var1) {
        return new OChunkPosition(this.a(), var1, this.b());
    }

    @Override
    public String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }
}
