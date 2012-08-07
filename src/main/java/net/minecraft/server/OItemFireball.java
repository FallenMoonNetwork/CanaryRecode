package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemFireball extends OItem {

    public OItemFireball(int var1) {
        super(var1);
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var3.F) {
            return true;
        } else {
            if (var7 == 0) {
                --var5;
            }

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
            } else {
                int var8 = var3.a(var4, var5, var6);
                if (var8 == 0) {
                    var3.a(var4 + 0.5D, var5 + 0.5D, var6 + 0.5D, "fire.ignite", 1.0F, c.nextFloat() * 0.4F + 0.8F);
                    var3.e(var4, var5, var6, OBlock.ar.bO);
                }

                if (!var2.L.d) {
                    --var1.a;
                }

                return true;
            }
        }
    }
}
