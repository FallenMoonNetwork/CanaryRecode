package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;


public class OWorldGenForest extends OWorldGenerator {

    public OWorldGenForest(boolean var1) {
        super(var1);
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        int var6 = var2.nextInt(3) + 5;
        boolean var7 = true;

        if (var4 >= 1 && var4 + var6 + 1 <= 256) {
            int var8;
            int var10;
            int var11;
            int var12;

            for (var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
                byte var9 = 1;

                if (var8 == var4) {
                    var9 = 0;
                }

                if (var8 >= var4 + 1 + var6 - 2) {
                    var9 = 2;
                }

                for (var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
                    for (var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                        if (var8 >= 0 && var8 < 256) {
                            var12 = var1.a(var10, var8, var11);
                            if (var12 != 0 && var12 != OBlock.K.bO) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7) {
                return false;
            } else {
                var8 = var1.a(var3, var4 - 1, var5);
                if ((var8 == OBlock.u.bO || var8 == OBlock.v.bO) && var4 < 256 - var6 - 1) {
                    this.a(var1, var3, var4 - 1, var5, OBlock.v.bO);

                    int var16;

                    for (var16 = var4 - 3 + var6; var16 <= var4 + var6; ++var16) {
                        var10 = var16 - (var4 + var6);
                        var11 = 1 - var10 / 2;

                        for (var12 = var3 - var11; var12 <= var3 + var11; ++var12) {
                            int var13 = var12 - var3;

                            for (int var14 = var5 - var11; var14 <= var5 + var11; ++var14) {
                                int var15 = var14 - var5;

                                if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || var2.nextInt(2) != 0 && var10 != 0) && !OBlock.n[var1.a(var12, var16, var14)]) {
                                    this.a(var1, var12, var16, var14, OBlock.K.bO, 2);
                                }
                            }
                        }
                    }

                    for (var16 = 0; var16 < var6; ++var16) {
                        var10 = var1.a(var3, var4 + var16, var5);
                        if (var10 == 0 || var10 == OBlock.K.bO) {
                            this.a(var1, var3, var4 + var16, var5, OBlock.J.bO, 2);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
