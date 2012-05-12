package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OBlockCrops extends OBlockFlower {

   protected OBlockCrops(int var1, int var2) {
      super(var1, var2);
      this.bN = var2;
      this.a(true);
      float var3 = 0.5F;
      this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
   }

   protected boolean d(int var1) {
      return var1 == OBlock.aA.bO;
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      super.a(var1, var2, var3, var4, var5);
      if(var1.n(var2, var3 + 1, var4) >= 9) {
         int var6 = var1.c(var2, var3, var4);
         if(var6 < 7) {
            float var7 = this.i(var1, var2, var3, var4);
            if(var5.nextInt((int)(25.0F / var7) + 1) == 0) {
               ++var6;
               var1.c(var2, var3, var4, var6);
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

      for(int var17 = var2 - 1; var17 <= var2 + 1; ++var17) {
         for(int var18 = var4 - 1; var18 <= var4 + 1; ++var18) {
            int var19 = var1.a(var17, var3 - 1, var18);
            float var20 = 0.0F;
            if(var19 == OBlock.aA.bO) {
               var20 = 1.0F;
               if(var1.c(var17, var3 - 1, var18) > 0) {
                  var20 = 3.0F;
               }
            }

            if(var17 != var2 || var18 != var4) {
               var20 /= 4.0F;
            }

            var5 += var20;
         }
      }

      if(var16 || var14 && var15) {
         var5 /= 2.0F;
      }

      return var5;
   }

   public int a(int var1, int var2) {
      if(var2 < 0) {
         var2 = 7;
      }

      return this.bN + var2;
   }

   public int c() {
      return 6;
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.a(var1, var2, var3, var4, var5, var6, 0);
      if(!var1.F) {
         int var8 = 3 + var7;

         for(int var9 = 0; var9 < var8; ++var9) {
            if(var1.r.nextInt(15) <= var5) {
               float var10 = 0.7F;
               float var11 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
               float var12 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
               float var13 = var1.r.nextFloat() * var10 + (1.0F - var10) * 0.5F;
               OEntityItem var14 = new OEntityItem(var1, (double)((float)var2 + var11), (double)((float)var3 + var12), (double)((float)var4 + var13), new OItemStack(OItem.R));
               var14.c = 10;
               var1.b((OEntity)var14);
            }
         }

      }
   }

   public int a(int var1, Random var2, int var3) {
      return var1 == 7?OItem.S.bP:-1;
   }

   public int a(Random var1) {
      return 1;
   }
}
