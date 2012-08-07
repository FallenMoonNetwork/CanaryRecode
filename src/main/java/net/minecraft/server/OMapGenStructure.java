package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OMapGenBase;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OWorld;

public abstract class OMapGenStructure extends OMapGenBase {

    protected HashMap e = new HashMap();

    public OMapGenStructure() {
        super();
    }

    @Override
    public void a(OIChunkProvider var1, OWorld var2, int var3, int var4, byte[] var5) {
        super.a(var1, var2, var3, var4, var5);
    }

    @Override
    protected void a(OWorld var1, int var2, int var3, int var4, int var5, byte[] var6) {
        if (!this.e.containsKey(Long.valueOf(OChunkCoordIntPair.a(var2, var3)))) {
            this.c.nextInt();
            if (this.a(var2, var3)) {
                OStructureStart var7 = this.b(var2, var3);
                this.e.put(Long.valueOf(OChunkCoordIntPair.a(var2, var3)), var7);
            }

        }
    }

    public boolean a(OWorld var1, Random var2, int var3, int var4) {
        int var5 = (var3 << 4) + 8;
        int var6 = (var4 << 4) + 8;
        boolean var7 = false;
        Iterator var8 = this.e.values().iterator();

        while (var8.hasNext()) {
            OStructureStart var9 = (OStructureStart) var8.next();
            if (var9.a() && var9.b().a(var5, var6, var5 + 15, var6 + 15)) {
                var9.a(var1, var2, new OStructureBoundingBox(var5, var6, var5 + 15, var6 + 15));
                var7 = true;
            }
        }

        return var7;
    }

    public boolean a(int var1, int var2, int var3) {
        Iterator var4 = this.e.values().iterator();

        while (var4.hasNext()) {
            OStructureStart var5 = (OStructureStart) var4.next();
            if (var5.a() && var5.b().a(var1, var3, var1, var3)) {
                Iterator var6 = var5.c().iterator();

                while (var6.hasNext()) {
                    OStructureComponent var7 = (OStructureComponent) var6.next();
                    if (var7.b().b(var1, var2, var3)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public OChunkPosition a(OWorld var1, int var2, int var3, int var4) {
        this.d = var1;
        this.c.setSeed(var1.n());
        long var5 = this.c.nextLong();
        long var7 = this.c.nextLong();
        long var9 = (var2 >> 4) * var5;
        long var11 = (var4 >> 4) * var7;
        this.c.setSeed(var9 ^ var11 ^ var1.n());
        this.a(var1, var2 >> 4, var4 >> 4, 0, 0, (byte[]) null);
        double var13 = Double.MAX_VALUE;
        OChunkPosition var15 = null;
        Iterator var16 = this.e.values().iterator();

        OChunkPosition var19;
        int var21;
        int var20;
        double var23;
        int var22;
        while (var16.hasNext()) {
            OStructureStart var17 = (OStructureStart) var16.next();
            if (var17.a()) {
                OStructureComponent var18 = (OStructureComponent) var17.c().get(0);
                var19 = var18.b_();
                var20 = var19.a - var2;
                var21 = var19.b - var3;
                var22 = var19.c - var4;
                var23 = (var20 + var20 * var21 * var21 + var22 * var22);
                if (var23 < var13) {
                    var13 = var23;
                    var15 = var19;
                }
            }
        }

        if (var15 != null) {
            return var15;
        } else {
            List var25 = this.a();
            if (var25 != null) {
                OChunkPosition var26 = null;
                Iterator var27 = var25.iterator();

                while (var27.hasNext()) {
                    var19 = (OChunkPosition) var27.next();
                    var20 = var19.a - var2;
                    var21 = var19.b - var3;
                    var22 = var19.c - var4;
                    var23 = (var20 + var20 * var21 * var21 + var22 * var22);
                    if (var23 < var13) {
                        var13 = var23;
                        var26 = var19;
                    }
                }

                return var26;
            } else {
                return null;
            }
        }
    }

    protected List a() {
        return null;
    }

    protected abstract boolean a(int var1, int var2);

    protected abstract OStructureStart b(int var1, int var2);
}
