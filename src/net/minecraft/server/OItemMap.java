package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OChunk;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemMapBase;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMapColor;
import net.minecraft.server.OMapData;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket131MapData;
import net.minecraft.server.OWorld;

public class OItemMap extends OItemMapBase {

    protected OItemMap(int var1) {
        super(var1);
        this.e(1);
    }

    public OMapData a(OItemStack var1, OWorld var2) {
      //"map_" + var1.h();
      OMapData var4 = (OMapData)var2.a(OMapData.class, "map_" + var1.h());
      if(var4 == null) {
         var1.b(var2.b("map"));
         String var3 = "map_" + var1.h();
         var4 = new OMapData(var3);
         var4.b = var2.s().c();
         var4.c = var2.s().e();
         var4.e = 3;
         var4.d = (byte)var2.t.g;
         var4.a();
         var2.a(var3, var4);
      }

      return var4;
   }

    public void a(OWorld var1, OEntity var2, OMapData var3) {
        if (var1.t.g == var3.d) {
            short var4 = 128;
            short var5 = 128;
            int var6 = 1 << var3.e;
            int var7 = var3.b;
            int var8 = var3.c;
            int var9 = OMathHelper.b(var2.bm - var7) / var6 + var4 / 2;
            int var10 = OMathHelper.b(var2.bo - var8) / var6 + var5 / 2;
            int var11 = 128 / var6;
            if (var1.t.e) {
                var11 /= 2;
            }

            ++var3.g;

            for (int var12 = var9 - var11 + 1; var12 < var9 + var11; ++var12) {
                if ((var12 & 15) == (var3.g & 15)) {
                    int var13 = 255;
                    int var14 = 0;
                    double var15 = 0.0D;

                    for (int var17 = var10 - var11 - 1; var17 < var10 + var11; ++var17) {
                        if (var12 >= 0 && var17 >= -1 && var12 < var4 && var17 < var5) {
                            int var18 = var12 - var9;
                            int var19 = var17 - var10;
                            boolean var20 = var18 * var18 + var19 * var19 > (var11 - 2) * (var11 - 2);
                            int var21 = (var7 / var6 + var12 - var4 / 2) * var6;
                            int var22 = (var8 / var6 + var17 - var5 / 2) * var6;
                            byte var23 = 0;
                            byte var24 = 0;
                            byte var25 = 0;
                            int[] var26 = new int[256];
                            OChunk var27 = var1.c(var21, var22);
                            int var28 = var21 & 15;
                            int var29 = var22 & 15;
                            int var30 = 0;
                            double var31 = 0.0D;
                            int var34;
                            int var35;
                            int var33;
                            int var38;
                            if (var1.t.e) {
                                var33 = var21 + var22 * 231871;
                                var33 = var33 * var33 * 31287121 + var33 * 11;
                                if ((var33 >> 20 & 1) == 0) {
                                    var26[OBlock.v.bO] += 10;
                                } else {
                                    var26[OBlock.t.bO] += 10;
                                }

                                var31 = 100.0D;
                            } else {
                                for (var33 = 0; var33 < var6; ++var33) {
                                    for (var34 = 0; var34 < var6; ++var34) {
                                        var35 = var27.b(var33 + var28, var34 + var29) + 1;
                                        int var36 = 0;
                                        if (var35 > 1) {
                                            boolean var37 = false;

                                            do {
                                                var37 = true;
                                                var36 = var27.a(var33 + var28, var35 - 1, var34 + var29);
                                                if (var36 == 0) {
                                                    var37 = false;
                                                } else if (var35 > 0 && var36 > 0 && OBlock.m[var36].cd.F == OMapColor.b) {
                                                    var37 = false;
                                                }

                                                if (!var37) {
                                                    --var35;
                                                    var36 = var27.a(var33 + var28, var35 - 1, var34 + var29);
                                                }
                                            } while (var35 > 0 && !var37);

                                            if (var35 > 0 && var36 != 0 && OBlock.m[var36].cd.d()) {
                                                var38 = var35 - 1;
                                                boolean var39 = false;

                                                int var43;
                                                do {
                                                    var43 = var27.a(var33 + var28, var38--, var34 + var29);
                                                    ++var30;
                                                } while (var38 > 0 && var43 != 0 && OBlock.m[var43].cd.d());
                                            }
                                        }

                                        var31 += (double) var35 / (double) (var6 * var6);
                                        ++var26[var36];
                                    }
                                }
                            }

                            var30 /= var6 * var6;
                            int var10000 = var23 / (var6 * var6);
                            var10000 = var24 / (var6 * var6);
                            var10000 = var25 / (var6 * var6);
                            var33 = 0;
                            var34 = 0;

                            for (var35 = 0; var35 < 256; ++var35) {
                                if (var26[var35] > var33) {
                                    var34 = var35;
                                    var33 = var26[var35];
                                }
                            }

                            double var40 = (var31 - var15) * 4.0D / (var6 + 4) + ((var12 + var17 & 1) - 0.5D) * 0.4D;
                            byte var44 = 1;
                            if (var40 > 0.6D) {
                                var44 = 2;
                            }

                            if (var40 < -0.6D) {
                                var44 = 0;
                            }

                            var38 = 0;
                            if (var34 > 0) {
                                OMapColor var46 = OBlock.m[var34].cd.F;
                                if (var46 == OMapColor.n) {
                                    var40 = var30 * 0.1D + (var12 + var17 & 1) * 0.2D;
                                    var44 = 1;
                                    if (var40 < 0.5D) {
                                        var44 = 2;
                                    }

                                    if (var40 > 0.9D) {
                                        var44 = 0;
                                    }
                                }

                                var38 = var46.q;
                            }

                            var15 = var31;
                            if (var17 >= 0 && var18 * var18 + var19 * var19 < var11 * var11 && (!var20 || (var12 + var17 & 1) != 0)) {
                                byte var45 = var3.f[var12 + var17 * var4];
                                byte var42 = (byte) (var38 * 4 + var44);
                                if (var45 != var42) {
                                    if (var13 > var17) {
                                        var13 = var17;
                                    }

                                    if (var14 < var17) {
                                        var14 = var17;
                                    }

                                    var3.f[var12 + var17 * var4] = var42;
                                }
                            }
                        }
                    }

                    if (var13 <= var14) {
                        var3.a(var12, var13, var14);
                    }
                }
            }

        }
    }

    @Override
    public void a(OItemStack var1, OWorld var2, OEntity var3, int var4, boolean var5) {
        if (!var2.F) {
            OMapData var6 = this.a(var1, var2);
            if (var3 instanceof OEntityPlayer) {
                OEntityPlayer var7 = (OEntityPlayer) var3;
                var6.a(var7, var1);
            }

            if (var5) {
                this.a(var2, var3, var6);
            }

        }
    }

    @Override
    public void d(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        var1.b(var2.b("map"));
        String var4 = "map_" + var1.h();
        OMapData var5 = new OMapData(var4);
        var2.a(var4, var5);
        var5.b = OMathHelper.b(var3.bm);
        var5.c = OMathHelper.b(var3.bo);
        var5.e = 3;
        var5.d = (byte) var2.t.g;
        var5.a();
    }

    @Override
    public OPacket c(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        byte[] var4 = this.a(var1, var2).a(var1, var2, var3);
        return var4 == null ? null : new OPacket131MapData((short) OItem.bc.bP, (short) var1.h(), var4);
    }
}
