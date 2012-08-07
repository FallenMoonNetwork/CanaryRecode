package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;

public class ORandomPositionGenerator {

    private static OVec3D a = OVec3D.a(0.0D, 0.0D, 0.0D);

    public ORandomPositionGenerator() {
        super();
    }

    public static OVec3D a(OEntityCreature var0, int var1, int var2) {
        return c(var0, var1, var2, (OVec3D) null);
    }

    public static OVec3D a(OEntityCreature var0, int var1, int var2, OVec3D var3) {
        a.a = var3.a - var0.bm;
        a.b = var3.b - var0.bn;
        a.c = var3.c - var0.bo;
        return c(var0, var1, var2, a);
    }

    public static OVec3D b(OEntityCreature var0, int var1, int var2, OVec3D var3) {
        a.a = var0.bm - var3.a;
        a.b = var0.bn - var3.b;
        a.c = var0.bo - var3.c;
        return c(var0, var1, var2, a);
    }

    private static OVec3D c(OEntityCreature var0, int var1, int var2, OVec3D var3) {
        Random var4 = var0.an();
        boolean var5 = false;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        float var9 = -99999.0F;
        boolean var12;
        if (var0.ay()) {
            double var10 = var0.av().b(OMathHelper.b(var0.bm), OMathHelper.b(var0.bn), OMathHelper.b(var0.bo)) + 4.0D;
            var12 = var10 < (var0.aw() + var1);
        } else {
            var12 = false;
        }

        for (int var13 = 0; var13 < 10; ++var13) {
            int var14 = var4.nextInt(2 * var1) - var1;
            int var15 = var4.nextInt(2 * var2) - var2;
            int var16 = var4.nextInt(2 * var1) - var1;
            if (var3 == null || var14 * var3.a + var16 * var3.c >= 0.0D) {
                var14 += OMathHelper.b(var0.bm);
                var15 += OMathHelper.b(var0.bn);
                var16 += OMathHelper.b(var0.bo);
                if (!var12 || var0.e(var14, var15, var16)) {
                    float var17 = var0.a(var14, var15, var16);
                    if (var17 > var9) {
                        var9 = var17;
                        var6 = var14;
                        var7 = var15;
                        var8 = var16;
                        var5 = true;
                    }
                }
            }
        }

        if (var5) {
            return OVec3D.b(var6, var7, var8);
        } else {
            return null;
        }
    }

}
