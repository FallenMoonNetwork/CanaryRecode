package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMapGenBase;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OMapGenRavine extends OMapGenBase {

    private float[] a = new float[1024];

    public OMapGenRavine() {
        super();
    }

    protected void a(long var1, int var3, int var4, byte[] var5, double var6, double var8, double var10, float var12, float var13, float var14, int var15, int var16, double var17) {
        Random var19 = new Random(var1);
        double var20 = (var3 * 16 + 8);
        double var22 = (var4 * 16 + 8);
        float var24 = 0.0F;
        float var25 = 0.0F;
        if (var16 <= 0) {
            int var26 = this.b * 16 - 16;
            var16 = var26 - var19.nextInt(var26 / 4);
        }

        boolean var64 = false;
        if (var15 == -1) {
            var15 = var16 / 2;
            var64 = true;
        }

        float var27 = 1.0F;

        for (int var28 = 0; var28 < 128; ++var28) {
            if (var28 == 0 || var19.nextInt(3) == 0) {
                var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
            }

            this.a[var28] = var27 * var27;
        }

        for (; var15 < var16; ++var15) {
            double var29 = 1.5D + (OMathHelper.a(var15 * 3.1415927F / var16) * var12 * 1.0F);
            double var31 = var29 * var17;
            var29 *= var19.nextFloat() * 0.25D + 0.75D;
            var31 *= var19.nextFloat() * 0.25D + 0.75D;
            float var33 = OMathHelper.b(var14);
            float var34 = OMathHelper.a(var14);
            var6 += (OMathHelper.b(var13) * var33);
            var8 += var34;
            var10 += (OMathHelper.a(var13) * var33);
            var14 *= 0.7F;
            var14 += var25 * 0.05F;
            var13 += var24 * 0.05F;
            var25 *= 0.8F;
            var24 *= 0.5F;
            var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
            var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;
            if (var64 || var19.nextInt(4) != 0) {
                double var35 = var6 - var20;
                double var37 = var10 - var22;
                double var39 = (var16 - var15);
                double var41 = (var12 + 2.0F + 16.0F);
                if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
                    return;
                }

                if (var6 >= var20 - 16.0D - var29 * 2.0D && var10 >= var22 - 16.0D - var29 * 2.0D && var6 <= var20 + 16.0D + var29 * 2.0D && var10 <= var22 + 16.0D + var29 * 2.0D) {
                    int var43 = OMathHelper.b(var6 - var29) - var3 * 16 - 1;
                    int var44 = OMathHelper.b(var6 + var29) - var3 * 16 + 1;
                    int var45 = OMathHelper.b(var8 - var31) - 1;
                    int var46 = OMathHelper.b(var8 + var31) + 1;
                    int var47 = OMathHelper.b(var10 - var29) - var4 * 16 - 1;
                    int var48 = OMathHelper.b(var10 + var29) - var4 * 16 + 1;
                    if (var43 < 0) {
                        var43 = 0;
                    }

                    if (var44 > 16) {
                        var44 = 16;
                    }

                    if (var45 < 1) {
                        var45 = 1;
                    }

                    if (var46 > 120) {
                        var46 = 120;
                    }

                    if (var47 < 0) {
                        var47 = 0;
                    }

                    if (var48 > 16) {
                        var48 = 16;
                    }

                    boolean var49 = false;

                    int var50;
                    int var53;
                    for (var50 = var43; !var49 && var50 < var44; ++var50) {
                        for (int var51 = var47; !var49 && var51 < var48; ++var51) {
                            for (int var52 = var46 + 1; !var49 && var52 >= var45 - 1; --var52) {
                                var53 = (var50 * 16 + var51) * 128 + var52;
                                if (var52 >= 0 && var52 < 128) {
                                    if (var5[var53] == OBlock.A.bO || var5[var53] == OBlock.B.bO) {
                                        var49 = true;
                                    }

                                    if (var52 != var45 - 1 && var50 != var43 && var50 != var44 - 1 && var51 != var47 && var51 != var48 - 1) {
                                        var52 = var45;
                                    }
                                }
                            }
                        }
                    }

                    if (!var49) {
                        for (var50 = var43; var50 < var44; ++var50) {
                            double var54 = ((var50 + var3 * 16) + 0.5D - var6) / var29;

                            for (var53 = var47; var53 < var48; ++var53) {
                                double var56 = ((var53 + var4 * 16) + 0.5D - var10) / var29;
                                int var58 = (var50 * 16 + var53) * 128 + var46;
                                boolean var59 = false;
                                if (var54 * var54 + var56 * var56 < 1.0D) {
                                    for (int var60 = var46 - 1; var60 >= var45; --var60) {
                                        double var61 = (var60 + 0.5D - var8) / var31;
                                        if ((var54 * var54 + var56 * var56) * this.a[var60] + var61 * var61 / 6.0D < 1.0D) {
                                            byte var63 = var5[var58];
                                            if (var63 == OBlock.u.bO) {
                                                var59 = true;
                                            }

                                            if (var63 == OBlock.t.bO || var63 == OBlock.v.bO || var63 == OBlock.u.bO) {
                                                if (var60 < 10) {
                                                    var5[var58] = (byte) OBlock.C.bO;
                                                } else {
                                                    var5[var58] = 0;
                                                    if (var59 && var5[var58 - 1] == OBlock.v.bO) {
                                                        var5[var58 - 1] = this.d.a(var50 + var3 * 16, var53 + var4 * 16).A;
                                                    }
                                                }
                                            }
                                        }

                                        --var58;
                                    }
                                }
                            }
                        }

                        if (var64) {
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    protected void a(OWorld var1, int var2, int var3, int var4, int var5, byte[] var6) {
        if (this.c.nextInt(50) == 0) {
            double var7 = (var2 * 16 + this.c.nextInt(16));
            double var9 = (this.c.nextInt(this.c.nextInt(40) + 8) + 20);
            double var11 = (var3 * 16 + this.c.nextInt(16));
            byte var13 = 1;

            for (int var14 = 0; var14 < var13; ++var14) {
                float var15 = this.c.nextFloat() * 3.1415927F * 2.0F;
                float var16 = (this.c.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var17 = (this.c.nextFloat() * 2.0F + this.c.nextFloat()) * 2.0F;
                this.a(this.c.nextLong(), var4, var5, var6, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D);
            }

        }
    }
}
