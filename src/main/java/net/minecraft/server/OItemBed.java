package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockBed;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OItemBed extends OItem {

    public OItemBed(int var1) {
        super(var1);
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var7 != 1) {
            return false;
        } else {
            ++var5;
            OBlockBed var8 = (OBlockBed) OBlock.S;
            int var9 = OMathHelper.b((var2.bs * 4.0F / 360.0F) + 0.5D) & 3;
            byte var10 = 0;
            byte var11 = 0;
            if (var9 == 0) {
                var11 = 1;
            }

            if (var9 == 1) {
                var10 = -1;
            }

            if (var9 == 2) {
                var11 = -1;
            }

            if (var9 == 3) {
                var10 = 1;
            }

            if (var2.d(var4, var5, var6) && var2.d(var4 + var10, var5, var6 + var11)) {
                if (var3.g(var4, var5, var6) && var3.g(var4 + var10, var5, var6 + var11) && var3.e(var4, var5 - 1, var6) && var3.e(var4 + var10, var5 - 1, var6 + var11)) {
                    var3.b(var4, var5, var6, var8.bO, var9);
                    if (var3.a(var4, var5, var6) == var8.bO) {
                        var3.b(var4 + var10, var5, var6 + var11, var8.bO, var9 + 8);
                    }

                    --var1.a;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
