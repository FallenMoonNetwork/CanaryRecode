package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OMapGenStructure;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OStructureStrongholdStart;

public class OMapGenStronghold extends OMapGenStructure {

    private OBiomeGenBase[] a;
    private boolean f;
    private OChunkCoordIntPair[] g;

    public OMapGenStronghold() {
        super();
        this.a = new OBiomeGenBase[] { OBiomeGenBase.d, OBiomeGenBase.f, OBiomeGenBase.e, OBiomeGenBase.h, OBiomeGenBase.g, OBiomeGenBase.n, OBiomeGenBase.o, OBiomeGenBase.s, OBiomeGenBase.t, OBiomeGenBase.v, OBiomeGenBase.w, OBiomeGenBase.x };
        this.g = new OChunkCoordIntPair[3];
    }

    @Override
    protected boolean a(int var1, int var2) {
        if (!this.f) {
            Random var3 = new Random();
            var3.setSeed(this.d.n());
            double var4 = var3.nextDouble() * 3.141592653589793D * 2.0D;

            for (int var6 = 0; var6 < this.g.length; ++var6) {
                double var7 = (1.25D + var3.nextDouble()) * 32.0D;
                int var9 = (int) Math.round(Math.cos(var4) * var7);
                int var10 = (int) Math.round(Math.sin(var4) * var7);
                ArrayList var11 = new ArrayList();
                OBiomeGenBase[] var12 = this.a;
                int var13 = var12.length;

                for (int var14 = 0; var14 < var13; ++var14) {
                    OBiomeGenBase var15 = var12[var14];
                    var11.add(var15);
                }

                OChunkPosition var20 = this.d.a().a((var9 << 4) + 8, (var10 << 4) + 8, 112, var11, var3);
                if (var20 != null) {
                    var9 = var20.a >> 4;
                    var10 = var20.c >> 4;
                } else {
                    System.out.println("Placed stronghold in INVALID biome at (" + var9 + ", " + var10 + ")");
                }

                this.g[var6] = new OChunkCoordIntPair(var9, var10);
                var4 += 6.283185307179586D / this.g.length;
            }

            this.f = true;
        }

        OChunkCoordIntPair[] var18 = this.g;
        int var16 = var18.length;

        for (int var17 = 0; var17 < var16; ++var17) {
            OChunkCoordIntPair var19 = var18[var17];
            if (var1 == var19.a && var2 == var19.b) {
                System.out.println(var1 + ", " + var2);
                return true;
            }
        }

        return false;
    }

    @Override
    protected List a() {
        ArrayList var1 = new ArrayList();
        OChunkCoordIntPair[] var2 = this.g;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            OChunkCoordIntPair var5 = var2[var4];
            if (var5 != null) {
                var1.add(var5.a(64));
            }
        }

        return var1;
    }

    @Override
    protected OStructureStart b(int var1, int var2) {
        OStructureStrongholdStart var3;
        for (var3 = new OStructureStrongholdStart(this.d, this.c, var1, var2); var3.c().isEmpty() || ((OComponentStrongholdStairs2) var3.c().get(0)).b == null; var3 = new OStructureStrongholdStart(this.d, this.c, var1, var2)) {
            ;
        }

        return var3;
    }
}
