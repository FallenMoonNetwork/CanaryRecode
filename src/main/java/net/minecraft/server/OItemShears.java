package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class OItemShears extends OItem {

    public OItemShears(int var1) {
        super(var1);
        this.e(1);
        this.f(238);
    }

    @Override
    public boolean a(OItemStack var1, int var2, int var3, int var4, int var5, OEntityLiving var6) {
        if (var2 != OBlock.K.bO && var2 != OBlock.W.bO && var2 != OBlock.X.bO && var2 != OBlock.bu.bO) {
            return super.a(var1, var2, var3, var4, var5, var6);
        } else {
            var1.a(1, var6);
            return true;
        }
    }

    @Override
    public boolean a(OBlock var1) {
        return var1.bO == OBlock.W.bO;
    }

    @Override
    public float a(OItemStack var1, OBlock var2) {
        return var2.bO != OBlock.W.bO && var2.bO != OBlock.K.bO ? (var2.bO == OBlock.ab.bO ? 5.0F : super.a(var1, var2)) : 15.0F;
    }
}
