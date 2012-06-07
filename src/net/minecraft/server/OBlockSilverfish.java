package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySilverfish;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockSilverfish extends OBlock {

    public OBlockSilverfish(int var1) {
        super(var1, 1, OMaterial.y);
        this.c(0.0F);
    }

    @Override
    public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
        super.a(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public int a(int var1, int var2) {
        return var2 == 1 ? OBlock.w.bN : (var2 == 2 ? OBlock.bm.bN : OBlock.t.bN);
    }

    @Override
    public void c(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            OEntitySilverfish var6 = new OEntitySilverfish(var1);
            var6.c(var2 + 0.5D, var3, var4 + 0.5D, 0.0F, 0.0F);
            var1.b(var6);
            var6.aC();
        }

        super.c(var1, var2, var3, var4, var5);
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    public static boolean d(int var0) {
        return var0 == OBlock.t.bO || var0 == OBlock.w.bO || var0 == OBlock.bm.bO;
    }

    public static int e(int var0) {
        return var0 == OBlock.w.bO ? 1 : (var0 == OBlock.bm.bO ? 2 : 0);
    }

    @Override
    protected OItemStack a_(int var1) {
        OBlock var2 = OBlock.t;
        if (var1 == 1) {
            var2 = OBlock.w;
        }

        if (var1 == 2) {
            var2 = OBlock.bm;
        }

        return new OItemStack(var2);
    }
}
