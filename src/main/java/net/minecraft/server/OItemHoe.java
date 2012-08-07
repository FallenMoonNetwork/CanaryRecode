package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemHoe extends OItem {

    public OItemHoe(int var1, OEnumToolMaterial var2) {
        super(var1);
        this.bQ = 1;
        this.f(var2.a());
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (!var2.d(var4, var5, var6)) {
            return false;
        } else {
            int var8 = var3.a(var4, var5, var6);
            int var9 = var3.a(var4, var5 + 1, var6);
            if ((var7 == 0 || var9 != 0 || var8 != OBlock.u.bO) && var8 != OBlock.v.bO) {
                return false;
            } else {
                OBlock var10 = OBlock.aA;
                var3.a((var4 + 0.5F), (var5 + 0.5F), (var6 + 0.5F), var10.cb.c(), (var10.cb.a() + 1.0F) / 2.0F, var10.cb.b() * 0.8F);
                if (var3.F) {
                    return true;
                } else {
                    var3.e(var4, var5, var6, var10.bO);
                    var1.a(1, var2);
                    return true;
                }
            }
        }
    }
}
