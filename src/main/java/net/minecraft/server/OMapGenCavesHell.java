package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMapGenBase;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;


public class OMapGenCavesHell extends OMapGenBase {

    public OMapGenCavesHell() {
        super();
    }

    protected void a(int var1, int var2, byte[] var3, double var4, double var6, double var8) {
        this.a(var1, var2, var3, var4, var6, var8, 1.0F + this.c.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void a(int var1, int var2, byte[] var3, double var4, double var6, double var8, float var10, float var11, float var12, int var13, int var14, double var15) {
        double var17 = (var1 * 16 + 8);
        double var19 = (var2 * 16 + 8);
        float var21 = 0.0F;
        float var22 = 0.0F;
        Random var23 = new Random(this.c.nextLong());

        if (var14 <= 0) {
            int var24 = this.b * 16 - 16;

            var14 = var24 - var23.nextInt(var24 / 4);
        }

        boolean var61 = false;

        if (var13 == -1) {
            var13 = var14 / 2;
            var61 = true;
        }

        int var25 = var23.nextInt(var14 / 2) + var14 / 4;

        for (boolean var26 = var23.nextInt(6) == 0; var13 < var14; ++var13) {
            double var27 = 1.5D + (OMathHelper.a(var13 * 3.1415927F / var14) * var10 * 1.0F);
            double var29 = var27 * var15;
            float var31 = OMathHelper.b(var12);
            float var32 = OMathHelper.a(var12);

            var4 += (OMathHelper.b(var11) * var31);
            var6 += var32;
            var8 += (OMathHelper.a(var11) * var31);
            if (var26) {
                var12 *= 0.92F;
            } else {
                var12 *= 0.7F;
            }

            var12 += var22 * 0.1F;
            var11 += var21 * 0.1F;
            var22 *= 0.9F;
            var21 *= 0.75F;
            var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
            var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;
            if (!var61 && var13 == var25 && var10 > 1.0F) {
                this.a(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 - 1.5707964F, var12 / 3.0F, var13, var14, 1.0D);
                this.a(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 + 1.5707964F, var12 / 3.0F, var13, var14, 1.0D);
                return;
            }

            if (var61 || var23.nextInt(4) != 0) {
                double var33 = var4 - var17;
                double var35 = var8 - var19;
                double var37 = (var14 - var13);
                double var39 = (var10 + 2.0F + 16.0F);

                if (var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39) {
                    return;
                }

                if (var4 >= var17 - 16.0D - var27 * 2.0D && var8 >= var19 - 16.0D - var27 * 2.0D && var4 <= var17 + 16.0D + var27 * 2.0D && var8 <= var19 + 16.0D + var27 * 2.0D) {
                    int var41 = OMathHelper.b(var4 - var27) - var1 * 16 - 1;
                    int var42 = OMathHelper.b(var4 + var27) - var1 * 16 + 1;
                    int var43 = OMathHelper.b(var6 - var29) - 1;
                    int var44 = OMathHelper.b(var6 + var29) + 1;
                    int var45 = OMathHelper.b(var8 - var27) - var2 * 16 - 1;
                    int var46 = OMathHelper.b(var8 + var27) - var2 * 16 + 1;

                    if (var41 < 0) {
                        var41 = 0;
                    }

                    if (var42 > 16) {
                        var42 = 16;
                    }

                    if (var43 < 1) {
                        var43 = 1;
                    }

                    if (var44 > 120) {
                        var44 = 120;
                    }

                    if (var45 < 0) {
                        var45 = 0;
                    }

                    if (var46 > 16) {
                        var46 = 16;
                    }

                    boolean var47 = false;

                    int var51;
                    int var48;

                    for (var48 = var41; !var47 && var48 < var42; ++var48) {
                        for (int var49 = var45; !var47 && var49 < var46; ++var49) {
                            for (int var50 = var44 + 1; !var47 && var50 >= var43 - 1; --var50) {
                                var51 = (var48 * 16 + var49) * 128 + var50;
                                if (var50 >= 0 && var50 < 128) {
                                    if (var3[var51] == OBlock.C.bO || var3[var51] == OBlock.D.bO) {
                                        var47 = true;
                                    }

                                    if (var50 != var43 - 1 && var48 != var41 && var48 != var42 - 1 && var49 != var45 && var49 != var46 - 1) {
                                        var50 = var43;
                                    }
                                }
                            }
                        }
                    }

                    if (!var47) {
                        for (var48 = var41; var48 < var42; ++var48) {
                            double var52 = ((var48 + var1 * 16) + 0.5D - var4) / var27;

                            for (var51 = var45; var51 < var46; ++var51) {
                                double var54 = ((var51 + var2 * 16) + 0.5D - var8) / var27;
                                int var56 = (var48 * 16 + var51) * 128 + var44;

                                for (int var57 = var44 - 1; var57 >= var43; --var57) {
                                    double var58 = (var57 + 0.5D - var6) / var29;

                                    if (var58 > -0.7D && var52 * var52 + var58 * var58 + var54 * var54 < 1.0D) {
                                        byte var60 = var3[var56];

                                        if (var60 == OBlock.bb.bO || var60 == OBlock.v.bO || var60 == OBlock.u.bO) {
                                            var3[var56] = 0;
                                        }
                                    }

                                    --var56;
                                }
                            }
                        }

                        if (var61) {
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    protected void a(OWorld var1, int var2, int var3, int var4, int var5, byte[] var6) {
        int var7 = this.c.nextInt(this.c.nextInt(this.c.nextInt(10) + 1) + 1);

        if (this.c.nextInt(5) != 0) {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; ++var8) {
            double var9 = (var2 * 16 + this.c.nextInt(16));
            double var11 = this.c.nextInt(128);
            double var13 = (var3 * 16 + this.c.nextInt(16));
            int var15 = 1;

            if (this.c.nextInt(4) == 0) {
                this.a(var4, var5, var6, var9, var11, var13);
                var15 += this.c.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; ++var16) {
                float var17 = this.c.nextFloat() * 3.1415927F * 2.0F;
                float var18 = (this.c.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.c.nextFloat() * 2.0F + this.c.nextFloat();

                this.a(var4, var5, var6, var9, var11, var13, var19 * 2.0F, var17, var18, 0, 0, 0.5D);
            }
        }

    }
}
