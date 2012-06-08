package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OTileEntitySign;
import net.minecraft.server.OWorld;

public class OItemSign extends OItem {

    public OItemSign(int var1) {
        super(var1);
        this.bQ = 1;
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var7 == 0) {
            return false;
        } else if (!var3.d(var4, var5, var6).a()) {
            return false;
        } else {
            if (var7 == 1) {
                ++var5;
            }

            if (var7 == 2) {
                --var6;
            }

            if (var7 == 3) {
                ++var6;
            }

            if (var7 == 4) {
                --var4;
            }

            if (var7 == 5) {
                ++var4;
            }

            if (!var2.d(var4, var5, var6)) {
                return false;
            } else if (!OBlock.aD.c(var3, var4, var5, var6)) {
                return false;
            } else {
                if (var7 == 1) {
                    int var8 = OMathHelper.b(((var2.bs + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    var3.b(var4, var5, var6, OBlock.aD.bO, var8);
                } else {
                    var3.b(var4, var5, var6, OBlock.aI.bO, var7);
                }

                --var1.a;
                OTileEntitySign var9 = (OTileEntitySign) var3.b(var4, var5, var6);
                if (var9 != null) {
                    var2.a(var9);
                }

                return true;
            }
        }
    }
}
