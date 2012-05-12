package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenTaiga2 extends OWorldGenerator {

   public OWorldGenTaiga2(boolean var1) {
      super(var1);
   }

   public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(4) + 6;
      int var7 = 1 + var2.nextInt(2);
      int var8 = var6 - var7;
      int var9 = 2 + var2.nextInt(2);
      boolean var10 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         int var11;
         int var13;
         int var15;
         int var21;
         for(var11 = var4; var11 <= var4 + 1 + var6 && var10; ++var11) {
            boolean var12 = true;
            if(var11 - var4 < var7) {
               var21 = 0;
            } else {
               var21 = var9;
            }

            for(var13 = var3 - var21; var13 <= var3 + var21 && var10; ++var13) {
               for(int var14 = var5 - var21; var14 <= var5 + var21 && var10; ++var14) {
                  if(var11 >= 0 && var11 < 256) {
                     var15 = var1.a(var13, var11, var14);
                     if(var15 != 0 && var15 != OBlock.K.bO) {
                        var10 = false;
                     }
                  } else {
                     var10 = false;
                  }
               }
            }
         }

         if(!var10) {
            return false;
         } else {
            var11 = var1.a(var3, var4 - 1, var5);
            if((var11 == OBlock.u.bO || var11 == OBlock.v.bO) && var4 < 256 - var6 - 1) {
               this.a(var1, var3, var4 - 1, var5, OBlock.v.bO);
               var21 = var2.nextInt(2);
               var13 = 1;
               byte var22 = 0;

               int var17;
               int var16;
               for(var15 = 0; var15 <= var8; ++var15) {
                  var16 = var4 + var6 - var15;

                  for(var17 = var3 - var21; var17 <= var3 + var21; ++var17) {
                     int var18 = var17 - var3;

                     for(int var19 = var5 - var21; var19 <= var5 + var21; ++var19) {
                        int var20 = var19 - var5;
                        if((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !OBlock.n[var1.a(var17, var16, var19)]) {
                           this.a(var1, var17, var16, var19, OBlock.K.bO, 1);
                        }
                     }
                  }

                  if(var21 >= var13) {
                     var21 = var22;
                     var22 = 1;
                     ++var13;
                     if(var13 > var9) {
                        var13 = var9;
                     }
                  } else {
                     ++var21;
                  }
               }

               var15 = var2.nextInt(3);

               for(var16 = 0; var16 < var6 - var15; ++var16) {
                  var17 = var1.a(var3, var4 + var16, var5);
                  if(var17 == 0 || var17 == OBlock.K.bO) {
                     this.a(var1, var3, var4 + var16, var5, OBlock.J.bO, 1);
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
