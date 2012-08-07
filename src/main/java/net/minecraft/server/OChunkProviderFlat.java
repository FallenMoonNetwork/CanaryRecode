package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OMapGenVillage;
import net.minecraft.server.OWorld;

public class OChunkProviderFlat implements OIChunkProvider {

    private OWorld a;
    private Random b;
    private final boolean c;
    private OMapGenVillage d = new OMapGenVillage(1);

    public OChunkProviderFlat(OWorld var1, long var2, boolean var4) {
        super();
        this.a = var1;
        this.c = var4;
        this.b = new Random(var2);
    }

    private void a(byte[] var1) {
        int var2 = var1.length / 256;

        for (int var3 = 0; var3 < 16; ++var3) {
            for (int var4 = 0; var4 < 16; ++var4) {
                for (int var5 = 0; var5 < var2; ++var5) {
                    int var6 = 0;
                    if (var5 == 0) {
                        var6 = OBlock.z.bO;
                    } else if (var5 <= 2) {
                        var6 = OBlock.v.bO;
                    } else if (var5 == 3) {
                        var6 = OBlock.u.bO;
                    }

                    var1[var3 << 11 | var4 << 7 | var5] = (byte) var6;
                }
            }
        }

    }

    @Override
    public OChunk c(int var1, int var2) {
        return this.b(var1, var2);
    }

    @Override
    public OChunk b(int var1, int var2) {
        byte[] var3 = new byte['\u8000'];
        this.a(var3);
        OChunk var4 = new OChunk(this.a, var3, var1, var2);
        if (this.c) {
            this.d.a(this, this.a, var1, var2, var3);
        }

        OBiomeGenBase[] var5 = this.a.a().b((OBiomeGenBase[]) null, var1 * 16, var2 * 16, 16, 16);
        byte[] var6 = var4.l();

        for (int var7 = 0; var7 < var6.length; ++var7) {
            var6[var7] = (byte) var5[var7].M;
        }

        var4.a();
        return var4;
    }

    @Override
    public boolean a(int var1, int var2) {
        return true;
    }

    @Override
    public void a(OIChunkProvider var1, int var2, int var3) {
        this.b.setSeed(this.a.n());
        long var4 = this.b.nextLong() / 2L * 2L + 1L;
        long var6 = this.b.nextLong() / 2L * 2L + 1L;
        this.b.setSeed(var2 * var4 + var3 * var6 ^ this.a.n());
        if (this.c) {
            this.d.a(this.a, this.b, var2, var3);
        }

    }

    @Override
    public boolean a(boolean var1, OIProgressUpdate var2) {
        return true;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
        OBiomeGenBase var5 = this.a.a(var2, var4);
        return var5 == null ? null : var5.a(var1);
    }

    @Override
    public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
        return null;
    }
}
