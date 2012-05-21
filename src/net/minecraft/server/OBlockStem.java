package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OBlockStem extends OBlockFlower {

    private OBlock a;

    protected OBlockStem(int var1, OBlock var2) {
        super(var1, 111);
        this.a = var2;
        this.a(true);
        float var3 = 0.125F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
    }

    protected boolean d(int var1) {
        return var1 == OBlock.aA.bO;
    }

    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        super.a(var1, var2, var3, var4, var5);
        if (var1.n(var2, var3 + 1, var4) >= 9) {
            float var6 = this.i(var1, var2, var3, var4);
            if (var5.nextInt((int) (25.0F / var6) + 1) == 0) {
                int var7 = var1.c(var2, var3, var4);
                if (var7 < 7) {
                    ++var7;
                    var1.c(var2, var3, var4, var7);
                } else {
                    if (var1.a(var2 - 1, var3, var4) == this.a.bO) {
                        return;
                    }

                    if (var1.a(var2 + 1, var3, var4) == this.a.bO) {
                        return;
                    }

                    if (var1.a(var2, var3, var4 - 1) == this.a.bO) {
                        return;
                    }

                    if (var1.a(var2, var3, var4 + 1) == this.a.bO) {
                        return;
                    }

                    int var8 = var5.nextInt(4);
                    int var9 = var2;
                    int var10 = var4;
                    if (var8 == 0) {
                        var9 = var2 - 1;
                    }

                    if (var8 == 1) {
                        ++var9;
                    }

                    if (var8 == 2) {
                        var10 = var4 - 1;
                    }

                    if (var8 == 3) {
                        ++var10;
                    }

                    int var11 = var1.a(var9, var3 - 1, var10);
                    if (var1.a(var9, var3, var10) == 0 && (var11 == OBlock.aA.bO || var11 == OBlock.v.bO || var11 == OBlock.u.bO)) {
                        var1.e(var9, var3, var10, this.a.bO);
                    }
                }
            }
        }

    }

    public void g(OWorld var1, int var2, int var3, int var4) {
        var1.c(var2, var3, var4, 7);
    }

    private float i(OWorld var1, int var2, int var3, int var4) {
        float var5 = 1.0F;
        int var6 = var1.a(var2, var3, var4 - 1);
        int var7 = var1.a(var2, var3, var4 + 1);
        int var8 = var1.a(var2 - 1, var3, var4);
        int var9 = var1.a(var2 + 1, var3, var4);
        int var10 = var1.a(var2 - 1, var3, var4 - 1);
        int var11 = var1.a(var2 + 1, var3, var4 - 1);
        int var12 = var1.a(var2 + 1, var3, var4 + 1);
        int var13 = var1.a(var2 - 1, var3, var4 + 1);
        boolean var14 = var8 == this.bO || var9 == this.bO;
        boolean var15 = var6 == this.bO || var7 == this.bO;
        boolean var16 = var10 == this.bO || var11 == this.bO || var12 == this.bO || var13 == this.bO;

        for (int var17 = var2 - 1; var17 <= var2 + 1; ++var17) {
            for (int var18 = var4 - 1; var18 <= var4 + 1; ++var18) {
                int var19 = var1.a(var17, var3 - 1, var18);
                float var20 = 0.0F;
                if (var19 == OBlock.aA.bO) {
                    var20 = 1.0F;
                    if (var1.c(var17, var3 - 1, var18) > 0) {
                        var20 = 3.0F;
                    }
                }

                if (var17 != var2 || var18 != var4) {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15) {
            var5 /= 2.0F;
        }

        return var5;
    }

    public int a(int var1, int var2) {
        return this.bN;
    }

    public void f() {
        float var1 = 0.125F;
        this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
    }

    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        this.bZ = ((var1.c(var2, var3, var4) * 2 + 2) / 16.0F);
        float var5 = 0.125F;
        this.a(0.5F - var5, 0.0F, 0.5F - var5, 0.5F + var5, (float) this.bZ, 0.5F + var5);
    }

    public int c() {
        return 19;
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        super.a(var1, var2, var3, var4, var5, var6, var7);
        if (!var1.F) {
            OItem var8 = null;
            if (this.a == OBlock.ba) {
                var8 = OItem.bf;
            }

            if (this.a == OBlock.br) {
                var8 = OItem.bg;
            }

            for (int var9 = 0; var9 < 3; ++var9) {
                if (var1.r.nextInt(15) <= var5) {
                    float var10 = 0.7F;
                    float var11 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
                    float var12 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
                    float var13 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
                    OEntityItem var14 = new OEntityItem(var1, (var2 + var11), (var3 + var12), (var4 + var13), new OItemStack(var8));
                    var14.c = 10;
                    var1.b(var14);
                }
            }

        }
    }

    public int a(int var1, Random var2, int var3) {
        if (var1 == 7) {
            ;
        }

        return -1;
    }

    public int a(Random var1) {
        return 1;
    }
}
