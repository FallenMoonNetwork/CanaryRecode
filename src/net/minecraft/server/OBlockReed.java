package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockReed extends OBlock {

    protected OBlockReed(int var1, int var2) {
        super(var1, OMaterial.j);
        this.bN = var2;
        float var3 = 0.375F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
        this.a(true);
    }

    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (var1.g(var2, var3 + 1, var4)) {
            int var6;
            for (var6 = 1; var1.a(var2, var3 - var6, var4) == this.bO; ++var6) {
                ;
            }

            if (var6 < 3) {
                int var7 = var1.c(var2, var3, var4);
                if (var7 == 15) {
                    var1.e(var2, var3 + 1, var4, this.bO);
                    var1.c(var2, var3, var4, 0);
                } else {
                    var1.c(var2, var3, var4, var7 + 1);
                }
            }
        }

    }

    public boolean c(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.a(var2, var3 - 1, var4);
        return var5 == this.bO ? true : (var5 != OBlock.u.bO && var5 != OBlock.v.bO && var5 != OBlock.E.bO ? false : (var1.d(var2 - 1, var3 - 1, var4) == OMaterial.g ? true : (var1.d(var2 + 1, var3 - 1, var4) == OMaterial.g ? true : (var1.d(var2, var3 - 1, var4 - 1) == OMaterial.g ? true : var1.d(var2, var3 - 1, var4 + 1) == OMaterial.g))));
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        this.g(var1, var2, var3, var4);
    }

    protected final void g(OWorld var1, int var2, int var3, int var4) {
        if (!this.f(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }

    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return this.c(var1, var2, var3, var4);
    }

    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    public int a(int var1, Random var2, int var3) {
        return OItem.aI.bP;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 1;
    }
}
