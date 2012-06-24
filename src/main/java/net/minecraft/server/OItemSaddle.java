package net.minecraft.server;

import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPig;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class OItemSaddle extends OItem {

    public OItemSaddle(int var1) {
        super(var1);
        this.bQ = 1;
    }

    @Override
    public void a(OItemStack var1, OEntityLiving var2) {
        if (var2 instanceof OEntityPig) {
            OEntityPig var3 = (OEntityPig) var2;
            if (!var3.A() && !var3.aO()) {
                var3.a(true);
                --var1.a;
            }
        }

    }

    @Override
    public boolean a(OItemStack var1, OEntityLiving var2, OEntityLiving var3) {
        this.a(var1, var2);
        return true;
    }
}
