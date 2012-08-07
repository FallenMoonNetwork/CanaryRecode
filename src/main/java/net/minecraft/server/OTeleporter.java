package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OTeleporter {

    private Random a = new Random();

    public OTeleporter() {
        super();
    }

    public void a(OWorld var1, OEntity var2) {
        if (var1.t.g != 1) {
            if (!this.b(var1, var2)) {
                this.c(var1, var2);
                this.b(var1, var2);
            }
        } else {
            int var3 = OMathHelper.b(var2.bm);
            int var4 = OMathHelper.b(var2.bn) - 1;
            int var5 = OMathHelper.b(var2.bo);
            byte var6 = 1;
            byte var7 = 0;

            for (int var8 = -2; var8 <= 2; ++var8) {
                for (int var9 = -2; var9 <= 2; ++var9) {
                    for (int var10 = -1; var10 < 3; ++var10) {
                        int var11 = var3 + var9 * var6 + var8 * var7;
                        int var12 = var4 + var10;
                        int var13 = var5 + var9 * var7 - var8 * var6;
                        boolean var14 = var10 < 0;
                        var1.e(var11, var12, var13, var14 ? OBlock.ap.bO : 0);
                    }
                }
            }

            var2.c(var3, var4, var5, var2.bs, 0.0F);
            var2.bp = var2.bq = var2.br = 0.0D;
        }
    }

    public boolean b(OWorld var1, OEntity var2) {
        short var3 = 128;
        double var4 = -1.0D;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        int var9 = OMathHelper.b(var2.bm);
        int var10 = OMathHelper.b(var2.bo);

        double var18;
        for (int var11 = var9 - var3; var11 <= var9 + var3; ++var11) {
            double var12 = var11 + 0.5D - var2.bm;

            for (int var14 = var10 - var3; var14 <= var10 + var3; ++var14) {
                double var15 = var14 + 0.5D - var2.bo;

                for (int var17 = 127; var17 >= 0; --var17) {
                    if (var1.a(var11, var17, var14) == OBlock.be.bO) {
                        while (var1.a(var11, var17 - 1, var14) == OBlock.be.bO) {
                            --var17;
                        }

                        var18 = var17 + 0.5D - var2.bn;
                        double var20 = var12 * var12 + var18 * var18 + var15 * var15;
                        if (var4 < 0.0D || var20 < var4) {
                            var4 = var20;
                            var6 = var11;
                            var7 = var17;
                            var8 = var14;
                        }
                    }
                }
            }
        }

        if (var4 >= 0.0D) {
            double var24 = var6 + 0.5D;
            double var26 = var7 + 0.5D;
            var18 = var8 + 0.5D;
            if (var1.a(var6 - 1, var7, var8) == OBlock.be.bO) {
                var24 -= 0.5D;
            }

            if (var1.a(var6 + 1, var7, var8) == OBlock.be.bO) {
                var24 += 0.5D;
            }

            if (var1.a(var6, var7, var8 - 1) == OBlock.be.bO) {
                var18 -= 0.5D;
            }

            if (var1.a(var6, var7, var8 + 1) == OBlock.be.bO) {
                var18 += 0.5D;
            }

            var2.c(var24, var26, var18, var2.bs, 0.0F);
            var2.bp = var2.bq = var2.br = 0.0D;
            return true;
        } else {
            return false;
        }
    }

    public boolean c(OWorld var1, OEntity var2) {
        byte var3 = 16;
        double var4 = -1.0D;
        int var6 = OMathHelper.b(var2.bm);
        int var7 = OMathHelper.b(var2.bn);
        int var8 = OMathHelper.b(var2.bo);
        int var9 = var6;
        int var10 = var7;
        int var11 = var8;
        int var12 = 0;
        int var13 = this.a.nextInt(4);

        int var14;
        double var15;
        int var17;
        double var18;
        int var21;
        int var20;
        int var23;
        int var22;
        int var25;
        int var24;
        int var27;
        int var26;
        int var28;
        double var30;
        double var32;
        for (var14 = var6 - var3; var14 <= var6 + var3; ++var14) {
            var15 = var14 + 0.5D - var2.bm;

            for (var17 = var8 - var3; var17 <= var8 + var3; ++var17) {
                var18 = var17 + 0.5D - var2.bo;

                label274: for (var20 = 127; var20 >= 0; --var20) {
                    if (var1.g(var14, var20, var17)) {
                        while (var20 > 0 && var1.g(var14, var20 - 1, var17)) {
                            --var20;
                        }

                        for (var21 = var13; var21 < var13 + 4; ++var21) {
                            var22 = var21 % 2;
                            var23 = 1 - var22;
                            if (var21 % 4 >= 2) {
                                var22 = -var22;
                                var23 = -var23;
                            }

                            for (var24 = 0; var24 < 3; ++var24) {
                                for (var25 = 0; var25 < 4; ++var25) {
                                    for (var26 = -1; var26 < 4; ++var26) {
                                        var27 = var14 + (var25 - 1) * var22 + var24 * var23;
                                        var28 = var20 + var26;
                                        int var29 = var17 + (var25 - 1) * var23 - var24 * var22;
                                        if (var26 < 0 && !var1.d(var27, var28, var29).a() || var26 >= 0 && !var1.g(var27, var28, var29)) {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            var30 = var20 + 0.5D - var2.bn;
                            var32 = var15 * var15 + var30 * var30 + var18 * var18;
                            if (var4 < 0.0D || var32 < var4) {
                                var4 = var32;
                                var9 = var14;
                                var10 = var20;
                                var11 = var17;
                                var12 = var21 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (var4 < 0.0D) {
            for (var14 = var6 - var3; var14 <= var6 + var3; ++var14) {
                var15 = var14 + 0.5D - var2.bm;

                for (var17 = var8 - var3; var17 <= var8 + var3; ++var17) {
                    var18 = var17 + 0.5D - var2.bo;

                    label222: for (var20 = 127; var20 >= 0; --var20) {
                        if (var1.g(var14, var20, var17)) {
                            while (var20 > 0 && var1.g(var14, var20 - 1, var17)) {
                                --var20;
                            }

                            for (var21 = var13; var21 < var13 + 2; ++var21) {
                                var22 = var21 % 2;
                                var23 = 1 - var22;

                                for (var24 = 0; var24 < 4; ++var24) {
                                    for (var25 = -1; var25 < 4; ++var25) {
                                        var26 = var14 + (var24 - 1) * var22;
                                        var27 = var20 + var25;
                                        var28 = var17 + (var24 - 1) * var23;
                                        if (var25 < 0 && !var1.d(var26, var27, var28).a() || var25 >= 0 && !var1.g(var26, var27, var28)) {
                                            continue label222;
                                        }
                                    }
                                }

                                var30 = var20 + 0.5D - var2.bn;
                                var32 = var15 * var15 + var30 * var30 + var18 * var18;
                                if (var4 < 0.0D || var32 < var4) {
                                    var4 = var32;
                                    var9 = var14;
                                    var10 = var20;
                                    var11 = var17;
                                    var12 = var21 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int var34 = var9;
        int var35 = var10;
        var17 = var11;
        int var36 = var12 % 2;
        int var37 = 1 - var36;
        if (var12 % 4 >= 2) {
            var36 = -var36;
            var37 = -var37;
        }

        boolean var38;
        if (var4 < 0.0D) {
            if (var10 < 70) {
                var10 = 70;
            }

            if (var10 > 118) {
                var10 = 118;
            }

            var35 = var10;

            for (var20 = -1; var20 <= 1; ++var20) {
                for (var21 = 1; var21 < 3; ++var21) {
                    for (var22 = -1; var22 < 3; ++var22) {
                        var23 = var34 + (var21 - 1) * var36 + var20 * var37;
                        var24 = var35 + var22;
                        var25 = var17 + (var21 - 1) * var37 - var20 * var36;
                        var38 = var22 < 0;
                        var1.e(var23, var24, var25, var38 ? OBlock.ap.bO : 0);
                    }
                }
            }
        }

        for (var20 = 0; var20 < 4; ++var20) {
            var1.o = true;

            for (var21 = 0; var21 < 4; ++var21) {
                for (var22 = -1; var22 < 4; ++var22) {
                    var23 = var34 + (var21 - 1) * var36;
                    var24 = var35 + var22;
                    var25 = var17 + (var21 - 1) * var37;
                    var38 = var21 == 0 || var21 == 3 || var22 == -1 || var22 == 3;
                    var1.e(var23, var24, var25, var38 ? OBlock.ap.bO : OBlock.be.bO);
                }
            }

            var1.o = false;

            for (var21 = 0; var21 < 4; ++var21) {
                for (var22 = -1; var22 < 4; ++var22) {
                    var23 = var34 + (var21 - 1) * var36;
                    var24 = var35 + var22;
                    var25 = var17 + (var21 - 1) * var37;
                    var1.h(var23, var24, var25, var1.a(var23, var24, var25));
                }
            }
        }

        return true;
    }
}
