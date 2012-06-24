package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OChunk;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;

public class OChunkCache implements OIBlockAccess {

    private int a;
    private int b;
    private OChunk[][] c;
    private boolean d;
    private OWorld e;

    public OChunkCache(OWorld var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        super();
        this.e = var1;
        this.a = var2 >> 4;
        this.b = var4 >> 4;
        int var8 = var5 >> 4;
        int var9 = var7 >> 4;
        this.c = new OChunk[var8 - this.a + 1][var9 - this.b + 1];
        this.d = true;

        for (int var10 = this.a; var10 <= var8; ++var10) {
            for (int var11 = this.b; var11 <= var9; ++var11) {
                OChunk var12 = var1.d(var10, var11);
                if (var12 != null) {
                    this.c[var10 - this.a][var11 - this.b] = var12;
                    if (!var12.c(var3, var6)) {
                        this.d = false;
                    }
                }
            }
        }

    }

    @Override
    public int a(int var1, int var2, int var3) {
        if (var2 < 0) {
            return 0;
        } else if (var2 >= 256) {
            return 0;
        } else {
            int var4 = (var1 >> 4) - this.a;
            int var5 = (var3 >> 4) - this.b;
            if (var4 >= 0 && var4 < this.c.length && var5 >= 0 && var5 < this.c[var4].length) {
                OChunk var6 = this.c[var4][var5];
                return var6 == null ? 0 : var6.a(var1 & 15, var2, var3 & 15);
            } else {
                return 0;
            }
        }
    }

    @Override
    public OTileEntity b(int var1, int var2, int var3) {
        int var4 = (var1 >> 4) - this.a;
        int var5 = (var3 >> 4) - this.b;
        return this.c[var4][var5].e(var1 & 15, var2, var3 & 15);
    }

    @Override
    public int c(int var1, int var2, int var3) {
        if (var2 < 0) {
            return 0;
        } else if (var2 >= 256) {
            return 0;
        } else {
            int var4 = (var1 >> 4) - this.a;
            int var5 = (var3 >> 4) - this.b;
            return this.c[var4][var5].c(var1 & 15, var2, var3 & 15);
        }
    }

    @Override
    public OMaterial d(int var1, int var2, int var3) {
        int var4 = this.a(var1, var2, var3);
        return var4 == 0 ? OMaterial.a : OBlock.m[var4].cd;
    }

    @Override
    public boolean e(int var1, int var2, int var3) {
        OBlock var4 = OBlock.m[this.a(var1, var2, var3)];
        return var4 == null ? false : var4.cd.c() && var4.b();
    }
}
