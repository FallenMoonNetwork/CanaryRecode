package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockCauldron extends OBlock {

    public OBlockCauldron(int var1) {
        super(var1, OMaterial.f);
        this.bN = 154;
    }

    @Override
    public int a(int var1, int var2) {
        return var1 == 1 ? 138 : (var1 == 0 ? 155 : 154);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
        float var7 = 0.125F;
        this.a(0.0F, 0.0F, 0.0F, var7, 1.0F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var7);
        super.a(var1, var2, var3, var4, var5, var6);
        this.a(1.0F - var7, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
        this.a(0.0F, 0.0F, 1.0F - var7, 1.0F, 1.0F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
        this.f();
    }

    @Override
    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int c() {
        return 24;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            OItemStack var6 = var5.k.d();
            if (var6 == null) {
                return true;
            } else {
                int var7 = var1.c(var2, var3, var4);
                if (var6.c == OItem.aw.bP) {
                    if (var7 < 3) {
                        if (!var5.L.d) {
                            var5.k.a(var5.k.c, new OItemStack(OItem.av));
                        }

                        var1.c(var2, var3, var4, 3);
                    }

                    return true;
                } else {
                    if (var6.c == OItem.bs.bP && var7 > 0) {
                        OItemStack var8 = new OItemStack(OItem.br, 1, 0);
                        if (!var5.k.a(var8)) {
                            var1.b((new OEntityItem(var1, var2 + 0.5D, var3 + 1.5D, var4 + 0.5D, var8)));
                        }

                        --var6.a;
                        if (var6.a <= 0) {
                            var5.k.a(var5.k.c, (OItemStack) null);
                        }

                        var1.c(var2, var3, var4, var7 - 1);
                    }

                    return true;
                }
            }
        }
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.by.bP;
    }
}
