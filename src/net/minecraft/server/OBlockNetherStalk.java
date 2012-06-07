package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBiomeGenHell;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OBlockNetherStalk extends OBlockFlower {

    protected OBlockNetherStalk(int var1) {
        super(var1, 226);
        this.a(true);
        float var2 = 0.5F;
        this.a(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
    }

    @Override
    protected boolean d(int var1) {
        return var1 == OBlock.bc.bO;
    }

    @Override
    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return this.d(var1.a(var2, var3 - 1, var4));
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.c(var2, var3, var4);
        if (var6 < 3) {
            OBiomeGenBase var7 = var1.a(var2, var4);
            if (var7 instanceof OBiomeGenHell && var5.nextInt(10) == 0) {
                ++var6;
                var1.c(var2, var3, var4, var6);
            }
        }

        super.a(var1, var2, var3, var4, var5);
    }

    @Override
    public int a(int var1, int var2) {
        return var2 >= 3 ? this.bN + 2 : (var2 > 0 ? this.bN + 1 : this.bN);
    }

    @Override
    public int c() {
        return 6;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!var1.F) {
            int var8 = 1;
            if (var5 >= 3) {
                var8 = 2 + var1.r.nextInt(3);
                if (var7 > 0) {
                    var8 += var1.r.nextInt(var7 + 1);
                }
            }

            for (int var9 = 0; var9 < var8; ++var9) {
                this.a(var1, var2, var3, var4, new OItemStack(OItem.bq));
            }

        }
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }
}
