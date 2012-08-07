package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemBow extends OItem {

    public OItemBow(int var1) {
        super(var1);
        this.bQ = 1;
        this.f(384);
    }

    @Override
    public void a(OItemStack var1, OWorld var2, OEntityPlayer var3, int var4) {
        boolean var5 = var3.L.d || OEnchantmentHelper.a(OEnchantment.w.x, var1) > 0;
        if (var5 || var3.k.hasItem(OItem.k.bP)) {
            int var6 = this.c(var1) - var4;
            float var7 = var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
            if (var7 < 0.1D) {
                return;
            }

            if (var7 > 1.0F) {
                var7 = 1.0F;
            }

            OEntityArrow var8 = new OEntityArrow(var2, var3, var7 * 2.0F);
            if (var7 == 1.0F) {
                var8.d = true;
            }

            int var9 = OEnchantmentHelper.a(OEnchantment.t.x, var1);
            if (var9 > 0) {
                var8.a(var8.k() + var9 * 0.5D + 0.5D);
            }

            int var10 = OEnchantmentHelper.a(OEnchantment.u.x, var1);
            if (var10 > 0) {
                var8.b(var10);
            }

            if (OEnchantmentHelper.a(OEnchantment.v.x, var1) > 0) {
                var8.i(100);
            }

            var1.a(1, var3);
            var2.a(var3, "random.bow", 1.0F, 1.0F / (c.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
            if (!var5) {
                var3.k.c(OItem.k.bP);
            } else {
                var8.a = false;
            }

            if (!var2.F) {
                var2.b(var8);
            }
        }

    }

    @Override
    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        return var1;
    }

    @Override
    public int c(OItemStack var1) {
        return 72000;
    }

    @Override
    public OEnumAction d(OItemStack var1) {
        return OEnumAction.e;
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        if (var3.L.d || var3.k.hasItem(OItem.k.bP)) {
            var3.a(var1, this.c(var1));
        }

        return var1;
    }

    @Override
    public int c() {
        return 1;
    }
}
