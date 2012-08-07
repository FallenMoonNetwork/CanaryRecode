package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityCow;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMovingObjectType;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;


public class OItemBucket extends OItem {

    private int a;

    public OItemBucket(int var1, int var2) {
        super(var1);
        this.bQ = 1;
        this.a = var2;
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        float var4 = 1.0F;
        double var5 = var3.bj + (var3.bm - var3.bj) * var4;
        double var7 = var3.bk + (var3.bn - var3.bk) * var4 + 1.62D - var3.bF;
        double var9 = var3.bl + (var3.bo - var3.bl) * var4;
        boolean var11 = this.a == 0;
        OMovingObjectPosition var12 = this.a(var2, var3, var11);

        if (var12 == null) {
            return var1;
        } else {
            if (var12.a == OEnumMovingObjectType.a) {
                int var13 = var12.b;
                int var14 = var12.c;
                int var15 = var12.d;

                if (!var2.a(var3, var13, var14, var15)) {
                    return var1;
                }

                if (this.a == 0) {
                    if (!var3.d(var13, var14, var15)) {
                        return var1;
                    }

                    if (var2.d(var13, var14, var15) == OMaterial.g && var2.c(var13, var14, var15) == 0) {
                        var2.e(var13, var14, var15, 0);
                        if (var3.L.d) {
                            return var1;
                        }

                        return new OItemStack(OItem.aw);
                    }

                    if (var2.d(var13, var14, var15) == OMaterial.h && var2.c(var13, var14, var15) == 0) {
                        var2.e(var13, var14, var15, 0);
                        if (var3.L.d) {
                            return var1;
                        }

                        return new OItemStack(OItem.ax);
                    }
                } else {
                    if (this.a < 0) {
                        return new OItemStack(OItem.av);
                    }

                    if (var12.e == 0) {
                        --var14;
                    }

                    if (var12.e == 1) {
                        ++var14;
                    }

                    if (var12.e == 2) {
                        --var15;
                    }

                    if (var12.e == 3) {
                        ++var15;
                    }

                    if (var12.e == 4) {
                        --var13;
                    }

                    if (var12.e == 5) {
                        ++var13;
                    }

                    if (!var3.d(var13, var14, var15)) {
                        return var1;
                    }

                    if (var2.g(var13, var14, var15) || !var2.d(var13, var14, var15).a()) {
                        if (var2.t.d && this.a == OBlock.A.bO) {
                            var2.a(var5 + 0.5D, var7 + 0.5D, var9 + 0.5D, "random.fizz", 0.5F, 2.6F + (var2.r.nextFloat() - var2.r.nextFloat()) * 0.8F);

                            for (int var16 = 0; var16 < 8; ++var16) {
                                var2.a("largesmoke", var13 + Math.random(), var14 + Math.random(), var15 + Math.random(), 0.0D, 0.0D, 0.0D);
                            }
                        } else {
                            var2.b(var13, var14, var15, this.a, 0);
                        }

                        if (var3.L.d) {
                            return var1;
                        }

                        return new OItemStack(OItem.av);
                    }
                }
            } else if (this.a == 0 && var12.g instanceof OEntityCow) {
                return new OItemStack(OItem.aF);
            }

            return var1;
        }
    }
}
