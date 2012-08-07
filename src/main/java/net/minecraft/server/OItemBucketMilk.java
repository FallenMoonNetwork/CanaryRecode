package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemBucketMilk extends OItem {

    public OItemBucketMilk(int var1) {
        super(var1);
        this.e(1);
    }

    @Override
    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        --var1.a;
        if (!var2.F) {
            var3.aL();
        }

        return var1.a <= 0 ? new OItemStack(OItem.av) : var1;
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
        var3.a(var1, this.c(var1));
        return var1;
    }
}
