package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.ONoiseGenerator;

public class ONoiseGeneratorPerlin extends ONoiseGenerator {

   private int[] d;
   public double a;
   public double b;
   public double c;


   public ONoiseGeneratorPerlin() {
      this(new Random());
   }

   public ONoiseGeneratorPerlin(Random var1) {
      super();
      this.d = new int[512];
      this.a = var1.nextDouble() * 256.0D;
      this.b = var1.nextDouble() * 256.0D;
      this.c = var1.nextDouble() * 256.0D;

      int var2;
      for(var2 = 0; var2 < 256; this.d[var2] = var2++) {
         ;
      }

      for(var2 = 0; var2 < 256; ++var2) {
         int var3 = var1.nextInt(256 - var2) + var2;
         int var4 = this.d[var2];
         this.d[var2] = this.d[var3];
         this.d[var3] = var4;
         this.d[var2 + 256] = this.d[var2];
      }

   }

   public final double a(double var1, double var3, double var5) {
      return var3 + var1 * (var5 - var3);
   }

   public final double a(int var1, double var2, double var4) {
      int var6 = var1 & 15;
      double var7 = (double)(1 - ((var6 & 8) >> 3)) * var2;
      double var9 = var6 < 4?0.0D:(var6 != 12 && var6 != 14?var4:var2);
      return ((var6 & 1) == 0?var7:-var7) + ((var6 & 2) == 0?var9:-var9);
   }

   public final double a(int var1, double var2, double var4, double var6) {
      int var8 = var1 & 15;
      double var9 = var8 < 8?var2:var4;
      double var11 = var8 < 4?var4:(var8 != 12 && var8 != 14?var6:var2);
      return ((var8 & 1) == 0?var9:-var9) + ((var8 & 2) == 0?var11:-var11);
   }

   public void a(double[] var1, double var2, double var4, double var6, int var8, int var9, int var10, double var11, double var13, double var15, double var17) {
      int var19;
      int var22;
      double var31;
      double var35;
      double var38;
      int var37;
      double var42;
      int var40;
      int var41;
      int var10001;
      int var81;
      if(var9 == 1) {
         boolean var78 = false;
         boolean var20 = false;
         boolean var21 = false;
         boolean var79 = false;
         double var23 = 0.0D;
         double var25 = 0.0D;
         var81 = 0;
         double var28 = 1.0D / var17;

         for(int var30 = 0; var30 < var8; ++var30) {
            var31 = var2 + (double)var30 * var11 + this.a;
            int var33 = (int)var31;
            if(var31 < (double)var33) {
               --var33;
            }

            int var34 = var33 & 255;
            var31 -= (double)var33;
            var35 = var31 * var31 * var31 * (var31 * (var31 * 6.0D - 15.0D) + 10.0D);

            for(var37 = 0; var37 < var10; ++var37) {
               var38 = var6 + (double)var37 * var15 + this.c;
               var40 = (int)var38;
               if(var38 < (double)var40) {
                  --var40;
               }

               var41 = var40 & 255;
               var38 -= (double)var40;
               var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
               var19 = this.d[var34] + 0;
               int var77 = this.d[var19] + var41;
               int var80 = this.d[var34 + 1] + 0;
               var22 = this.d[var80] + var41;
               var23 = this.a(var35, this.a(this.d[var77], var31, var38), this.a(this.d[var22], var31 - 1.0D, 0.0D, var38));
               var25 = this.a(var35, this.a(this.d[var77 + 1], var31, 0.0D, var38 - 1.0D), this.a(this.d[var22 + 1], var31 - 1.0D, 0.0D, var38 - 1.0D));
               double var44 = this.a(var42, var23, var25);
               var10001 = var81++;
               var1[var10001] += var44 * var28;
            }
         }

      } else {
         var19 = 0;
         double var46 = 1.0D / var17;
         var22 = -1;
         boolean var48 = false;
         boolean var49 = false;
         boolean var50 = false;
         boolean var51 = false;
         boolean var27 = false;
         boolean var52 = false;
         double var53 = 0.0D;
         var31 = 0.0D;
         double var55 = 0.0D;
         var35 = 0.0D;

         for(var37 = 0; var37 < var8; ++var37) {
            var38 = var2 + (double)var37 * var11 + this.a;
            var40 = (int)var38;
            if(var38 < (double)var40) {
               --var40;
            }

            var41 = var40 & 255;
            var38 -= (double)var40;
            var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);

            for(int var57 = 0; var57 < var10; ++var57) {
               double var58 = var6 + (double)var57 * var15 + this.c;
               int var60 = (int)var58;
               if(var58 < (double)var60) {
                  --var60;
               }

               int var61 = var60 & 255;
               var58 -= (double)var60;
               double var62 = var58 * var58 * var58 * (var58 * (var58 * 6.0D - 15.0D) + 10.0D);

               for(int var64 = 0; var64 < var9; ++var64) {
                  double var65 = var4 + (double)var64 * var13 + this.b;
                  int var67 = (int)var65;
                  if(var65 < (double)var67) {
                     --var67;
                  }

                  int var68 = var67 & 255;
                  var65 -= (double)var67;
                  double var69 = var65 * var65 * var65 * (var65 * (var65 * 6.0D - 15.0D) + 10.0D);
                  if(var64 == 0 || var68 != var22) {
                     var22 = var68;
                     int var85 = this.d[var41] + var68;
                     int var86 = this.d[var85] + var61;
                     int var82 = this.d[var85 + 1] + var61;
                     int var83 = this.d[var41 + 1] + var68;
                     var81 = this.d[var83] + var61;
                     int var84 = this.d[var83 + 1] + var61;
                     var53 = this.a(var42, this.a(this.d[var86], var38, var65, var58), this.a(this.d[var81], var38 - 1.0D, var65, var58));
                     var31 = this.a(var42, this.a(this.d[var82], var38, var65 - 1.0D, var58), this.a(this.d[var84], var38 - 1.0D, var65 - 1.0D, var58));
                     var55 = this.a(var42, this.a(this.d[var86 + 1], var38, var65, var58 - 1.0D), this.a(this.d[var81 + 1], var38 - 1.0D, var65, var58 - 1.0D));
                     var35 = this.a(var42, this.a(this.d[var82 + 1], var38, var65 - 1.0D, var58 - 1.0D), this.a(this.d[var84 + 1], var38 - 1.0D, var65 - 1.0D, var58 - 1.0D));
                  }

                  double var71 = this.a(var69, var53, var31);
                  double var73 = this.a(var69, var55, var35);
                  double var75 = this.a(var62, var71, var73);
                  var10001 = var19++;
                  var1[var10001] += var75 * var46;
               }
            }
         }

      }
   }
}
