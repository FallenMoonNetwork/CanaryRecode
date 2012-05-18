package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OFacing;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemMonsterPlacer extends OItem {

    public OItemMonsterPlacer(int var1) {
        super(var1);
        this.a(true);
    }

    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var3.F) {
            return true;
        } else {
            int var8 = var3.a(var4, var5, var6);
            var4 += OFacing.b[var7];
            var5 += OFacing.c[var7];
            var6 += OFacing.d[var7];
            double var9 = 0.0D;
            if (var7 == 1 && var8 == OBlock.aZ.bO || var8 == OBlock.bB.bO) {
                var9 = 0.5D;
            }

            if (a(var3, var1.h(), var4 + 0.5D, var5 + var9, var6 + 0.5D) && !var2.L.d) {
                --var1.a;
            }

            return true;
        }
    }

    public static boolean a(OWorld var0, int var1, double var2, double var4, double var6) {
        if (!OEntityList.a.containsKey(Integer.valueOf(var1))) {
            return false;
        } else {
            OEntity var8 = OEntityList.a(var1, var0);
            if (var8 != null) {
                var8.c(var2, var4, var6, var0.r.nextFloat() * 360.0F, 0.0F);
                var0.b(var8);
                ((OEntityLiving) var8).az();
            }

            return var8 != null;
        }
    }
}
