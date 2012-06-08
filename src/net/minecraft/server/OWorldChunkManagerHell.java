package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OWorldChunkManager;

public class OWorldChunkManagerHell extends OWorldChunkManager {

    private OBiomeGenBase a;
    private float b;
    private float c;

    public OWorldChunkManagerHell(OBiomeGenBase var1, float var2, float var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public OBiomeGenBase a(int var1, int var2) {
        return this.a;
    }

    @Override
    public OBiomeGenBase[] a(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new OBiomeGenBase[var4 * var5];
        }

        Arrays.fill(var1, 0, var4 * var5, this.a);
        return var1;
    }

    @Override
    public float[] a(float[] var1, int var2, int var3, int var4, int var5) {
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new float[var4 * var5];
        }

        Arrays.fill(var1, 0, var4 * var5, this.b);
        return var1;
    }

    @Override
    public float[] b(float[] var1, int var2, int var3, int var4, int var5) {
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new float[var4 * var5];
        }

        Arrays.fill(var1, 0, var4 * var5, this.c);
        return var1;
    }

    @Override
    public OBiomeGenBase[] b(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
        if (var1 == null || var1.length < var4 * var5) {
            var1 = new OBiomeGenBase[var4 * var5];
        }

        Arrays.fill(var1, 0, var4 * var5, this.a);
        return var1;
    }

    @Override
    public OBiomeGenBase[] a(OBiomeGenBase[] var1, int var2, int var3, int var4, int var5, boolean var6) {
        return this.b(var1, var2, var3, var4, var5);
    }

    @Override
    public OChunkPosition a(int var1, int var2, int var3, List var4, Random var5) {
        return var4.contains(this.a) ? new OChunkPosition(var1 - var3 + var5.nextInt(var3 * 2 + 1), 0, var2 - var3 + var5.nextInt(var3 * 2 + 1)) : null;
    }

    @Override
    public boolean a(int var1, int var2, int var3, List var4) {
        return var4.contains(this.a);
    }
}
