package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMapGenBase;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OMapGenCaves extends OMapGenBase {

   public OMapGenCaves() {
      super();
   }

   protected void a(long var1, int var3, int var4, byte[] var5, double var6, double var8, double var10) {
      this.a(var1, var3, var4, var5, var6, var8, var10, 1.0F + this.c.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
   }

   protected void a(long var1, int var3, int var4, byte[] var5, double var6, double var8, double var10, float var12, float var13, float var14, int var15, int var16, double var17) {
      double var19 = (double)(var3 * 16 + 8);
      double var21 = (double)(var4 * 16 + 8);
      float var23 = 0.0F;
      float var24 = 0.0F;
      Random var25 = new Random(var1);
      if(var16 <= 0) {
         int var26 = this.b * 16 - 16;
         var16 = var26 - var25.nextInt(var26 / 4);
      }

      boolean var64 = false;
      if(var15 == -1) {
         var15 = var16 / 2;
         var64 = true;
      }

      int var27 = var25.nextInt(var16 / 2) + var16 / 4;

      for(boolean var28 = var25.nextInt(6) == 0; var15 < var16; ++var15) {
         double var29 = 1.5D + (double)(OMathHelper.a((float)var15 * 3.1415927F / (float)var16) * var12 * 1.0F);
         double var31 = var29 * var17;
         float var33 = OMathHelper.b(var14);
         float var34 = OMathHelper.a(var14);
         var6 += (double)(OMathHelper.b(var13) * var33);
         var8 += (double)var34;
         var10 += (double)(OMathHelper.a(var13) * var33);
         if(var28) {
            var14 *= 0.92F;
         } else {
            var14 *= 0.7F;
         }

         var14 += var24 * 0.1F;
         var13 += var23 * 0.1F;
         var24 *= 0.9F;
         var23 *= 0.75F;
         var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
         var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
         if(!var64 && var15 == var27 && var12 > 1.0F && var16 > 0) {
            this.a(var25.nextLong(), var3, var4, var5, var6, var8, var10, var25.nextFloat() * 0.5F + 0.5F, var13 - 1.5707964F, var14 / 3.0F, var15, var16, 1.0D);
            this.a(var25.nextLong(), var3, var4, var5, var6, var8, var10, var25.nextFloat() * 0.5F + 0.5F, var13 + 1.5707964F, var14 / 3.0F, var15, var16, 1.0D);
            return;
         }

         if(var64 || var25.nextInt(4) != 0) {
            double var35 = var6 - var19;
            double var37 = var10 - var21;
            double var39 = (double)(var16 - var15);
            double var41 = (double)(var12 + 2.0F + 16.0F);
            if(var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
               return;
            }

            if(var6 >= var19 - 16.0D - var29 * 2.0D && var10 >= var21 - 16.0D - var29 * 2.0D && var6 <= var19 + 16.0D + var29 * 2.0D && var10 <= var21 + 16.0D + var29 * 2.0D) {
               int var43 = OMathHelper.b(var6 - var29) - var3 * 16 - 1;
               int var44 = OMathHelper.b(var6 + var29) - var3 * 16 + 1;
               int var45 = OMathHelper.b(var8 - var31) - 1;
               int var46 = OMathHelper.b(var8 + var31) + 1;
               int var47 = OMathHelper.b(var10 - var29) - var4 * 16 - 1;
               int var48 = OMathHelper.b(var10 + var29) - var4 * 16 + 1;
               if(var43 < 0) {
                  var43 = 0;
               }

               if(var44 > 16) {
                  var44 = 16;
               }

               if(var45 < 1) {
                  var45 = 1;
               }

               if(var46 > 120) {
                  var46 = 120;
               }

               if(var47 < 0) {
                  var47 = 0;
               }

               if(var48 > 16) {
                  var48 = 16;
               }

               boolean var49 = false;

               int var50;
               int var53;
               for(var50 = var43; !var49 && var50 < var44; ++var50) {
                  for(int var51 = var47; !var49 && var51 < var48; ++var51) {
                     for(int var52 = var46 + 1; !var49 && var52 >= var45 - 1; --var52) {
                        var53 = (var50 * 16 + var51) * 128 + var52;
                        if(var52 >= 0 && var52 < 128) {
                           if(var5[var53] == OBlock.A.bO || var5[var53] == OBlock.B.bO) {
                              var49 = true;
                           }

                           if(var52 != var45 - 1 && var50 != var43 && var50 != var44 - 1 && var51 != var47 && var51 != var48 - 1) {
                              var52 = var45;
                           }
                        }
                     }
                  }
               }

               if(!var49) {
                  for(var50 = var43; var50 < var44; ++var50) {
                     double var54 = ((double)(var50 + var3 * 16) + 0.5D - var6) / var29;

                     for(var53 = var47; var53 < var48; ++var53) {
                        double var56 = ((double)(var53 + var4 * 16) + 0.5D - var10) / var29;
                        int var58 = (var50 * 16 + var53) * 128 + var46;
                        boolean var59 = false;
                        if(var54 * var54 + var56 * var56 < 1.0D) {
                           for(int var60 = var46 - 1; var60 >= var45; --var60) {
                              double var61 = ((double)var60 + 0.5D - var8) / var31;
                              if(var61 > -0.7D && var54 * var54 + var61 * var61 + var56 * var56 < 1.0D) {
                                 byte var63 = var5[var58];
                                 if(var63 == OBlock.u.bO) {
                                    var59 = true;
                                 }

                                 if(var63 == OBlock.t.bO || var63 == OBlock.v.bO || var63 == OBlock.u.bO) {
                                    if(var60 < 10) {
                                       var5[var58] = (byte)OBlock.C.bO;
                                    } else {
                                       var5[var58] = 0;
                                       if(var59 && var5[var58 - 1] == OBlock.v.bO) {
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

                  if(var64) {
                     break;
                  }
               }
            }
         }
      }

   }

   protected void a(OWorld var1, int var2, int var3, int var4, int var5, byte[] var6) {
      int var7 = this.c.nextInt(this.c.nextInt(this.c.nextInt(40) + 1) + 1);
      if(this.c.nextInt(15) != 0) {
         var7 = 0;
      }

      for(int var8 = 0; var8 < var7; ++var8) {
         double var9 = (double)(var2 * 16 + this.c.nextInt(16));
         double var11 = (double)this.c.nextInt(this.c.nextInt(120) + 8);
         double var13 = (double)(var3 * 16 + this.c.nextInt(16));
         int var15 = 1;
         if(this.c.nextInt(4) == 0) {
            this.a(this.c.nextLong(), var4, var5, var6, var9, var11, var13);
            var15 += this.c.nextInt(4);
         }

         for(int var16 = 0; var16 < var15; ++var16) {
            float var17 = this.c.nextFloat() * 3.1415927F * 2.0F;
            float var18 = (this.c.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float var19 = this.c.nextFloat() * 2.0F + this.c.nextFloat();
            if(this.c.nextInt(10) == 0) {
               var19 *= this.c.nextFloat() * this.c.nextFloat() * 3.0F + 1.0F;
            }

            this.a(this.c.nextLong(), var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
         }
      }

   }
}
