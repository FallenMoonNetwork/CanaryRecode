package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemRedstone extends OItem {

    public OItemRedstone(int var1) {
        super(var1);
    }

    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (var3.a(var4, var5, var6) != OBlock.aS.bO) {
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

            if (!var3.g(var4, var5, var6)) {
                return false;
            }
        }

        if (!var2.d(var4, var5, var6)) {
            return false;
        } else {
            if (OBlock.av.c(var3, var4, var5, var6)) {
                --var1.a;
                var3.e(var4, var5, var6, OBlock.av.bO);
            }

            return true;
        }
    }
}
