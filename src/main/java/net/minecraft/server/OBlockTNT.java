package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityTNTPrimed;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockTNT extends OBlock {

    public OBlockTNT(int var1, int var2) {
        super(var1, var2, OMaterial.s);
    }

    @Override
    public int a(int var1) {
        return var1 == 0 ? this.bN + 2 : (var1 == 1 ? this.bN + 1 : this.bN);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        if (var1.x(var2, var3, var4)) {
            this.c(var1, var2, var3, var4, 1);
            var1.e(var2, var3, var4, 0);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (var5 > 0 && OBlock.m[var5].e() && var1.x(var2, var3, var4)) {
            this.c(var1, var2, var3, var4, 1);
            var1.e(var2, var3, var4, 0);
        }

    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public void a_(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F) {
            OEntityTNTPrimed var5 = new OEntityTNTPrimed(var1, (var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F));

            var5.a = var1.r.nextInt(var5.a / 4) + var5.a / 8;
            var1.b(var5);
        }
    }

    @Override
    public void c(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            if ((var5 & 1) == 0) {
                this.a(var1, var2, var3, var4, new OItemStack(OBlock.am.bO, 1, 0));
            } else {
                OEntityTNTPrimed var6 = new OEntityTNTPrimed(var1, (var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F));

                var1.b(var6);
                var1.a(var6, "random.fuse", 1.0F, 1.0F);
            }

        }
    }

    @Override
    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        super.b(var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var5.U() != null && var5.U().c == OItem.h.bP) {
            this.c(var1, var2, var3, var4, 1);
            var1.e(var2, var3, var4, 0);
            return true;
        } else {
            return super.a(var1, var2, var3, var4, var5);
        }
    }

    @Override
    protected OItemStack a_(int var1) {
        return null;
    }
}
