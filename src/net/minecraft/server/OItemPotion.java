package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPotion;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OPotionHelper;
import net.minecraft.server.OWorld;

public class OItemPotion extends OItem {

    private HashMap a = new HashMap();

    public OItemPotion(int var1) {
        super(var1);
        this.e(1);
        this.a(true);
        this.f(0);
    }

    public List b(OItemStack var1) {
        return this.b(var1.h());
    }

    public List b(int var1) {
        List var2 = (List) this.a.get(Integer.valueOf(var1));
        if (var2 == null) {
            var2 = OPotionHelper.a(var1, false);
            this.a.put(Integer.valueOf(var1), var2);
        }

        return var2;
    }

    @Override
    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        --var1.a;
        if (!var2.F) {
            List var4 = this.b(var1);
            if (var4 != null) {
                Iterator var5 = var4.iterator();

                while (var5.hasNext()) {
                    OPotionEffect var6 = (OPotionEffect) var5.next();
                    var3.e(new OPotionEffect(var6));
                }
            }
        }

        if (var1.a <= 0) {
            return new OItemStack(OItem.bs);
        } else {
            var3.k.addItemToBackPack(new OItemStack(OItem.bs));
            return var1;
        }
    }

    @Override
    public int c(OItemStack var1) {
        return 32;
    }

    @Override
    public OEnumAction d(OItemStack var1) {
        return OEnumAction.c;
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        if (c(var1.h())) {
            --var1.a;
            var2.a(var3, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
            if (!var2.F) {
                var2.b((new OEntityPotion(var2, var3, var1.h())));
            }

            return var1;
        } else {
            var3.a(var1, this.c(var1));
            return var1;
        }
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        return false;
    }

    public static boolean c(int var0) {
        return (var0 & 16384) != 0;
    }
}
