package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenLakes extends OWorldGenerator {

    private int a;

    public OWorldGenLakes(int var1) {
        super();
        this.a = var1;
    }

    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        var3 -= 8;

        for (var5 -= 8; var4 > 5 && var1.g(var3, var4, var5); --var4) {
            ;
        }

        if (var4 <= 4) {
            return false;
        } else {
            var4 -= 4;
            boolean[] var6 = new boolean[2048];
            int var7 = var2.nextInt(4) + 4;

            int var8;
            for (var8 = 0; var8 < var7; ++var8) {
                double var9 = var2.nextDouble() * 6.0D + 3.0D;
                double var11 = var2.nextDouble() * 4.0D + 2.0D;
                double var13 = var2.nextDouble() * 6.0D + 3.0D;
                double var15 = var2.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
                double var17 = var2.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
                double var19 = var2.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

                for (int var21 = 1; var21 < 15; ++var21) {
                    for (int var22 = 1; var22 < 15; ++var22) {
                        for (int var23 = 1; var23 < 7; ++var23) {
                            double var24 = (var21 - var15) / (var9 / 2.0D);
                            double var26 = (var23 - var17) / (var11 / 2.0D);
                            double var28 = (var22 - var19) / (var13 / 2.0D);
                            double var30 = var24 * var24 + var26 * var26 + var28 * var28;
                            if (var30 < 1.0D) {
                                var6[(var21 * 16 + var22) * 8 + var23] = true;
                            }
                        }
                    }
                }
            }

            boolean var34;
            int var32;
            int var33;
            for (var8 = 0; var8 < 16; ++var8) {
                for (var32 = 0; var32 < 16; ++var32) {
                    for (var33 = 0; var33 < 8; ++var33) {
                        var34 = !var6[(var8 * 16 + var32) * 8 + var33] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var33] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var33] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var33] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var33] || var33 < 7 && var6[(var8 * 16 + var32) * 8 + var33 + 1] || var33 > 0 && var6[(var8 * 16 + var32) * 8 + (var33 - 1)]);
                        if (var34) {
                            OMaterial var35 = var1.d(var3 + var8, var4 + var33, var5 + var32);
                            if (var33 >= 4 && var35.d()) {
                                return false;
                            }

                            if (var33 < 4 && !var35.a() && var1.a(var3 + var8, var4 + var33, var5 + var32) != this.a) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (var8 = 0; var8 < 16; ++var8) {
                for (var32 = 0; var32 < 16; ++var32) {
                    for (var33 = 0; var33 < 8; ++var33) {
                        if (var6[(var8 * 16 + var32) * 8 + var33]) {
                            var1.b(var3 + var8, var4 + var33, var5 + var32, var33 >= 4 ? 0 : this.a);
                        }
                    }
                }
            }

            for (var8 = 0; var8 < 16; ++var8) {
                for (var32 = 0; var32 < 16; ++var32) {
                    for (var33 = 4; var33 < 8; ++var33) {
                        if (var6[(var8 * 16 + var32) * 8 + var33] && var1.a(var3 + var8, var4 + var33 - 1, var5 + var32) == OBlock.v.bO && var1.a(OEnumSkyBlock.a, var3 + var8, var4 + var33, var5 + var32) > 0) {
                            OBiomeGenBase var37 = var1.a(var3 + var8, var5 + var32);
                            if (var37.A == OBlock.by.bO) {
                                var1.b(var3 + var8, var4 + var33 - 1, var5 + var32, OBlock.by.bO);
                            } else {
                                var1.b(var3 + var8, var4 + var33 - 1, var5 + var32, OBlock.u.bO);
                            }
                        }
                    }
                }
            }

            if (OBlock.m[this.a].cd == OMaterial.h) {
                for (var8 = 0; var8 < 16; ++var8) {
                    for (var32 = 0; var32 < 16; ++var32) {
                        for (var33 = 0; var33 < 8; ++var33) {
                            var34 = !var6[(var8 * 16 + var32) * 8 + var33] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var33] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var33] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var33] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var33] || var33 < 7 && var6[(var8 * 16 + var32) * 8 + var33 + 1] || var33 > 0 && var6[(var8 * 16 + var32) * 8 + (var33 - 1)]);
                            if (var34 && (var33 < 4 || var2.nextInt(2) != 0) && var1.d(var3 + var8, var4 + var33, var5 + var32).a()) {
                                var1.b(var3 + var8, var4 + var33, var5 + var32, OBlock.t.bO);
                            }
                        }
                    }
                }
            }

            if (OBlock.m[this.a].cd == OMaterial.g) {
                for (var8 = 0; var8 < 16; ++var8) {
                    for (var32 = 0; var32 < 16; ++var32) {
                        byte var36 = 4;
                        if (var1.s(var3 + var8, var4 + var36, var5 + var32)) {
                            var1.b(var3 + var8, var4 + var36, var5 + var32, OBlock.aT.bO);
                        }
                    }
                }
            }

            return true;
        }
    }
}
